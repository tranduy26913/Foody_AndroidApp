package hcmute.spkt.nguyenphucan19110321.uidesign.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.UserDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmailLogin,txtPasswordLogin;
    private Button btnLogin;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        SetControl();
        SetEvent();
        LoadDataFromSharePref();
    }

    private void LoadDataFromSharePref() {
        SharedPreferences pref = getSharedPreferences(GLOBAL.PREF_USER,MODE_PRIVATE);
        String username = pref.getString("email","");
        String password =pref.getString("password","");
        this.txtEmailLogin.setText(username);
        this.txtPasswordLogin.setText(password);
    }

    private void SetEvent(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLogin();
            }
        });
    }

    private void SetControl(){
        txtEmailLogin = findViewById(R.id.txtEmailLogin);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);
        btnLogin  =findViewById(R.id.btnLogin);
    }

    public void onClickGoToRegister(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    public void onClickBack(View view) {
        finish();
    }

    private void onClickLogin(){
        String email = txtEmailLogin.getText().toString().trim();
        String password = txtPasswordLogin.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            DocumentReference docRef = db.collection(GLOBAL.USER_COLLECTION).document(email);
                            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            try {
                                                GLOBAL.USER = document.toObject(User.class);
                                                SharedPreferences.Editor pref = getSharedPreferences(GLOBAL.PREF_USER,MODE_PRIVATE).edit();
                                                pref.putString("email",txtEmailLogin.getText().toString().trim());
                                                pref.putString("password",txtPasswordLogin.getText().toString().trim());
                                                pref.apply();
                                                GoToMainActivity();
                                            }catch (Exception ex){
                                                Log.d("Login",ex.getMessage());
                                            }
                                        }
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Sai Email hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



    }

    private void GoToMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}
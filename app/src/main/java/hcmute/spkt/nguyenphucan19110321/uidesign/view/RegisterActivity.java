package hcmute.spkt.nguyenphucan19110321.uidesign.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.regex.Pattern;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.UserDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail, txtPassword,txtName;
    private TextView txtGotoLogin;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String TAG="REGISTER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        SetControl();
        SetEvent();
    }

    private void SetEvent() {
    btnRegister.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onClickRegister();
        }
    });
    txtGotoLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GoToLogin();
        }
    });
    }

    private void onClickRegister() {
        try {

            String name =txtName.getText().toString().trim();
            String email = txtEmail.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();

            String EMAIL_PATTERN =
                    "^[a-zA-Z][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
            if(!Pattern.matches(EMAIL_PATTERN,email)){
                Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }
            if(name.length()<2){
                Toast.makeText(this, "Tên phải dài hơn 1 kí tự", Toast.LENGTH_SHORT).show();
                return;
            }
            if(password.length()<5){
                Toast.makeText(this, "Mật khẩu phải dài hơn 5 kí tự", Toast.LENGTH_SHORT).show();
                return;
            }
            User newUser = new User(new Date().getTime(),name,"",email.split("@")[0],password,
                    "","Nam","",email);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                db.collection(GLOBAL.USER_COLLECTION)
                                        .document(email)
                                        .set(newUser.toHashMap())
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                GoToLogin();
                                                Toast.makeText(RegisterActivity.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }catch (Exception ex){
            Toast.makeText(this, "Lỗi đăng ký", Toast.LENGTH_SHORT).show();
        }

    }

    private void GoToLogin() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    private void SetControl(){
        txtEmail = findViewById(R.id.txtEmailRegister);
        txtName =findViewById(R.id.txtNameRegister);
        txtPassword =findViewById(R.id.txtPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);
        txtGotoLogin = findViewById(R.id.txtGotoLogin);
    }


}
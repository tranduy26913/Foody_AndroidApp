package hcmute.spkt.nguyenphucan19110321.uidesign.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.User;

public class ProfileActivity extends AppCompatActivity {

    private Button btnEditProfile,btnFinish;
    private RadioButton rbNam,rbNu;
    private TextView tvNameProfile,txtAddressProfile,txtPhoneProfile;
    private  User user;
    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SetControl();
        SetEvent();
        LoadData();
    }

    private void SetEvent() {
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateProfile();
            }
        });
    }

    private void SetControl(){
        btnEditProfile=findViewById(R.id.btnEditProfile);
        txtAddressProfile =findViewById(R.id.txtAddressProfile);
        tvNameProfile = findViewById(R.id.tvNameProfile);
        rbNam=findViewById(R.id.rbNam);
        rbNu=findViewById(R.id.rbNu);
        txtPhoneProfile = findViewById(R.id.txtPhoneProfile);
        btnFinish = findViewById(R.id.btnFinishProfile);

    }
    private void LoadData(){
        user =  GLOBAL.USER;
        if(user!=null){
            tvNameProfile.setText(user.getName());
            txtAddressProfile.setText(user.getAddress());
            txtPhoneProfile.setText(user.getPhone());
            if(user.getGender().toLowerCase().equals("nam")){
                rbNam.setChecked(true);
            }
            else {
                rbNu.setChecked(true);
            }
        }
    }

    private void UpdateProfile(){
        try {
            String address = txtAddressProfile.getText().toString().trim();
            String phone = txtPhoneProfile.getText().toString().trim();
            String gender = "Nam";
            if(!rbNam.isChecked()){
                gender = "Nữ";
            }
            GLOBAL.USER.setAddress(address);
            GLOBAL.USER.setPhone(phone);
            GLOBAL.USER.setGender(gender);
            Database database = new Database(this,"Foody.sqlite",null,1);
            GLOBAL.USER.UpdateProfile(database);
            Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        }catch (Exception ex){
            Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
        }

    }
}
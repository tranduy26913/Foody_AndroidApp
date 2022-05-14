package hcmute.spkt.nguyenphucan19110321.uidesign.view.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import hcmute.spkt.nguyenphucan19110321.uidesign.view.ProfileActivity;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.LoginActivity;
import hcmute.spkt.nguyenphucan19110321.uidesign.R;

public class AccountFragment extends Fragment {

    private Button btnGoToLogin;
    private ConstraintLayout layoutBtnDangXuat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SetControl(view);
        SetEvent();
        LoadData();
    }

    public void SetControl(View view) {
        layoutBtnDangXuat =view.findViewById(R.id.layoutBtnDangXuat);
        btnGoToLogin = view.findViewById(R.id.btnGoToLogin);
    }

    private void SetEvent() {
        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GLOBAL.USER != null)
                    GoToProfile();
                else
                    GoToLogin();
            }
        });
        layoutBtnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.USER = null;
                Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                btnGoToLogin.setText("Đăng nhập");
                SharedPreferences.Editor pref = getContext().getSharedPreferences(GLOBAL.PREF_USER, Context.MODE_PRIVATE).edit();
                pref.remove(GLOBAL.PREF_USER);
                pref.apply();
            }
        });
    }

    private void GoToProfile() {
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        startActivity(intent);
    }

    private void GoToLogin() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void LoadData() {
        if (GLOBAL.USER != null) {
            btnGoToLogin.setText("Thông tin tài khoản");
        }
    }
}
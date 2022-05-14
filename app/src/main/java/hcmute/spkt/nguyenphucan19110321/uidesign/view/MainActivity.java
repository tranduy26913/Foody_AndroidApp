package hcmute.spkt.nguyenphucan19110321.uidesign.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import hcmute.spkt.nguyenphucan19110321.uidesign.view.Fragment.NotifyFragment;
import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.DatabaseFactory;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.UserDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.User;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.Fragment.AccountFragment;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.Fragment.HomeFragment;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.Fragment.InvoiceFragment;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.Fragment.SavedFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private Database database;
    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment;
            switch (item.getItemId()){
                case R.id.miHome: {
                   selectedFragment = new HomeFragment();
                    break;
                }
                case R.id.miAccount: {
                    selectedFragment = new AccountFragment();
                    break;
                }
                case R.id.miBookmark: {
                    selectedFragment = new SavedFragment();
                    break;
                }
                case R.id.miNotification: {
                    selectedFragment = new NotifyFragment();
                    break;
                }
                case R.id.miInvoice: {
                    selectedFragment = new InvoiceFragment();
                    break;
                }
                default:
                    selectedFragment = new HomeFragment();
                    break;

            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frameLayoutMain,selectedFragment).commit();
            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this,"Foody.sqlite",null,1);
        //DatabaseFactory.MakeData(database);
        //DatabaseFactory.MakeDataFood(database);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        setEventNavigation();
        LoadUserFromSharePreference();
    }

    protected  void setEventNavigation(){
        bottomNavigation.setOnItemSelectedListener(navListener);
        bottomNavigation.setSelectedItemId(R.id.miHome);
    }

    public void onCickSearchBar(View view) {
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    private void LoadUserFromSharePreference(){
        SharedPreferences pref = getSharedPreferences(GLOBAL.PREF_USER,MODE_PRIVATE);
        String email = pref.getString("email","");
        String password =pref.getString("password","");
        if(!(email.equals("") || password.equals(""))){
            User user =new User(email,password);
            UserDAO userDAO = new UserDAO(database);
            User userLogin = userDAO.CheckLogin(user);
            if(userLogin!=null){
                GLOBAL.USER = userLogin;
            }
        }
    }

}
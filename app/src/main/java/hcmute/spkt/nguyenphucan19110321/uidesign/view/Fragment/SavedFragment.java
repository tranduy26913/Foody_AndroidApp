package hcmute.spkt.nguyenphucan19110321.uidesign.view.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.google.gson.Gson;

import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.SavedAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.DatabaseFactory;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.mapping.SaveShopMapping;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.ShopDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.UserDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.SaveShop;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.ShopDetailActivity;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.ShopHomeAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.event.IClickItemShopHomeListener;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Shop;


public class SavedFragment extends Fragment {

    private RecyclerView recyclerViewSaved;
    private Database database;
    private Button btnGotoLogin;
    public SavedFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewSaved = view.findViewById(R.id.recyclerViewSaved);
        btnGotoLogin = view.findViewById(R.id.btnGoToLoginFromSaved);
        database= new Database(this.getContext(),"Foody.sqlite",null,1);
        if(GLOBAL.USER!=null){
            UserDAO userDAO =new UserDAO(database);
            List<SaveShopMapping> savedList = userDAO.getSavedShop(GLOBAL.USER.getId());
            SavedAdapter savedAdapter = new SavedAdapter(this.getContext(), savedList, new SavedAdapter.IClickItemSavedShopListener() {
                @Override
                public void onClickItemSavedShop(View view) {
                    int position = recyclerViewSaved.getChildAdapterPosition(view);
                    GoToShopDetail(savedList.get(position));
                }
            });
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
            recyclerViewSaved.setAdapter(savedAdapter);
            recyclerViewSaved.setVisibility(View.VISIBLE);
            recyclerViewSaved.setLayoutManager(layoutManager);
            btnGotoLogin.setVisibility(View.GONE);
        }
        else {
            recyclerViewSaved.setVisibility(View.GONE);
        }
    }

    private void GoToShopDetail(SaveShopMapping saved) {
        Intent intent = new Intent(this.getContext(), ShopDetailActivity.class);
        ShopDAO shopDAO = new ShopDAO(database);
        Shop shop = shopDAO.getListShopById(saved.getId());
        if(shop==null){
            return;
        }
        intent.putExtra("Shop",shop);
        startActivity(intent);
    }
}
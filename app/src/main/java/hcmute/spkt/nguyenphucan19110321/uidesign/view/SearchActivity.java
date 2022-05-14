package hcmute.spkt.nguyenphucan19110321.uidesign.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.SearchShopAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.event.IClickItemShopHomeListener;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.ShopDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Shop;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recycleViewSearchFood;
    private SearchShopAdapter searchShopAdapter;
    private SearchView svSearch;
    private List<Shop> shopList = new ArrayList<>();
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        database = new Database(this, GLOBAL.DATABASE_NAME, null, 1);
        setControl();
        LoadData();
    }

    private void setControl() {
        recycleViewSearchFood = findViewById(R.id.recycleViewSearchFood);
        svSearch = findViewById(R.id.svSearch);
    }

    private void LoadData() {
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                shopList.clear();
                if(!s.isEmpty()){
                    ShopDAO shopDAO = new ShopDAO(database);
                    shopList = shopDAO.getListShopByName(s);
                }
                searchShopAdapter = new SearchShopAdapter(getApplicationContext(), shopList, new SearchShopAdapter.IClickItemShopSearchListener() {
                    @Override
                    public void onClickItemShopSearch(View view) {
                        int position = recycleViewSearchFood.getChildAdapterPosition(view);
                        Shop shop = shopList.get(position);
                        GotoActivityShop(shop);
                    }
                });
                LinearLayoutManager linear = new LinearLayoutManager(getApplicationContext());
                recycleViewSearchFood.setAdapter(searchShopAdapter);
                recycleViewSearchFood.setLayoutManager(linear);
                return false;
            }
        });
    }

    private void GotoActivityShop(Shop shop) {
        Intent intent = new Intent(this, ShopDetailActivity.class);
        intent.putExtra("Shop",shop);
        startActivity(intent);
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
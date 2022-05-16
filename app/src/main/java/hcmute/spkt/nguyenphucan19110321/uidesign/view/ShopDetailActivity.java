package hcmute.spkt.nguyenphucan19110321.uidesign.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.FoodAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.event.IAddToCartListener;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.SaveShopDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Food;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Notify;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Order;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.OrderDetails;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.SaveShop;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Shop;

public class ShopDetailActivity extends AppCompatActivity {

    RecyclerView recycleViewFoodMost;
    private Shop shop;
    private TextView tvTitleShopDetail,tvNameShopDetail, tvAddressShopDetail,tvTypeShopDetail,tvRangePriceShopDetail;
    private ImageView imgShopDetail;
    private Button btnSaveShop,btnCart;
    Database database;
    private FirebaseFirestore db;
    private List<Food> foodList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        database = new Database(this,"Foody.sqlite",null,1);
        db = FirebaseFirestore.getInstance();
        SetControl();
        LoadDataFromIntent();
        LoadListFood();
        SetEvent();
    }

    private void LoadDataFromIntent() {
        if(getIntent().getSerializableExtra("Shop")!=null){
            shop = (Shop)getIntent().getSerializableExtra("Shop");
            tvTitleShopDetail.setText(shop.getName());
            tvNameShopDetail.setText(shop.getName());
            tvAddressShopDetail.setText(shop.getAddress());
            Picasso.get().load(shop.getImage()).into(imgShopDetail);
            if(GLOBAL.USER!=null){
                GLOBAL.ORDER = new Order(1,GLOBAL.USER.getId(),shop.getName(),new Date(),0,0);
                GLOBAL.ORDERDETAILS = new ArrayList<>();
                SaveShopDAO saveShopDAO = new SaveShopDAO(database);
                db.collection(GLOBAL.SAVED_COLLECTION)
                        .whereEqualTo("idShop",shop.getId())
                        .whereEqualTo("idUser",GLOBAL.USER.getId())
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            if(task.getResult().getDocuments().size()!=0){
                                Drawable img = getApplicationContext().getDrawable(R.drawable.ic_baseline_bookmark_24);
                                img.setBounds(0, 0, 60, 60);
                                btnSaveShop.setCompoundDrawables(img, null, null, null);
                                btnSaveShop.setTag(true);
                            }
                            else{
                                btnSaveShop.setTag(false);
                            }
                        }
                    }
                });
            }
        }
    }

    private void SetEvent(){
        btnSaveShop.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                if(GLOBAL.USER!=null){
                    if((boolean) btnSaveShop.getTag()){
                        db.collection(GLOBAL.SAVED_COLLECTION)//Xử lý thêm save vào firebase
                                .whereEqualTo("idShop",shop.getId())
                                .whereEqualTo("idUser",GLOBAL.USER.getId())
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful()){
                                            for (QueryDocumentSnapshot doc:task.getResult()){
                                                db.collection(GLOBAL.SAVED_COLLECTION).document(doc.getId()).delete();
                                            }
                                        }
                                    }
                                });
                        Drawable img = getApplication().getDrawable(R.drawable.ic_baseline_bookmark_border_24);
                        img.setBounds(0, 0, 60, 60);
                        btnSaveShop.setCompoundDrawables(img, null, null, null);
                        btnSaveShop.setTag(false);
                        Notify notify = new Notify(GLOBAL.USER.getId(),"Lưu shop","Bạn đã lưu shop "+shop.getName(),new Date());
                        notify.InsertToDatabase(database);
                    }
                    else {
                        SaveShop saveShop = new SaveShop(new Date().getTime(),shop.getId(),GLOBAL.USER.getId());
                        db.collection(GLOBAL.SAVED_COLLECTION)
                                .document(String.valueOf(saveShop.getId()))
                                .set(saveShop);
                        Drawable img = getApplication().getDrawable(R.drawable.ic_baseline_bookmark_24);
                        img.setBounds(0, 0, 60, 60);
                        btnSaveShop.setCompoundDrawables(img, null, null, null);
                        btnSaveShop.setTag(true);
                        Notify notify = new Notify(GLOBAL.USER.getId(),"Huỷ lưu shop","Bạn đã huỷ lưu shop "+shop.getName(),new Date());
                        notify.InsertToDatabase(database);
                    }
                }
                else {
                    Toast.makeText(ShopDetailActivity.this,"Bạn phải đăng nhập để thực hiện tính năng này",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GotoCart();
            }
        });
    }

    private void GotoCart(){
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    private void SetControl(){
        recycleViewFoodMost = findViewById(R.id.recycleViewFoodMost);
        imgShopDetail = findViewById(R.id.imgShopDetail);
        tvTitleShopDetail = findViewById(R.id.tvTitleShopDetail);
        tvNameShopDetail  =findViewById(R.id.tvNameShopDetail);
        tvAddressShopDetail = findViewById(R.id.tvAddressShopDetail);
        btnSaveShop = findViewById(R.id.btnSaveShop);
        btnCart=findViewById(R.id.btnCart);
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    private void LoadListFood(){
        foodList = new ArrayList<>();
        FoodAdapter foodAdapter =new FoodAdapter(this, foodList, new IAddToCartListener() {
            @Override
            public void AddToCart(Food food) {
                if(GLOBAL.USER != null){
                    if(GLOBAL.ORDER == null){
                        GLOBAL.ORDER = new Order(1,GLOBAL.USER.getId(),shop.getName(),new Date(),0,0);
                        GLOBAL.ORDERDETAILS.clear();
                        GLOBAL.ORDERDETAILS = new ArrayList<>();
                    }
                    int flag = 0;
                    for(OrderDetails orders : GLOBAL.ORDERDETAILS){
                        if(orders.getFoodID() == food.getId()){
                            flag = 1;
                            orders.setNumber(orders.getNumber()+1);
                            GLOBAL.ORDER.setTotalNumber(GLOBAL.ORDER.getTotalNumber()+1);
                            GLOBAL.ORDER.setPrice(GLOBAL.ORDER.getPrice()+orders.getPrice());
                        }
                    }
                    if(flag==0){
                        OrderDetails orderDetails = new OrderDetails(1,GLOBAL.ORDER.getId(),food.getId(),food.getName(),1,food.getPrice());
                        GLOBAL.ORDERDETAILS.add(orderDetails);
                        GLOBAL.ORDER.setTotalNumber(GLOBAL.ORDER.getTotalNumber()+1);
                        GLOBAL.ORDER.setPrice(GLOBAL.ORDER.getPrice()+orderDetails.getPrice());
                    }
                    btnCart.setVisibility(View.VISIBLE);
                    btnCart.setText("GIỎ HÀNG"+" ("+GLOBAL.ORDER.getTotalNumber()+")");
                } else{
                    Toast.makeText(getApplicationContext(),"Bạn phải đăng nhập để thực hiện tính năng này",Toast.LENGTH_SHORT).show();
                }
            }
        });
        LinearLayoutManager linear =new LinearLayoutManager(this);
        recycleViewFoodMost.setAdapter(foodAdapter);
        recycleViewFoodMost.setLayoutManager(linear);

        db.collection(GLOBAL.FOOD_COLLECTION)//Load dữ liệu từ firebase
                .whereEqualTo("idShop",shop.getId())//điều kiện
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                foodList.add(document.toObject(Food.class));
                            }
                            foodAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GLOBAL.ORDER = null;
        GLOBAL.ORDERDETAILS = null;
    }
}
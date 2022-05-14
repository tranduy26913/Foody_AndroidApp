package hcmute.spkt.nguyenphucan19110321.uidesign.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.FoodCartAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.event.IChangeNumberOfFoodCart;

public class CartActivity extends AppCompatActivity implements IChangeNumberOfFoodCart {
    private TextView tvTotal;
    private TextView tvNameCart,tvDelete;
    private ImageView imgProfileCart;
    private Button btnContinue,btnCancle;
    RecyclerView recyclerViewCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        SetControls();
        SetEvents();
        SetTexts();
        LoadListOrder();
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GLOBAL.ORDER = null;
                GLOBAL.ORDERDETAILS.clear();
                GLOBAL.ORDERDETAILS = null;
                finish();
            }
        });

        tvNameCart.setText(GLOBAL.USER.getName());
        //imgProfileCart = GLOBAL.USER.getAvatar();
    }

    private void SetEvents() {
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void SetControls(){
        tvTotal = findViewById(R.id.tvTotal);
        tvNameCart = findViewById(R.id.tvNameCart);
        imgProfileCart = findViewById(R.id.imgProfileCart);
        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        tvNameCart = findViewById(R.id.tvNameCart);
        tvDelete = findViewById(R.id.tvDelete);
        btnContinue = findViewById(R.id.btnContinue);
        btnCancle = findViewById(R.id.btnCancelPayment);

    }
    private void SetTexts(){
        String totalPrice = GLOBAL.formatString(String.valueOf(GLOBAL.ORDER.getPrice()));

        SpannableString s1 = new SpannableString(String.valueOf(GLOBAL.ORDER.getTotalNumber()));
        SpannableString s2 = new SpannableString(" phần - ");
        SpannableString s3 = new SpannableString(totalPrice+"đ");

        int flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
        s1.setSpan(new StyleSpan(Typeface.BOLD), 0, s1.length(), flag);
        s2.setSpan(new StyleSpan(Typeface.NORMAL), 0, s2.length(), flag);
        s3.setSpan(new StyleSpan(Typeface.BOLD), 0, s3.length(), flag);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(s1);
        builder.append(s2);
        builder.append(s3);

        tvTotal.setText(builder);
    }

    private void LoadListOrder(){
        FoodCartAdapter foodCartAdapter =new FoodCartAdapter(this, GLOBAL.ORDER,GLOBAL.ORDERDETAILS);
        LinearLayoutManager linear =new LinearLayoutManager(this);
        recyclerViewCart.setAdapter(foodCartAdapter);
        recyclerViewCart.setLayoutManager(linear);
    }

    @Override
    public void OnChangeNumberOfFood() {
        SetTexts();
    }
}
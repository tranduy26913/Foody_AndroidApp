package hcmute.spkt.nguyenphucan19110321.uidesign.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.FoodCartAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.FoodPaymentAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Order;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.OrderDetails;

public class PaymentActivity extends AppCompatActivity {
    TextView tvNamePayment,tvAddressPayment,tvTotalItem,tvShippingFee,tvPlatformFee,tvTotalPrice,tvPricePayment,tvShippingPrice,tvPlatformPrice;
    Button btnPayment,btnCanclePayment;
    ImageView imgProfilePayment;
    RecyclerView recyclerViewCartPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        SetControls();

        LoadData();

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database database  = new Database(PaymentActivity.this,GLOBAL.DATABASE_NAME,null,1);
                String[] params = new String[4];
                ContentValues values = new ContentValues();
                values.put("idUser",GLOBAL.ORDER.getIdUser());
                values.put("nameShop",GLOBAL.ORDER.getNameShop());
                values.put("date",(new Date()).getTime());
                values.put("totalNumber",GLOBAL.ORDER.getTotalNumber());
                values.put("price",GLOBAL.ORDER.getPrice());
                long id = database.ExecQueryGetID("Orders",values);
                Log.i("SQLite","Insert Order id = "+id);
                for(OrderDetails order : GLOBAL.ORDERDETAILS){
                    ContentValues vl = new ContentValues();
                    vl.put("orderID",(int) id);
                    vl.put("foodID",order.getFoodID());
                    vl.put("foodName",order.getFoodName());
                    vl.put("number",order.getNumber());
                    vl.put("price",order.getPrice());
                    database.ExecQueryGetID("OrderDetails",vl);
                    Log.i("SQLite","Insert OrderDetails id = "+order.getFoodName());
                }
                finish();
                Intent intent = new Intent(PaymentActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnCanclePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LoadListOrder();
    }

    private void LoadData(){
        GLOBAL.ORDER.setTotalNumber(GLOBAL.ORDERDETAILS.size());
        GLOBAL.ORDER.setPrice(totalPrice());
        String PricePayment = GLOBAL.formatString(String.valueOf(GLOBAL.ORDER.getPrice()));
        int totalPrice = GLOBAL.ORDER.getPrice()+20000+2000;
        String TotalNumber = GLOBAL.formatString(String.valueOf(GLOBAL.ORDER.getTotalNumber()));
        String TotalPrice = GLOBAL.formatString(String.valueOf(totalPrice));
        tvPricePayment.setText(PricePayment+"đ");
        tvNamePayment.setText(GLOBAL.USER.getName().trim()+" - "+GLOBAL.USER.getPhone());
        tvAddressPayment.setText(GLOBAL.USER.getAddress().trim());
        tvTotalItem.setText("Tổng cộng: "+TotalNumber+" phần");
        imgProfilePayment.setImageResource(R.drawable.foody_logo);
        tvTotalPrice.setText(TotalPrice+"đ");
    }

    private int totalPrice(){
        int total = 0;
        for (OrderDetails od: GLOBAL.ORDERDETAILS) {
            total += od.getPrice() * od.getNumber();
        }
        return total;
    }

    private void SetControls(){
        btnPayment = findViewById(R.id.btnPayment);
        imgProfilePayment = findViewById(R.id.imgProfilePayment);
        tvNamePayment =findViewById(R.id.tvNamePayment);
        tvAddressPayment = findViewById(R.id.tvAddressPayment);
        tvTotalItem = findViewById(R.id.tvTotalItem);
        tvShippingFee = findViewById(R.id.tvShippingFee);
        tvShippingPrice = findViewById(R.id.tvShippingPrice);
        tvPlatformFee = findViewById(R.id.tvPlatformFee);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvPricePayment = findViewById(R.id.tvPricePayment);
        tvPlatformPrice = findViewById(R.id.tvPlatformPrice);
        recyclerViewCartPayment = findViewById(R.id.recycleViewCartPayment);
        btnCanclePayment = findViewById(R.id.btnCancelPayment);
    }

    private void LoadListOrder(){
        FoodPaymentAdapter foodCartAdapter =new FoodPaymentAdapter(this, GLOBAL.ORDER,GLOBAL.ORDERDETAILS);
        LinearLayoutManager linear =new LinearLayoutManager(this);

        recyclerViewCartPayment.setAdapter(foodCartAdapter);
        recyclerViewCartPayment.setLayoutManager(linear);
    }

}
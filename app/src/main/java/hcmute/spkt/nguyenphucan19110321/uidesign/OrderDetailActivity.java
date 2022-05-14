package hcmute.spkt.nguyenphucan19110321.uidesign;

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

import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.FoodPaymentAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.OrderDetailDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Order;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.OrderDetails;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.MainActivity;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.PaymentActivity;

public class OrderDetailActivity extends AppCompatActivity {
    private TextView tvNameInvoice,tvAddressInvoice,tvTotalItem,tvTotalPrice,tvPriceInvoice,tvShippingPrice,tvPlatformPrice;
    private ImageView imgProfileInvoice;
    private Button btnCancelPayment;
    private RecyclerView recyclerViewCartInvoice;
    private Database database;
    private Order order;
    private List<OrderDetails> detailsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = new Database(this,GLOBAL.DATABASE_NAME,null,1);
        setContentView(R.layout.activity_order_detail);
        order = (Order)getIntent().getSerializableExtra("order");
        SetControls();
        LoadData();
        LoadListOrder();
        btnCancelPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void LoadData(){
        String PriceInvoice = GLOBAL.formatString(String.valueOf(order.getPrice()));
        int totalPrice = order.getPrice()+20000+2000;
        String TotalNumber = GLOBAL.formatString(String.valueOf(order.getTotalNumber()));
        String TotalPrice = GLOBAL.formatString(String.valueOf(totalPrice));
        tvPriceInvoice.setText(PriceInvoice+"đ");
        tvNameInvoice.setText(GLOBAL.USER.getName().trim()+" - "+GLOBAL.USER.getPhone());
        tvAddressInvoice.setText(GLOBAL.USER.getAddress().trim());
        tvTotalItem.setText("Tổng cộng: "+TotalNumber+" phần");
        imgProfileInvoice.setImageResource(R.drawable.foody_logo);
        tvTotalPrice.setText(TotalPrice+"đ");
    }

    private void SetControls(){
        imgProfileInvoice = findViewById(R.id.imgProfileInvoice);
        tvNameInvoice =findViewById(R.id.tvNameInvoice);
        tvAddressInvoice = findViewById(R.id.tvAddressInvoice);
        tvShippingPrice = findViewById(R.id.tvShippingPrice);
        tvTotalPrice = findViewById(R.id.tvTotalPriceInvoice);
        tvPriceInvoice = findViewById(R.id.tvPriceInvoice);
        tvPlatformPrice = findViewById(R.id.tvPlatformPrice);
        recyclerViewCartInvoice = findViewById(R.id.recycleViewCartInvoice);
        tvTotalItem = findViewById(R.id.tvTotalItemInvoice);
        btnCancelPayment = findViewById(R.id.btnCancelPayment);
    }

    private void LoadListOrder(){

        OrderDetailDAO odDAO = new OrderDetailDAO(database);
        List<OrderDetails> list = odDAO.getOrderDetailByOrder(order.getId());
        FoodPaymentAdapter foodCartAdapter =new FoodPaymentAdapter(this,order,list);
        LinearLayoutManager linear =new LinearLayoutManager(this);

        recyclerViewCartInvoice.setAdapter(foodCartAdapter);
        recyclerViewCartInvoice.setLayoutManager(linear);
    }
}
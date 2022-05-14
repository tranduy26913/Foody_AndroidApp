package hcmute.spkt.nguyenphucan19110321.uidesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder.FoodCartHolder;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder.FoodHolder;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.event.IChangeNumberOfFoodCart;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Food;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Order;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.OrderDetails;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.CartActivity;

public class FoodCartAdapter extends RecyclerView.Adapter<FoodCartHolder> {
    private CartActivity context;
    private Order order;
    private List<OrderDetails> orderDetailsList;

    public FoodCartAdapter(CartActivity context, Order order, List<OrderDetails> orderDetailsList) {
        this.context = context;
        this.order = order;
        this.orderDetailsList = orderDetailsList;
    }

    @NonNull
    @Override
    public FoodCartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodCartHolder(LayoutInflater.from(context).inflate(R.layout.item_food_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCartHolder holder, int position) {
        OrderDetails orderDetails = orderDetailsList.get(position);
        String TotalPrice = GLOBAL.formatString(String.valueOf(orderDetails.getPrice() * orderDetails.getNumber()));
        String Price = GLOBAL.formatString(String.valueOf(orderDetails.getPrice()));
        String Number = String.valueOf(orderDetails.getNumber());
        holder.tvFoodNameCart.setText(orderDetails.getFoodName());
        holder.tvNumberofFood.setText(Number);
        holder.tvTotal.setText(Price + "đ x " + Number + " = " + TotalPrice + "đ");
        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderDetails.getNumber()>1){
                    order.setTotalNumber(order.getTotalNumber()-1);
                    order.setPrice(order.getPrice()-orderDetails.getPrice());
                    ((IChangeNumberOfFoodCart)context).OnChangeNumberOfFood();
                    orderDetails.setNumber(orderDetails.getNumber() - 1);
                    String TotalPrice = GLOBAL.formatString(String.valueOf(orderDetails.getPrice() * orderDetails.getNumber()));
                    String Price = GLOBAL.formatString(String.valueOf(orderDetails.getPrice()));
                    String Number = String.valueOf(orderDetails.getNumber());
                    holder.tvFoodNameCart.setText(orderDetails.getFoodName());
                    holder.tvNumberofFood.setText(Number);
                    holder.tvTotal.setText(Price + "đ x " + Number + " = " + TotalPrice + "đ");
                    notifyDataSetChanged();
                }
            }
        });

        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order.setTotalNumber(order.getTotalNumber()+1);
                order.setPrice(order.getPrice()+orderDetails.getPrice());
                ((IChangeNumberOfFoodCart)context).OnChangeNumberOfFood();
                orderDetails.setNumber(orderDetails.getNumber() + 1);
                String TotalPrice = GLOBAL.formatString(String.valueOf(orderDetails.getPrice() * orderDetails.getNumber()));
                String Price = GLOBAL.formatString(String.valueOf(orderDetails.getPrice()));
                String Number = String.valueOf(orderDetails.getNumber());
                holder.tvFoodNameCart.setText(orderDetails.getFoodName());
                holder.tvNumberofFood.setText(Number);
                holder.tvTotal.setText(Price + "đ x " + Number + " = " + TotalPrice + "đ");
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderDetailsList.size();
    }
}

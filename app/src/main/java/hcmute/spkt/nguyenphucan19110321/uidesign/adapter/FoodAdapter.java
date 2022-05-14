package hcmute.spkt.nguyenphucan19110321.uidesign.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.event.IAddToCartListener;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Order;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.OrderDetails;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.CartActivity;
import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder.FoodHolder;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Food;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.ShopDetailActivity;

public class FoodAdapter extends RecyclerView.Adapter<FoodHolder> {

    private Context context;
    private List<Food> foodList;
    private IAddToCartListener listener;

    public FoodAdapter(Context context, List<Food> foodList,IAddToCartListener listener){
        this.context = context;
        this.foodList = foodList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodHolder(LayoutInflater.from(context).inflate(R.layout.item_food_most,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
        Food food = foodList.get(position);
        Picasso.get().load(food.getImage()).into(holder.imgFood);
        holder.tvNameFood.setText(food.getName());
        holder.tvDescriptionFood.setText(food.getDescription());
        holder.tvPriceFood.setText(GLOBAL.formatString(String.valueOf(food.getPrice()))+"đ");
        holder.btnAddFoodToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.AddToCart(food);
            }
        });
    }

    private void GoToCart() {
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}

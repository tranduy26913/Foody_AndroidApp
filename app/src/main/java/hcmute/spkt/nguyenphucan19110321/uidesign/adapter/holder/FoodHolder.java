package hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;

public class FoodHolder extends RecyclerView.ViewHolder {
public final ImageView imgFood;
public final TextView tvNameFood;
public final TextView tvDescriptionFood;
public final TextView tvPriceFood;
public final Button btnAddFoodToCart;
    public FoodHolder(@NonNull View itemView) {
        super(itemView);
        this.imgFood = itemView.findViewById(R.id.imgFood);
        this.tvNameFood = itemView.findViewById(R.id.tvNameFood);
        this.tvDescriptionFood = itemView.findViewById(R.id.tvDescriptionFood);
        this.tvPriceFood = itemView.findViewById(R.id.tvPriceFood);
        this.btnAddFoodToCart = itemView.findViewById(R.id.btnAddFoodToCart);
    }
}

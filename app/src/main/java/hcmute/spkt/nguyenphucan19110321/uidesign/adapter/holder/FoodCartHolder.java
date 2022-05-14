package hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;

public class FoodCartHolder extends RecyclerView.ViewHolder {
    public final TextView tvFoodNameCart;
    public final TextView tvNumberofFood;
    public final TextView tvTotal;
    public final Button btnIncrease;
    public final Button btnDecrease;


    public FoodCartHolder(@NonNull View itemView) {
        super(itemView);
        this.tvFoodNameCart = itemView.findViewById(R.id.tvFoodNameCart);
        this.tvTotal = itemView.findViewById(R.id.tvPriceCart);
        this.tvNumberofFood = itemView.findViewById(R.id.tvNumberofFood);
        this.btnDecrease = itemView.findViewById(R.id.btnDescrease);
        this.btnIncrease = itemView.findViewById(R.id.btnIncrease);
    }
}

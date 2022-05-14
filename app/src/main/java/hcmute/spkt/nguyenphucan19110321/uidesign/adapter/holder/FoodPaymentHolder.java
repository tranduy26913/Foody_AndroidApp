package hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;


public class FoodPaymentHolder extends RecyclerView.ViewHolder {
    public final TextView tvNoFPayment;
    public final TextView tvFoodNamePayment;
    public final TextView tvFoodPrice;

    public FoodPaymentHolder(@NonNull View itemView) {
        super(itemView);
        this.tvFoodNamePayment = itemView.findViewById(R.id.tvFoodNamePayment);
        this.tvNoFPayment = itemView.findViewById(R.id.tvNoFPayment);
        this.tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice);
    }
}

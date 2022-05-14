package hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;

public class ShopHomeHolder extends RecyclerView.ViewHolder {
    public final ImageView imvItemShop;
    public final TextView lbName,lbDesc;


    public ShopHomeHolder(@NonNull View itemView) {
        super(itemView);
        imvItemShop = itemView.findViewById(R.id.imvItemShopHome);
        lbName = itemView.findViewById(R.id.tvNameShopHome);
        lbDesc = itemView.findViewById(R.id.tvDescriptionShopHome);
    }
}

package hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;

public class SavedHolder extends RecyclerView.ViewHolder {
    public final ImageView imgShopSearch;
    public final TextView tvNameShopSearch;
    public final TextView tvAddressShopSearch;
    public final TextView tvTypeStoreShopSearch;
    public final TextView tvRateShopSearch;
    public SavedHolder(@NonNull View itemView) {
        super(itemView);
        this.imgShopSearch = itemView.findViewById(R.id.imgShopSearch);
        this.tvNameShopSearch =itemView.findViewById(R.id.tvNameShopSearch);
        this.tvAddressShopSearch = itemView.findViewById(R.id.tvAddressShopSearch);
        this.tvTypeStoreShopSearch = itemView.findViewById(R.id.tvTypeStoreShopSearch);
        this.tvRateShopSearch =itemView.findViewById(R.id.tvRateShopSearch);
    }
}

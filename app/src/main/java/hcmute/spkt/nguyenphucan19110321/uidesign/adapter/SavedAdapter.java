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
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder.SavedHolder;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder.SearchShopHolder;
import hcmute.spkt.nguyenphucan19110321.uidesign.mapping.SaveShopMapping;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Shop;

public class SavedAdapter extends RecyclerView.Adapter<SavedHolder> {
    Context context;
    List<SaveShopMapping> shopList;
    public interface IClickItemSavedShopListener {
        void onClickItemSavedShop(View view);
    }
    private IClickItemSavedShopListener listener;
    public SavedAdapter(Context context,List<SaveShopMapping> shopList,IClickItemSavedShopListener listener){
        this.context = context;
        this.shopList=shopList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public SavedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_search_shop, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickItemSavedShop(view);
            }
        });
        return new SavedHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedHolder holder, int position) {
        SaveShopMapping shop = shopList.get(position);
        Picasso.get().load(shop.getImage()).into(holder.imgShopSearch);
        holder.tvNameShopSearch.setText(shop.getNameShop());
        holder.tvAddressShopSearch.setText(shop.getAddress());
        holder.tvTypeStoreShopSearch.setText(shop.getType());
        holder.tvRateShopSearch.setText(String.valueOf(shop.getRate()));
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }
}

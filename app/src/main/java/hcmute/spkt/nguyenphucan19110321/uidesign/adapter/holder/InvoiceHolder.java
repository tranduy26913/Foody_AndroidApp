package hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;

public class InvoiceHolder extends RecyclerView.ViewHolder {
    public final TextView tvTitle,tvPrice,tvTime;
    public InvoiceHolder(@NonNull View itemView) {
        super(itemView);
        this.tvTitle = itemView.findViewById(R.id.tvTitleInvoice);
        this.tvPrice = itemView.findViewById(R.id.tvPriceInvoice);
        this.tvTime = itemView.findViewById(R.id.tvTimeInvoice);
    }
}

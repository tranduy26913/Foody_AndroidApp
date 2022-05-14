package hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;


public class NotifyHolder extends RecyclerView.ViewHolder {
    public final TextView tvTilte,tvDescription,tvTime;
    public NotifyHolder(@NonNull View itemView) {
        super(itemView);
        this.tvTilte = itemView.findViewById(R.id.tvTitleNotify);
        this.tvDescription = itemView.findViewById(R.id.tvDescriptionNotify);
        this.tvTime = itemView.findViewById(R.id.tvTimeNotify);
    }
}

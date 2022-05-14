package hcmute.spkt.nguyenphucan19110321.uidesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder.NotifyHolder;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Notify;

public class NotifyAdapter extends RecyclerView.Adapter<NotifyHolder> {
    private Context context;
    private List<Notify> notifyList;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" HH:mm MM-dd-yyyy");
    public NotifyAdapter(Context context,List<Notify> notifyList){
        this.notifyList = notifyList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotifyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifyHolder(LayoutInflater.from(context).inflate(R.layout.item_notify,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotifyHolder holder, int position) {
        Notify notify = notifyList.get(position);
        holder.tvTilte.setText(notify.getTitle());
        holder.tvDescription.setText(notify.getDescription());
        holder.tvTime.setText(simpleDateFormat.format(notify.getTime()));
    }

    @Override
    public int getItemCount() {
        return notifyList.size();
    }
}

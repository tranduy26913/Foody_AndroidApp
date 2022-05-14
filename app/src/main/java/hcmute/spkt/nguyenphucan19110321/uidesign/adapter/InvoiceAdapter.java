package hcmute.spkt.nguyenphucan19110321.uidesign.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder.InvoiceHolder;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.holder.NotifyHolder;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Notify;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Order;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceHolder> {
    private Context context;
    private List<Order> invoiceList;
    public interface IClickItemOrderListener {
        void onClickItemOrder(View view);
    }
    private IClickItemOrderListener listener;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" HH:mm MM-dd-yyyy");
    public InvoiceAdapter(Context context,List<Order> invoiceList,IClickItemOrderListener listener){
        this.invoiceList = invoiceList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public InvoiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_invoice,parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickItemOrder(view);
            }
        });
        return new InvoiceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceHolder holder, int position) {
        Order invoice = invoiceList.get(position);
        holder.tvTitle.setText(invoice.getNameShop());
        holder.tvPrice.setText(GLOBAL.formatString(String.valueOf(invoice.getPrice()))+"đ");
        holder.tvTime.setText(simpleDateFormat.format(invoice.getTime()));
    }

    @Override
    public int getItemCount() {
        return invoiceList.size();
    }
}

package hcmute.spkt.nguyenphucan19110321.uidesign.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.OrderDetailActivity;
import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.InvoiceAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.NotifyAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.DatabaseFactory;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.NotifyDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.OrderDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Notify;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Order;

public class InvoiceFragment extends Fragment {
    private RecyclerView recyclerViewListInvoice;
    private Button btnGoToLoginFromInvoice;
    public InvoiceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_invoice, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SetControl();
        if(GLOBAL.USER!=null){
            Database database = new Database(this.getContext(),"Foody.sqlite",null,1);
            OrderDAO orderDAO = new OrderDAO(database);

            List<Order> orderList = orderDAO.getListOrderByUser(GLOBAL.USER.getId());
            InvoiceAdapter adapter = new InvoiceAdapter(this.getContext(), orderList, new InvoiceAdapter.IClickItemOrderListener() {
                @Override
                public void onClickItemOrder(View view) {
                    int i = recyclerViewListInvoice.getChildAdapterPosition(view);
                    Order order = orderList.get(i);
                    GotoDetail(order);
                }
            });
            btnGoToLoginFromInvoice.setVisibility(View.GONE);
            recyclerViewListInvoice.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
            recyclerViewListInvoice.setAdapter(adapter);
            recyclerViewListInvoice.setLayoutManager(manager);

        }else {
            btnGoToLoginFromInvoice.setVisibility(View.VISIBLE);
            recyclerViewListInvoice.setVisibility(View.GONE);
        }
    }

    private void GotoDetail(Order order) {
        Intent intent = new Intent(getContext(), OrderDetailActivity.class);
        intent.putExtra("order",order);
        startActivity(intent);
    }

    private void SetControl(){
        this.recyclerViewListInvoice = getActivity().findViewById(R.id.recyclerViewListInvoice);
        this.btnGoToLoginFromInvoice = getActivity().findViewById(R.id.btnGoToLoginFromInvoice);
    }
}

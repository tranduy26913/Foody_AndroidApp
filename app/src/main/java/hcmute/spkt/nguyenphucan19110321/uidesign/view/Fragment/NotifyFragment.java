package hcmute.spkt.nguyenphucan19110321.uidesign.view.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.NotifyAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.NotifyDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Notify;

public class NotifyFragment extends Fragment {

    private RecyclerView recyclerViewListNotify;
    private Button btnGoToLoginFromNotify;
    public NotifyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notify, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SetControl();
        if(GLOBAL.USER!=null){
            Database database = new Database(this.getContext(),"Foody.sqlite",null,1);
            NotifyDAO notifyDAO = new NotifyDAO(database);
            List<Notify> notifyList = notifyDAO.getListNotifyByUser(GLOBAL.USER.getId());
            NotifyAdapter adapter = new NotifyAdapter(this.getContext(),notifyList);
            btnGoToLoginFromNotify.setVisibility(View.GONE);
            recyclerViewListNotify.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
            recyclerViewListNotify.setAdapter(adapter);
            recyclerViewListNotify.setLayoutManager(manager);

        }else {
            btnGoToLoginFromNotify.setVisibility(View.VISIBLE);
            recyclerViewListNotify.setVisibility(View.GONE);
        }
    }
    private void SetControl(){
        this.recyclerViewListNotify = getActivity().findViewById(R.id.recyclerViewListNotify);
        this.btnGoToLoginFromNotify = getActivity().findViewById(R.id.btnGoToLoginFromNotify);
    }
}
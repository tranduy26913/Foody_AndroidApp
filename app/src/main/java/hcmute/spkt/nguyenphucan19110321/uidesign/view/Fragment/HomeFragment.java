package hcmute.spkt.nguyenphucan19110321.uidesign.view.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.DatabaseFactory;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.ShopDetailActivity;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.ShopHomeAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.event.IClickItemShopHomeListener;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Shop;


public class HomeFragment extends Fragment {

    private GridView gridViewFoodHome;
    private List<Shop> shopList;
    private FirebaseFirestore db;
    private ShopHomeAdapter shopHomeAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db=FirebaseFirestore.getInstance();
        gridViewFoodHome = view.findViewById(R.id.gridViewFoodHome);
        shopList = new ArrayList<>();
        shopHomeAdapter = new ShopHomeAdapter(this.getContext(), shopList, new IClickItemShopHomeListener() {
            @Override
            public void onClickItemShopHome(Shop shop) {
                GoToShopDetail(shop);
            }
        });
        db.collection(GLOBAL.SHOP_COLLECTION)//xử lý lấy dữ liệu từ firebase
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                shopList.add(document.toObject(Shop.class));
                            }
                            shopHomeAdapter.notifyDataSetChanged();
                        }
                    }
                });

        gridViewFoodHome.setAdapter(shopHomeAdapter);

    }

    private void GoToShopDetail(Shop shop) {
        Intent intent = new Intent(this.getContext(), ShopDetailActivity.class);
        intent.putExtra("Shop",shop);
        startActivity(intent);
    }
}
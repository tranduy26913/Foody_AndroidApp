package hcmute.spkt.nguyenphucan19110321.uidesign.view.Fragment;

import android.content.Intent;
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
import android.widget.GridView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.R;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.SavedAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.DatabaseFactory;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.GLOBAL;
import hcmute.spkt.nguyenphucan19110321.uidesign.mapping.SaveShopMapping;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.ShopDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO.UserDAO;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.SaveShop;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Test;
import hcmute.spkt.nguyenphucan19110321.uidesign.view.ShopDetailActivity;
import hcmute.spkt.nguyenphucan19110321.uidesign.adapter.ShopHomeAdapter;
import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.event.IClickItemShopHomeListener;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Shop;


public class SavedFragment extends Fragment {

    private RecyclerView recyclerViewSaved;
    private FirebaseFirestore database;
    private Button btnGotoLogin;
    private SavedAdapter savedAdapter;
    private List<SaveShopMapping> savedList = new ArrayList<>();

    public SavedFragment() {
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
        return inflater.inflate(R.layout.fragment_saved_shop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewSaved = view.findViewById(R.id.recyclerViewSaved);
        btnGotoLogin = view.findViewById(R.id.btnGoToLoginFromSaved);
        database = FirebaseFirestore.getInstance();
        if (GLOBAL.USER != null) {
            database.collection(GLOBAL.SAVED_COLLECTION)
                    .whereEqualTo("idUser", GLOBAL.USER.getId())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    SaveShop saveShop = document.toObject(SaveShop.class);
                                    database.collection(GLOBAL.SHOP_COLLECTION)
                                            .document(String.valueOf(saveShop.getIdShop()))
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        DocumentSnapshot document = task.getResult();
                                                        if (document.exists()) {
                                                            Shop shop = document.toObject(Shop.class);
                                                            SaveShopMapping saveShopMapping = new SaveShopMapping(
                                                                    new Date().getTime(), shop.getId(), GLOBAL.USER.getId(),
                                                                    shop.getName(), shop.getImage(), shop.getAddress(),
                                                                    shop.getType(), shop.getRate());
                                                            savedList.add(saveShopMapping);
                                                            savedAdapter.notifyDataSetChanged();
                                                        }
                                                    }

                                                }
                                            });
                                }

                            }
                        }
                    });
            savedAdapter = new SavedAdapter(this.getContext(), savedList, new SavedAdapter.IClickItemSavedShopListener() {
                @Override
                public void onClickItemSavedShop(View view) {
                    int position = recyclerViewSaved.getChildAdapterPosition(view);
                    GoToShopDetail(savedList.get(position));
                }
            });
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
            recyclerViewSaved.setAdapter(savedAdapter);
            recyclerViewSaved.setVisibility(View.VISIBLE);
            recyclerViewSaved.setLayoutManager(layoutManager);
            btnGotoLogin.setVisibility(View.GONE);
        } else {
            recyclerViewSaved.setVisibility(View.GONE);
        }
    }

    private void GoToShopDetail(SaveShopMapping saved) {
        Intent intent = new Intent(this.getContext(), ShopDetailActivity.class);

        Shop shop = new Shop();
        if (shop == null) {
            return;
        }
        intent.putExtra("Shop", shop);
        startActivity(intent);
    }
}
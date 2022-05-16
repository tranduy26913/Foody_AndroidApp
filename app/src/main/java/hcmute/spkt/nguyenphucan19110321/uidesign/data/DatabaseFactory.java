package hcmute.spkt.nguyenphucan19110321.uidesign.data;

import android.database.Cursor;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.model.Food;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.SaveShop;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Shop;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.User;

public class DatabaseFactory {

    public static List<Shop> getListShop(FirebaseFirestore db) {
        List<Shop> shopList = new ArrayList<>();
        db.collection(GLOBAL.SHOP_COLLECTION)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                shopList.add(document.toObject(Shop.class));
                            }
                        }
                    }
                });
        return shopList;
    }
}

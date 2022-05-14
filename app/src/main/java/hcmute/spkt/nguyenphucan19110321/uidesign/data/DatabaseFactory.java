package hcmute.spkt.nguyenphucan19110321.uidesign.data;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.model.Food;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.SaveShop;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Shop;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.User;

public class DatabaseFactory {

    public static List<Shop> getListShop(Database db) {
        Cursor cursor = db.SelectData("select * from Shops");
        List<Shop> shopList = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            String image = cursor.getString(3);
            String imageSearch = cursor.getString(4);
            String address = cursor.getString(5);
            String type = cursor.getString(6);
            double rate = cursor.getDouble(7);
            shopList.add(new Shop(id, name, des, image, imageSearch, address, type, rate));
        }
        return shopList;
    }
}

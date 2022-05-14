package hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Shop;

public class ShopDAO {
    private Database database;
    public ShopDAO(Database database){
        this.database = database;
    }

    public List<Shop> getListShopByName(String query){
        List<Shop> shopList = new ArrayList<>();
        Cursor cursor = database.SelectData("select * from Shops where name like '%"+query+"%'");
        while (cursor.moveToNext()){
            shopList.add(Shop.GetShopFromCursor(cursor));
        }
        return shopList;
    }
    public Shop getListShopById(long id){
        Cursor cursor = database.SelectData("select * from Shops where id ="+id);
        if (cursor.moveToNext()){
            return Shop.GetShopFromCursor(cursor);
        }
        return null;
    }
}

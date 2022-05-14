package hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO;

import android.database.Cursor;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.SaveShop;

public class SaveShopDAO {
    private Database database;
    public SaveShopDAO(Database database){
        this.database=database;
    }

    public boolean existSaved(long idShop,long idUser){
        String[] params=new String[2];
        params[0]=String.valueOf(idShop);
        params[1]=String.valueOf(idUser);
        Cursor cursor= database.SelectData("select * from Saveds where idShop=? and idUser=?",params);
        return cursor.moveToNext();
    }
}

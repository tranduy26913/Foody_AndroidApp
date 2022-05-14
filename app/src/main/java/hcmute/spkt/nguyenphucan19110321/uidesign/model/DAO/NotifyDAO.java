package hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Notify;

public class NotifyDAO {
    private Database database;
    public NotifyDAO(Database database){
        this.database = database;
    }

    public List<Notify> getListNotifyByUser(long idUser){
        Cursor cursor = database.SelectData("Select * from Notifies where idUser ="+String.valueOf(idUser));
        List<Notify> notifyList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id  = cursor.getInt(0);
            String title = cursor.getString(2);
            String des = cursor.getString(3);
            Date time  =new Date(cursor.getLong(4));
            notifyList.add(new Notify(id,idUser,title,des,time));
        }
        return notifyList;
    }
}

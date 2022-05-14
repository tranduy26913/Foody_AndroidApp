package hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Notify;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Order;

public class OrderDAO {
    private Database database;
    public OrderDAO(Database database){
        this.database=database;
    }

    public List<Order> getListOrderByUser(long idUser){
        Cursor cursor = database.SelectData("Select * from Orders where idUser ="+String.valueOf(idUser));
        List<Order> orderList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id  = cursor.getInt(0);
            String nameShop = cursor.getString(2);
            Date time = new Date(cursor.getLong(3));
            int totalNumber = cursor.getInt(4);
            int price = cursor.getInt(5);
            orderList.add(new Order(id,idUser,nameShop,time,price,totalNumber));
        }
        return orderList;
    }
}

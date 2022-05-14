package hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.Order;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.OrderDetails;

public class OrderDetailDAO {
    private Database database;
    public  OrderDetailDAO(Database database){
        this.database = database;
    }
    public List<OrderDetails> getOrderDetailByOrder(long idOD){
        Cursor cursor = database.SelectData("Select * from OrderDetails where orderID ="+String.valueOf(idOD));
        List<OrderDetails> orderList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id  = cursor.getInt(0);
            int idFood = cursor.getInt(2);
            String foodname = cursor.getString(3);
            int number  =cursor.getInt(4);
            int price = cursor.getInt(5);
            orderList.add(new OrderDetails(id,idOD,idFood,foodname,number,price));
        }
        return orderList;
    }
}

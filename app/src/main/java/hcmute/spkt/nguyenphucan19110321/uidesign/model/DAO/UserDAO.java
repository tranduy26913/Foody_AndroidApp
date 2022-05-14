package hcmute.spkt.nguyenphucan19110321.uidesign.model.DAO;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;
import hcmute.spkt.nguyenphucan19110321.uidesign.mapping.SaveShopMapping;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.User;

public class UserDAO {
    private Database database;
    public UserDAO(Database database){
        this.database = database;
    }
    public User CheckLogin(User user){
        String[] params = new String[2];
        params[0]=user.getUsername();
        params[1]=user.getPassword();

        Cursor cursor = database.SelectData("select * from Users where email='"+user.getEmail()+
                "' and password='"+user.getPassword()+"'");
        if(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String avatar = cursor.getString(2);
            String username = cursor.getString(3);
            String password =cursor.getString(4);
            String address=cursor.getString(5);
            String type = cursor.getString(6);
            String phone =cursor.getString(7);
            String email =cursor.getString(8);
            return new User(id,name,avatar,username,password,address,type,phone,email);
        }
        return null;
    }

    public boolean CheckExistEmail(String email){
        String[] params = new String[1];
        params[0]=email;
        Cursor cursor = database.SelectData("select * from Users where email=?",params);
        return cursor.moveToNext();
    }

    public List<SaveShopMapping> getSavedShop(long idUser){
        List<SaveShopMapping> savedShop = new ArrayList<>();
        Cursor cursor = database.SelectData("select Saveds.id as id, Shops.id as idShop, Shops.name as name," +
                "Shops.imageSearch as image, Shops.address as address, Shops.type as type,Shops.rate as rate  " +
                "from Saveds inner join Shops on Saveds.idShop=Shops.id  where idUser="+String.valueOf(idUser));
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            int idShop = cursor.getInt(1);
            String nameShop = cursor.getString(2);
            String image = cursor.getString(3);
            String address = cursor.getString(4);
            String type =cursor.getString(5);
            double rate=cursor.getDouble(6);
            savedShop.add(new SaveShopMapping(id,idShop,idUser,nameShop,image,address,type,rate));
        }
        return savedShop;
    }
}

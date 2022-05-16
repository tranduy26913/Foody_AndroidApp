package hcmute.spkt.nguyenphucan19110321.uidesign.data;

import java.util.List;

import hcmute.spkt.nguyenphucan19110321.uidesign.model.Order;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.OrderDetails;
import hcmute.spkt.nguyenphucan19110321.uidesign.model.User;

public class GLOBAL {
    public static User USER;
    public static Order ORDER;
    public static List<OrderDetails> ORDERDETAILS;
    public static final String PREF_USER = "PREF_USER";
    public static final String DATABASE_NAME = "Foody.sqlite";
    public static final String USER_COLLECTION = "USER";
    public static final String SHOP_COLLECTION = "SHOP";
    public static final String FOOD_COLLECTION = "FOOD";
    public static final String SAVED_COLLECTION = "SAVED";

    public static String formatString(String price){
        String result ;
        String temp = new StringBuilder(price).reverse().toString();
        int lenght = temp.length();
        for(int i = 0 ;  i <= lenght;i++){
            if(i == 3){
                StringBuffer newString = new StringBuffer(temp);
                newString.insert(i,",");
                temp = newString.toString();
            }
            if(i == 7){
                StringBuffer newString = new StringBuffer(temp);
                newString.insert(i,",");
                temp = newString.toString();
            }
            if(i == 11){
                StringBuffer newString = new StringBuffer(temp);
                newString.insert(i,",");
                temp = newString.toString();
            }
        }
        result = new StringBuilder(temp).reverse().toString();
        return result;
    }
}

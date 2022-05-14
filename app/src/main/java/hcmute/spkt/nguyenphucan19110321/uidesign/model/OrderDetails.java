package hcmute.spkt.nguyenphucan19110321.uidesign.model;

import android.os.Parcelable;

import java.io.Serializable;

public class OrderDetails implements Serializable {
    protected long id;
    protected long orderID;
    protected long foodID;
    protected String foodName;
    protected int number;
    protected int price;

    public OrderDetails() {

    }

    public OrderDetails(long id, long orderID, long foodID, String foodName, int number, int price) {
        this.id = id;
        this.orderID = orderID;
        this.foodID = foodID;
        this.foodName = foodName;
        this.number = number;
        this.price = price;
    }

    public long getFoodID() {
        return foodID;
    }

    public void setFoodID(long foodID) {
        this.foodID = foodID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

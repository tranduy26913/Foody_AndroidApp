package hcmute.spkt.nguyenphucan19110321.uidesign.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    protected long id;
    protected long idUser;
    protected String nameShop;
    protected Date date;
    protected int totalNumber;
    protected int price;

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public Date getTime() {
        return date;
    }

    public void setTime(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Order(){

    }
    public Order(long id,long idUser, String nameShop, Date date, int price, int totalNumber) {
        this.id = id;
        this.idUser=idUser;
        this.nameShop = nameShop;
        this.date = date;
        this.price = price;
        this.totalNumber = totalNumber;
    }

    public Order(long id, long idUser, String nameShop, Date date, int price) {
        this.id = id;
        this.idUser = idUser;
        this.nameShop = nameShop;
        this.date = date;
        this.price = price;
    }
}

package hcmute.spkt.nguyenphucan19110321.uidesign.model;

import java.io.Serializable;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;

public class Food implements Serializable {
    protected int id;
    protected String name;
    protected String description;
    protected String image;
    protected int price;
    protected int idShop;

    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Food(int id, String name, String des, String img, int price,int idShop){
        this.id = id;
        this.name = name;
        this.description = des;
        this.image=img;
        this.price = price;
        this.idShop=idShop;
    }

    public void InsertToDatabase(Database db){
        String[] params = new String[5] ;
        params[0]=this.name;
        params[1]=this.description;
        params[2]=this.image;
        params[3]=String.valueOf(this.price);
        params[4]=String.valueOf(this.idShop);
        db.ExecQuery("insert into Foods values(null,?,?,?,?,?)",params);
    }

    public String[] toParams(){
        String[] params = new String[5] ;
        params[0]=this.name;
        params[1]=this.description;
        params[2]=this.image;
        params[3]=String.valueOf(this.price);
        params[4]=String.valueOf(this.idShop);
        return params;
    }

}

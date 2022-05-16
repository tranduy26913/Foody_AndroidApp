package hcmute.spkt.nguyenphucan19110321.uidesign.model;

import java.io.Serializable;
import java.util.HashMap;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;

public class Food implements Serializable {
    protected long id;
    protected String name;
    protected String description;
    protected String image;
    protected int price;
    protected long idShop;

    public long getIdShop() {
        return idShop;
    }

    public void setIdShop(long idShop) {
        this.idShop = idShop;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Food(){

    }
    public Food(long id, String name, String des, String img, int price,long idShop){
        this.id = id;
        this.name = name;
        this.description = des;
        this.image=img;
        this.price = price;
        this.idShop=idShop;
    }

    public HashMap<String,Object> toHashMap(){
        HashMap<String,Object> entity = new HashMap<>();
        entity.put("id",this.id);
        entity.put("name",this.name);
        entity.put("description",this.description);
        entity.put("image",this.image);
        entity.put("price",this.price);
        entity.put("idShop",this.idShop);
        return entity;
    }

}

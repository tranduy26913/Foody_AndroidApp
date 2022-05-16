package hcmute.spkt.nguyenphucan19110321.uidesign.model;

import java.io.Serializable;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;

public class SaveShop implements Serializable {
    private long id;
    private long idShop;
    private long idUser;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdShop() {
        return idShop;
    }

    public void setIdShop(long idShop) {
        this.idShop = idShop;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
    public SaveShop(){}
    public SaveShop(long id,long idShop,long idUser){
        this.id=id;
        this.idShop=idShop;
        this.idUser=idUser;
    }
    public SaveShop(long idShop,long idUser){
       
        this.idShop=idShop;
        this.idUser=idUser;
    }

    public void InsertToDatabase(Database db) {
        String[] params = new String[2];

        params[0] = String.valueOf(this.idShop);
        params[1] = String.valueOf(this.idUser);
        db.ExecQuery("insert into Saveds values(null,?,?)", params);
    }

}

package hcmute.spkt.nguyenphucan19110321.uidesign.model;

import java.util.Date;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;

public class Notify {
    protected long id;
    protected long idUser;
    protected String title;
    protected String description;
    protected Date time;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    public Notify(){}

    public Notify(long id,long idUser, String title,String description,Date time){
        this.id=id;
        this.idUser=idUser;
        this.description=description;
        this.time=time;
        this.title=title;
    }
    public Notify(long idUser, String title,String description,Date time){
        this.id=id;
        this.idUser=idUser;
        this.description=description;
        this.time=time;
        this.title=title;
    }

    public void InsertToDatabase(Database db){
        String[] params =new String[4];
        params[0]=String.valueOf(this.idUser);
        params[1]=this.title;
        params[2]=this.description;
        params[3]=String.valueOf(this.time.getTime());
        db.ExecQuery("Insert into Notifies values(null,?,?,?,?)",params);
    }
}

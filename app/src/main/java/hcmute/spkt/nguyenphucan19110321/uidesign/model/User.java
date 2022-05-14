package hcmute.spkt.nguyenphucan19110321.uidesign.model;

import java.util.Date;
import java.util.HashMap;

import hcmute.spkt.nguyenphucan19110321.uidesign.data.Database;

public class User {
    private long id;
    private String name;
    private String avatar;
    private String username;
    private String password;
    private String address;
    private String gender;
    private String phone;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public User(){

    }
    public User(String email, String password){
        this.email=email;
        this.password=password;
    }
    public User(String email,String username, String password,String name){
        this.id = new Date().getTime();
        this.email =email;
        this.username=username;
        this.password=password;
        this.name=name;
        this.address = "";
        this.gender = "Nam";
        this.phone = "";
        this.avatar = "";
    }

    public User(long id,String name,String avatar,String username,String password,String address,String gender,String phone,String email){
        this.id=id;
        this.name=name;
        this.avatar=avatar;
        this.username=username;
        this.password=password;
        this.address=address;
        this.gender=gender;
        this.phone=phone;
        this.email=email;
    }

    public HashMap<String,Object> toHashMap(){
        HashMap<String,Object> entity = new HashMap<>();
        entity.put("id",this.id);
        entity.put("name",this.name);
        entity.put("avatar",this.avatar);
        entity.put("username",this.username);
        entity.put("password",this.password);
        entity.put("address",this.address);
        entity.put("gender",this.gender);
        entity.put("phone",this.phone);
        entity.put("email",this.email);
        return entity;
    }

    public void UpdateProfile(Database db) {
        String[] params = new String[5];
        params[0] = this.name;
        params[1] = this.address;
        params[2] = this.gender;
        params[3] = this.phone;
        params[4] = String.valueOf(this.id);
        db.ExecQuery("Update Users set name=?,address=?,gender=?,phone=? where id=?", params);
    }


}

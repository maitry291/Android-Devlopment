package com.example.cafefrio;

public class User {
    String userName;
    String password;
    String email;
    String userId;

   public User(){}

   public User(String userName,String email ,String password){
       this.email=email;
       this.userName=userName;
       this.password=password;
   }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

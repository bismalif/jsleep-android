package com.BismaAlifAlghifariJSleepMN.jsleep_android.model;

public class Account extends Serializable {
    public  double balance;
    public String email;
    public String name;
    public String password;
    public Renter renter;

    public Account(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance= " + balance +
                ", email= '" + email + '\'' +
                ", name= '" + name + '\'' +
                ", password= '" + password + '\'' +
                ", renter= " + renter +
                '}';
    }


}

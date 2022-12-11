package com.BismaAlifAlghifariJSleepMN.jsleep_android.model;

/**
 * The `Renter` class represents a person who rents a room.
 *
 * @author Bisma Alif Alghifari
 * @see Serializable
 */
public class Renter extends Serializable {
    public String phoneNumber;
    public String address;
    public String username;

    public Renter(int id) {
        super(id);
    }
}

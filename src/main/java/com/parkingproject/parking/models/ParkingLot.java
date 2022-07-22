package com.parkingproject.parking.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Parking_Lot")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private char state;

    @Column
    private char type;

    @Column
    private float price;

    @Column
    private int parkingId;

    /**
     * Empty constructor for ParkingLot
     */
    public ParkingLot() {}

    /**
     * Constructor for ParkingLot with params
     * @param state
     * @param type
     * @param price
     * @param parkingId
     */
    public ParkingLot(char state, char type, float price, int parkingId) {
        this.state = state;
        this.type = type;
        this.price = price;
        this.parkingId = parkingId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the state
     */
    public char getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(char state) {
        this.state = state;
    }

    /**
     * @return the type
     */
    public char getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(char type) {
        this.type = type;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the parkingId
     */
    public int getParkingId() {
        return parkingId;
    }

    /**
     * @param parkingId the parkingId to set
     */
    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "ParkingLot [parkingId=" + parkingId + ", price=" + price + ", state=" + state + ", type=" + type + "]";
    }

    
    
}

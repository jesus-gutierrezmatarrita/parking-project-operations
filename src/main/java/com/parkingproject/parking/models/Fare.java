package com.parkingproject.parking.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Author: Jesús Gutiérrez Matarrita
 * Date: July, 20th, 2022
 */

/**
 * This is the entity for Fare in which the attributes are declared
 */

@Entity
@Table(name = "Fare")
public class Fare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @NotNull
    @Column
    private int parkingSlotId;

    @Column
    private float price;

    @NotNull
    @Column
    private int vehicleCategoryId;

    @NotNull
    @Column
    private int unitTimeId;

    /**
     * This is the constructor for the entity Fare with params
     * 
     * @param id_parking_slot
     * @param price
     * @param vehicle_category
     * @param unit_time
     */
    public Fare(int parkingSlotId, float price, int vehicleCategoryId, int unitTimeId) {
        this.parkingSlotId = parkingSlotId;
        this.price = price;
        this.vehicleCategoryId = vehicleCategoryId;
        this.unitTimeId = unitTimeId;
    }

    /**
     * Empty constructor for entity Fare
     */
    public Fare() {
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
     * @return the parkingSlotId
     */
    public int getParkingSlotId() {
        return parkingSlotId;
    }

    /**
     * @param parkingSlotId the parkingSlotId to set
     */
    public void setParkingSlotId(int parkingSlotId) {
        this.parkingSlotId = parkingSlotId;
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
     * @return the vehicleCategoryId
     */
    public int getVehicleCategoryId() {
        return vehicleCategoryId;
    }

    /**
     * @param vehicleCategoryId the vehicleCategoryId to set
     */
    public void setVehicleCategoryId(int vehicleCategoryId) {
        this.vehicleCategoryId = vehicleCategoryId;
    }

    /**
     * @return the unitTimeId
     */
    public int getUnitTimeId() {
        return unitTimeId;
    }

    /**
     * @param unitTimeId the unitTimeId to set
     */
    public void setUnitTimeId(int unitTimeId) {
        this.unitTimeId = unitTimeId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Fare [parkingSlotId=" + parkingSlotId + ", price=" + price + ", unitTimeId=" + unitTimeId
                + ", vehicleCategoryId=" + vehicleCategoryId + "]";
    }

}

package com.parkingproject.parking.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author: Jesús Gutiérrez Matarrita
 * Date: July, 10th, 2022
 */

/**
 * This is the entity for Vehicle in which the attributes are declared
 */
@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String licensePlate;

    private String carBrand;

    private String carModel;

    private String color;

    private int categoryId;

    

    /**
     * The empty constructor for Vehicle
     */
    public Vehicle() {
    }

    

    /**
     * @param licensePlate
     * @param carBrand
     * @param carModel
     * @param color
     * @param categoryId
     * This is the constructor with all the parameters
     */
    public Vehicle(String licensePlate, String carBrand, String carModel, String color, int categoryId) {
        this.licensePlate = licensePlate;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.color = color;
        this.categoryId = categoryId;
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
     * @return the licensePlate
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * @param licensePlate the licensePlate to set
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * @return the carBrand
     */
    public String getCarBrand() {
        return carBrand;
    }

    /**
     * @param carBrand the carBrand to set
     */
    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    /**
     * @return the carModel
     */
    public String getCarModel() {
        return carModel;
    }

    /**
     * @param carModel the carModel to set
     */
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    
    @Override
    public String toString() {
        return "Vehicle [carBrand=" + carBrand + ", carModel=" + carModel + ", categoryId=" + categoryId + ", color="
                + color + ", id=" + id + ", licensePlate=" + licensePlate + "]";
    }

    
    
}

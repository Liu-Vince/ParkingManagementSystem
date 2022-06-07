package com.lwc.parkinglot.entity;

import java.util.Date;

/**
 * @author Liu Wenchang
 */
public class Car {
    private int id;
    private String carNumber;

    /*
    中小型车：1
    大型车：2
    新能源车：3
     */
    private int type;
    private boolean monthlySubscription;
    private Date entryTime;
    private double price;
    private boolean present;
    private Date leaveTime;

    public Car(int id, String carNumber, int type, boolean monthlySubscription, Date entryTime, double price, boolean present) {
        this.id = id;
        this.carNumber = carNumber;
        this.type = type;
        this.monthlySubscription = monthlySubscription;
        this.entryTime = entryTime;
        this.price = price;
        this.present = present;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isMonthlySubscription() {
        return monthlySubscription;
    }

    public void setMonthlySubscription(boolean monthlySubscription) {
        this.monthlySubscription = monthlySubscription;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carNumber='" + carNumber + '\'' +
                ", type=" + type +
                ", monthlySubscription=" + monthlySubscription +
                ", entryTime=" + entryTime +
                ", price=" + price +
                ", present=" + present +
                ", leaveTime=" + leaveTime +
                '}';
    }
}

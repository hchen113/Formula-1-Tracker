package com.example.formula1tracker;

public class RowItem {

    private String driver_name;
    private int driver_profile_pic;
    private int driver_car;

    public RowItem(String driver_name, int driver_profile_pic, int driver_car){
        this.driver_name = driver_name;
        this.driver_profile_pic = driver_profile_pic;
        this.driver_car = driver_car;
    }

    public String getDriver_name(){
        return driver_name;
    }

    public void setDriver_name(String driver_name){
        this.driver_name = driver_name;
    }

    public int getDriver_profile_pic(){
        return driver_profile_pic;
    }

    public void setDriver_profile_pic(int driver_profile_pic){
        this.driver_profile_pic = driver_profile_pic;
    }

    public int getDriver_car(){
        return driver_car;
    }


    public void setDriver_car(int driver_car){
        this.driver_car = driver_car;
    }



}

package com.rnb.install.buildcalculator;

/**
 * Created by Blaze on 2017-03-23.
 * Creating Build class to store the users Build that they create.
 */

public class Build {
    private int id;
    private String name;
    private int weapon;
    private int gear;

    public Build(){

    }

    public Build(int id, String name, int weapon, int gear){
        this.id = id;
        this.name = name;
        this.weapon = weapon;
        this.gear = gear;
    }

    public Build(String name, int weapon, int gear){
        this.name = name;
        this.weapon = weapon;
        this.gear = gear;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

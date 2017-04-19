package com.rnb.install.buildcalculator;

/**
 * Created by web on 2017-03-23.
 */

public class Build {
    private int id;
    private String name;
    private long weapon;
    private long gear;

    public Build(){

    }

    public Build(int id, String name, long weapon, long gear){
        this.id = id;
        this.name = name;
        this.weapon = weapon;
        this.gear = gear;
    }

    public Build(String name, long weapon, long gear){
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

    public long getWeapon() {
        return weapon;
    }

    public void setWeapon(long weapon) {
        this.weapon = weapon;
    }

    public long getGear() {
        return gear;
    }

    public void setGear(long gear) {
        this.gear = gear;
    }
}

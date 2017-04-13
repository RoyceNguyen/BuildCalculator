package com.rnb.install.buildcalculator;

/**
 * Created by web on 2017-03-23.
 */

public class Build {
    private int id;
    private String name;
    private String weapon;
    private String gear;

    public Build(){

    }

    public Build(int id, String name, String weapon, String gear){
        this.id = id;
        this.name = name;
        this.weapon = weapon;
        this.gear = gear;
    }

    public Build(String name, String weapon, String gear){
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

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }
}

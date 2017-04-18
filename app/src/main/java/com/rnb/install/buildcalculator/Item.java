package com.rnb.install.buildcalculator;

/**
 * Created by blaze on 2017-04-14.
 */

public class Item {

    private String name;
    private int attackSpeed;
    private int attackDamage;
    private int magicDamage;
    private int crit;
    private int critDamage;
    private int health;
    private int armor;
    private int magicResist;

    public Item(){

    }

    public Item(String name, int attackDamage, int crit, int critDamage, int attackSpeed){
        this.name = name;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.crit = crit;
        this.critDamage = critDamage;
    }

    public Item(String name, int magicDamage, int crit, int critDamage){
        this.name = name;
        this.critDamage = critDamage;
        this.crit = crit;
        this.magicDamage = magicDamage;
    }

    public Item(int health, int armor, int magicResist, String name){
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.magicResist = magicResist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getMagicDamage() {
        return magicDamage;
    }

    public void setMagicDamage(int magicDamage) {
        this.magicDamage = magicDamage;
    }

    public int getCrit() {
        return crit;
    }

    public void setCrit(int crit) {
        this.crit = crit;
    }

    public int getCritDamage() {
        return critDamage;
    }

    public void setCritDamage(int critDamage) {
        this.critDamage = critDamage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getMagicResist() {
        return magicResist;
    }

    public void setMagicResist(int magicResist) {
        this.magicResist = magicResist;
    }
}
package com.rnb.install.buildcalculator;

/**
 * Created by Blaze on 2017-04-14.
 * Creating Item class for our Database
 * Using two different constructs, one is for Weapon and one is for Gear
 */

public class Item {

    private int id;
    private String name;
    private double attackSpeed;
    private int attackDamage;
    private int magicDamage;
    private int crit;
    private int critDamage;
    private int health;
    private int armor;
    private int magicResist;

    public Item(){

    }

    public Item(int id, String name, int attackDamage, int crit, int critDamage, double attackSpeed){
        this.id = id;
        this.name = name;
        this.attackDamage = attackDamage;
        this.attackSpeed = attackSpeed;
        this.crit = crit;
        this.critDamage = critDamage;
    }

    public Item(int id, String name, int health, int armor, int magicResist){
        this.id = id;
        this.name = name;
        this.health = health;
        this.armor = armor;
        this.magicResist = magicResist;
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

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
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

    @Override
    public String toString() {
        return this.id + " " + this.name + " "  +this.attackSpeed;
    }
}

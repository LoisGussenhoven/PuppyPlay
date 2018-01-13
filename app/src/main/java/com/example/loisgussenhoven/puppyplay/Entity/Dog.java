package com.example.loisgussenhoven.puppyplay.Entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Lois Gussenhoven on 13-12-2017.
 */

public class Dog implements Serializable {

    private String uuid = UUID.randomUUID().toString();

    private String name;
    private String nameOwner;
    private String gender;  // TODO: 30-Dec-17 Change gender to bool 
    private String color1;
    private String color2;
    private double hunger;
    private double thirst;
    private double poop;
    private double social;

    public Dog(String name, String nameOwner, String gender, String color1, String color2, double hunger, double thirst, double poop, double social) {
        this.name = name;
        this.nameOwner = nameOwner;
        this.color1 = color1;
        this.color2 = color2;
        this.gender = gender;
        this.hunger = hunger;
        this.thirst = thirst;
        this.poop = poop;
        this.social = social;
    }

    public Dog(String uuid, String name, String nameOwner, String gender, String color1, String color2) {
        this.uuid = uuid;
        this.name = name;
        this.nameOwner = nameOwner;
        this.color1 = color1;
        this.color2 = color2;
        this.gender = gender;
        this.hunger = 100;
        this.thirst = 100;
        this.poop = 100;
        this.social = 100;
    }

    public Dog(String name, String nameOwner, String gender, String color1, String color2) {
        this.name = name;
        this.nameOwner = nameOwner;
        this.color1 = color1;
        this.color2 = color2;
        this.gender = gender;
        this.hunger = 100;
        this.thirst = 100;
        this.poop = 100;
        this.social = 100;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public String getGender() {
        return gender;
    }


    public double getHunger() {
        return hunger;
    }

    public double getThirst() {
        return thirst;
    }

    public double getPoop() {
        return poop;
    }

    public double getSocial() {
        return social;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public void setThirst(double thirst) {
        this.thirst = thirst;
    }

    public void setPoop(double poop) {
        this.poop = poop;
    }

    public void setSocial(double social) {
        this.social = social;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public void live() {
        if(thirst > 0 )
            setThirst(thirst - 0.5);
        if (hunger > 0)
            setHunger(hunger - 0.5);
       if (social >0)
           setSocial(social - 0.1);
       if (poop > 0)
        setPoop(poop + 0.1);
    }
}

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
    private int colour;
    private double hunger;
    private double thirst;
    private double poop;
    private double social;

    public Dog(String name, String nameOwner, String gender, int colour, double hunger, double thirst, double poop, double social) {
        this.name = name;
        this.nameOwner = nameOwner;
        this.colour = colour;
        this.gender = gender;
        this.hunger = hunger;
        this.thirst = thirst;
        this.poop = poop;
        this.social = social;
    }

    public Dog(String uuid, String name, String nameOwner, String gender, int colour) {
        this.uuid = uuid;
        this.name = name;
        this.nameOwner = nameOwner;
        this.colour = colour;
        this.gender = gender;
        this.hunger = 100;
        this.thirst = 100;
        this.poop = 100;
        this.social = 100;
    }

    public Dog(String name, String nameOwner, String gender, int colour) {
        this.name = name;
        this.nameOwner = nameOwner;
        this.colour = colour;
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

    public int getColour() {
        return colour;
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

    public void setColour(int colour) {
        this.colour = colour;
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

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", nameOwner='" + nameOwner + '\'' +
                ", gender='" + gender + '\'' +
                ", colour='" + colour + '\'' +
                ", hunger=" + hunger +
                ", thirst=" + thirst +
                ", poop=" + poop +
                ", social=" + social +
                '}';
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

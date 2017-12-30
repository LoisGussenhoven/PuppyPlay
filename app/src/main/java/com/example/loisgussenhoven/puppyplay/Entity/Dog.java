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
    private int hunger;
    private int thirst;
    private int poop;
    private int social;

    public Dog(String name, String nameOwner, String gender, int colour, int hunger, int thirst, int poop, int social) {
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

    public int getHunger() {
        return hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public int getPoop() {
        return poop;
    }

    public int getSocial() {
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

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public void setPoop(int poop) {
        this.poop = poop;
    }

    public void setSocial(int social) {
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
}

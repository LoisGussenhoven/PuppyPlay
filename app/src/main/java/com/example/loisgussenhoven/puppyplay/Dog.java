package com.example.loisgussenhoven.puppyplay;

/**
 * Created by Lois Gussenhoven on 13-12-2017.
 */

public class Dog {
    public String name;
    public String nameOwner;
    public String gender;
    public String colour;
    public int hunger;
    public int thirst;
    public int poop;
    public int social;

    public Dog(String name, String nameOwner, String gender, String colour, int hunger, int thirst, int poop, int social) {
        this.name = name;
        this.nameOwner = nameOwner;
        this.colour = colour;
        this.hunger = hunger;
        this.thirst = thirst;
        this.poop = poop;
        this.social = social;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public int getPoop() {
        return poop;
    }

    public void setPoop(int poop) {
        this.poop = poop;
    }

    public int getSocial() {
        return social;
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

package source_code.general;

import java.math.BigInteger;

public class Lab {
    String name;
    int number;
    String room;
    int superv;
    int lvl;

    public Lab(int number,String name,int lvl, String room, int superv) {
        this.name = name;
        this.number = number;
        this.room = room;
        this.superv = superv;
        this.lvl = lvl;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getRoom() {
        return room;
    }

    public int getSuperv() {
        return superv;
    }

    public int getLvl() {
        return lvl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setSuperv(int superv) {
        this.superv = superv;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}

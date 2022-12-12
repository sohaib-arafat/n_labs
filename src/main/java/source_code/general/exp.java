package source_code.general;

public class exp {
    String name;
    String number;
    String week;

    public String getWeek() {
        return week;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public exp(String name, String number, String week) {
        this.name = name;
        this.number = number;
        this.week = week;
    }
}

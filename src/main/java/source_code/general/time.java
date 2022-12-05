package source_code.general;

public class time {
      String day;
      String starting;

    public String getEnding() {
        return ending;
    }

    public String getStarting() {
        return starting;
    }

    public String getDay() {
        return day;
    }

      String ending;

    public time(String day, String starting, String ending) {
        this.day = day;
        this.starting = starting;
        this.ending = ending;
    }
}

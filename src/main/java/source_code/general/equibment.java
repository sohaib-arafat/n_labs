package source_code.general;

public class equibment {
    String serial;
    String name;
    String lab;
    String count;
    String discription;
    String faulty;
    String runnig;
    String unknown;
    String service;

    public String getService() {
        return service;
    }

    public String getUnknown() {
        return unknown;
    }

    public String getRunnig() {
        return runnig;
    }

    public String getFaulty() {
        return faulty;
    }

    public String getDiscription() {
        return discription;
    }

    public String getCount() {
        return count;
    }

    public String getLab() {
        return lab;
    }

    public String getName() {
        return name;
    }

    public String getSerial() {
        return serial;
    }

    public equibment(String serial, String name, String lab, String count, String discription, String faulty, String runnig, String unknown, String service) {
        this.serial = serial;
        this.name = name;
        this.lab = lab;
        this.count = count;
        this.discription = discription;
        this.faulty = faulty;
        this.runnig = runnig;
        this.unknown = unknown;
        this.service = service;
    }
}

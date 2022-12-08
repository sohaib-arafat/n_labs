package source_code.general;

public class instructor {
    String name;
    String ins_email;
    String phone;
    String office;
    String id;

    public instructor(String name, String ins_email, String phone, String office, String id) {
        this.name = name;
        this.ins_email = ins_email;
        this.phone = phone;
        this.office = office;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getIns_email() {
        return ins_email;
    }

    public String getPhone() {
        return phone;
    }

    public String getOffice() {
        return office;
    }

    public String getId() {
        return id;
    }
}

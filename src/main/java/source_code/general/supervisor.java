package source_code.general;

public class supervisor {
    String name;
    String super_email;
    String phone;
    String special;
    String id;

    public supervisor(String name, String super_email, String phone, String special, String id) {
        this.name = name;
        this.super_email = super_email;
        this.phone = phone;
        this.special = special;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSuper_email() {
        return super_email;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpecial() {
        return special;
    }

    public String getId() {
        return id;
    }
}

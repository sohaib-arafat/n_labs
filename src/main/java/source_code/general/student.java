package source_code.general;

public class student {
    String uni_email;
    String stu_email;
    String name;
    String reg;
    String phone;
    String lvl;

    public student(String uni_email, String stu_email, String name, String reg, String phone, String lvl) {
        this.uni_email = uni_email;
        this.stu_email = stu_email;
        this.name = name;
        this.reg = reg;
        this.phone = phone;
        this.lvl = lvl;
    }

    public String getLvl() {
        return lvl;
    }

    public String getReg() {
        return reg;
    }

    public String getName() {
        return name;
    }

    public String getStu_email() {
        return stu_email;
    }

    public String getUni_email() {
        return uni_email;
    }

    public String getPhone() {
        return phone;
    }
}

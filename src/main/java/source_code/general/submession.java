package source_code.general;

import java.sql.Date;

public class submession {
    String lab;
    String section;
    String id;
    String date;
    String exp;
    String grade;

    public submession(String lab, String section, String id, String date, String exp, String grade) {

        this.lab = lab;
        this.section = section;
        this.id = id;
        this.date = date;
        this.exp = exp;
        this.grade = grade;
    }

    public String getGrade() {
        return grade;
    }

    public String getExp() {
        return exp;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getSection() {
        return section;
    }

    public String getLab() {
        return lab;
    }
}

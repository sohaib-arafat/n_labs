package source_code.general;

public class grade {
    String lab;
    String grade;
    String section;
    String submession;

    public grade(String lab, String grade, String section, String submession) {
        this.lab = lab;
        this.grade = grade;
        this.section = section;
        this.submession = submession;
    }

    public String getSubmession() {
        return submession;
    }

    public String getSection() {
        return section;
    }

    public String getGrade() {
        return grade;
    }

    public String getLab() {
        return lab;
    }
}

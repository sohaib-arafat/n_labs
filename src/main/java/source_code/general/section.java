package source_code.general;

public class section {
    String section;
    String instructor;
    String lab_num;
    String lab;
    String superv;

    public section(String section, String instructor, String lab_num, String lab, String superv) {
        this.section = section;
        this.instructor = instructor;
        this.lab_num = lab_num;
        this.lab = lab;
        this.superv = superv;
    }

    public String getSection() {
        return section;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getLab_num() {
        return lab_num;
    }

    public String getLab() {
        return lab;
    }

    public String getSuperv() {
        return superv;
    }
}

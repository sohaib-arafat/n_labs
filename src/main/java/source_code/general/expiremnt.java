package source_code.general;

public class expiremnt {
    public expiremnt(String exp_num, String exp_lab) {
        this.exp_num = exp_num;
        this.exp_lab = exp_lab;
    }

    private String exp_num;
    private String exp_lab;

    public String getExp_num() {
        return exp_num;
    }

    public void setExp_num(String exp_num) {
        this.exp_num = exp_num;
    }

    public String getExp_lab() {
        return exp_lab;
    }

    public void setExp_lab(String exp_lab) {
        this.exp_lab = exp_lab;
    }
}

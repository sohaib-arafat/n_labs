package source_code.head;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

public class users_reg_cont {

    @FXML
    private Button add_stu;

    @FXML
    private TextField addition;

    @FXML
    private TextField email;

    @FXML
    private TextField f_name;

    @FXML
    private ProgressBar pb;
    @FXML
    private TextField general;

    @FXML
    private TextField id;

    @FXML
    private TableColumn<?, ?> ins_email;

    @FXML
    private TableColumn<?, ?> ins_id;

    @FXML
    private TableColumn<?, ?> ins_name;

    @FXML
    private TableColumn<?, ?> ins_office;

    @FXML
    private TableColumn<?, ?> ins_phone;

    @FXML
    private TableView<?> instructor;

    @FXML
    private TextField l_name;

    @FXML
    private TableColumn<?, ?> lab_levelsuper_mail;

    @FXML
    private TextField level;

    @FXML
    private TableColumn<?, ?> level_col;

    @FXML
    private TextField pgone;

    @FXML
    private ComboBox<?> reg_sel;

    @FXML
    private Button spec;

    @FXML
    private TableColumn<?, ?> stu_mail;

    @FXML
    private TableColumn<?, ?> stu_name;

    @FXML
    private TableColumn<?, ?> stu_phone;

    @FXML
    private TableColumn<?, ?> stu_regc;

    @FXML
    private TableColumn<?, ?> stu_uni;

    @FXML
    private TableView<?> student;

    @FXML
    private TextField studentlevel;

    @FXML
    private TextField studentmail;

    @FXML
    private TextField studentname;

    @FXML
    private TextField studentnumber;

    @FXML
    private TextField studentreg;

    @FXML
    private TableColumn<?, ?> super_id;

    @FXML
    private TableColumn<?, ?> super_name;

    @FXML
    private TableColumn<?, ?> super_phone;

    @FXML
    private TableColumn<?, ?> super_special;

    @FXML
    private TableView<?> superv;

    @FXML
    private ComboBox<?> type;

    @FXML
    void reg_fac(ActionEvent event) {

    }

    @FXML
    void reg_stu(ActionEvent event) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String[]names=studentname.getText().split(" ");
        String uni="s"+studentreg.getText().trim()+"@stu.najah.edu";
            String sql="INSERT INTO N_LABS.STUDENT (STU_REG_NUM, FIRST_NAME, LAST_NAME, AC_LEVEL, STU_EMAIL, UNI_EMAIL, PHONE) VALUES ('"+studentreg.getText().trim()+"', '"+names[0]+"', '"+names[1]+"', '"+studentlevel.getText().trim()+"', '"+studentmail.getText().trim()+"', '"+uni+"', '"+studentnumber.getText().trim()+"')";
        Statement sr = con.createStatement();
try {
            sr.executeUpdate(sql);
        }catch (Exception E){
            Alert b = new Alert(Alert.AlertType.ERROR);
            b.setTitle("Invalid info");
            b.setContentText("Either student is registered to this lab or the section is full");
            b.show();
            studentreg.clear();
            studentname.clear();
            studentmail.clear();
            studentlevel.clear();
            studentnumber.clear();
            return;
        }
        con.commit();
        studentreg.clear();
        studentname.clear();
        studentmail.clear();
        studentlevel.clear();
        studentnumber.clear();
        String pass=generatePassayPassword();
        String sql1="INSERT INTO N_LABS.LOGIN (USERN, PASSWORD, ROLE) VALUES ('"+uni+"', '"+pass+"', 'STU')";
        Statement sr1 = con.createStatement();
        sr1.executeUpdate(sql1);
        con.commit();
        con.close();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mail(uni,pass);
            }
        }) ;

t1.start();

    }

    public String generatePassayPassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
    }



    void mail(String reciever,String pass){
        String to = "s12027621@stu.najah.edu";
        String from = "n.labs.2022@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("n.labs.2022@gmail.com", "skplvgeiotxmhilg");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(reciever));
            message.setSubject("This is the email subject");
            message.setContent("<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <a href=\"https://ibb.co/0sKGjQV\" target=\"_self\"><img src=\"https://i.postimg.cc/XY2YC1SK/Logo-black.png\" style=\"height:179px; width:180px\" /></a></p>\n" +
                    "\n" +
                    "<h2><strong><span style=\"font-size:20px\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</span><span style=\"font-size:22px\">Welcome to N-Labs&nbsp; &nbsp;</span></strong></h2>\n" +
                    "\n" +
                    "<p>&nbsp;</p>\n" +
                    "\n" +
                    "<h3><span style=\"font-size:18px\"><strong>Username:("+reciever+")"+"</strong></span></h3>\n" +
                    "\n" +
                    "<h3>&nbsp;</h3>\n" +
                    "\n" +
                    "<h3><span style=\"font-size:18px\"><strong>Password:("+pass+")"+"</strong></span></h3>\n", "text/html");

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }


}

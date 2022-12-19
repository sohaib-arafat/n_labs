package source_code.general;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.Properties;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

public class forgot_cont {

    @FXML
    private TextField user;
    String pass="";
    String email="";

    @FXML
    void send(ActionEvent event) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String sql = "SELECT * FROM LOGIN WHERE USERN='"+user.getText()+"'";
        Statement stnt=con.createStatement();
        ResultSet rs=stnt.executeQuery(sql);

        if(rs.next()){
            pass= generatePassayPassword();
            email=rs.getString("USERN");
            String sql2 = "UPDATE LOGIN SET PASSWORD='"+pass+"' WHERE USERN='"+user.getText()+"'";
            Statement stnt2=con.createStatement();
            stnt2.executeUpdate(sql2);
            con.commit();
            con.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password Reset");
            alert.setHeaderText("Password Reset");
            alert.setContentText(" A new password was sent to your official email" );
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("User not found" );
            user.clear();
            alert.showAndWait();
            return;
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                mail(email,pass);
            }
        });
t.start();
        user.clear();

    }
    void mail(String reciever, String pass) {
        String to = reciever;
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
            message.setSubject("Password Reset");
            message.setContent("<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <a href=\"https://ibb.co/0sKGjQV\" target=\"_self\"><img src=\"https://i.postimg.cc/XY2YC1SK/Logo-black.png\" style=\"height:179px; width:180px\" /></a></p>\n" +
                    "\n" +
                    "<h2><strong><span style=\"font-size:20px\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</span><span style=\"font-size:22px\">Your password has been reset &nbsp; &nbsp;</span></strong></h2>\n" +
                    "\n" +
                    "<p>&nbsp;</p>\n" +
                    "\n" +
                     "<h3>&nbsp;</h3>\n" +
                    "\n" +
                    "<h3><span style=\"font-size:18px\"><strong>Password: " + pass + "</strong></span></h3>\n", "text/html");

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();

        }

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

}

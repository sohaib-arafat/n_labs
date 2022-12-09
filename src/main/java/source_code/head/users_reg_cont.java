package source_code.head;

 import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import source_code.general.instructor;
import source_code.general.student;
import source_code.general.supervisor;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import static org.passay.DigestDictionaryRule.ERROR_CODE;

public class users_reg_cont implements Initializable {

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
    private TableColumn<source_code.general.instructor, String> ins_email;

    @FXML
    private TableColumn<source_code.general.instructor, String> ins_id;

    @FXML
    private TableColumn<source_code.general.instructor, String> ins_name;

    @FXML
    private TableColumn<source_code.general.instructor, String> ins_office;

    @FXML
    private TableColumn<source_code.general.instructor, String> ins_phone;

    @FXML
    private TableView<instructor> instructor;

    @FXML
    private TextField l_name;

    @FXML
    private TableColumn<supervisor, String> lab_levelsuper_mail;

    @FXML
    private TextField level;

    @FXML
    private TableColumn<source_code.general.student, String> level_col;

    @FXML
    private TextField pgone;
    @FXML
    ComboBox<String> gen_type;

    @FXML
    private ComboBox<?> reg_sel;

    @FXML
    private Button spec;

    @FXML
    private TableColumn<student, String> stu_mail;

    @FXML
    private TableColumn<student, String> stu_name;

    @FXML
    private TableColumn<student, String> stu_phone;
    @FXML
    TextField path;
    @FXML
    private TableColumn<student, String> stu_regc;

    @FXML
    private TableColumn<student, String> stu_uni;

    @FXML
    private TableView<student> student;

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
    private TableColumn<supervisor, String> super_id;
    @FXML
    private TextField fac_id;

    @FXML
    private TextField fac_mail;

    @FXML
    private TextField fac_name;

    @FXML
    private TextField fac_phone;

    @FXML
    private TextField fac_spe;
    @FXML
    private TableColumn<supervisor, String> super_name;

    @FXML
    private TableColumn<supervisor, String> super_phone;

    @FXML
    private TableColumn<supervisor, String> super_special;

    @FXML
    private TableView<supervisor> superv;

    @FXML
    private ComboBox<String> type;

    @FXML
    void reg_fac(ActionEvent event) {

        if (type.getValue().equals("Supervisor")) {
            fac_spe.setPromptText("Speciality");

        }
        if (type.getValue().equals("Instructor")) {
            fac_spe.setPromptText("Office number");

        }
    }

    @FXML
    void reg_fac_final() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        if (type.getValue().equals("Supervisor")) {
            Statement stmt = con.createStatement();
            String[] names = fac_name.getText().split(" ");
            String query = "insert into supervisor values('" + fac_id.getText() + "','" + names[0] + "','" + names[1] + "','" + fac_mail.getText() + "','" + fac_phone.getText() + "','" + fac_spe.getText() + "')";
            try {
                stmt.executeUpdate(query);
                con.commit();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Supervisor added successfully");
                alert.showAndWait();

            } catch (SQLException e) {
                con.rollback();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Supervisor not added");
                alert.showAndWait();
                fac_id.clear();
                fac_mail.clear();
                fac_name.clear();
                fac_phone.clear();
                fac_spe.clear();
            }

            String mail = fac_mail.getText().trim();
            fac_id.clear();
            fac_mail.clear();
            fac_name.clear();
            fac_phone.clear();
            fac_spe.clear();
            String pass = generatePassayPassword();
            String sql1 = "INSERT INTO N_LABS.LOGIN (USERN, PASSWORD, ROLE) VALUES ('" + mail + "', '" + pass + "', 'sup')";
            stmt.executeUpdate(sql1);
            con.commit();
            stmt.close();
            con.close();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    mail(mail, pass);
                }
            });

            t1.start();

        }


        if (type.getValue().equals("Instructor")) {
            Statement stmt = con.createStatement();
            String[] names = fac_name.getText().split(" ");
            String query = "insert into instructor values('" + fac_id.getText() + "','" + names[0] + "','" + names[1] + "','" + fac_mail.getText() + "','" + fac_phone.getText() + "','" + fac_spe.getText() + "')";
            try {
                stmt.executeUpdate(query);
                con.commit();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Instructor added successfully");
                alert.showAndWait();


            } catch (SQLException e) {
                con.rollback();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Instructor not added");
                alert.showAndWait();
                fac_id.clear();
                fac_mail.clear();
                fac_name.clear();
                fac_phone.clear();
                fac_spe.clear();
                return;
            }
            String pass = generatePassayPassword();
            String sql1 = "INSERT INTO N_LABS.LOGIN (USERN, PASSWORD, ROLE) VALUES ('" + fac_mail.getText().trim() + "', '" + pass + "', 'inst')";
            stmt.executeUpdate(sql1);
            con.commit();
            stmt.close();
            con.close();
            String mail = fac_mail.getText().trim();
            fac_id.clear();
            fac_mail.clear();
            fac_name.clear();
            fac_phone.clear();
            fac_spe.clear();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    mail(mail, pass);
                }
            });

            t1.start();

        }

    }


    @FXML
    void setsearch() {
        if (gen_type.getValue().equals("Student")) {
            general.setPromptText("Student");
        }
        if (gen_type.getValue().equals("Supervisor")) {
            general.setPromptText("Supervisor");
        }
        if (gen_type.getValue().equals("Instructor")) {
            general.setPromptText("Instructor");
        }

    }

    @FXML
    void reg_stu(ActionEvent event) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
        Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
        con.setAutoCommit(false);
        String[] names = studentname.getText().split(" ");
        String uni = "s" + studentreg.getText().trim() + "@stu.najah.edu";
        String sql = "INSERT INTO N_LABS.STUDENT (STU_REG_NUM, FIRST_NAME, LAST_NAME, AC_LEVEL, STU_EMAIL, UNI_EMAIL, PHONE) VALUES ('" + studentreg.getText().trim() + "', '" + names[0] + "', '" + names[1] + "', '" + studentlevel.getText().trim() + "', '" + studentmail.getText().trim() + "', '" + uni + "', '" + studentnumber.getText().trim() + "')";
        Statement sr = con.createStatement();
        try {
            sr.executeUpdate(sql);
        } catch (Exception E) {
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
        String pass = generatePassayPassword();
        String sql1 = "INSERT INTO N_LABS.LOGIN (USERN, PASSWORD, ROLE) VALUES ('" + uni + "', '" + pass + "', 'stu')";
        Statement sr1 = con.createStatement();
        sr1.executeUpdate(sql1);
        con.commit();
        con.close();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                mail(uni, pass);
            }
        });

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
            message.setSubject("This is the email subject");
            message.setContent("<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <a href=\"https://ibb.co/0sKGjQV\" target=\"_self\"><img src=\"https://i.postimg.cc/XY2YC1SK/Logo-black.png\" style=\"height:179px; width:180px\" /></a></p>\n" +
                    "\n" +
                    "<h2><strong><span style=\"font-size:20px\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;</span><span style=\"font-size:22px\">Welcome to N-Labs&nbsp; &nbsp;</span></strong></h2>\n" +
                    "\n" +
                    "<p>&nbsp;</p>\n" +
                    "\n" +
                    "<h3><span style=\"font-size:18px\"><strong>Username: " + reciever + "</strong></span></h3>\n" +
                    "\n" +
                    "<h3>&nbsp;</h3>\n" +
                    "\n" +
                    "<h3><span style=\"font-size:18px\"><strong>Password: " + pass + "</strong></span></h3>\n", "text/html");

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    @FXML
    void addfiles() {
    }

    @FXML
    void openfiles() {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            path.setText(selectedFile.getAbsolutePath());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File not selected");
            alert.setContentText("Please select a file");
            alert.showAndWait();
            return;
        }
    }

    @FXML
    void gen_c() throws SQLException {
        if (gen_type.getValue() == null)
            return;
        if (gen_type.getValue().equals("Student")) {
            student.setVisible(true);
            instructor.setVisible(false);
            superv.setVisible(false);
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
            Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
            con.setAutoCommit(false);
            String sql = "select * from student where STU_REG_NUM LIKE '%" + general.getText().trim() + "%' OR FIRST_NAME LIKE '%" + general.getText().trim() + "%' OR LAST_NAME LIKE '%" + general.getText().trim() + "%' OR STUDENT.UNI_EMAIL LIKE '%" + general.getText().trim() + "%' OR STUDENT.PHONE LIKE '%" + general.getText().trim() + "%' OR AC_LEVEL LIKE '%" + general.getText().trim() + "%' OR STU_EMAIL LIKE '%" + general.getText().trim() + "%' ORDER BY STU_REG_NUM ASC ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<student> res = new ArrayList<>();
            while (rs.next()) {
                res.add(new student(rs.getString(6), rs.getString(5), rs.getString(2) + " " + rs.getString(3), rs.getString(1), rs.getString(7), rs.getString(4)));
            }
            if (res.isEmpty()) {
                student.setItems(null);
                return;
            }
            ObservableList<student> lst = FXCollections.observableArrayList(res);
            student.setItems(lst);
        }
        if (gen_type.getValue().equals("Instructor")) {
            instructor.setVisible(true);
            student.setVisible(false);
            superv.setVisible(false);
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
            Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
            con.setAutoCommit(false);
            String sql = "select * from instructor where F_ID LIKE '%" + general.getText().trim() + "%' OR FIRST_NAME LIKE '%" + general.getText().trim() + "%' OR LAST_NAME LIKE '%" + general.getText().trim() + "%' OR OFFICE_NUMBER LIKE '%" + general.getText().trim() + "%' OR INSTRUCTOR.PHONE_NUMBER LIKE '%" + general.getText().trim() + "%' or EMAIL LIKE '%" + general.getText().trim() + "%' ORDER BY f_ID ASC ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<instructor> res = new ArrayList<>();
            while (rs.next()) {
                res.add(new instructor(rs.getString(2) + " " + rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(5), rs.getString(1)));
            }
            if (res.isEmpty()) {
                student.setItems(null);
                return;
            }
            ObservableList<instructor> lst = FXCollections.observableArrayList(res);
            instructor.setItems(lst);
        }
        if (gen_type.getValue().equals("Supervisor")) {
            superv.setVisible(true);
            student.setVisible(false);
            instructor.setVisible(false);
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
            Connection con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
            con.setAutoCommit(false);
            String sql = "select * from supervisor where F_ID LIKE '%" + general.getText().trim() + "%' OR FIRST_NAME LIKE '%" + general.getText().trim() + "%' OR LAST_NAME LIKE '%" + general.getText().trim() + "%' OR SPECIALTY LIKE '%" + general.getText().trim() + "%' OR SUPERVISOR.PHONE_NUMBER LIKE '%" + general.getText().trim() + "%' or EMAIL LIKE '%" + general.getText().trim() + "%' ORDER BY f_ID ASC ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<supervisor> res = new ArrayList<>();
            while (rs.next()) {
                res.add(new supervisor(rs.getString(2) + " " + rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(5), rs.getString(1)));
            }
            if (res.isEmpty()) {
                student.setItems(null);
                return;
            }
            ObservableList<supervisor> lst = FXCollections.observableArrayList(res);
            superv.setItems(lst);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.setItems(FXCollections.observableArrayList("Instructor", "Supervisor"));
        gen_type.setItems(FXCollections.observableArrayList("Student", "Supervisor", "Instructor"));
        stu_uni.setCellValueFactory(new PropertyValueFactory<student, String>("uni_email"));
        stu_name.setCellValueFactory(new PropertyValueFactory<student, String>("name"));
        stu_regc.setCellValueFactory(new PropertyValueFactory<student, String>("reg"));
        stu_phone.setCellValueFactory(new PropertyValueFactory<student, String>("phone"));
        stu_mail.setCellValueFactory(new PropertyValueFactory<student, String>("stu_email"));
        level_col.setCellValueFactory(new PropertyValueFactory<student, String>("lvl"));
        ins_email.setCellValueFactory(new PropertyValueFactory<instructor, String>("ins_email"));
        ins_name.setCellValueFactory(new PropertyValueFactory<instructor, String>("name"));
        ins_phone.setCellValueFactory(new PropertyValueFactory<instructor, String>("phone"));
        ins_office.setCellValueFactory(new PropertyValueFactory<instructor, String>("office"));
        ins_id.setCellValueFactory(new PropertyValueFactory<instructor, String>("id"));
        super_id.setCellValueFactory(new PropertyValueFactory<supervisor, String>("id"));
        super_name.setCellValueFactory(new PropertyValueFactory<supervisor, String>("name"));
        super_phone.setCellValueFactory(new PropertyValueFactory<supervisor, String>("phone"));
        lab_levelsuper_mail.setCellValueFactory(new PropertyValueFactory<supervisor, String>("super_email"));
        super_special.setCellValueFactory(new PropertyValueFactory<supervisor, String>("special"));
        student.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml_head/users_clk.fxml"));
                Parent root = null;
                try {
                    root = l.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                student_cont sc = l.getController();
                String[] names = student.getSelectionModel().getSelectedItem().getName().split(" ");
                sc.First.setText(names[0]);
                sc.last.setText(names[1]);
                sc.user.setText(student.getSelectionModel().getSelectedItem().getName());
                sc.Level.setText(student.getSelectionModel().getSelectedItem().getLvl());
                sc.uni.setText(student.getSelectionModel().getSelectedItem().getUni_email());
                sc.number.setText(student.getSelectionModel().getSelectedItem().getReg());
                sc.phone.setText(student.getSelectionModel().getSelectedItem().getPhone());
                sc.personal.setText(student.getSelectionModel().getSelectedItem().getStu_email());
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
                Connection con = null;
                try {
                    con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    con.setAutoCommit(false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String sql = "select PASSWORD from LOGIN where LOGIN.USERN='" + student.getSelectionModel().getSelectedItem().getUni_email() + "'";
                Statement st = null;
                try {
                    st = con.createStatement();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    ResultSet rs = st.executeQuery(sql);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    ResultSet rs = st.executeQuery(sql);
                    rs.next();
                    sc.password.setText(rs.getString(1));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                dialog.setScene(scene);
                dialog.show();


            }
        });

        instructor.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml_head/inst.fxml"));
                Parent root = null;
                try {
                    root = l.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                inst_cont sc = l.getController();
                String[] names = instructor.getSelectionModel().getSelectedItem().getName().split(" ");
                sc.First.setText(names[0]);
                sc.last.setText(names[1]);
                sc.user.setText(instructor.getSelectionModel().getSelectedItem().getName());
                sc.number.setText(instructor.getSelectionModel().getSelectedItem().getId());
                sc.phone.setText(instructor.getSelectionModel().getSelectedItem().getPhone());
                sc.personal.setText(instructor.getSelectionModel().getSelectedItem().getIns_email());
                sc.office.setText(instructor.getSelectionModel().getSelectedItem().getOffice());
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
                Connection con = null;
                try {
                    con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    con.setAutoCommit(false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String sql = "select PASSWORD from LOGIN where LOGIN.USERN='" + instructor.getSelectionModel().getSelectedItem().getIns_email() + "'";
                Statement st = null;
                try {
                    st = con.createStatement();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    ResultSet rs = st.executeQuery(sql);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    ResultSet rs = st.executeQuery(sql);
                    rs.next();
                    sc.password.setText(rs.getString(1));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                dialog.setScene(scene);
                dialog.show();


            }
        });
        superv.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader l = new FXMLLoader(getClass().getResource("/fxml_head/super.fxml"));
                Parent root = null;
                try {
                    root = l.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                super_cont sc = l.getController();
                String[] names = superv.getSelectionModel().getSelectedItem().getName().split(" ");
                sc.First.setText(names[0]);
                sc.last.setText(names[1]);
                sc.user.setText(superv.getSelectionModel().getSelectedItem().getName());
                sc.number.setText(superv.getSelectionModel().getSelectedItem().getId());
                sc.phone.setText(superv.getSelectionModel().getSelectedItem().getPhone());
                sc.personal.setText(superv.getSelectionModel().getSelectedItem().getSuper_email());
                sc.office.setText(superv.getSelectionModel().getSelectedItem().getSpecial());
                try {
                    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String oracleUrl = "jdbc:oracle:thin:@localhost:1521/xe";
                Connection con = null;
                try {
                    con = DriverManager.getConnection(oracleUrl, "N_LABS", "120120");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    con.setAutoCommit(false);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String sql = "select PASSWORD from LOGIN where LOGIN.USERN='" + superv.getSelectionModel().getSelectedItem().getSuper_email() + "'";
                Statement st = null;
                try {
                    st = con.createStatement();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    ResultSet rs = st.executeQuery(sql);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    ResultSet rs = st.executeQuery(sql);
                    rs.next();
                    sc.password.setText(rs.getString(1));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(root);
                dialog.setScene(scene);
                dialog.show();


            }
        });
    }
}

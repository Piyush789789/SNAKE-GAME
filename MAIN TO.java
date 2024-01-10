import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        JFrame frame = new JFrame("Chat Application");
        //register user
        //name,email,mobile,password, confirm password, output
        JLabel namelb,emaillb,mobilelb,passwordlb,cplb,outputlb;
        JTextField nametf,emailtf,mobiletf,passwordtf, cptf;
        JButton registerBtn,closeBtn,resetBtn;

        namelb = new JLabel("Name:");
        emaillb = new JLabel("Email Id:");
        mobilelb = new JLabel("Mobile No:");
        cplb = new JLabel("Confirm Password:");
        passwordlb = new JLabel("Password:");
        outputlb = new JLabel();

        nametf = new JTextField();
        emailtf = new JTextField();
        mobiletf = new JTextField();
        passwordtf = new JTextField();
        cptf = new JTextField();

        resetBtn = new JButton("Reset");
        registerBtn = new JButton("Register");
        closeBtn = new JButton("Close");

        namelb.setBounds(30, 50, 250, 40);
        emaillb.setBounds(30, 90, 250, 40);
        mobilelb.setBounds(30, 130, 250, 40);
        passwordlb.setBounds(30, 170, 250, 40);
        cplb.setBounds(30, 210, 250, 40);
        outputlb.setBounds(30, 250, 250, 40);

        nametf.setBounds(250, 50, 250, 40);
        emailtf.setBounds(250, 90, 250, 40);
        mobiletf.setBounds(250, 130, 250, 40);
        passwordtf.setBounds(250, 170, 250, 40);
        cptf.setBounds(250, 210, 250, 40);

        resetBtn.setBounds(30, 300, 150, 40);
        registerBtn.setBounds(200, 300, 150, 40);
        closeBtn.setBounds(380, 300, 150, 40);

        frame.add(namelb);
        frame.add(emaillb);
        frame.add(mobilelb);
        frame.add(passwordlb);
        frame.add(cplb);
        frame.add(nametf);
        frame.add(emailtf);
        frame.add(mobiletf);
        frame.add(passwordtf);
        frame.add(cptf);
        frame.add(outputlb);
        frame.add(closeBtn);
        frame.add(resetBtn);
        frame.add(registerBtn);

        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                nametf.setText("");
                emailtf.setText("");
                mobiletf.setText("");
                passwordtf.setText("");
                cptf.setText("");
            }
        });

        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(passwordtf.getText().toString().equals(cptf.getText().toString()))
                {
                    String url = "jdbc:mysql://localhost:3306/chatmit";
                    String username = "root";
                    String password = "";
                    try {
                        Connection connection = DriverManager.getConnection(url,username,password);
                        System.out.println("Db is connected");
                        String sql = " insert into users"
                                + " values (null, ?, ?, ?, ?)";
                        PreparedStatement preparedStmt = connection.prepareStatement(sql);
                        preparedStmt.setString (1, nametf.getText().toString());
                        preparedStmt.setString (2, emailtf.getText().toString());
                        preparedStmt.setString   (3, mobiletf.getText().toString());
                        preparedStmt.setString(4, passwordtf.getText().toString());

                        preparedStmt.execute();

                        outputlb.setText("User register done");

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex+"db not connected");
                    }
                }
                else{
                    outputlb.setText("Password dont match");
                }

            }
        });



        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(600, 500);
        frame.setResizable(false);

    }
}
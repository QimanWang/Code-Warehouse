import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Hashtable;

//this class handles the Login GUI and use accounts info
public class LoginView {
    public File loginFile = new File("Accounts.txt");
    public static String un;
    public static String pw;
    public Hashtable<String, String> logins = new Hashtable<String, String>();
    public boolean loggedIn = false;
    public static JFrame frame;

    //constructor will create the GUI view
    public LoginView() throws IOException {

        frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        LoadUserLogins();

    }

    //load user login to check if current user exists
    public void LoadUserLogins() throws FileNotFoundException, UnsupportedEncodingException {

        //if account file exists
        if (loginFile.exists() && !loginFile.isDirectory()) {
            try {

                BufferedReader br = new BufferedReader(new FileReader(loginFile));
                String text = null;

                while ((text = br.readLine()) != null) {
                    String[] fields = text.split(":");
                    System.out.println(fields[0]);
                    logins.put(fields[0], fields[1]);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//if file exists

        //else file don't exist, create file
        else {
            PrintWriter writer = new PrintWriter(loginFile, "UTF-8");
            writer.write("Admin:Admin\n");
//            writer.write(System.getProperty("line.separator"));
            writer.close();
        }
    }//load user logins form file

    //place login components
    private void placeComponents(JPanel panel) {

        panel.setLayout(null);

        //user labels
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        //user text
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        //user password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        //user text field
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        //login button
        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        //register button
        JButton registerButton = new JButton("register");
        registerButton.setBounds(180, 80, 80, 25);
        panel.add(registerButton);

        //when login is pressed, we check if the user exists and if the password matches
        ActionListener loginButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                un = userText.getText();
                pw = new String(passwordText.getPassword());

                //if login matches
                if (logins.containsKey(un) && logins.get(un).equals(pw)) {

                    JOptionPane.showMessageDialog(source, source.getText()
                            + " successful!");
                    System.out.println("login successful");
                    StartProgram.logger.writeToLogFile(LoginView.un +" Logged in successfully.");
                    loggedIn = true;
                    frame.dispose();
                    System.out.println("closing login screen");

                }//login match
                //if incorrect password
                else if (logins.containsKey(un)) {
                    JOptionPane.showMessageDialog(source, "Incorrect password");
                    System.out.println("Incorrect password");
                    StartProgram.logger.writeToLogFile(LoginView.un +" entered incorrect password.");
                }//not match
                //else tell user to create new account
                else {
                    JOptionPane.showMessageDialog(source, "Account don't exist. Please register.");
                    System.out.println("Account don't exist. Please register.");
                    StartProgram.logger.writeToLogFile(LoginView.un +" was never registered");
                }//nothing match
            }
        }; // login listener
        loginButton.addActionListener(loginButtonListener);

        //register button to add user to accounts file
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                un = userText.getText();
                pw = new String(passwordText.getPassword());

                //check if acc already exist
                if (logins.containsKey(un)) {
                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Account already exists");
                    System.out.println("Account already exists");
                    StartProgram.logger.writeToLogFile(LoginView.un +" tried to register again");
                }//acc already exists
                //else create the user accounts file that stores all users
                else {
                    logins.put(un, pw);
                    BufferedWriter bw = null;
                    PrintWriter htWriter = null;
                    try {
                        // APPEND MODE SET HERE to add more users to the same accounts file
                        bw = new BufferedWriter(new FileWriter(loginFile, true));
                        bw.write(un + ":" + pw);
                        bw.newLine();
                        bw.close();
                        htWriter = new PrintWriter(un + "_" + pw + ".txt", "UTF-8");
                        String columnNames = "";
                        for (Object col : JTableRow.columns) {
                            columnNames += col.toString() + " | ";
                        }
                        htWriter.write(columnNames + '\n');
                        htWriter.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }


                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Successfully registered");
                    StartProgram.logger.writeToLogFile(LoginView.un +" successfully registered.");
                }
            }
        });
    }
}

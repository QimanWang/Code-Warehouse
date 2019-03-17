import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Hashtable;

public class LoginView {
    public File loginFile = new File("Accounts.txt");
    public String un;
    public String pw;
    public Hashtable<String, String> logins = new Hashtable<String, String>();
    public boolean loggedIn = false;
    //constructor
    public LoginView() throws IOException {
        JFrame frame = new JFrame("Login");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
        LoadUserLogins();
    }

    public void LoadUserLogins() throws FileNotFoundException, UnsupportedEncodingException {


        if (loginFile.exists() && !loginFile.isDirectory()) {
            // do something

            try {

                BufferedReader br = new BufferedReader(new FileReader(loginFile));
                String text = null;

                while ((text = br.readLine()) != null) {
                    System.out.println(text);
                    String[] fields = text.split(":");
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
            writer.close();
        }

    }//load user logins form file


    //place components
    private void placeComponents(JPanel panel) {

        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JButton registerButton = new JButton("register");
        registerButton.setBounds(180, 80, 80, 25);
        panel.add(registerButton);

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
                   loggedIn = true;
                }//login match
                else if (logins.containsKey(un)) {
                    JOptionPane.showMessageDialog(source, "Incorrect password");
                    System.out.println("Incorrect password");

                }//un match
                else {
                    JOptionPane.showMessageDialog(source, "Account don't exist. Please register.");
                    System.out.println("Account don't exist. Please register.");
                }//nothing match
            }
        }; // login listener
        loginButton.addActionListener(loginButtonListener);

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
                }//acc already exists
                else {
                    logins.put(un, pw);
                    PrintWriter writer = null;
                    try {
                        writer = new PrintWriter(loginFile, "UTF-8");
                        writer.write(un + ":" + pw + '\n');
                        writer.close();
                    } catch (FileNotFoundException | UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }


                    JOptionPane.showMessageDialog((Component) e.getSource(),
                            "Successfully registered");
                }

            }
        });
    }


}

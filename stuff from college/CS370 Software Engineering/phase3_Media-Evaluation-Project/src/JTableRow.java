import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class JTableRow {



    public static void userGui(Hashtable<String,String[]> ht,String outfile,BufferedWriter bwLog) throws IOException {

        // create JFrame and JTable
        JFrame frame = new JFrame();
        JTable table = new JTable();

        // create a table model and set a Column Identifiers to this model
        //"Id","First Name","Last Name","Age"
        // Date Purchased | Timestamp
        Object[] columns = {"Inventory Number"," Item Description","Quantity","Price","Date Purchased","Timestamp"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);

        // create JTextFields
        JTextField textInvNum = new JTextField();
        JTextField textDescrip = new JTextField();
        JTextField textQty = new JTextField();
        JTextField textPrice = new JTextField();
        JTextField textDatePurchased = new JTextField();
        JTextField textTimeStamp = new JTextField();

        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");
        JButton btnSave = new JButton("Save to textfile");
        textInvNum.setBounds(20, 220, 100, 25);
        textDescrip.setBounds(20, 250, 100, 25);
        textQty.setBounds(20, 280, 100, 25);
        textPrice.setBounds(20, 310, 100, 25);
        textDatePurchased.setBounds(20, 340, 100, 25);
        textTimeStamp.setBounds(20, 370, 100, 25);


        btnAdd.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 265, 100, 25);
        btnDelete.setBounds(150, 310, 100, 25);
        btnSave.setBounds(150, 355, 180, 25);
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);

        frame.setLayout(null);

        frame.add(pane);

        // add JTextFields to the jframe
        frame.add(textInvNum);
        frame.add(textDescrip);
        frame.add(textQty);
        frame.add(textPrice);
        frame.add(textDatePurchased);
        frame.add(textTimeStamp);

        // add JButtons to the jframe
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);
        frame.add(btnSave);

        // create an array of objects to set the row data
        Object[] row = new Object[6];

        //add initial data
        Set<String> keys =ht.keySet();
        for(String key: keys){
//            System.out.println("Value of "+key+" is: "+ht.get(key)[1]);
//
            if (key != "colNames") {
                row[0] = key;
                row[1] = ht.get(key)[1];
                row[2] = ht.get(key)[2];
                row[3] = ht.get(key)[3];
                row[4] = ht.get(key)[4];
                row[5] = ht.get(key)[5];
                model.addRow(row);
            }
        }

        bwLog.write(new java.util.Date() +" Successfully loaded Hashtable to GUI");
        bwLog.newLine();
        // button save
        btnSave.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent ev) {

                String dir = System.getProperty("user.dir");
                System.out.println("current dir = " + dir);
                String filePath = dir + "\\"+ outfile;
                System.out.println("file loaded");
                System.out.println(filePath);

                BufferedWriter bw = null;
                FileWriter fw = null;

                try {

                    fw = new FileWriter(filePath);
                    bw = new BufferedWriter(fw);
                    String content="";
                    bw.write("Inventory Number|Item Description|Quantity|Price| Date Purchased|Timestamp");
                    bw.newLine();
                    for (int row = 0; row < table.getRowCount(); row++) {
                        content ="";
                        for (int col = 0; col < table.getColumnCount(); col++) {
                            content+=table.getValueAt(row, col) +"|";

                        }

                        content+='\n';
                        bw.write(content);
                        bw.newLine();
                        System.out.println(content);
                    }
                    //bw.write(content);

                    System.out.println("Done");


                } catch (IOException e) {

                    e.printStackTrace();

                } finally {

                    try {

                        if (bw != null)
                            bw.close();

                        if (fw != null)
                            fw.close();

                    } catch (IOException ex) {

                        ex.printStackTrace();

                    }

                }


            }
        });

        // button add row
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                row[0] = textInvNum.getText();
                row[1] = textDescrip.getText();
                row[2] = textQty.getText();
                row[3] = textPrice.getText();
                row[4] = textDatePurchased.getText();
                row[5] = textTimeStamp.getText();

                // add row to the model
                model.addRow(row);
                String[] stringValues = new String[6];
                for(int z=0;z<row.length;z++){
                    stringValues[z]= row[z].toString();
                    System.out.println(stringValues[z]);
                }
            }
        });

        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                    model.removeRow(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e){

                // i = the index of the selected row
                int i = table.getSelectedRow();

                textInvNum.setText(model.getValueAt(i, 0).toString());
                textDescrip.setText(model.getValueAt(i, 1).toString());
                textQty.setText(model.getValueAt(i, 2).toString());
                textPrice.setText(model.getValueAt(i, 3).toString());
                textDatePurchased.setText(model.getValueAt(i, 4).toString());
                textTimeStamp.setText(model.getValueAt(i, 5).toString());
            }
        });

        // button update row
        btnUpdate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if(i >= 0)
                {
                    model.setValueAt(textInvNum.getText(), i, 0);
                    model.setValueAt(textDescrip.getText(), i, 1);
                    model.setValueAt(textQty.getText(), i, 2);
                    model.setValueAt(textPrice.getText(), i, 3);
                    model.setValueAt(textDatePurchased.getText(), i, 4);
                    model.setValueAt(textTimeStamp.getText(), i, 5);
                }
                else{
                    System.out.println("Update Error");
                }
            }
        });




        frame.setSize(900,450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        bwLog.write(new java.util.Date() +" Successfully Saved to output file");
        bwLog.newLine();
    }

}

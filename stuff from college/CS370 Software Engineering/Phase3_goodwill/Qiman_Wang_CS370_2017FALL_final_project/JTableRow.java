import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Set;

//this is the GUI class
public class JTableRow {

    public static Object[] columns = {"Item Number", " Item Name", "Number of Bids", "Price", "Seller", "Date"};

    //user gui takes in the hashtable
    public static void userGui(Hashtable<String, GoodWillItem> ht) throws IOException {
        // create JFrame and JTable
        JFrame frame = new JFrame();
        JTable table = new JTable();
        JTable table1 = new JTable();

        // create a table model and set a Column Identifiers to this model
        //"Id","First Name","Last Name","Age"
        // String Purchased | Timestamp

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);
        // Change A JTable Background Color, Font Size, Font Color, Row Height
//        table.setBackground(Color.LIGHT_GRAY);
//        table.setForeground(Color.black);
        Font font = new Font("", 1, 16);
        table.setFont(font);
        table.setRowHeight(20);

        //table1
        //set the color for the table
        table.setBackground(Color.WHITE);
        table.setForeground(Color.black);


        // create JTextFields
        JTextField textItemNumber = new JTextField();
        JTextField textItemName = new JTextField();
        JTextField textItemBidNumber = new JTextField();
        JTextField textPrice = new JTextField();
        JTextField textSeller = new JTextField();
        JTextField textDate = new JTextField();

        //search field
        JTextField textSearchField = new JTextField();
        JTextField textHighPrice = new JTextField();
        JTextField textLowPrice = new JTextField();
        JTextField textSearchBuyNowOnly = new JTextField();
        JTextField textSearchPickupOnly = new JTextField();
        JTextField textSearchNoPickupOnly = new JTextField();
        JTextField textSearchClosedAuctions = new JTextField();

        // create JButtons
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");
        JButton btnSave = new JButton("Save to database");
        JButton btnSearch = new JButton("Search GoodWill.com");

        //create labels for the dataset
        JLabel labelItemNumber = new JLabel("Item Number ");
        JLabel labelItemName = new JLabel("Name");
        JLabel labelItemBidNumber = new JLabel("Number Of Bid");
        JLabel labelPrice = new JLabel("Price");
        JLabel labelSeller = new JLabel("Seller");
        JLabel labelDate = new JLabel("Date");

        //advanced search label
        JLabel labelSearchText = new JLabel("Search Text");
        JLabel labelHighPrice = new JLabel("High Price");
        JLabel labelLowPrice = new JLabel("Low Price");
        JLabel labelSearchBuyNowOnly = new JLabel("Search Buy Now Only");
        JLabel labelSearchPickupOnly = new JLabel("Search Pick up Only");
        JLabel labelSearchNoPickupOnly = new JLabel("Search No Pick up Only");
        JLabel labelSearchClosedAuctions = new JLabel("Search Closed Auctions");

        //label item location
        labelItemNumber.setBounds(20, 520, 100, 25);
        labelItemName.setBounds(20, 550, 100, 25);
        labelItemBidNumber.setBounds(20, 580, 100, 25);
        labelPrice.setBounds(20, 610, 100, 25);
        labelSeller.setBounds(20, 640, 100, 25);
        labelDate.setBounds(20, 670, 100, 25);

        //label advanced search
        int advLabelX = 450;
        int advLabelY = 520;
        labelSearchText.setBounds(advLabelX, advLabelY, 100, 25);
        labelHighPrice.setBounds(advLabelX, advLabelY +30, 100, 25);
        labelLowPrice.setBounds(advLabelX, advLabelY +60, 100, 25);
        labelSearchBuyNowOnly.setBounds(advLabelX, advLabelY +90, 100, 25);
        labelSearchPickupOnly.setBounds(advLabelX, advLabelY +120, 100, 25);
        labelSearchNoPickupOnly.setBounds(advLabelX, advLabelY +150, 100, 25);
        labelSearchClosedAuctions.setBounds(advLabelX, advLabelY +180, 100, 25);

        //text field
        int textX = 150;
        textItemNumber.setBounds(textX, 520, 100, 25);
        textItemName.setBounds(textX, 550, 100, 25);
        textItemBidNumber.setBounds(textX, 580, 100, 25);
        textPrice.setBounds(textX, 610, 100, 25);
        textSeller.setBounds(textX, 640, 100, 25);
        textDate.setBounds(textX, 670, 100, 25);

        //adv search text
        int advtextX =600;
        int advtextY = 520;
        textSearchField.setBounds(advtextX, advtextY, 100, 25);
        textHighPrice.setBounds(advtextX, advtextY +30, 100, 25);
        textLowPrice.setBounds(advtextX, advtextY +60, 100, 25);
        textSearchBuyNowOnly.setBounds(advtextX, advtextY +90, 100, 25);
        textSearchPickupOnly.setBounds(advtextX, advtextY +120, 100, 25);
        textSearchNoPickupOnly.setBounds(advtextX, advtextY +150, 100, 25);
        textSearchClosedAuctions.setBounds(advtextX, advtextY +180, 100, 25);

        //table buttons
        int btnX = 300;
        btnAdd.setBounds(btnX, 520, 100, 25);
        btnUpdate.setBounds(btnX, 565, 100, 25);
        btnDelete.setBounds(btnX, 610, 100, 25);

        //save button
        font = new Font("", 1, 30);
        btnSave.setFont(font);
        btnSave.setBounds(20, 700, 400, 200);

        //search button
        btnSearch.setBounds(500, 800, 200, 150);

        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 1000, 500);
        pane.setBackground(Color.yellow);

        //set the frame's layout
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.CYAN );

        //add pane ot frame
        frame.add(pane);

        // add JTextFields to the frame
        frame.add(textItemNumber);
        frame.add(textItemName);
        frame.add(textItemBidNumber);
        frame.add(textPrice);
        frame.add(textSeller);
        frame.add(textDate);

        //add text label
        frame.add(labelItemNumber);
        frame.add(labelItemName);
        frame.add(labelItemBidNumber);
        frame.add(labelPrice);
        frame.add(labelSeller);
        frame.add(labelDate);

        //add adv search label
        frame.add(labelSearchText);
        frame.add(labelHighPrice);
        frame.add(labelLowPrice);
        frame.add(labelSearchBuyNowOnly);
        frame.add(labelSearchPickupOnly);
        frame.add(labelSearchNoPickupOnly);
        frame.add(labelSearchClosedAuctions);

        // add advanced search text
        frame.add(textSearchField);
        frame.add(textHighPrice);
        frame.add(textLowPrice);
        frame.add(textSearchBuyNowOnly);
        frame.add(textSearchPickupOnly);
        frame.add(textSearchNoPickupOnly);
        frame.add(textSearchClosedAuctions);

        // add table Buttons to the frame
        frame.add(btnAdd);
        frame.add(btnDelete);
        frame.add(btnUpdate);
        frame.add(btnSave);
        frame.add(btnSearch);

        //add search labels
        frame.add(labelSearchText);

        // create an array of objects to set the row data
        Object[] row = new Object[6];

        //add initial data
        Set<String> keys = ht.keySet();
        for (String key : keys) {

            if (!Objects.equals(key, "colNames")) {
                row[0] = key;
                row[1] = ht.get(key).getName();
                row[2] = ht.get(key).getDesc();
                row[3] = ht.get(key).getPrice();
                row[4] = ht.get(key).getSeller();
                row[5] = ht.get(key).getDate(); ///////tostring
                model.addRow(row);
            }
        }

        StartProgram.logger.writeToLogFile("Successfully loaded GUI");

        // button save
        btnSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ev) {

                String filePath = LoginView.un + "_" + LoginView.pw + ".txt";
                System.out.println("file loaded");
                System.out.println(filePath);

                BufferedWriter bw = null;
                FileWriter fw = null;

                try {

                    fw = new FileWriter(filePath);
                    bw = new BufferedWriter(fw);
                    String content = "";
                    bw.write("Inventory Number|Item Name|Number of Bids|Price|Seller|Bid End Date");
                    bw.newLine();
                    for (int row = 0; row < table.getRowCount(); row++) {
                        content = "";
                        for (int col = 0; col < table.getColumnCount(); col++) {
                            content += table.getValueAt(row, col) + "|"  ;
                        }

                        //write to output file
                        bw.write(content);
                        bw.newLine();
                        System.out.println(content);
                    }

                    StartProgram.logger.writeToLogFile("Successfully Save to database");
                    System.out.println("Done writing to database");

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
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                row[0] = textItemNumber.getText();
                row[1] = textItemName.getText();
                row[2] = textItemBidNumber.getText();
                row[3] = textPrice.getText();
                row[4] = textSeller.getText();
                row[5] = textDate.getText();


                // add row to the model
                model.addRow(row);
                String[] stringValues = new String[6];

                String content = "";
                for (int z = 0; z < row.length; z++) {
                    stringValues[z] = row[z].toString();
                    System.out.println(stringValues[z]);
                content += stringValues[z] + "|";
                }
                StartProgram.logger.writeToLogFile("Successfully added "+ content+" to database");
                System.out.println("log: added");

            }
        });

        // button remove row
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                if (i >= 0) {
                    // remove a row from jtable
                    model.removeRow(i);
                    String content = "";
                    String[] stringValues = new String[6];
                    for (int z = 0; z < row.length; z++) {
                        stringValues[z] = row[z].toString();
                        System.out.println(stringValues[z]);
                        content += stringValues[z] +"|";
                    }
                    StartProgram.logger.writeToLogFile("Successfully added "+ content+" to database");
                } else {
                    System.out.println("Delete Error");
                    StartProgram.logger.writeToLogFile("failed to delete from database");
                }
            }
        });

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                textItemNumber.setText(model.getValueAt(i, 0).toString());
                textItemName.setText(model.getValueAt(i, 1).toString());
                textItemBidNumber.setText(model.getValueAt(i, 2).toString());
                textPrice.setText(model.getValueAt(i, 3).toString());
                textSeller.setText(model.getValueAt(i, 4).toString());
                textDate.setText(model.getValueAt(i, 5).toString());
            }
        });

        // button update row
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if (i >= 0) {
                    model.setValueAt(textItemNumber.getText(), i, 0);
                    model.setValueAt(textItemName.getText(), i, 1);
                    model.setValueAt(textItemBidNumber.getText(), i, 2);
                    model.setValueAt(textPrice.getText(), i, 3);
                    model.setValueAt(textSeller.getText(), i, 4);
                    model.setValueAt(textDate.getText(), i, 5);

                    String content = "";
                    String[] stringValues = new String[6];
                    for (int z = 0; z < row.length; z++) {
                        stringValues[z] = row[z].toString();
                        System.out.println(stringValues[z]);
                        content += stringValues[z] +"|";
                    }

                    StartProgram.logger.writeToLogFile("Successfully Updated "+ content+" to database");

                } else {
                    System.out.println("Update Error");
                }
            }
        });

        //search
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                UrlParser u = new UrlParser(textSearchField.getText());
                System.out.println("searching for: " + textSearchField.getText());

                //first grab the search text from the J text field
                try {
                    u.grabItemNumbers();
                    System.out.println("searched for " + textSearchField.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }// try to get item name

                //parse one item name at at time
                for (String itemNamee: u.urkItemNameeList){
                GoodWillItem newGoodWillItem = null;

                try {
                    //parse the item name
                    newGoodWillItem = u.parseHtml(itemNamee);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }//try to parse html

                    //add result to the table view
                assert newGoodWillItem != null;
                row[0] = newGoodWillItem.getNumber();
                row[1] = newGoodWillItem.getName();
                row[2] = newGoodWillItem.getDesc();
                row[3] = newGoodWillItem.getPrice();
                row[4] = newGoodWillItem.getSeller();
                row[5] = newGoodWillItem.getDate();

                // add row to the model
                model.addRow(row);

                String content = "";
                String[] stringValues = new String[6];
                for (int z = 0; z < row.length; z++) {
                    stringValues[z] = row[z].toString();
                    System.out.println(stringValues[z]);
                    content += stringValues[z] + "|";
                }
                StartProgram.logger.writeToLogFile("Successfully searched " +
                        textSearchField.getText() + " added " + content + " to database");
            }
            }
        });

        //set the frame sizes
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //when window is closed, we want to close the log writers
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.out.println("window is closed");
                try {
                    StartProgram.logger.writeToLogFile("Closing GUI");
                    StartProgram.logger.writeToLogFile("Successfully shut down program");
                    StartProgram.logger.closeLogger();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}

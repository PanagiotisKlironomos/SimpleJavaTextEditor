/* Copyright (C) 2019 Panagiotis Klironomos - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the GNU GENERAL PUBLIC LICENSE 
 */
package simple_java_text_editor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame  {
    // Main Frame Attributes
    private JMenuBar itemMenuBar, buttonMenuBar;    
    private JMenu fileMenu , editMenu;
    private JTextArea txtArea;
    private JMenuItem newItm, statItm, exitItm ;    
    private JButton openBtn,clearBtn,saveBtn,copyBtn; 
    private JTextField pathTxtFld;
    private JPanel itemPanel,btnPanel,txtAreaPanel,pathPanel,globalPanel;
    private JScrollPane scrollPane ; 
    // ActionListener Attributes
    String actionString;
    JFileChooser fileChooser;
    int returnOfFlChsr ;
    File createdFile, openedFile;
    FileWriter writeFile;
    BufferedWriter writeBuff;
    FileReader readFile;  
    BufferedReader readBuff ;
        
    public MainFrame() throws HeadlessException{
        
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        //the programs stops when closing the frame
        
        //exit by pressing x icon (Window Listener)
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                exitOperation();
            }
        });
        
        //panel initialisation
        itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout());
        btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout());
        pathPanel = new JPanel();
        pathPanel.setLayout(new GridLayout());
        txtAreaPanel = new JPanel(); 
        txtAreaPanel.setLayout(new BorderLayout());
        globalPanel = new JPanel();
        globalPanel.setLayout(new BoxLayout(globalPanel, BoxLayout.Y_AXIS));
        //menu items initialisation
        newItm = new JMenuItem("New");
        statItm = new JMenuItem("Statistics");
        exitItm = new JMenuItem("Exit");
       //  buttons initialisation
        openBtn = new JButton("Open");
        saveBtn = new JButton("Save");
        clearBtn = new JButton("Clear");
        copyBtn = new JButton("Copy (Save as)");
        // menu bars initialisation
        itemMenuBar = new JMenuBar();
        buttonMenuBar = new JMenuBar();
        // menus initialisation
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        // text area initialisation
        txtArea=new JTextArea();
        scrollPane  = new JScrollPane(txtArea);
        //scrollPane.setBounds(10,60,780,500);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants
                .VERTICAL_SCROLLBAR_ALWAYS);
        //text field (path) initialisation
        pathTxtFld = new JTextField("Opened File Path",1);
        pathTxtFld.setEditable(false);
        // add items in menus
        fileMenu.add(newItm);
        fileMenu.add(exitItm);
        editMenu.add(statItm);
        // add menus to menu bars
        itemMenuBar.add(fileMenu);        
        itemMenuBar.add(editMenu);
        buttonMenuBar.add(openBtn);
        buttonMenuBar.add(saveBtn);
        buttonMenuBar.add(copyBtn);
        buttonMenuBar.add(clearBtn);        
        // add elements to panels
        itemPanel.add(itemMenuBar);        
        btnPanel.add(buttonMenuBar);        
        pathPanel.add(pathTxtFld);
        globalPanel.add(itemPanel);
        globalPanel.add(btnPanel);
        globalPanel.add(pathPanel);
        txtAreaPanel.add(scrollPane);
        globalPanel.add(txtAreaPanel);
        //this.setJMenuBar(itemMenuBar);        
        this.add(globalPanel);     
        // add Action Listeners to buttons
        openBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                openOperation();  
            }
         });        
        saveBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                saveOperation();  
            }
         });        
        copyBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                saveAsOperation();  
            }
         });        
        clearBtn.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                txtArea.setText("");  
            }
         });
        // add Action Listeners to Items
        newItm.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            newOperation(); 
            }
         });        
        exitItm.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            exitOperation();
        }
        });  
        statItm.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            StatFrame statistics;
            
            int words = txtArea.getText().split("\\s+").length;
            int chars = txtArea.getText().length();
            String temp = txtArea.getText().replaceAll("\\s","");
            int charsNoSpace = temp.length();
            int paragraphs = txtArea.getText().split("\\n\t").length;
            int bytes = txtArea.getText().length();
            
            statistics = new StatFrame("Text Statistics",280,130,
            words,chars,charsNoSpace,paragraphs,bytes);              
            }
        
        });
    } //end of frame constructor
    
    public void saveAsOperation(){
    //  JFileChooser object creation
    fileChooser = new JFileChooser("f:");    
    // opening save window
    returnOfFlChsr = fileChooser.showSaveDialog(null);
    if (returnOfFlChsr == JFileChooser.APPROVE_OPTION) {   
        // Set the label to the path of the selected directory 
        createdFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
	try { 
            //  File Writer creation
            writeFile = new FileWriter(createdFile, false); 
            //  buffered writer creation for writing 
            writeBuff = new BufferedWriter(writeFile); 
            // writing to File
            writeBuff.write(txtArea.getText()); 
  
            writeBuff.flush(); 
            writeBuff.close(); 
            pathTxtFld.setText(createdFile.toString()); 
        } 
        catch (Exception evt) { 
            JOptionPane.showMessageDialog(this, evt.getMessage()); 
        } 
    } 
    // if the user stops the process
    else
    JOptionPane.showMessageDialog(this, "File Saving Has Been Canceled!"); 
    }
    
    public void openOperation() { 
    //  JFileChooser  object creation
    fileChooser = new JFileChooser("f:"); 
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Text Files",  "txt");
    fileChooser.setFileFilter(filter);
    // opening save window
    returnOfFlChsr = fileChooser.showOpenDialog(null);   
    // if the user picks a file
    if (returnOfFlChsr == JFileChooser.APPROVE_OPTION) { 
        // Set the label to the path of the selected directory 
        openedFile = new File(fileChooser.getSelectedFile().getAbsolutePath()); 
  
	try { 
            // helping Strings 
            String string1 = "", string2 = ""; 
            // File reader 
            readFile = new FileReader(openedFile); 
            // Buffered reader 
            readBuff = new BufferedReader(readFile); 
            //  string2 initialisation 
            string2 = readBuff.readLine(); 
            // file input
            while ((string1 = readBuff.readLine()) != null) { 
                string2 = string2 + "\n" + string1; 
            } 
            // Set the file 
            txtArea.setText(string2); 
            // Set to path text field 
            pathTxtFld.setText(openedFile.toString());                
        } 
	catch (Exception evt) { 
            JOptionPane.showMessageDialog(this, evt.getMessage()); 
	} 
    } 
    // if the user stops the process
    else JOptionPane.showMessageDialog(this, "File Opening Has Been Canceled!"); 
    } 
    
    public void saveOperation(){ 
    //  JFileChooser object creation
        if( pathTxtFld.getText().equals("Opened File Path") ) 
            JOptionPane.showMessageDialog(this, "Theres is no path for "
                    + "this text to be saved :( \n Use 'Save as' Option ");
        else{
            createdFile = new File(pathTxtFld.getText()); 
            try { 
                //  file writer  creation
                writeFile = new FileWriter(createdFile, false); 
                //  buffered writer creation for writing
                writeBuff = new BufferedWriter(writeFile); 
                //writing to file
                writeBuff.write(txtArea.getText()); 
  
                writeBuff.flush(); 
                writeBuff.close(); 
                pathTxtFld.setText(createdFile.toString()); 
                JOptionPane.showMessageDialog(this, "File Has Been Saved!");
                } 
                catch (Exception evt) { 
                    JOptionPane.showMessageDialog(this, evt.getMessage()); 
                }
        }
    }
    
    public void newOperation (){
	// if anything has been written into textArea
            if (!txtArea.getText().equals("")) {
                int dialogButton = JOptionPane.showConfirmDialog (null, "Would"
                        + " You Like to Save your Text First?");
                //and the user wants to save the File 
                if (dialogButton == JOptionPane.YES_OPTION){
                    // if the user hasnt opened the file from a certain path
                    if( pathTxtFld.getText().equals("Opened File Path") )
                        saveAsOperation();  
                    //if the user has opened the file from a certain path
                    else saveOperation();
                    // new file actions
                    txtArea.setText(""); 
                    pathTxtFld.setText("Opened File Path");
                }
                //else if the user doesnt want to save the file
                else if (dialogButton == JOptionPane.NO_OPTION){
                    // new file actions
                    txtArea.setText(""); 
                    pathTxtFld.setText("Opened File Path");
                }                
            }
            // if nothing has been written into textArea
            else {
                // if the file was saved somewhere
                if( !pathTxtFld.getText().equals("Opened File Path") ){
                    int dialogButton = JOptionPane.showConfirmDialog (null, "W"
                            + "ould You Like to Save your Text First?");
                    //if the user wants to save it
                    if (dialogButton == JOptionPane.YES_OPTION){
                        saveOperation();
                        // new file actions
                        txtArea.setText(""); 
                        pathTxtFld.setText("Opened File Path");
                    }
                    // if the user wants to save it
                    else if (dialogButton == JOptionPane.NO_OPTION){
                        // new file actions
                        txtArea.setText(""); 
                        pathTxtFld.setText("Opened File Path");
                    }
                }
                // if the file wasnt saved somewhere
                else{
                    // new file actions
                    txtArea.setText(""); 
                    pathTxtFld.setText("Opened File Path");
                }
            } 
    }
    
    public void exitOperation(){
    
            int dialogButton = JOptionPane.showConfirmDialog(null,"Are you "
                    + "sure you want to exit?");            
            if (dialogButton == JOptionPane.YES_OPTION){
                // if anything has been written into textArea
                // if the file was opened from a certain path
                if (!txtArea.getText().equals("") || 
                        !pathTxtFld.getText().equals("Opened File Path") ) {
                    // user question for saving the file
                    int dialogButton2 = JOptionPane.showConfirmDialog (null, ""
                            + "Would You Like to Save your Text First?");
                    if (dialogButton2 == JOptionPane.YES_OPTION){
                        //if the file has never been saved
                        if (pathTxtFld.getText().equals("Opened File Path"))
                            saveAsOperation(); 
                        // if the file existed in a certain path
                        else
                            saveOperation();
                        System.exit(0);  
                    }
                    else if (dialogButton2 == JOptionPane.NO_OPTION) 
                        System.exit(0);       
                }
                else System.exit(0); 
            }
    }
            
    
    
}
  
  
   


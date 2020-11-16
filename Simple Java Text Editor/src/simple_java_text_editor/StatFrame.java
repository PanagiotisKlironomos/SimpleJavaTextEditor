/* Copyright (C) 2019 Panagiotis Klironomos - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the GNU GENERAL PUBLIC LICENSE 
 */
package simple_java_text_editor;

import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;


public class StatFrame extends JFrame {   
   private JTextArea txtArea;
   String title; 
   int width,height,words, chars,charsNoSpaces,paragraphs, fSize ;
   
   
    public StatFrame(String title, int width, int height, int words, int chars, 
         int charsNoSpaces, int paragraphs, int fSize) throws HeadlessException
    {
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
            //the programs stops when closing the frame
         this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                exitOperation();
            }
        });

        txtArea = new JTextArea();
        txtArea.setEditable(false);
        txtArea.setText("Words : " + String.valueOf(words)+ " \n");
        txtArea.append("Characters : " + String.valueOf(chars)+ " \n");
        txtArea.append("Characters without Spaces : " + 
                String.valueOf(charsNoSpaces)+ " \n");
        txtArea.append("Paragraphs : " + String.valueOf(paragraphs)+ " \n");
        txtArea.append("File Size : " + String.valueOf(fSize) + " Bytes \n");
        this.setTitle(title);
        this.setSize(width, height);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.add(txtArea);        
        
    }

public void exitOperation(){
            //User question
            int dialogButton = JOptionPane.showConfirmDialog(null,"Are you "
                    + "sure you close statistics ?");            
            if (dialogButton == JOptionPane.YES_OPTION){
                 this.dispose();
            }
    }    
    
}



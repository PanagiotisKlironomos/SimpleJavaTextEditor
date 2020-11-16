/* Copyright (C) 2019 Panagiotis Klironomos - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the GNU GENERAL PUBLIC LICENSE 
 */
package simple_java_text_editor;


public class SimpleJavaTextEditor {


    public static void main(String[] args) {
        MainFrame textEditor = new MainFrame();
        textEditor.setTitle("Simple Java Text Editor | "
                + "Panagiotis Klironomos");
        textEditor.setSize(1000, 500);
        textEditor.setVisible(true);
        textEditor.setLocationRelativeTo(null);
        
    }
    
}

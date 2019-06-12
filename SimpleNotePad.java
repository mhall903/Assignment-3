import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;
public class SimpleNotePad extends JFrame implements ActionListener {
    public JMenuBar menubar = new JMenuBar();
    public JMenu fileMenu = new JMenu("File");
    public JMenu editMenu = new JMenu("Edit");
    public JTextPane textPane = new JTextPane();
    public JTextArea txtArea;
    public JMenuItem[] fileItem = new JMenuItem[5];
    public JMenuItem[] editItem = new JMenuItem[3];


    public SimpleNotePad() {
        initializeMenu();
        setJMenuBar(menubar);
        add(new JScrollPane(textPane));
        setPreferredSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        SimpleNotePad app = new SimpleNotePad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Actions action = new Actions(SimpleNotePad.this);
        Object source = e.getActionCommand();
        if (source.equals("New File")) {
            action.newFile();
            if (source.equals(("Save File"))) {
                action.saveFile();
            }
            if (source.equals("Print File")) {
                action.printFile();
            }
            if (source.equals("Open")) {
                action.openFile(null);
            }
            if (source.equals("Recent")) {
                //action.openRecent();
            }
            if (source.equals("Copy")) {
                textPane.copy();
            }
            if (source.equals("Paste")) {
                action.paste();
            }
            if (source.equals("Replace")) {
                action.replaceAll();
            }

        }

    }
    public void initializeMenu(){
        setTitle("A Simple Notepad Tool");
        menubar.add(fileMenu);
        menubar.add(editMenu);
        txtArea = new JTextArea(24, 54);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        String[] fileNames = {"New File", "Save File", "Print File", "Open", "Recent"};
        for (int i = 0; i < fileNames.length; i++) {
            JMenu temp1 = new JMenu(fileNames[i]);
            fileMenu.add(temp1);
            fileMenu.addActionListener(this);
            fileMenu.setActionCommand(fileNames[i].toString());
        }
        String[] editNames = {"Copy", "Paste", "Replace"};
        for (int i = 0; i < editNames.length; i++) {
            JMenu temp2 = new JMenu(editNames[i]);
            editMenu.add(temp2);
            editMenu.addActionListener(this);
            editMenu.setActionCommand(editNames[i].toString());
        }

    }
}

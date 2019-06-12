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
public class Actions {
    SimpleNotePad pad;

    public Actions(SimpleNotePad notePad) {
        pad = notePad;
    }

    public void newFile(){
        pad.textPane.setText("");
    }
    public void copy(){
        pad.textPane.copy();
    }
    public void saveFile() {
        File fileToWrite = null;
        JFileChooser fc = new JFileChooser();
        fc.setVisible(true);
        String temp=pad.textPane.getText();
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            fileToWrite = fc.getSelectedFile();
        try {
            PrintWriter out = new PrintWriter(new FileWriter(fileToWrite));
            out.println(pad.textPane.getText());
            JOptionPane.showMessageDialog(null, "File is saved successfully...");
            out.close();
        } catch (IOException ex) {
        }
    }

    public void printFile() {
        try {
            PrinterJob pjob = PrinterJob.getPrinterJob();
            pjob.setJobName("Sample Command Pattern");
            pjob.setCopies(1);
            pjob.setPrintable(new Printable() {
                public int print(Graphics pg, PageFormat pf, int pageNum) {
                    if (pageNum > 0)
                        return Printable.NO_SUCH_PAGE;
                    pg.drawString(pad.textPane.getText(), 500, 500);
                   // pad.paint(pad.textPane);
                    return Printable.PAGE_EXISTS;
                }
            });

            if (pjob.printDialog() == false)
                return;
            pjob.print();
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(null,
                    "Printer error" + pe, "Printing error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void openFile(File file) {
        JFileChooser open = new JFileChooser();
        open.setVisible(true);
        int option = open.showOpenDialog(null);
        if (option == JFileChooser.APPROVE_OPTION) {
            this.pad.txtArea.setText("");
            try {
                Scanner sc = new Scanner(new FileReader(file));
                while (sc.hasNext()) {
                    this.pad.txtArea.append(sc.nextLine() + "\n");
                }
            } catch (Exception e) {
                System.out.println("Error Message");
            }
        }
    }
    public void paste(){
        StyledDocument doc = pad.textPane.getStyledDocument();
        Position position = doc.getEndPosition();
        System.out.println("offset"+position.getOffset());
        pad.textPane.paste();
    }
    public void replaceAll(){
        String replace=JOptionPane.showInputDialog("Replace or Insert with");
        if(replace!=""){
            pad.textPane.replaceSelection(replace);
        }
    }
    public void openRecent(String filepath){
        File file= new File(filepath);
        openFile(file);

    }
}

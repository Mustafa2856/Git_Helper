/*
Launches UI on new Thread
using javax.swing (TO CHANGE)
and connects it to services.
 */

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel{

    public JTextArea term;

    public JPanel repo;
    public JLabel CurrentRepo;
    public JLabel CurrentBranch;
    public JButton CreateRepo;
    public JButton CreateBranch;
    public JButton ChangeRepo;
    public JButton ChangeBranch;
    public JButton mergeBranch;

    public JButton addChanges;
    public JButton commitChanges;
    public JButton stashChanges;
    public JButton unstashChanges;
    public JButton revertChanges;

    public UI(){
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        setBounds(0,0,800,500);
        term = new JTextArea();
        term.setLineWrap(true);
        term.setBackground(Color.BLACK);
        term.setForeground(Color.WHITE);
        term.append("Hello There");
        term.setBounds(400,0,400,250);
        add(term);
    }
}

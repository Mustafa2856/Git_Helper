/*
Launches UI on new Thread
using javax.swing (TO CHANGE)
and connects it to services.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

public class UI extends JPanel{

    Main Parent;

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

    public UI(Main Parent){
        this.Parent = Parent;
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        setBounds(0,0,800,500);
        term = new JTextArea();
        term.setLineWrap(true);
        term.setBackground(Color.BLACK);
        term.setForeground(Color.WHITE);
        term.setBounds(5,405,990,190);
        add(term);
        repo = new JPanel();
        repo.setBounds(5,5,990,90);
        add(repo);
    }

    void initRepoinfo(String rep,String Branch){
        rep = rep.substring(rep.lastIndexOf('/')+1);
        repo.removeAll();
        CurrentRepo = new JLabel(rep);
        CurrentRepo.setBounds(5,5,190,35);
        repo.add(CurrentRepo);
        CurrentBranch = new JLabel(Branch);
        CurrentBranch.setBounds(205,5,90,35);
        repo.add(CurrentBranch);
        ChangeRepo = new JButton("Change Repo");
        ChangeRepo.setBounds(305,5,140,35);
        ChangeRepo.addActionListener((ActionEvent e)->{
            try {
                Parent.selectRepo();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });
        repo.add(ChangeRepo);
        repaint();
    }


}

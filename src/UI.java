/*
Launches UI on new Thread
using javax.swing (TO CHANGE)
and connects it to services.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UI extends JPanel {

    Main Parent;

    public JTextArea term;

    public JPanel repo;
    public JLabel CurrentRepo;
    public JComboBox<String> CurrentBranch;
    public JLabel CurrentBranchL;
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

    public UI(Main Parent) {
        this.Parent = Parent;
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        setBounds(0, 0, 800, 500);
        term = new JTextArea();
        term.setLineWrap(true);
        term.setBackground(Color.BLACK);
        term.setForeground(Color.WHITE);
        term.setBounds(5, 405, 990, 190);
        add(term);
        repo = new JPanel();
        repo.setBounds(5, 5, 990, 90);
        repo.setLayout(null);
        add(repo);
    }

    void initRepoinfo(String rep, String Branch) {
        rep = rep.substring(rep.lastIndexOf('/') + 1);
        repo.removeAll();
        repo.setBackground(Color.DARK_GRAY);
        repo.setForeground(Color.WHITE);
        CurrentRepo = new JLabel(rep);
        CurrentRepo.setBounds(5, 5, 190, 35);
        CurrentRepo.setForeground(Color.WHITE);
        CurrentRepo.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        CurrentRepo.setHorizontalAlignment(JLabel.CENTER);
        repo.add(CurrentRepo);
        CurrentBranchL = new JLabel(Branch);
        CurrentBranchL.setBounds(5, 45, 190, 35);
        CurrentBranchL.setForeground(Color.WHITE);
        CurrentBranchL.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        CurrentBranchL.setHorizontalAlignment(JLabel.CENTER);
        repo.add(CurrentBranchL);
        CurrentBranch = new JComboBox<>();
        CurrentBranch.setBounds(355, 5, 90, 35);
        CurrentBranch.setBackground(Color.DARK_GRAY);
        CurrentBranch.setForeground(Color.WHITE);
        CurrentBranch.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        for (String br : Parent.BRANCHES) {
            CurrentBranch.addItem(br);
        }
        repo.add(CurrentBranch);
        ChangeRepo = new JButton("Change Repo");
        ChangeRepo.setBounds(205, 5, 140, 35);
        ChangeRepo.addActionListener((ActionEvent e) -> {
            try {
                Parent.selectRepo();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        ChangeRepo.setBackground(Color.BLACK);
        ChangeRepo.setForeground(Color.LIGHT_GRAY);
        repo.add(ChangeRepo);
        ChangeBranch = new JButton("Change Branch");
        ChangeBranch.setBounds(455, 5, 150, 35);
        ChangeBranch.addActionListener((ActionEvent e) -> {
            try {
                Parent.changeBranch();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            repaint();
        });
        ChangeBranch.setBackground(Color.BLACK);
        ChangeBranch.setForeground(Color.LIGHT_GRAY);
        repo.add(ChangeBranch);
        CreateRepo = new JButton("Create Repo");
        CreateRepo.setBounds(205, 45, 140, 35);
        CreateRepo.setBackground(Color.BLACK);
        CreateRepo.setForeground(Color.LIGHT_GRAY);
        CreateRepo.addActionListener((ActionEvent e) -> {
            try {
                Parent.createRepo();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });
        repo.add(CreateRepo);
        CreateBranch = new JButton("Create Branch");
        CreateBranch.setBounds(355, 45, 140, 35);
        CreateBranch.setBackground(Color.BLACK);
        CreateBranch.setForeground(Color.LIGHT_GRAY);
        CreateBranch.addActionListener((ActionEvent e) -> {
            JDialog jd = new JDialog();
            jd.setLayout(null);
            JLabel Bname = new JLabel("Branch name:");
            Bname.setBounds(5, 5, 140, 35);
            jd.add(Bname);
            JTextField br = new JTextField("");
            br.setBounds(155, 5, 200, 35);
            jd.add(br);
            JButton brb = new JButton("Create");
            brb.setBounds(365, 5, 100, 35);
            brb.addActionListener((ActionEvent evt) -> {
                try {
                    Parent.createBranch(br.getText());
                    jd.setVisible(false);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            });
            jd.add(brb);
            jd.setVisible(true);
            jd.setBounds(0,0,500,200);
        });
        repo.add(CreateBranch);
        repaint();
    }


}

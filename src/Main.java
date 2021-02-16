import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends JFrame{

    public ArrayList<String> REPOSITORIES;
    public ArrayList<String> BRANCHES;
    UI ui;
    File repo,cnf;
    String Branch;

    public Main() throws IOException, InterruptedException {
        initUser();
        initUI();
    }

    void initUser() throws IOException {
        cnf = new File(System.getenv("HOME")+"/GITHELPER.cnf");
        REPOSITORIES = new ArrayList<>();
        BRANCHES = new ArrayList<>();
        if(cnf.exists()){
            Scanner sc = new Scanner(new FileInputStream(cnf.getAbsolutePath()));
            while(sc.hasNextLine()){
                REPOSITORIES.add(sc.nextLine());
            }
        }else{
            cnf.createNewFile();
        }
    }

    void initUI(){
        setVisible(true);
        setBounds(0,0,1000,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ui = new UI(this);
        add(ui);
        repaint();
    }

    void selectRepo() throws IOException, InterruptedException {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showDialog(this,"Select Repo");
        repo = fc.getSelectedFile();
        REPOSITORIES.add(repo.getAbsolutePath());
        ProcessBuilder prr = new ProcessBuilder("git","branch");
        prr.directory(repo);
        Process p = prr.start();
        p.waitFor();
        Branch = "";
        Scanner sc = new Scanner(p.getInputStream());
        BRANCHES.clear();
        if(sc.hasNextLine()){
            ui.term.append("Branches:\n");
            while(sc.hasNextLine()){
                BRANCHES.add(sc.nextLine().substring(2));
                ui.term.append(BRANCHES.get(BRANCHES.size()-1)+"\n");
            }
            Branch = BRANCHES.get(0);
            this.ui.initRepoinfo(repo.getAbsolutePath(),Branch);
            PrintWriter pr = new PrintWriter(new FileOutputStream(cnf.getAbsolutePath()));
            pr.println(repo.getAbsolutePath());
            pr.close();
        }
        else{
            JDialog dia = new JDialog();
            dia.add(new JLabel("This is not a git repository -- use create repository to make it a git repository"));
            dia.setVisible(true);
            dia.setBounds(0,0,800,50);
        }
    }

    void changeBranch() throws IOException, InterruptedException {
        ProcessBuilder prr = new ProcessBuilder("git","checkout",ui.CurrentBranch.getSelectedItem().toString());
        prr.directory(repo);
        Process p = prr.start();
        p.waitFor();
        Scanner sc = new Scanner(p.getInputStream());
        while(sc.hasNextLine()){
            ui.term.append(sc.nextLine()+"\n");
        }
        sc.close();
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        Main m = new Main();
        if(m.REPOSITORIES.size()==0){
            m.selectRepo();
        }else{
            ProcessBuilder prr = new ProcessBuilder("git","branch");
            prr.directory(m.repo);
            Process p = prr.start();
            p.waitFor();
            m.Branch = "";
            Scanner sc = new Scanner(p.getInputStream());
            while(sc.hasNextLine()){
                m.BRANCHES.add(sc.nextLine().substring(2));
            }
            m.Branch = m.BRANCHES.get(0);
            m.ui.initRepoinfo(m.REPOSITORIES.get(0),m.Branch);
        }
    }
}

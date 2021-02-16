import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends JFrame{

    public ArrayList<String> REPOSITORIES;
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

    void selectRepo() throws FileNotFoundException {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.showDialog(this,"Select Repo");
        repo = fc.getSelectedFile();
        Branch = "Main";
        REPOSITORIES.add(repo.getAbsolutePath());
        this.ui.initRepoinfo(repo.getAbsolutePath(),Branch);
        PrintWriter pr = new PrintWriter(new FileOutputStream(cnf.getAbsolutePath()));
        pr.println(repo.getAbsolutePath());
        pr.close();
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        Main m = new Main();
        if(m.REPOSITORIES.size()==0){
            m.selectRepo();
        }else{
            m.Branch = "Main";
            m.ui.initRepoinfo(m.REPOSITORIES.get(0),m.Branch);
        }
    }
}

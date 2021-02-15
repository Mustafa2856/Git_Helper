import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends JFrame{

    public ArrayList<String> REPOSITORIES;
    UI ui;

    public Main() throws IOException, InterruptedException {
        initUser();
        initUI();
    }

    void initUser() throws IOException {
        File cnf = new File(System.getenv("HOME")+"/GITHELPER.cnf");
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
        setBounds(0,0,800,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ui = new UI();
        add(ui);
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        Main m = new Main();
    }
}

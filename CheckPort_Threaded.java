import java.io.*;
import java.util.*;
import java.net.*;
import java.text.SimpleDateFormat;

public class CheckPort_Threaded {

    public static void checker(String hostname) throws Exception {
        
        for (int i = 1; i <= 65535; i++) {
            Checker_Threaded name = new Checker_Threaded(hostname, i);
            name.start();
        }
        
    }

    public static void main(String[] args) throws Exception {
        ReadFile_Threaded.FileReading("Hosts_check.txt");
        WriteFile_Threaded.FileWriting(Checker_Threaded.out);
    }
}

class ReadFile_Threaded {
    public static void FileReading(String filepath) throws Exception {

        File file = new File(filepath);
        BufferedReader read = new BufferedReader(new FileReader(file));
        String st;
        while ((st = read.readLine()) != null) {
            CheckPort_Threaded.checker(st);
        }
        read.close();

    }
}

class WriteFile_Threaded {

    public static void FileWriting( String toWrite) throws Exception {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        File file = new File(timestamp+".txt");
        BufferedWriter write = new BufferedWriter(new FileWriter(file));
        write.write(toWrite);
        write.close();
    }

}

class Checker_Threaded extends Thread {

    String host = "";
    int port;
    public static String out="";

    public Checker_Threaded(String hostname, int i) {
        this.host = hostname;
        this.port = i;
    }


    @Override
    public void run() {
        Socket s = null;
        
        try {
            s = new Socket(host, port);
            out +=  port + "\n";
            System.out.println("aperta " + port);
        } catch (IOException e) {

        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                }
            }
        }
        try {
        } catch (Exception e) {
        }
    }
}
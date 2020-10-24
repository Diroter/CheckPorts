import java.io.*;
import java.util.*;
import java.net.*;


public class CheckPort {

    public static void checker(String hostname) {
        Socket s = null;
        for(int i = 1; i <= 65535; i -= -1){
            try{
                s = new Socket(hostname, i);
                System.out.println("port: " + i + " porta aperta");
            }catch(IOException e){
                System.out.println("port: " + i + " porta chiusa");
            } finally{
                if(s != null){
                    try{
                        s.close();
                    }catch (IOException e ){   
                    }
                }
            }  
        }
    }

    public static void main(String[] args) throws Exception {
        ReadFile.FileReading("Hosts_check.txt");
    }
}

class ReadFile{

    public static void FileReading(String filepath) throws Exception {

        File file = new File(filepath);
        BufferedReader read = new BufferedReader(new FileReader(file));
        String st;
        while((st = read.readLine()) != null){
            System.out.println(st);
        }

    }  
} 
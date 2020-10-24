import java.io.*;
import java.util.*;
import java.net.*;
import java.text.SimpleDateFormat;


public class CheckPort {

    public static void checker(String hostname) throws Exception {
        Socket s = null;
        String out = "";
        for(int i = 20; i <= 85; i++){
            try{
                s = new Socket(hostname, i);
                out += "porta n."+i+" aperta";
                System.out.println("aperta "+i);
            }catch(IOException e){

            } finally{
                if(s != null){
                    try{
                        s.close();
                    }catch (IOException e ){   
                    }
                }
            }  
        }
        WriteFile.FileWriting(hostname, out);

        
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
            CheckPort.checker(st);
        }
        read.close();

    }  
} 

class WriteFile{

    public static void FileWriting(String ip, String toWrite) throws Exception {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        File file = new File(ip + " " +timestamp);
        BufferedWriter write = new BufferedWriter(new FileWriter(file));
        write.write(toWrite);
        write.close();
    }



}
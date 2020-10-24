import java.io.*;
import java.util.*;
import java.net.*;
import java.text.SimpleDateFormat;


public class CheckPort_Threaded {

    public static void checker(String hostname) throws Exception {
        Socket s = null;
        String out = "";
        for(int i = 18; i <= 25; i -= -1){
            try{
                s = new Socket(hostname, i);
                out += "porta n."+i+" aperta";
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
        WriteFile_Threaded.FileWriting(hostname, out);

        
    }

    public static void main(String[] args) throws Exception {
        ReadFile_Threaded.FileReading("Hosts_check.txt");
    }
}

class ReadFile_Threaded{

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

class WriteFile_Threaded{

    public static void FileWriting(String ip, String toWrite) throws Exception {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        File file = new File(ip + " " +timestamp);
        BufferedWriter write = new BufferedWriter(new FileWriter(file));
        write.write(toWrite);
        write.close();
    }



}
package frc.robot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Logging {
    public static File file;
    public static BufferedWriter out;
    public static LinkedHashMap<String,Object> data = new LinkedHashMap<String,Object>();
    
    public static void open(String filename) {
        try {
        	/* file locations are:
        	 *   /U/			First USB drive
        	 *   /V/			Second USB drive
        	 *   /media/sda1	USB drive (which?)
        	 *   /home/lvuser	user space on RIO; access via FTP or SFTP:
        	 *   					roboRIO-TEAMNUMBER-frc.local
        	 *   					username lvuser
        	 *   					password (blank)
        	 */
            file = new File("/home/lvuser/logs/"+filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new BufferedWriter(new FileWriter(file));
        } catch (IOException e) {}
    }

    public static void close() {
        flush();
        writeKeys();
        try {
            if (out!=null) {
                out.close();
                out=null;
                data.clear();
            }
        } catch (IOException e) {}
    }

    private static void writeKeys() {
        try {
            if (out!=null) {
                String line=data.keySet().toString();
                line=line.replaceAll("[\\[\\],]","");
                line=line.replaceAll("null","");
                out.write("\r\n"+line+"\r\n");
            }
        } catch (IOException e) {}
    }

    private static void flush() {
        try {
            if (out!=null) {
                String line=data.values().toString();
                line=line.replaceAll("[\\[\\],]","");
                line=line.replaceAll("null","");
                out.write(line+"\r\n");
            }
        } catch (IOException e) {}
        // Set all values to null, but don't clear the keys:
        for(Map.Entry<String,Object> entry : data.entrySet()) {
            entry.setValue(null);
        }        
    }
    
    public static void log(String message) {
        log("Message", message);
    }
    
    public static void log(String key, Object value) {
        if (data.get(key)!=null) {    // dataset already has data in this key, so flush to file
            flush();
        }
        data.put(key, value);
    }
}
package csci310.servlets;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;


public class Configurations {
    public String url;

    public Configurations(){
        File configFile = new File("config.properties");
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            this.url = props.getProperty("NGROK_URL");

            reader.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
            System.out.println(ex);
        } catch (IOException ex) {
            // I/O error
            System.out.println("IO error");
        }
    }
}

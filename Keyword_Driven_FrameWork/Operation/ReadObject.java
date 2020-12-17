package Operation;

import java.io.*;
import java.util.Properties;

//https://docs.oracle.com/javase/7/docs/api/java/util/Properties.html
//Properties class has a method load(InputStream s) which means a interface InputStream is to be used instantiated
//by FileInputStream

public class ReadObject {

    Properties p = new Properties();

    public Properties getObjectRepository(){

        String filepath = System.getProperty("user.dir");
        String filename = "object.properties";
        String folder = "/Keyword_Driven_FrameWork";

        try {
            File file = new File(filepath + folder + "/" + filename);
            InputStream istream = new FileInputStream(file);

            //load all objects
            p.load(istream);

        }catch(FileNotFoundException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return p;
    }

}

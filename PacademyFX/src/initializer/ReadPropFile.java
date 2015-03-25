package initializer;

import sample.GreentechConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by anurag on 10/25/2014.
 */
public
class ReadPropFile {

    private static  Properties properties = new Properties();
    private static InputStream inputStream;
    private static
    GreentechConstants greentechConstants = GreentechConstants.getInstance();
    public static void init(){
        try{
      //      GreentechConstants.Global_Logger.log(Level.INFO,"Loading Property File From :- E:\\PAcademy\\PAcademy.properties");
            //get FileInputStream
            inputStream = new FileInputStream("PAcademy.properties");
            //Loading Properties File
            properties.load(inputStream);


            greentechConstants.sDriverName = properties.getProperty("driver");
   //         GreentechConstants.Global_Logger.log(Level.INFO,"DriverName:-"+greentechConstants.sDriverName);
            greentechConstants.sURL        = properties.getProperty("url");
   //         GreentechConstants.Global_Logger.log(Level.INFO,"URL:-"+greentechConstants.sURL);
            greentechConstants.sUserName   = properties.getProperty("username");
   //         GreentechConstants.Global_Logger.log(Level.INFO,"UserName:-"+greentechConstants.sUserName);
            greentechConstants.sPassword   = properties.getProperty("password");
     //       GreentechConstants.Global_Logger.log(Level.INFO,"Password:-"+greentechConstants.sPassword);
            greentechConstants.sLogFile   = properties.getProperty("logfile");
     //       GreentechConstants.Global_Logger.log(Level.INFO,"LogFile:-"+greentechConstants.sLogFile);
            greentechConstants.sLogSize   = properties.getProperty("logsize");
       //     GreentechConstants.Global_Logger.log(Level.INFO,"LogiSize:-"+greentechConstants.sLogSize);

            System.out.println("Property File Loaded...");
        }catch (IOException io){
            System.err.println("Property file Loading failed "+io.getMessage());
     //       GreentechConstants.Global_Logger.log(Level.INFO, "Property file Loading failed " + io.getMessage());
            io.printStackTrace();
        }catch (Exception ex){
            System.err.println("Property file Loading failed " + ex.getMessage());
       //     GreentechConstants.Global_Logger.log(Level.INFO, "Property file Loading failed " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}

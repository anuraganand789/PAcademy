package initializer;

import sample.GreentechConstants;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by anurag on 10/25/2014.
 */
public
class WriteLog {
    private final static Logger LOGGER = Logger.getLogger("Greentech");

    private static
    FileHandler fileHandlerTxt;

    /**
     * Initializes Logger properties...
     */
    private static void init(){
        File file =  new File(GreentechConstants.sLogFile);
        try {
            System.out.println("Log File :- "+ GreentechConstants.sLogFile+" --- Exists:-"+file.exists());

            if(!file.exists()){
                System.out.println("Log File :- "+ GreentechConstants.sLogFile);
                file.createNewFile();
            }

            fileHandlerTxt = new FileHandler(GreentechConstants.sLogFile,true);
            fileHandlerTxt.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandlerTxt);
            LOGGER.setLevel(Level.FINEST);
        } catch (IOException e) {
                 e.printStackTrace();
             }
    }

        public static final Logger getLogger(){
            if(fileHandlerTxt == null){
                init();
                //throw new IllegalAccessError("Call init() to initialize LOGGER");
            }
            return LOGGER;
        }

}

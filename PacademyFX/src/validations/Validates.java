package validations;

import DBHandler.DBActions;
import sample.EmpDetailsToDB;
import sample.GreentechConstants;

import java.sql.SQLException;

/**
 * Created by anurag on 10/5/2014.
 */

public
class Validates {
    private
    String userName;
    private
    String password;
    private final
    DBActions objDBAction = new DBActions();
    private final
    GreentechConstants objGtpConstant =  GreentechConstants.getInstance();

    private
    String getUserName() {
        return userName;
    }

    private
    void setUserName(String userName) {
        //Converting username to Uppercase, to remove Case restriction on users
        this.userName = userName.toUpperCase();
    }

    private
    String getPassword() {
        return password;
    }

    private
    void setPassword(String password) {
        this.password = password;
    }

   public boolean validateLogin(String userName,String password){

       boolean returnVal = false;
       setUserName(userName);
       setPassword(password);

       //Connecting to Database
       if (null == GreentechConstants.gtpConnectionObj) {
           try {

               objDBAction.openConnection();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
           //sELECTING Data and closing Database, If connection is made , it means gtpConnectionObj is not NUll :).
           //so call insert functions :)
           if (null != GreentechConstants.gtpConnectionObj) {

//VERIFYING CREDITS Details to Table
               try {
                    if(EmpDetailsToDB.checkValidCredits(getUserName(),getPassword(),objGtpConstant,objDBAction) >0){
                        returnVal=true;
                    }
               } catch (SQLException ex) {
                   ex.printStackTrace();
               }
           }


       return returnVal;
   }
}

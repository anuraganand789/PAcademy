package DBHandler;

import sample.GreentechConstants;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static sample.GreentechConstants.*;

/**
 * Created by anurag on 9/19/2014.
 * It handles all the database operations.
 */


public
class DBActions {

    //  private GreentechConstants objGreentechConst = GreentechConstants.getInstance();
    public final GreentechConstants gtpObj = GreentechConstants.getInstance();

    public synchronized
    void openConnection() throws SQLException {
        if (null != gtpConnectionObj) {
            System.out.println("Connection exists Already ...");
            return;
        }
        System.out.println("Connecting to PostgreSQl ...");
        try {
            Class.forName(sDriverName);
        } catch (ClassNotFoundException ex) {
            System.out.println("The requested Driver ["+sDriverName+"] \n could no be loaded");
            ex.printStackTrace();
            return;
        }

        gtpConnectionObj = DriverManager.getConnection(sURL,sUserName,
                                                    sPassword);
        gtpConnectionObj.setAutoCommit(true);
        Properties prop = new Properties();
        prop.setProperty("ApplicationName","Pacademy");
      //  prop.setProperty("ClientHostname","Pacademy-Desktop");

        gtpConnectionObj.setClientInfo(prop);
        if(null != gtpConnectionObj){
            System.out.println("The connection  created successfully...");
        }else{
            System.out.println("The connectionn failed");
        }
    }

    public synchronized
    void closeConnection(){
        try{
            System.out.println("Closing Connection");
            if(null != gtpConnectionObj) {
                gtpConnectionObj.close();
                gtpConnectionObj = null;
            }
            System.out.println("Connection Closed Successfully");
        }catch (SQLException ex){
            ex.printStackTrace();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public synchronized
    PreparedStatement prepareStmt(final String query, final String sTableName) throws SQLException{

        if(!(gtpObj.prepdStmtHMap.containsKey(sTableName)) ){

            System.out.println("Creating new PreparedStatement for :- \n Table :- "+
                              sTableName+" ,Query:- "+query +"\n " +
                              "Thread Instance is :- "+Thread.currentThread().getName());

            PreparedStatement prepLocalStmt = gtpConnectionObj.prepareStatement(query);
            gtpObj.prepdStmtHMap.put(sTableName,prepLocalStmt);
            return prepLocalStmt;
        }
        return gtpObj.prepdStmtHMap.get(sTableName);
    }

    public synchronized
    int executeInsert(String sTableName) throws SQLException{
        if(!(gtpObj.prepdStmtHMap.containsKey(sTableName))){
            System.out.println(new StringBuilder().append("The Specified Key does Not Exist.\n").append(
                    "First Create a PreparedStatement.").toString());
            return -1;
        }else{
            System.out.println("Executing insert for \n Table:- "+sTableName  +
                              " Thread Instance :- "+Thread.currentThread().getName());
            return gtpObj.prepdStmtHMap.get(sTableName).executeUpdate();
        }
    }

}

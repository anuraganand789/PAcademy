package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Hashtable;
import java.util.logging.Logger;

/**
 * Created by anurag on 9/20/2014.
 * It contains all the constants required by this app.
 */

public
class GreentechConstants {
    private static final GreentechConstants GREENTECH_CONSTANTS_OBJECT = new GreentechConstants();

    private
    GreentechConstants() {}

    public static final
    GreentechConstants getInstance() {
        return GREENTECH_CONSTANTS_OBJECT;
    }

    //driver name
    public static
    String sDriverName = "org.postgresql.Driver";
    //URL
    public static
    String sURL        = "jdbc:postgresql://127.0.0.1:5432/postgres";
    //Username
    public static
    String sUserName   = "postgres";
    //Password
    public static
    String sPassword   = "anurag";
    public static
    String sLogFile;
    public static
    String sLogSize;
    public static
    Logger Global_Logger;

    /*
     * Name of all the tables required
     */
    public final
    String sBasicDetailsTable     = "GTP_Emp_Basic_Details";
    public final
    String sEmpEduTable           = "GTP_Emp_Edu_Qualification";
    public final
    String sEmpFamilyTable        = "GTP_Emp_Family_Details";
    public final
    String sEmpMiscTable          = "GTP_Emp_Misc_Info";
    public final
    String sEmpProfProgTable      = "GTP_Emp_Prof_Prog_Attended";
    public final
    String sEmpSalaryDetailsTable = "GTP_Emp_Salary_Details";
    public final
    String sEmpWorkExpTable       = "GTP_Emp_Work_Exp";
    public final
    String sDailySalesTable       = "GTP_Daily_Sales";
    public final
    String sDistributorsTable     = "GTP_Distributors";
    public final
    String sRetailersTable        = "GTP_Retailers";
    public final
    String sSalesManagerTable     = "GTP_Sales_Manager";
    public final
    String sSalesOfficerTable     = "GTP_Sales_Officer";
    public final
    String sZoneDefTable          = "GTP_Zone_Def";
    public final
    String sSeqName               = "EmpKeyGenerator";
    public final
    String sUserInfo              = "UserInfoTable";

    //PAcademy
    public final
    String sMemberFormTable       = "GymMemForm";
    public final
    String sMemberFormUpdateTable       = "GymMemFormUpdate";
    public final
    String sMemberFormTableSel    = "GymMemFormSelect";
    public final
    String sGymPaymentTable       = "GymPaymentTable";
    public final
    String sGymPaymentTableDel    = "GymPaymentTableDelete";
    public final
    String sGymPaymentTableSelect = "GymPaymentTableSelect";
    public final
    String sGymDefaulterView      = "GymDefaulters";
    public final
    String sSearch                = "Search";
    public final
    String sDelete                = "DeleteMemeber";


    //Insert queries for all the tables
    public final
    String sBasicDetailsTableInsert = "INSERT INTO \"GTP_Emp_Basic_Details\"(" +
                                      "            \"PostApplied\", \"Name\", \"FatherName\", \"DOB\", \"Address\"," +
                                      "            \"State\", \"Pincode\", \"PhoneNumber\", \"Email\", \"MartialStatus\")" +
                                      "    VALUES (?, ?, ?, ?, ?," +
                                      "            ?, ?, ?, ?, ?)";
    public final
    String sEmpEduTableInsert       = "INSERT INTO \"GTP_Emp_Edu_Qualification\"(\n" +
                                      "            \"Employee_ID\", \"Standard\", \"Edu_Qualification_ID\", \"Board_Or_University\"," +
                                      "            \"Main_Subject\", \"Passing_year\", \"Percentage\", \"Question_1\", \"Question_2\")" +
                                      "    VALUES (?, ?, ?, ?," +
                                      "            ?, ?, ?, ?, ?)";
    public final
    String sEmpFamilyTableInsert    = "INSERT INTO \"GTP_Emp_Family_Details\"(" +
                                      "            \"Employee_ID\", \"Family_ID\", \"Relation\", \"Profession\", \"Age\", \"IsDependent\"," +
                                      "            \"Name\")" +
                                      "    VALUES (?, ?, ?, ?, ?, ?," +
                                      "            ?)";

    public final
    String sEmpMiscTableInsert = "INSERT INTO \"GTP_Emp_Misc_Info\"(\"Employee_ID\", " +
                                 "            \"Misc_ID\", \"CTC\", \"Strength\", \"Weakness\", \"Question_1\",\"Question_2\", " +
                                 "            \"Question_3\", \"Question_4\", \"Question_5\", \"Question_6\")" +
                                 "             VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?)";

    public final
    String sEmpProfProgTableInsert = "INSERT INTO \"GTP_Emp_Prof_Prog_Attended\"(" +
                                     "            \"Employee_ID\", \"Prof_Prog_ID\", \"Course_Name\", \"Course_Duration\"," +
                                     "            \"Institute_Name\", \"Institute_Place\", \"Year_Of_Passing\")" +
                                     "    VALUES (?, ?, ?, ?, " +
                                     "            ?, ?, ?)";

    public final
    String sEmpSalaryDetailsTableInsert = "INSERT INTO \"GTP_Emp_Salary_Details\"(" +
                                          "            \"Employee_ID\", \"Salary_ID\", \"DA\", \"Spl_Pay\", \"Personal_Pay\"," +
                                          "            \"Conveyance\", \"Other_Allow\", \"Tot_Amount\", \"Per_Annum\", \"Tot_Per_Annum\"," +
                                          "            \"Annual_Comp\", \"LTC\", \"Medical\", \"Bonus\", \"Other_Compo\", \"PF\", " +
                                          "            gratuity, \"Superannuation\", \"Gross\")" +
                                          "    VALUES (?, ?, ?, ?, ?, " +
                                          "            ?, ?, ?, ?, ?, " +
                                          "            ?, ?, ?, ?, ?, ?," +
                                          "            ?, ?, ?)";
    public final
    String sEmpWorkExpTableInsert       = "INSERT INTO \"GTP_Emp_Work_Exp\"(" +
                                          "            \"Employee_ID\", \"Work_Exp_ID\", \"Duration_From\", \"Duration_to\"," +
                                          "            \"Location\", \"Org_Name\", \"Org_Contact\", \"Emp_Business\", \"Designation\"," +
                                          "            \"Job_Desc\", \"Salary_Starting\", \"Salary_Leaving\", \"Reason_Leave\"," +
                                          "            \"Work_Duration\", \"Exp_Month\", \"Exp_Year\")" +
                                          "    VALUES (?, ?, ?, ?," +
                                          "            ?, ?, ?, ?, ?," +
                                          "            ?, ?, ?, ?," +
                                          "            ?, ?, ?)";

    public final
    String sDailySalesTableInsert = "INSERT INTO \"GTP_Daily_Sales\"(" +
                                    "            \"Sales_Manager_ID\", \"Sales_Officer_ID\", \"Distributor_Name\", \"Distributor_ID\", " +
                                    "            \"Market_Division\", \"Date\", \"Retailer_Name\", \"Retailer_ID\", \"With_SM\"," +
                                    "            \"Current_Stock\", \"Order_Value\", \"Receipt_Amount\", \"Prepared_By\"," +
                                    "            \"Verified_By\", \"Date_Only\", \"Month_Only\", \"Year_Only\")" +
                                    "    VALUES (?, ?, ?, ?," +
                                    "            ?, ?, ?, ?, ?," +
                                    "            ?, ?, ?, ?," +
                                    "            ?, ?, ?, ?)";

    public final
    String sDistributorsTableInsert = "INSERT INTO \"GTP_Distributors\"(" +
                                      "            \"Sales_Officer_ID\", \"Distributor-ID\", \"Date_Added\")" +
                                      "    VALUES (?, ?, ?)";

    public final
    String sRetailersTableInsert    = "INSERT INTO \"GTP_Retailers\"(" +
                                      "            \"Distributor_ID\", \"Retailer_ID\", \"Date_Added\")" +
                                      "    VALUES (?, ?, ?)";
    public final
    String sSalesManagerTableInsert = "INSERT INTO \"GTP_Sales_Manager\"(" +
                                      "            \"Sales_Manager_ID\", \"Date_Added\")" +
                                      "    VALUES (?, ?)";
    public final
    String sSalesOfficerTableInsert = "INSERT INTO \"GTP_Sales_Officer\"(" +
                                      "            \"Sales_Officer_ID\", \"Date_Added\", \"Sales_Manager_ID\")" +
                                      "    VALUES (?, ?, ?)";
    public final
    String sZoneDefTableInsert      = "INSERT INTO \"GTP_Zone_Def\"(" +
                                      "            \"State\", \"Zone\", \"IDS\")" +
                                      "    VALUES (?, ?, ?)";
    public final
    String sCurrSequenceValue       = "select 'GTP_'||currval('\"EmpKeyGenerator\"'::regclass)";

    public final
    String sMemberTableInsert = "INSERT INTO \"GymMemForm\"(" +
                                "            \"MembershipID\", \"Registration_Date\", \"Name\", \"Father_Name\", \"Date_Of_Birth\", " +
                                "            \"Gender\", \"Mobile_Number\", \"Residence_Number\", \"Address\", \"EMail\", " +
                                "            \"Package\")" +
                                "    VALUES (?, ?, ?, ?, ?," +
                                "            ?, ?, ?, ?, ?, " +
                                "            ?);";

    public final
    String sMemberTableUpdate = "UPDATE \"GymMemForm\" set " +
                                " \"Registration_Date\" = ?, \"Name\" = ?, " +
                                "\"Father_Name\" = ?, \"Date_Of_Birth\" = ?, " +
                                "  \"Gender\" = ?, \"Mobile_Number\" = ?, \"Residence_Number\" = ?, \"Address\" = ?," +
                                " \"EMail\" = ?, \"Package\" = ? where \"MembershipID\" = ?;";
    public final
    String sMemberTableSearch =
            "select \"MembershipID\" from \"GymMemForm\" where \"Name\" like '%?%' and \"MembershipID\" like '%?%' \n" +
            "and \"Mobile_Number\" like '%?%' and \"Residence_Number\" like '%?%'";

    public final String sGymPaymentTableInsert = "INSERT INTO \"GymPaymentTable\"(\n" +
                                                 "            \"MembershipID\", \"Package\", \"NextPaymentDate\", \"PaymentDate\"," +
                                                 "\"Comment\")\n" +
                                                 "    VALUES (?, ?, ?, ?,?)";
    public final String sGymPaymentTableDelete = "Delete from \"GymPaymentTable\" where  \"MembershipID\" = ?";


    public final String sGymPaymentNextDateSelect = "Select \"NextPaymentDate\" from \"GymPaymentTable\" where \"MembershipID\" = ?";

    public final String sGymDefaulterViewSelect =
            "SELECT \"MembershipID\",\"Name\", \"Address\", \"Mobile_Number\", \"Residence_Number\", " +
            "\"EMail\"  FROM \"GymDefaulters\"";

    public final StringBuilder sSearchSelect = new StringBuilder(
            "SELECT \"MembershipID\", \"Registration_Date\", \"Name\", \n" +
            "        \"Mobile_Number\", \"Residence_Number\", \"EMail\",\"Address\" \n" +
            "    FROM \"GymMemForm\" where \"Name\" like '%searchName%' and \"Mobile_Number\" like '%searchMobile%' and \"Residence_Number\" " +
            "like '%searchResidence%' and \"MembershipID\" like '%searchMembershipID%'\n"
    );

    public final StringBuilder sDeleteMember = new StringBuilder(
            "delete from \"GymMemForm\" where \"MembershipID\" = 'PutMemID'");

    public final
    String sUserInfoTableSelect = "SELECT count(\"Username\") FROM \"UserInfoTable\" where \"State\" = 'ACTIVE'" +
                                  " and upper(\"Username\") = ? and \"Password\" = ? ";

    //holds PreparedStatement Objects for Each table
    public final
    Hashtable<String, PreparedStatement> prepdStmtHMap = new Hashtable<>();

    //Connection Object
    public static
    Connection gtpConnectionObj;

}
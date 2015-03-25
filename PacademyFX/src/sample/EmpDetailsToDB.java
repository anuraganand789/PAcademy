package sample;

import DBHandler.DBActions;
import javafx.collections.ObservableList;
import sample.propertyClass.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Created by anurag on 9/30/2014.
 */
public
class EmpDetailsToDB {

//Professional Programme
    public static
    int insertProfProgDetails(ObservableList<GtpProfessionalProgramme> profProgContainer
            , GreentechConstants objGtpConstant, DBActions objDBAction, String employeeID
                             )  throws SQLException {
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sEmpProfProgTable)){
            objDBAction.prepareStmt(objGtpConstant.sEmpProfProgTableInsert,objGtpConstant.sEmpProfProgTable);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sEmpProfProgTable);
        //Getting Basic Details from Hashtable
        for (int i = 0; i < profProgContainer.size(); i++) {
            GtpProfessionalProgramme gtpProfessionalProgramme = profProgContainer.get(i);

            prepStmtLocal.setString(1,employeeID);
            prepStmtLocal.setString(2, "" + (i + 1));
            prepStmtLocal.setString(3, gtpProfessionalProgramme.courseNameProperty().getValue());
            prepStmtLocal.setString(4, gtpProfessionalProgramme.courseDurationProperty().getValue());
            prepStmtLocal.setString(5, gtpProfessionalProgramme.instituteNameProperty().getValue());
            prepStmtLocal.setString(6, gtpProfessionalProgramme.institutePlaceProperty().getValue());
            prepStmtLocal.setString(7, gtpProfessionalProgramme.yearOfPassingProperty().getValue());

            //adding to batch~
            prepStmtLocal.addBatch();
        }

        System.out.println("Inserting data to Professional details table ");
        GreentechConstants.Global_Logger.log(Level.INFO,"Inserting data to Professional details table ");
        GreentechConstants.Global_Logger.log(Level.INFO,prepStmtLocal.toString());
        int[] isValueAdded =prepStmtLocal.executeBatch();
        prepStmtLocal.clearBatch();
//        prepStmtLocal.close();

        return isValueAdded.length;        //basicDetailsContainer
    }
//Salary details
    public static
    int insertSalaryDetailsToTable(ObservableList<GtpSalaryBifurcation> salBifurcateContainer,
                                   GreentechConstants objGtpConstant, DBActions objDBAction
            , String employeeID
                                  )  throws SQLException {
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sEmpSalaryDetailsTable)){
            objDBAction.prepareStmt(objGtpConstant.sEmpSalaryDetailsTableInsert,objGtpConstant.sEmpSalaryDetailsTable);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sEmpSalaryDetailsTable);
        //Getting Basic Details from Hashtable
        GtpSalaryBifurcation gtpSalaryBifurcation = salBifurcateContainer.get(0);

        prepStmtLocal.setString(1,employeeID);
        prepStmtLocal.setString(2,""+1);
        prepStmtLocal.setString(3,gtpSalaryBifurcation.daProperty().getValue());
        prepStmtLocal.setString(4,gtpSalaryBifurcation.splDayProperty().getValue());
        prepStmtLocal.setString(5,gtpSalaryBifurcation.personalPayProperty().getValue());
        prepStmtLocal.setString(6,gtpSalaryBifurcation.conveyanceProperty().getValue());
        prepStmtLocal.setString(7,gtpSalaryBifurcation.otherAllowanceProperty().getValue());
        prepStmtLocal.setString(8,gtpSalaryBifurcation.totAmountProperty().getValue());
        prepStmtLocal.setString(9,gtpSalaryBifurcation.perAnnumProperty().getValue());
        prepStmtLocal.setString(10,gtpSalaryBifurcation.totPerAnnumProperty().getValue());
        prepStmtLocal.setString(11,"");
        prepStmtLocal.setString(12,gtpSalaryBifurcation.ltcProperty().getValue());
        prepStmtLocal.setString(13,gtpSalaryBifurcation.medicalProperty().getValue());
        prepStmtLocal.setString(14,gtpSalaryBifurcation.bonusProperty().getValue());
        prepStmtLocal.setString(15,gtpSalaryBifurcation.otherComponentsProperty().getValue());
        prepStmtLocal.setString(16, gtpSalaryBifurcation.pfProperty().getValue());
        prepStmtLocal.setString(17,gtpSalaryBifurcation.gratuityProperty().getValue());
        prepStmtLocal.setString(18,gtpSalaryBifurcation.superAnnuationProperty().getValue());
        prepStmtLocal.setString(19,gtpSalaryBifurcation.grossProperty().getValue());

        System.out.println("Inserting data to Salary Bifurcation details table ");
        GreentechConstants.Global_Logger.log(Level.INFO,"Inserting data to Salary Bifurcation details table ");
        GreentechConstants.Global_Logger.log(Level.INFO,prepStmtLocal.toString());
        int isValueAdded =prepStmtLocal.executeUpdate();
        prepStmtLocal.clearParameters();

        return isValueAdded;        //basicDetailsContainer
    }


//Work Experience
    public static
    int insertWorkExpDetailsToTable(ObservableList<GtpWorkExperience> workExpContainer,
                                    GreentechConstants objGtpConstant, DBActions objDBAction, String employeeID
                                   )  throws SQLException {
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sEmpWorkExpTable)){
            objDBAction.prepareStmt(objGtpConstant.sEmpWorkExpTableInsert,objGtpConstant.sEmpWorkExpTable);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sEmpWorkExpTable);
        //Getting Work Experience Details from Hashtable
        for (int i = 0; i < workExpContainer.size(); i++) {
            GtpWorkExperience gtpWorkExperience = workExpContainer.get(i);

            prepStmtLocal.setString(1,employeeID);
            prepStmtLocal.setString(2, "" + (i + 1));
            prepStmtLocal.setString(3, gtpWorkExperience.durationFromProperty().getValue());
            prepStmtLocal.setString(4, gtpWorkExperience.durationToProperty().getValue());
            prepStmtLocal.setString(5, gtpWorkExperience.locationProperty().getValue());
            prepStmtLocal.setString(6, gtpWorkExperience.orgNameProperty().getValue());
            prepStmtLocal.setString(7, gtpWorkExperience.orgContactProperty().getValue());
            prepStmtLocal.setString(8, gtpWorkExperience.empBusinessProperty().getValue());
            prepStmtLocal.setString(9, gtpWorkExperience.designationProperty().getValue());
            prepStmtLocal.setString(10, gtpWorkExperience.jobDescProperty().getValue());
            prepStmtLocal.setString(11, gtpWorkExperience.salaryStartingProperty().getValue());
            prepStmtLocal.setString(12, gtpWorkExperience.salaryLeavingProperty().getValue());
            prepStmtLocal.setString(13, gtpWorkExperience.reasonLeaveProperty().getValue());
            prepStmtLocal.setString(14, gtpWorkExperience.workDurationProperty().getValue());
            prepStmtLocal.setString(15, gtpWorkExperience.expMonthProperty().getValue());
            prepStmtLocal.setString(16, gtpWorkExperience.expYearProperty().getValue());
            //adding to batch
            prepStmtLocal.addBatch();
        }

        System.out.println("Inserting data to Work Experience details table ");
        int[] isValueAdded =prepStmtLocal.executeBatch();
        prepStmtLocal.clearBatch();
//        prepStmtLocal.close();

        return isValueAdded.length;       //basicDetailsContainer
    }
//Misc Details
    public static
    int insertMiscDetailsToTable(ObservableList<GtpMiscellaneous> miscContainer,
                                 GreentechConstants objGtpConstant, DBActions objDBAction, String employeeID
                                ) throws SQLException {
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sEmpMiscTable)){
            objDBAction.prepareStmt(objGtpConstant.sEmpMiscTableInsert,objGtpConstant.sEmpMiscTable);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sEmpMiscTable);
        //Getting Basic Details from Hashtable
        GtpMiscellaneous gtpMiscellaneous = miscContainer.get(0);

        prepStmtLocal.setString(1,employeeID);
        prepStmtLocal.setString(2,""+1);
        prepStmtLocal.setString(3,gtpMiscellaneous.ctcProperty().getValue());
        prepStmtLocal.setString(4,gtpMiscellaneous.strengthProperty().getValue());
        prepStmtLocal.setString(5,gtpMiscellaneous.weaknessProperty().getValue());
        prepStmtLocal.setString(6,gtpMiscellaneous.question1Property().getValue());
        prepStmtLocal.setString(7,gtpMiscellaneous.question2Property().getValue());
        prepStmtLocal.setString(8,gtpMiscellaneous.question3Property().getValue());
        prepStmtLocal.setString(9,gtpMiscellaneous.question4Property().getValue());
        prepStmtLocal.setString(10,gtpMiscellaneous.question5Property().getValue());
        prepStmtLocal.setString(11,gtpMiscellaneous.question6Property().getValue());

        System.out.println("Inserting data to Miscellaneous details table ");
        int isValueAdded =prepStmtLocal.executeUpdate();
        prepStmtLocal.clearParameters();

        return isValueAdded;        //basicDetailsContainer
    }
//Education Details
    public static
    int insertEduDetailsToTable(ObservableList<GtpEducationQualification> eduQualContainer,
                                GreentechConstants objGtpConstant, DBActions objDBAction, String employeeID
                               ) throws SQLException {
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sEmpEduTable)){
            objDBAction.prepareStmt(objGtpConstant.sEmpEduTableInsert,objGtpConstant.sEmpEduTable);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sEmpEduTable);
        //Getting Basic Details from Hashtable
        for (int i = 0; i < eduQualContainer.size(); i++) {
            GtpEducationQualification gtpEducationQualification = eduQualContainer.get(i);

            prepStmtLocal.setString(1,employeeID);
            prepStmtLocal.setString(2, gtpEducationQualification.standardProperty().getValue());
            prepStmtLocal.setString(3, "" + (i + 1));
            prepStmtLocal.setString(4, gtpEducationQualification.boardProperty().getValue());
            prepStmtLocal.setString(5, gtpEducationQualification.mainSubjectProperty().getValue());
            prepStmtLocal.setString(6, gtpEducationQualification.passingYearProperty().getValue());
            prepStmtLocal.setString(7, gtpEducationQualification.percentageProperty().getValue());
            prepStmtLocal.setString(8, "");
            prepStmtLocal.setString(9, "");
            //adding to batch
            prepStmtLocal.addBatch();
        }

        System.out.println("Inserting data to Education details table ");
        int[] isValueAdded =prepStmtLocal.executeBatch();
        prepStmtLocal.clearBatch();
//        prepStmtLocal.close();

        return isValueAdded.length;
        //basicDetailsContainer
    }
//Family Details
    public static
    int insertFamilyDetailsToTable(ObservableList<GtpFamilyDetails> familyContainer,
                                   GreentechConstants objGtpConstant, DBActions objDBAction, String employeeID
                                  ) throws SQLException {
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sEmpFamilyTable)){
            objDBAction.prepareStmt(objGtpConstant.sEmpFamilyTableInsert,objGtpConstant.sEmpFamilyTable);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sEmpFamilyTable);
        //Getting Basic Details from Hashtable
        for (int i = 0; i < familyContainer.size(); i++) {
            GtpFamilyDetails gtpFamilyDetails = familyContainer.get(i);

            prepStmtLocal.setString(1,employeeID);
            prepStmtLocal.setString(2, "" + (i + 1));
            prepStmtLocal.setString(3, gtpFamilyDetails.relationProperty().getValue());
            prepStmtLocal.setString(4, gtpFamilyDetails.professionProperty().getValue());
            prepStmtLocal.setString(5, gtpFamilyDetails.ageProperty().getValue());
            prepStmtLocal.setString(6, gtpFamilyDetails.isDependentProperty().getValue());
            prepStmtLocal.setString(7, gtpFamilyDetails.nameProperty().getValue());

            //adding to batch
            prepStmtLocal.addBatch();
        }

        System.out.println("Inserting data to Family details table ");
        int[] isValueAdded =prepStmtLocal.executeBatch();
        prepStmtLocal.clearBatch();
//        prepStmtLocal.close();

        return isValueAdded.length;
        //basicDetailsContainer
    }
//Basic Details
    public static
    int insertBasicDetailsToTable(ObservableList<GtpBasicDetails> basicDetailsContainer,
                                  GreentechConstants objGtpConstant, DBActions objDBAction
                                 ) throws SQLException {
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sBasicDetailsTable)){
            objDBAction.prepareStmt(objGtpConstant.sBasicDetailsTableInsert,objGtpConstant.sBasicDetailsTable);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sBasicDetailsTable);
        //Getting Basic Details from Hashtable
        GtpBasicDetails basicDetails1 = basicDetailsContainer.get(0);

        prepStmtLocal.setString(1, basicDetails1.postAppliedProperty().getValue());
        prepStmtLocal.setString(2,basicDetails1.nameProperty().getValue());
        prepStmtLocal.setString(3,basicDetails1.fatherNameProperty().getValue());
        prepStmtLocal.setString(4,basicDetails1.dateOfBirthProperty().getValue());
        prepStmtLocal.setString(5,basicDetails1.addressProperty().getValue());
        prepStmtLocal.setString(6,basicDetails1.stateProperty().getValue());
        prepStmtLocal.setString(7,basicDetails1.pincodeProperty().getValue());
        prepStmtLocal.setString(8,basicDetails1.phoneNumberProperty().getValue());
        prepStmtLocal.setString(9,basicDetails1.emailProperty().getValue());
        prepStmtLocal.setString(10,basicDetails1.martialStatusProperty().getValue());

        System.out.println("Inserting data to Basic details table ");
        int isValueAdded =prepStmtLocal.executeUpdate();

        System.out.println("Inserting data to Basic details boolean value "+isValueAdded);
       // prepStmtLocal.close();

        return isValueAdded;
        //basicDetailsContainer
    }


    //Misc Details
    public static
    int insertMemberDetailToTable(ObservableList<PAcadAddMemberProp> membershipFormCollection,
                                 GreentechConstants objGtpConstant, DBActions objDBAction
                                ) throws SQLException {
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sMemberFormTable)){
            objDBAction.prepareStmt(objGtpConstant.sMemberTableInsert,objGtpConstant.sMemberFormTable);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sMemberFormTable);
        //Getting Basic Details from Hashtable
        PAcadAddMemberProp pAcadAddMemberProp = membershipFormCollection.get(0);

        prepStmtLocal.setString(1,pAcadAddMemberProp.membershipNumberProperty().getValue());
        prepStmtLocal.setString(2,pAcadAddMemberProp.getRegistrationDate());
        prepStmtLocal.setString(3,pAcadAddMemberProp.nameProperty().getValue());
        prepStmtLocal.setString(4,pAcadAddMemberProp.fatherNameProperty().getValue());
        prepStmtLocal.setString(5,pAcadAddMemberProp.getDateOfBirth());
        prepStmtLocal.setString(6,pAcadAddMemberProp.genderProperty().getValue());
        prepStmtLocal.setString(7,pAcadAddMemberProp.mobileNumberProperty().getValue());
        prepStmtLocal.setString(8,pAcadAddMemberProp.residenceNumberProperty().getValue());
        prepStmtLocal.setString(9,pAcadAddMemberProp.addressProperty().getValue());
        prepStmtLocal.setString(10,pAcadAddMemberProp.emailProperty().getValue());
        prepStmtLocal.setString(11,pAcadAddMemberProp.gymPackageProperty().getValue());

        System.out.println("Inserting data to Miscellaneous details table ");
        GreentechConstants.Global_Logger.log(Level.INFO,"Inserting data to Member Table");
        GreentechConstants.Global_Logger.log(Level.INFO,prepStmtLocal.toString());
        int isValueAdded =prepStmtLocal.executeUpdate();
        prepStmtLocal.clearParameters();

        return isValueAdded;        //basicDetailsContainer
    }

    //Misc Details
    public static
    int updateMemberDetailTable(ObservableList<PAcadAddMemberProp> membershipFormCollection,
                                  GreentechConstants objGtpConstant, DBActions objDBAction
                                 ) throws SQLException {
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sMemberFormUpdateTable)){
            objDBAction.prepareStmt(objGtpConstant.sMemberTableUpdate,objGtpConstant.sMemberFormUpdateTable);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sMemberFormUpdateTable);
        //Getting Basic Details from Hashtable
        PAcadAddMemberProp pAcadAddMemberProp = membershipFormCollection.get(0);

        prepStmtLocal.setString(1,pAcadAddMemberProp.getRegistrationDate());//FRegistration Date
        prepStmtLocal.setString(2,pAcadAddMemberProp.nameProperty().getValue());
        prepStmtLocal.setString(3,pAcadAddMemberProp.fatherNameProperty().getValue());
        prepStmtLocal.setString(4,pAcadAddMemberProp.getDateOfBirth());
        prepStmtLocal.setString(5,pAcadAddMemberProp.genderProperty().getValue());
        prepStmtLocal.setString(6,pAcadAddMemberProp.mobileNumberProperty().getValue());
        prepStmtLocal.setString(7,pAcadAddMemberProp.residenceNumberProperty().getValue());
        prepStmtLocal.setString(8,pAcadAddMemberProp.addressProperty().getValue());
        prepStmtLocal.setString(9,pAcadAddMemberProp.emailProperty().getValue());
        prepStmtLocal.setString(10,pAcadAddMemberProp.gymPackageProperty().getValue());
        prepStmtLocal.setString(11,pAcadAddMemberProp.membershipNumberProperty().getValue());

        System.out.println("Updating data to Memeber form table...");
        GreentechConstants.Global_Logger.log(Level.INFO,"Updating data to Member Table");
        GreentechConstants.Global_Logger.log(Level.INFO,prepStmtLocal.toString());
        int isValueAdded =prepStmtLocal.executeUpdate();
        prepStmtLocal.clearParameters();

        return isValueAdded;        //basicDetailsContainer
    }


    //Misc Details
    public static
    int deletePaymentDetailFromTable(String membershipID,
                                   GreentechConstants objGtpConstant, DBActions objDBAction
                                  ) throws SQLException {
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sGymPaymentTableDel)){
            objDBAction.prepareStmt(objGtpConstant.sGymPaymentTableDelete,objGtpConstant.sGymPaymentTableDel);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sGymPaymentTableDel);
        //Getting Basic Details from Hashtable

        prepStmtLocal.setString(1,membershipID);


        System.out.println("deleting data to MakePaymentTable details table ");
        GreentechConstants.Global_Logger.log(Level.INFO,"Deleting payment details from MakePayment Table");
        GreentechConstants.Global_Logger.log(Level.INFO,prepStmtLocal.toString());
        int isValueAdded =prepStmtLocal.executeUpdate();
    //    prepStmtLocal.clearParameters();
        return isValueAdded;        //basicDetailsContainer
    }

    public static
    int insertPaymentDetailToTable(ObservableList<PAcadMakePayment> paymentCollection,
                                  GreentechConstants objGtpConstant, DBActions objDBAction
                                 ) throws SQLException {
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sGymPaymentTable)){
            objDBAction.prepareStmt(objGtpConstant.sGymPaymentTableInsert,objGtpConstant.sGymPaymentTable);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sGymPaymentTable);
        //Getting Basic Details from Hashtable
        PAcadMakePayment pAcadMakePayment = paymentCollection.get(0);

        prepStmtLocal.setString(1,pAcadMakePayment.membershipNumberProperty().getValue());
        prepStmtLocal.setString(2,pAcadMakePayment.gymPackageProperty().getValue());
        prepStmtLocal.setDate(3,pAcadMakePayment.getDateNextPayment());
        prepStmtLocal.setDate(4,pAcadMakePayment.getDateCurrent());
        prepStmtLocal.setString(5,pAcadMakePayment.commentProperty().getValue());

        System.out.println("Inserting data to MakePaymentTable details table ");
        GreentechConstants.Global_Logger.log(Level.INFO,"Inserting data to Make Payment table ");
        GreentechConstants.Global_Logger.log(Level.INFO,prepStmtLocal.toString());
        int isValueAdded =prepStmtLocal.executeUpdate();
        prepStmtLocal.clearParameters();

        return isValueAdded;        //basicDetailsContainer
    }

//Member have not paid Fees
    public static ResultSet getDefaulters(GreentechConstants objGtpConstant, DBActions objDBAction
                                         ) throws SQLException{
        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sGymDefaulterView)){
            objDBAction.prepareStmt(objGtpConstant.sGymDefaulterViewSelect,objGtpConstant.sGymDefaulterView);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sGymDefaulterView);
        GreentechConstants.Global_Logger.log(Level.INFO,"Getting Defaulter");
        GreentechConstants.Global_Logger.log(Level.INFO,prepStmtLocal.toString());
        return prepStmtLocal.executeQuery();
    }

    //Get Search Result
    public static ResultSet getSearchResult(String name,String mobileNumber,String residenceNumber
                                            ,String memID,GreentechConstants objGtpConstant, DBActions objDBAction
                                         ) throws SQLException{
      //  if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sSearch)){
            objDBAction.prepareStmt(objGtpConstant.sSearchSelect.toString().replaceFirst("searchName",name)
                                    .replaceFirst("searchMobile",mobileNumber).replaceFirst("searchResidence",residenceNumber)
                                    .replaceFirst("searchMembershipID",memID)
                    ,objGtpConstant.sSearch);
      //  }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sSearch);
        objGtpConstant.prepdStmtHMap.remove(objGtpConstant.sSearch);
        GreentechConstants.Global_Logger.log(Level.INFO,"Making Search call.. ");
        GreentechConstants.Global_Logger.log(Level.INFO,prepStmtLocal.toString());
        return prepStmtLocal.executeQuery();
    }

    //Get Search Result
    public static int deleteMember(String memberID,GreentechConstants objGtpConstant, DBActions objDBAction
                                           ) throws SQLException{
        //  if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sSearch)){
        objDBAction.prepareStmt(objGtpConstant.sDeleteMember.toString().replaceFirst("PutMemID",memberID)
                ,objGtpConstant.sDelete);
        //  }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sDelete);
        objGtpConstant.prepdStmtHMap.remove(objGtpConstant.sDelete);
        GreentechConstants.Global_Logger.log(Level.INFO,"Making sDelete call.. ");
        GreentechConstants.Global_Logger.log(Level.INFO,prepStmtLocal.toString());
       return prepStmtLocal.executeUpdate();
    }

    public static
    String getErrorMessage(String message){
        if(message.contains("violates unique constraint")){
            message = "%s is already present ";
        }

        return message;
    }

    public static
    int checkValidCredits(final String userName,final String password,final GreentechConstants objGtpConstant
            , DBActions objDBAction) throws SQLException{

        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sUserInfo)){
            objDBAction.prepareStmt(objGtpConstant.sUserInfoTableSelect,objGtpConstant.sUserInfo);
        }

        PreparedStatement prepStmtLocal = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sUserInfo);
        prepStmtLocal.setString(1,userName);
        prepStmtLocal.setString(2,password);
        ResultSet rs =  prepStmtLocal.executeQuery();
        rs.next();

        String userCount = rs.getString(1);
        System.out.println("Usercount is "+userCount);
        GreentechConstants.Global_Logger.log(Level.INFO,"Usercount is "+userCount+", UserName:-"+userName+
                                                        ", Password:-"+password);
        GreentechConstants.Global_Logger.log(Level.INFO,prepStmtLocal.toString());
        return Integer.parseInt(userCount);
    }
}

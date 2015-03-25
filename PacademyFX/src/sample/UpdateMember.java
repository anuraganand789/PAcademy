package sample;

import DBHandler.DBActions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sample.EmpDetailsToDB;
import sample.GreentechConstants;
import sample.Viewable;
import sample.propertyClass.PAcadAddMemberProp;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.logging.Level;

/**
 * Created by anurag on 10/5/2014.
 */
public
class UpdateMember implements Viewable{
    private final
    ObservableList<PAcadAddMemberProp> membershipFormCollection = FXCollections.observableArrayList();

    //Labels
    private final
    Label labelHeader  = new Label();
    private final
    Label labelMessage = new Label();

    //ComboBox
    private final
    ComboBox comboGender  = new ComboBox();
    private final
    ComboBox comboPackage = new ComboBox();

    //Date
    private final
    DatePicker datePicker    = new DatePicker();
    private final
    DatePicker datePickerDOB = new DatePicker();

    //Button
    private final
    Button btnUpdate = new Button();

    //TextFields
    private final
    TextField textName          = new TextField();
    private final
    TextField textFatherName    = new TextField();
    private final
    TextField textMembershipNum = new TextField();
    private final
    TextField textMobileNum     = new TextField();
    private final
    TextField textResidenceNum  = new TextField();
    private final
    TextField textEmail         = new TextField();

    //TextArea
    private final
    TextArea textAreaAddress = new TextArea();

    //layout
    private final
    VBox vBox       = new VBox();
    private final
    VBox vBoxParent = new VBox();

    //Objects
    private final
    GreentechConstants objGtpConstant = GreentechConstants.getInstance();
    private final
    DBActions          objDBAction    = new DBActions();

    public
    Parent getParent() {
        return vBoxParent;
    }

    private
    void addChildren() {
        vBoxParent.getChildren().addAll(getComponentsLayout());
        vBoxParent.setMaxSize(500, 500);
        vBoxParent.setPrefSize(350, 400);
    }

    @Override
    public
    Parent getView() {
        addChildren();
        return vBoxParent;
    }


    private
    void setComponentsID() {
        labelHeader.setId("MemberFormHeader");
        labelMessage.setId("MemberFormMessage");
/*------------------------*/
        comboGender.setId("MemberFormGender");
        comboPackage.setId("MemberFormPackage");
 /*----------------------------------------------------------------*/
        datePicker.setId("MemberFormDate");
        datePickerDOB.setId("MemberFormDOB");
/*----------------------------------------------------------------*/
        btnUpdate.setId("MemberFormUpdate");
/*---------------------------------------------------------------*/
        textName.setId("MemberFormName");
        textFatherName.setId("MemberFormFatherName");
        textMembershipNum.setId("MemberFormMemNum");
        textMobileNum.setId("MemberFormMobileNum");
        textResidenceNum.setId("MemberFormResidenceNum");
        textEmail.setId("MemberFormEmail");
/*----------------------------------------------------------------*/
        textAreaAddress.setId("MemberFormAddress");
    }

    private
    void setComponentsPromptText() {
        comboGender.setPromptText("Gender");
        comboPackage.setPromptText("Package");
 /*----------------------------------------------------------------*/
        datePicker.setPromptText("Date");
        datePickerDOB.setPromptText("DOB");
/*----------------------------------------------------------------*/
//        btnSubmit.setPromptText("Submit");
/*---------------------------------------------------------------*/
        textName.setPromptText("Name");
        textFatherName.setPromptText("Father`s Name");
        textMembershipNum.setPromptText("Mem Num");
        textMobileNum.setPromptText("Mobile Number");
        textResidenceNum.setPromptText("Residence Number");
        textEmail.setPromptText("Email");
/*----------------------------------------------------------------*/
        textAreaAddress.setPromptText("Address");
    }

    private void
    setTooltip(){
        datePicker.setTooltip(new Tooltip("Registration Date"));
        datePickerDOB.setTooltip(new Tooltip("Date Of Birth"));
    }
    private
    void setComponentsLayout() {


        labelHeader.setTextFill(Color.CHOCOLATE);
        labelHeader.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 35.0));
        labelHeader.setPadding(new Insets(10,0,20,20));
        //vBox.setMargin();

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(textMembershipNum,datePicker);

        HBox hBox2 = new HBox();
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(datePickerDOB,comboGender);
/*---------------------------------------------------------------*/
        HBox hBox1 = new HBox();
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(textMobileNum,textResidenceNum);

        HBox hBox3 = new HBox();
        hBox3.setSpacing(10);
        hBox3.getChildren().addAll(textEmail,comboPackage);
        //Calling functions to initialize Controls
        setComponentsID();
        setComponentsPromptText();
        setDefaultValues();
        setActions();
        setTooltip();

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(10);
        dropShadow.setOffsetY(10);
        dropShadow.setRadius(12);

        vBox.setEffect(dropShadow);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(labelHeader,hBox,textName,textFatherName, hBox2,hBox1,textAreaAddress,hBox3,btnUpdate,labelMessage);
    }

    private
    void setActions() {
        //btnSubmit.setOnAction(()->);

        btnUpdate.setOnAction((event)->{
            PAcadAddMemberProp data = getData();
            if(null != data) {
                membershipFormCollection.add(data);

                //Connecting to Database
                if (null == GreentechConstants.gtpConnectionObj) {
                    try {

                        objDBAction.openConnection();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


                //Inserting Data and closing Database, If connection is made , it means gtpConnectionObj is not NUll :).
                //so call insert functions :)
                if (null != GreentechConstants.gtpConnectionObj) {

//Inserting Details to Table
                    try {

                        if (EmpDetailsToDB.updateMemberDetailTable(membershipFormCollection, objGtpConstant,
                                                                   objDBAction) > 0) {
                            System.out.println("Data Successfully updated...");
                            labelMessage.setText(String.format("Data with MembeshipID:- %s & Name :- %s. updated successfully",textMembershipNum.getText()
                                                              ,textName.getText()));
                            labelMessage.setTextFill(Color.GREEN);

                            GreentechConstants.Global_Logger.log(Level.INFO,
                                                                 "Data Successfully updated to membership Form Table"+
                                                                 String.format("Data with MembeshipID:- %s & Name :- %s. Added successfully",textMembershipNum.getText()
                                                                 ,textName.getText()));
                        }else{
                            labelMessage.setText(String.format("Data with MembeshipID:- %s & Name :- %s. Update Failed",textMembershipNum.getText()
                                    ,textName.getText()));

                            GreentechConstants.Global_Logger.log(Level.SEVERE,
                                                                 String.format("Data with MembeshipID:- %s & Name :- %s. Update Failed",textMembershipNum.getText()
                                                                         ,textName.getText()));
                        }



                        membershipFormCollection.clear();
                        clearControlValues();
                    } catch (SQLException ex) {
                        //Clearing Last values
                        membershipFormCollection.clear();

                        //Provides Error Message
                        labelMessage.setText(String.format(EmpDetailsToDB.getErrorMessage(ex.getMessage()),textMembershipNum.getText()));
                        GreentechConstants.Global_Logger.log(Level.SEVERE,
                                                             String.format(EmpDetailsToDB.getErrorMessage(ex.getMessage()),
                                                             textMembershipNum.getText()));
                        textMembershipNum.clear();//Clearing Membership Number

                        labelMessage.setTextFill(Color.RED);
                        ex.printStackTrace();
                    }
                }
            }
            }
          );
    }

    private
    void clearControlValues() {
        textMembershipNum.clear();
        textName.clear();
        textFatherName.clear();
        textMobileNum.clear();
        textResidenceNum.clear();
        textEmail.clear();
        datePickerDOB.setValue(null);
        datePicker.setValue(LocalDate.now());
        comboGender.getSelectionModel().clearAndSelect(-1);
        comboPackage.getSelectionModel().clearAndSelect(-1);
    }

    private
    void setDefaultValues() {
        labelHeader.setText("MEMBERSHIP FORM");
        /*-----------Date-----------*/
        datePicker.setValue(LocalDate.now());
        datePickerDOB.setValue(LocalDate.now());
/*------------------------------------------------*/
        comboPackage.getItems().addAll("1 Month","3 Months","6 Months","1 Year");
        comboGender.getItems().addAll("Male","Female");

        btnUpdate.setText("Update");

    }

    private
    PAcadAddMemberProp getData(){
        String membershipNumber = textMembershipNum.getText();
        String name=textName.getText();
        String fatherName = textFatherName.getText();
        String gender = (String) comboGender.getValue();
        String mobileNumber = textMobileNum.getText();
        String residenceNumber = textResidenceNum.getText();
        String address = textAreaAddress.getText();
        String email = textEmail.getText();
        String gymPackage = (String) comboPackage.getValue();
        Date dateCurrent = Date.valueOf(datePicker.getValue());
        Date dateOfBirth = Date.valueOf(datePickerDOB.getValue());

        PAcadAddMemberProp objData = null;
        //   String[] sArrStrings = new String[]{name,relation,age,isDependent,profession};

        //removes null value
        Function<Object,String> removeNull = (it)-> (it == null || it.toString().isEmpty()) ? "n/a" : it.toString().trim();
        //  sArrStrings = (String[]) Arrays.asList(sArrStrings).stream().map(removeNull).collect(Collectors.toList()).toArray();

        membershipNumber = (String) removeNull.apply(membershipNumber);
        name = (String) removeNull.apply(name);
        fatherName = (String) removeNull.apply(fatherName);
        gender = (String) removeNull.apply(gender);
        residenceNumber = (String) removeNull.apply(residenceNumber);
        mobileNumber = (String) removeNull.apply(mobileNumber);
        address = (String) removeNull.apply(address);
        email = (String) removeNull.apply(email);
        gymPackage = (String) removeNull.apply(gymPackage);

        if(!    (name.equalsIgnoreCase("n/a") && membershipNumber.equalsIgnoreCase("n/a"))
                ) {

            objData = new PAcadAddMemberProp(membershipNumber,name,fatherName,gender,dateCurrent,mobileNumber,
                                              residenceNumber,address,email,gymPackage,dateOfBirth);

        }
        return objData;
    }

    protected
    VBox getComponentsLayout(){
        setComponentsLayout();
        return vBox;
    }

}

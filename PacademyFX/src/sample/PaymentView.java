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
import sample.propertyClass.PAcadMakePayment;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.function.Function;

/**
 * Created by anurag on 10/22/2014.
 */
public
class PaymentView implements Viewable {

    private final
    ObservableList<PAcadMakePayment> paymentCollection = FXCollections.observableArrayList();
    private
    PACKAGE enumPackage;
    private final
    Calendar calendar = Calendar.getInstance();
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
    Button btnSubmit = new Button();

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
    TextArea textAreaComment = new TextArea();

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
        btnSubmit.setId("MemberFormSubmit");
/*---------------------------------------------------------------*/
        textName.setId("MemberFormName");
        textFatherName.setId("MemberFormFatherName");
        textMembershipNum.setId("MemberFormMemNum");
        textMobileNum.setId("MemberFormMobileNum");
        textResidenceNum.setId("MemberFormResidenceNum");
        textEmail.setId("MemberFormEmail");
/*----------------------------------------------------------------*/
        textAreaComment.setId("Comments");
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
        textAreaComment.setPromptText("Comments");
    }

    private
    void setComponentsLayout() {


        labelHeader.setTextFill(Color.CHOCOLATE);
        labelHeader.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD, 35.0));
        labelHeader.setPadding(new Insets(10, 0, 20, 20));
        //vBox.setMargin();

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(textMembershipNum, datePicker);

        //Calling functions to initialize Controls
        setComponentsID();
        setComponentsPromptText();
        setDefaultValues();
        setActions();

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(10);
        dropShadow.setOffsetY(10);
        dropShadow.setRadius(12);

        vBox.setEffect(dropShadow);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(textMembershipNum,datePicker,comboPackage, textAreaComment,
                                  btnSubmit,labelMessage);
    }

    private
    void setActions() {
        //btnSubmit.setOnAction(()->);

        btnSubmit.setOnAction((event) -> {
                                  PAcadMakePayment data = getData();
                                  if (null != data) {
                                      paymentCollection.add(data);

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
                                              if(EmpDetailsToDB.deletePaymentDetailFromTable(
                                                      paymentCollection.get(0).membershipNumberProperty().getValue(),
                                                      objGtpConstant,
                                                      objDBAction ) >0)
                                              {
                                                  System.out.println("Member ID was already present,So deleted...");
                                              }

                                              if (EmpDetailsToDB.insertPaymentDetailToTable(paymentCollection,
                                                                                            objGtpConstant,
                                                                                            objDBAction) > 0) {
                                                  System.out.println(
                                                          "Payment Successfully Made");
                                                  labelMessage.setText(String.format(
                                                          "Payment Successfully Made for MembeshipID:- %s & Payment Date :- %s.",
                                                          textMembershipNum.getText()
                                                          , datePicker.getValue()));
                                                  labelMessage.setTextFill(Color.GREEN);
                                              } else {
                                                  labelMessage.setText(String.format(
                                                          " Payment Failed.Data with MembeshipID:- %s ",
                                                          textMembershipNum.getText()));
                                              }


                                              paymentCollection.clear();
                                              clearControlValues();
                                          } catch (SQLException ex) {
                                              //Clearing Last values
                                              paymentCollection.clear();

                                              //Provides Error Message
                                              labelMessage.setText(String.format(EmpDetailsToDB.getErrorMessage(
                                                      ex.getMessage()), textMembershipNum.getText()));
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
        comboPackage.getItems().addAll("1 Month", "3 Months", "6 Months", "1 Year");
        comboGender.getItems().addAll("Male", "Female");

        btnSubmit.setText("Submit");

    }

    private
    PAcadMakePayment getData() {
        String membershipNumber = textMembershipNum.getText();
        String comment = textAreaComment.getText();
        String gymPackage = (String) comboPackage.getValue();
        Date dateCurrent = Date.valueOf(datePicker.getValue());
        Date nextPaymentDate;
        PAcadMakePayment objData = null;

        //removes null value
        Function<Object, String> removeNull = (it) -> (it == null || it.toString().isEmpty()) ? "n/a" : it.toString();
        membershipNumber = (String) removeNull.apply(membershipNumber);
         gymPackage = (String) removeNull.apply(gymPackage);
         comment = (String) removeNull.apply(comment);


        if (!(gymPackage.equalsIgnoreCase("n/a") || membershipNumber.equalsIgnoreCase("n/a"))
                ) {
            calendar.setTime(dateCurrent);

            System.out.println("Gym Package is :- "+gymPackage);
           if(gymPackage.equalsIgnoreCase("1 Month")){
                //"1 Month","3 Months","6 Months","1 Year"
                calendar.add(Calendar.MONTH,PACKAGE.MONTH_1.getMonths());
            }
            if(gymPackage.equalsIgnoreCase("3 Months")){
                calendar.add(Calendar.MONTH,PACKAGE.MONTH_3.getMonths());
            }
            if(gymPackage.equalsIgnoreCase("6 Months")){
                calendar.add(Calendar.MONTH,PACKAGE.MONTH_6.getMonths());
            }
            if(gymPackage.equalsIgnoreCase("1 Year")){
                calendar.add(Calendar.MONTH,PACKAGE.YEAR_1.getMonths());
            }
            //Calculating nextPayment date
            nextPaymentDate = new Date(calendar.getTime().getTime());


            objData = new PAcadMakePayment(membershipNumber, dateCurrent,comment, gymPackage,nextPaymentDate);

        }
        return objData;
    }

    protected
    VBox getComponentsLayout() {
        setComponentsLayout();
        return vBox;
    }
}
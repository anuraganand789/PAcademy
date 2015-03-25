package sample;

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
import sample.propertyClass.GtpDailySalesReport;

import java.sql.Date;
import java.time.LocalDate;
import java.util.function.Function;

/**
 * Created by anurag on 10/2/2014.
 */
public
class DailySalesReport implements Viewable{
    private final
    ObservableList<GtpDailySalesReport> dailySalesReportsCollection = FXCollections.observableArrayList();

    //Labels
    private final
    Label labelHeader = new  Label();

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
    TextArea textAreaAddress = new TextArea();

    //layout
    private final
    VBox vBox = new VBox();
    private final
    VBox vBoxParent = new VBox();

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

    private
    void setComponentsLayout() {


        labelHeader.setTextFill(Color.CHOCOLATE);
        labelHeader.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BOLD,35.0));
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

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(10);
        dropShadow.setOffsetY(10);
        dropShadow.setRadius(12);
        vBox.setEffect(dropShadow);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(labelHeader,hBox,textName,textFatherName, hBox2,hBox1,textAreaAddress,hBox3,btnSubmit);
    }

    private
    void setActions() {
        //btnSubmit.setOnAction(()->);

        btnSubmit.setOnAction((event)->{
            GtpDailySalesReport data = getData();
            if(null != data){
                dailySalesReportsCollection.add(data);
                dailySalesReportsCollection.clear();
                clearControlValues();
            }
        });
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
/*------------------------------------------------*/
        comboPackage.getItems().addAll("1 Month","3 Months","6 Months","1 Year");
        comboGender.getItems().addAll("Male","Female");

        btnSubmit.setText("Submit");

    }

    private
    GtpDailySalesReport getData(){
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
        GtpDailySalesReport objData = null;
     //   String[] sArrStrings = new String[]{name,relation,age,isDependent,profession};

        //removes null value
        Function<Object,String> removeNull = (it)-> (it == null || it.toString().isEmpty()) ? "n/a" : it.toString();
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

            objData = new GtpDailySalesReport(membershipNumber,name,fatherName,gender,mobileNumber,
                                              residenceNumber,address,email,gymPackage,dateCurrent,dateOfBirth);

        }
        return objData;
    }

    protected
    VBox getComponentsLayout(){
        setComponentsLayout();
        return vBox;
    }

}

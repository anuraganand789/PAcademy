package sample;

import DBHandler.DBActions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import sample.propertyClass.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.Function;

/**
 * Created by anurag on 9/21/2014.
 */
public
class AccordionView implements Viewable {
    private final
    Accordion                                 accordion              = new Accordion();
    private final
    Button                                    submitButton           = new Button();
    private final
    VBox                                      vBox                   = new VBox();
    private
    ObservableList<GtpFamilyDetails>          familyContainer        = FXCollections.observableArrayList();
    private
    ObservableList<GtpBasicDetails>           basicDetailsContainer  = FXCollections.observableArrayList();
    private
    ObservableList<GtpEducationQualification> eduQualContainer       = FXCollections.observableArrayList();
    private
    ObservableList<GtpMiscellaneous>          miscContainer          = FXCollections.observableArrayList();
    private
    ObservableList<GtpProfessionalProgramme>  profProgContainer      = FXCollections.observableArrayList();
    private
    ObservableList<GtpSalaryBifurcation>      salBifurcateContainer  = FXCollections.observableArrayList();
    private
    ObservableList<GtpWorkExperience>         workExpContainer       = FXCollections.observableArrayList();
    private
    BasicDetails                              basicDetails           = new BasicDetails();
    private
    FamilyDetails                             familyDetails          = new FamilyDetails();
    private
    EducationQualification                    educationQualification = new EducationQualification();
    private
    ProfessionalProgramme                     professionalProgramme  = new ProfessionalProgramme();
    private
    SalaryBifurcation                         salaryBifurcation      = new SalaryBifurcation();
    private
    WorkExperience                            workExperience         = new WorkExperience();
    private
    Miscellaneous                             miscellaneous          = new Miscellaneous();
    private
    GreentechConstants                        objGtpConstant         = GreentechConstants.getInstance();
    private
    DBActions                                 objDBAction            = new DBActions();

    public
    Parent getParent() {
        return vBox;
    }

    @Override
    public
    Parent getView() {
        //Submit button operations
        setSubmitButtonText();
        setSubmitButtonAction();

        createAccordion();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(this.accordion, submitButton);
        return this.vBox;
    }

    /*It creates Submit Button*/
    private
    void setSubmitButtonText() { this.submitButton.setText("Submit"); }

    private
    void setSubmitButtonAction() {

        submitButton.setOnAction((event) -> {
//Adding data to collection Object
            basicDetails.addDataToContainer();
            salaryBifurcation.addDataToContainer();
            miscellaneous.addDataToContainer();

            //Connecting to Database
            if( null == GreentechConstants.gtpConnectionObj){
                try {
                    objDBAction.openConnection();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //Inserting Data and closing Database, If connection is made , it means gtpConnectionObj is not NUll :).
            //so call insert functions :)
            if( null != GreentechConstants.gtpConnectionObj){

//Inserting Details to Table
                try {
                    if(EmpDetailsToDB.insertBasicDetailsToTable(basicDetailsContainer, objGtpConstant, objDBAction) > 0){
                        System.out.println("Data Successfully inserted to Basic Details Table");

                        //Getting current Sequence Value
                        if(!objGtpConstant.prepdStmtHMap.containsKey(objGtpConstant.sSeqName)){
                            objDBAction.prepareStmt(objGtpConstant.sCurrSequenceValue,objGtpConstant.sSeqName);
                        }

                        ResultSet currValRS = objGtpConstant.prepdStmtHMap.get(objGtpConstant.sSeqName).executeQuery();

                        currValRS.next();
                        String sEmployeeID = currValRS.getString(1);
                        System.out.println("The current EmployeeID is :- "+sEmployeeID);

                        EmpDetailsToDB.insertFamilyDetailsToTable(familyContainer, objGtpConstant, objDBAction,
                                                                  sEmployeeID);
                        EmpDetailsToDB.insertEduDetailsToTable(eduQualContainer, objGtpConstant, objDBAction,
                                                               sEmployeeID);
                        EmpDetailsToDB.insertProfProgDetails(profProgContainer, objGtpConstant, objDBAction,
                                                             sEmployeeID);
                        EmpDetailsToDB.insertSalaryDetailsToTable(salBifurcateContainer, objGtpConstant, objDBAction,
                                                                  sEmployeeID);
                        EmpDetailsToDB.insertWorkExpDetailsToTable(workExpContainer, objGtpConstant, objDBAction,
                                                                   sEmployeeID);
                        EmpDetailsToDB.insertMiscDetailsToTable(miscContainer, objGtpConstant, objDBAction, sEmployeeID);

                    }else {
                        System.out.println("Failed to enter data to Basic Details Table");
                    }

                    //closing connection
                    objDBAction.closeConnection();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }

        });
    }



    /*It creates Accordion*/
    private
    void createAccordion() {
//Basic Details
        TitledPane tileBasicDetails = new TitledPane("Basic Details", basicDetails.getComponentLayout());
        accordion.getPanes().add(tileBasicDetails);
//Family Details
        tileBasicDetails = new TitledPane("Family Details",familyDetails.getComponentLayout());
   //     tileBasicDetails.setMaxHeight(100);
        accordion.getPanes().add(tileBasicDetails);
//Education Qualification
        tileBasicDetails = new TitledPane("Education Qualification",educationQualification.getComponentLayout());
        //tileBasicDetails.setMaxHeight(300);
        accordion.getPanes().add(tileBasicDetails);
//Professional Programme
        tileBasicDetails = new TitledPane("Professional Programme",professionalProgramme.getComponentLayout());
        tileBasicDetails.setMaxHeight(250);
        accordion.getPanes().add(tileBasicDetails);
//Work Experience
        tileBasicDetails = new TitledPane("Work Experience",workExperience.getComponentLayout());
        //tileBasicDetails.setMaxHeight(500);
        accordion.getPanes().add(tileBasicDetails);
//For Salary Bifurcation
        tileBasicDetails = new TitledPane("Salary Bifurcation",salaryBifurcation.getComponentLayout());
        //tileBasicDetails.setMaxHeight(300);
        accordion.getPanes().add(tileBasicDetails);
//For Miscellaneous Questions
        tileBasicDetails = new TitledPane("Miscellaneous",miscellaneous.getComponentLayout());
        //tileBasicDetails.setMaxHeight(300);
        accordion.getPanes().add(tileBasicDetails);

        accordion.setMinSize(250,250);
        accordion.setPrefSize(500,600);
        accordion.setMaxSize(600,700);
    }

/*View to Handle Basic Details Of Employee*/
    private
    class BasicDetails{
        public
        DatePicker       pickerDate            = new DatePicker();
        public
        ComboBox         comboBoxPostApplied   = new ComboBox();
        public
        TextField        txtFieldName          = new TextField();
        public
        TextField        txtFieldFatherName          = new TextField();
        public
        DatePicker       pickDateDOB           = new DatePicker();
        public
        TextArea         txtAreaAddress        = new TextArea();
        public
        TextField        txtFieldPhoneNumber   = new TextField();
        public
        ComboBox<String> comboBoxState         = new ComboBox<String>();
        public
        TextField        txtFieldPincode       = new TextField();
        public
        TextField        txtFieldEmail         = new TextField();
        public
        ComboBox<String> comboBoxMaritalStatus = new ComboBox<String>();
        public
        VBox             vBox                  = new VBox();

        void setComponentsID() {
            /*Setting ID for all the components*/
            pickerDate.setId("BasicDetailsDate");
            comboBoxPostApplied.setId("BasicDetailsPostApplied");
            txtFieldName.setId("BasicDetailsName");
            txtFieldFatherName.setId("BasicDetailsFatherName");
            pickDateDOB.setId("BasicDetailsDOB");
            txtAreaAddress.setId("BasicDetailsAddress");
            txtFieldPhoneNumber.setId("BasicDetailsPhoneNumber");
            comboBoxState.setId("BasicDetailsState");
            txtFieldPincode.setId("BasicDetailsPincode");
            txtFieldEmail.setId("BasicDetailsEmail");
            comboBoxMaritalStatus.setId("BasicDetailsMaritalStatus");
        }

        void setComponentsPromptText(){
             /*Setting Prompt Text for all the components*/
            pickerDate.setPromptText("Entry Date");
            comboBoxPostApplied.setPromptText("Post Applied");
            txtFieldName.setPromptText("Name");
            txtFieldFatherName.setPromptText("Father Name");
            pickDateDOB.setPromptText("Date Of Birth");
            txtAreaAddress.setPromptText("Address");
            txtFieldPhoneNumber.setPromptText("Phone Number");
            comboBoxState.setPromptText("State");
            txtFieldPincode.setPromptText("Pincode");
            txtFieldEmail.setPromptText("Email");
            comboBoxMaritalStatus.setPromptText("Marital Status");
        }

        void setComponentLayout(){
            /*Setting VBox properties*/
            vBox.setSpacing(10);
            //vBox.setPadding(10);

            setComponentsID();
            setComponentsPromptText();
            setDefaultValues();
            vBox.getChildren().addAll(pickerDate,comboBoxPostApplied,txtFieldName,txtFieldFatherName,pickDateDOB
                                      ,txtAreaAddress,comboBoxState,txtFieldPincode,txtFieldPhoneNumber
                                     ,txtFieldEmail,comboBoxMaritalStatus);

        }

        void setDefaultValues(){
            comboBoxPostApplied.getItems().addAll("Sales Manager","Sales Officer");
            comboBoxMaritalStatus.getItems().addAll("Married","Divorced","Unmarried");
            comboBoxState.getItems().addAll("Delhi");
        }

        VBox getComponentLayout(){
            setComponentLayout();
            return vBox;
        }

        void addDataToContainer(){
            basicDetailsContainer.add(new GtpBasicDetails((String)comboBoxPostApplied.getValue(),txtFieldName.getText(),txtFieldFatherName.getText(),
                                                          pickDateDOB.getValue().toString(),txtAreaAddress.getText(),(String)comboBoxState.getValue(),
                                                          txtFieldPincode.getText(),txtFieldPhoneNumber.getText(),txtFieldEmail.getText(),
                                                          (String)comboBoxMaritalStatus.getValue()));
        }

    }
/*View To Handle Family Details*/
    private
    class FamilyDetails{
    private
    TableColumn                      nameColumn          = new TableColumn();
    private
    TableColumn                      professionColumn    = new TableColumn();
    private
    TableColumn                      relationColumn      = new TableColumn();
    private
    TableColumn                      ageColumn           = new TableColumn();
    private
    TableColumn                      isDependentColumn   = new TableColumn();
    private
    TableView                        tblView             = new TableView();
    private
    TextField                        textFieldName       = new TextField();
    private
    TextField                        textFieldRelation   = new TextField();
    private
    TextField                        textFieldProfession = new TextField();
    private
    TextField                        textFieldAge        = new TextField();
    private
    ComboBox<String>                 comboBoxIsDependent = new ComboBox<>();
    private
    Button                           btnAdd              = new Button();
    private
    VBox                             vBox                = new VBox();


    private
    void setComponentsID() {
        textFieldName.setId("FamilyDetailsName");
        textFieldRelation.setId("FamilyDetailsRelation");
        textFieldProfession.setId("FamilyDetailsProfession");
        textFieldAge.setId("FamilyDetailsAge");
        comboBoxIsDependent.setId("FamilyDetailsIsDependent");
        btnAdd.setId("AddFamilyDetails");
        tblView.setId("FamilyDetailsTable");
    }

    private
    void setComponentsPromptText() {
        textFieldName.setPromptText("Member Name");
        textFieldRelation.setPromptText("Member Relation");
        textFieldProfession.setPromptText("Member Profession");
        textFieldAge.setPromptText("Member Age");
        comboBoxIsDependent.setPromptText("IsDependent");
    }

    private
    void setComponentsLayout() {
        vBox.setSpacing(10);
        //Calling funtions to initialize Controls
        setComponentsID();
        setComponentsPromptText();
        setDefaultValues();
        setActions();
        vBox.getChildren().addAll(textFieldName, textFieldRelation, textFieldProfession,
                                  textFieldAge, comboBoxIsDependent, btnAdd,tblView);
    }

    private
    void setDefaultValues() {
        comboBoxIsDependent.getItems().addAll("Yes", "No");
        nameColumn.setText("Name");
        relationColumn.setText("relation");
        ageColumn.setText("Age");
        professionColumn.setText("Profession");
        isDependentColumn.setText("Dependent");
        btnAdd.setText("Add");

        //Setting column default values
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        relationColumn.setCellValueFactory(new PropertyValueFactory("relation"));
        ageColumn.setCellValueFactory(new PropertyValueFactory("age"));
        professionColumn.setCellValueFactory(new PropertyValueFactory("profession"));
        isDependentColumn.setCellValueFactory(new PropertyValueFactory("isDependent"));

        //Binding Table View To Data
        tblView.setItems(familyContainer);
        tblView.getColumns().addAll(nameColumn,relationColumn,professionColumn,ageColumn,isDependentColumn);

    }

    protected
    VBox getComponentLayout() {
        setComponentsLayout();
        return vBox;
    }


    private
    GtpFamilyDetails getData(){
        String name=textFieldName.getText();
        String relation = textFieldRelation.getText();
        String age = textFieldAge.getText();
        String isDependent = comboBoxIsDependent.getValue();
        String profession = textFieldProfession.getText();
        GtpFamilyDetails objData = null;
        String[] sArrStrings = new String[]{name,relation,age,isDependent,profession};

        //removes null value
        Function<Object,String> removeNull = (it)-> (it == null || it.toString().isEmpty()) ? "n/a" : it.toString();
      //  sArrStrings = (String[]) Arrays.asList(sArrStrings).stream().map(removeNull).collect(Collectors.toList()).toArray();

        name = (String) removeNull.apply(name);
        relation = (String) removeNull.apply(relation);
        age = (String) removeNull.apply(age);
        isDependent = (String) removeNull.apply(isDependent);
        profession = (String) removeNull.apply(profession);

        if(!(name.equalsIgnoreCase("n/a") && relation.equalsIgnoreCase("n/a"))) {

            objData = new GtpFamilyDetails(name,relation,profession,age,isDependent);

        }
        return objData;
    }

    private
    void setActions() {
        btnAdd.setOnAction((event)-> {
            GtpFamilyDetails data = getData();
            if(null != data) {
                familyContainer.add(getData());
                clearControlValues();
            }
    });
    }

    private void clearControlValues(){
        textFieldAge.clear();
        textFieldProfession.clear();
        textFieldRelation.clear();
        textFieldName.clear();
        //comboBoxIsDependen
    }

}

    /*View to Handle Education Qualification*/
    private
    class EducationQualification {
        private
        TableColumn standardColumn       = new TableColumn();
        private
        TableColumn boardColumn          = new TableColumn();
        private
        TableColumn mainSubjectColumn    = new TableColumn();
        private
        TableColumn passingYearColumn    = new TableColumn();
        private
        TableColumn percentageColumn     = new TableColumn();
        private
        TableView   tblView              = new TableView();
        private
        TextField   textFieldStandard    = new TextField();
        private
        TextField   textFieldUniversity  = new TextField();
        private
        TextField   textFieldMainSubject = new TextField();
        private
        TextField   textFieldPassingYear = new TextField();
        private
        TextField   textFieldPercentage  = new TextField();
        private
        VBox        vBox                 = new VBox();
        private
        Button      btnAdd               = new Button();

        private
        void setComponentsID() {
            textFieldStandard.setId("EduStandard");
            textFieldUniversity.setId("EduUniversity");
            textFieldMainSubject.setId("EduMainSubject");
            textFieldPassingYear.setId("EduPassingYear");
            textFieldPercentage.setId("EduPercentage");

            tblView.setId("EduQualTableView");
        }

        private
        void setComponentsPromptText() {
            textFieldStandard.setPromptText("Standard");
            textFieldUniversity.setPromptText("Board/University");
            textFieldMainSubject.setPromptText("Main Subject");
            textFieldPassingYear.setPromptText("Passing Year");
            textFieldPercentage.setPromptText("Percentage");
        }

        private
        void setComponentsLayout() {

            vBox.setSpacing(10);

            //Calling funtions to initialize Controls
            setComponentsID();
            setComponentsPromptText();
            setDefaultValues();
            setActions();

            vBox.getChildren().addAll(textFieldStandard, textFieldUniversity, textFieldMainSubject,
                                      textFieldPassingYear, textFieldPercentage,btnAdd,tblView);
        }

        private
        void setDefaultValues() {
            //No control For Default Values
            btnAdd.setText("Add");
            boardColumn.setText("Board");
            standardColumn.setText("Standard");
            mainSubjectColumn.setText("Subject");
            passingYearColumn.setText("Passing Year");
            percentageColumn.setText("Percentage");

            //Setting colums default value
            boardColumn.setCellValueFactory(new PropertyValueFactory<>("board"));
            standardColumn.setCellValueFactory(new PropertyValueFactory<>("standard"));
            mainSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("mainSubject"));
            passingYearColumn.setCellValueFactory(new PropertyValueFactory<>("passingYear"));
            percentageColumn.setCellValueFactory(new PropertyValueFactory<>("percentage"));

            //Binding TableView
            tblView.setItems(eduQualContainer);
            tblView.getColumns().addAll(boardColumn,standardColumn,mainSubjectColumn,passingYearColumn
                    ,percentageColumn);
        }

        protected
        VBox getComponentLayout() {
            setComponentsLayout();
            return vBox;
        }

        private
        GtpEducationQualification getData(){
            String standard = textFieldStandard.getText();
            String board = textFieldUniversity.getText();
            String subject = textFieldMainSubject.getText();
            String percentage = textFieldPercentage.getText();
            String passingYear = textFieldPassingYear.getText();
            GtpEducationQualification objData = null;
          //  String[] sArrStrings = new String[]{standard,board,subject,isDependent,profession};

            //removes null value
            Function<Object,String> removeNull = (it)-> (it == null || it.toString().isEmpty()) ? "n/a" : it.toString();
            //  sArrStrings = (String[]) Arrays.asList(sArrStrings).stream().map(removeNull).collect(Collectors.toList()).toArray();

            standard = (String) removeNull.apply(standard);
            board = (String) removeNull.apply(board);
            subject = (String) removeNull.apply(subject);
            percentage = (String) removeNull.apply(percentage);
            passingYear = (String) removeNull.apply(passingYear);
            passingYear = (String) removeNull.apply(passingYear);
            passingYear = (String) removeNull.apply(passingYear);

            if(!(standard.equalsIgnoreCase("n/a") && board.equalsIgnoreCase("n/a"))) {

                objData = new GtpEducationQualification(standard,board,subject,passingYear,percentage);

            }
            return objData;
        }
        private
        void setActions() {
            btnAdd.setOnAction((event)-> {
                GtpEducationQualification data = getData();
                if(null != data) {
                    eduQualContainer.add(getData());
                    clearControlValues();
                }
            });
        }

        private void clearControlValues(){
            textFieldPassingYear.clear();
            textFieldMainSubject.clear();
            textFieldPercentage.clear();
            textFieldUniversity.clear();
            textFieldStandard.clear();
            //comboBoxIsDependen
        }


    }

    /*View to Professional Programme Attended*/
    private
    class ProfessionalProgramme {
        private
        TableColumn courseNameColumn             = new TableColumn();
        private
        TableColumn courseDurationColumn             = new TableColumn();
        private
        TableColumn instituteNameColumn             = new TableColumn();
        private
        TableColumn institutePlaceColumn             = new TableColumn();
        private
        TableColumn passingYearColumn             = new TableColumn();
        private
        TableView   tblView            = new TableView();
        private
        TextField   textCourseName     = new TextField();
        private
        TextField   textCourseDuration = new TextField();
        private
        TextField   textInstituteName  = new TextField();
        private
        TextField   textInstitutePlace = new TextField();
        private
        TextField   textYearOfPassing  = new TextField();
        private
        TextField   textQuestionOne    = new TextField();
        private
        TextField   textQuestionTwo    = new TextField();
        private
        VBox        vBox               = new VBox();
        private
        Button      btnAdd             = new Button();

        private
        void setComponentsID() {
            textCourseName.setId("CourseName");
            textCourseDuration.setId("CourseDuration");
            textInstituteName.setId("InstituteName");
            textInstitutePlace.setId("InstituteDuration");
            textYearOfPassing.setId("YearOfPassing");
            textQuestionOne.setId("PursuingCourse");
            textQuestionTwo.setId("FutureInterest");
            tblView.setId("ProfProgTableView");
        }

        private
        void setComponentsPromptText() {
            textCourseName.setPromptText("Course Name");
            textCourseDuration.setPromptText("Course Duration");
            textInstituteName.setPromptText("Institute Name");
            textInstitutePlace.setPromptText("Institute Duration");
            textYearOfPassing.setPromptText("Year Of Passing");
            textQuestionOne.setPromptText("Pursuing any course currently ...");
            textQuestionTwo.setPromptText("Future Interest in higher study");
        }

        private
        void setComponentsLayout() {
            vBox.setSpacing(3);

            //Calling funtions to initialize Controls
            setComponentsID();
            setComponentsPromptText();
            setDefaultValues();
            setActions();

            //vBox.setMaxHeight(0);
            vBox.getChildren().addAll(textCourseName, textCourseDuration, textInstituteName,
                                      textInstitutePlace, textYearOfPassing,
                                      textQuestionOne, textQuestionTwo,btnAdd,tblView);
        }

        private
        void setDefaultValues() {
            btnAdd.setText("Add");

            courseNameColumn.setText("Course Name");
            courseDurationColumn.setText("Course Duration");
            instituteNameColumn.setText("Institute Name");
            institutePlaceColumn.setText("Institute Place");
            passingYearColumn.setText("Passing Year");

            //Binding columns to DataVariables
            courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
            courseDurationColumn.setCellValueFactory(new PropertyValueFactory<>("courseDuration"));
            instituteNameColumn.setCellValueFactory(new PropertyValueFactory<>("instituteName"));
            institutePlaceColumn.setCellValueFactory(new PropertyValueFactory<>("institutePlace"));
            passingYearColumn.setCellValueFactory(new PropertyValueFactory<>("yearOfPassing"));

            //Binding data to TableView
            tblView.setItems(profProgContainer);
            tblView.getColumns().addAll(courseNameColumn,courseDurationColumn,instituteNameColumn,
                                        institutePlaceColumn,passingYearColumn);
        }

        protected
        VBox getComponentLayout() {
            setComponentsLayout();
            return vBox;
        }

        private
        GtpProfessionalProgramme getData() {
            String courseName = textCourseName.getText();
            String courseDuration = textCourseDuration.getText();
            String instituteName = textInstituteName.getText();
            String institutePlace = textInstitutePlace.getText();
            String passingYear = textYearOfPassing.getText();
            GtpProfessionalProgramme objData = null;
            //  String[] sArrStrings = new String[]{standard,board,subject,isDependent,profession};

            //removes null value
            Function<Object, String> removeNull = (it) -> (it == null || it.toString().isEmpty()) ? "n/a"
                    : it.toString();
            //  sArrStrings = (String[]) Arrays.asList(sArrStrings).stream().map(removeNull).collect(Collectors.toList()).toArray();

            courseName = (String) removeNull.apply(courseName);
            courseDuration = (String) removeNull.apply(courseDuration);
            instituteName = (String) removeNull.apply(instituteName);
            institutePlace = (String) removeNull.apply(institutePlace);
            passingYear = (String) removeNull.apply(passingYear);

            if (!(courseName.equalsIgnoreCase("n/a") && courseDuration.equalsIgnoreCase("n/a"))) {

                objData = new GtpProfessionalProgramme(courseName, courseDuration, instituteName, institutePlace,
                                                       passingYear);

            }
            return objData;
        }

        private
        void setActions() {

            btnAdd.setOnAction((event) -> {
                GtpProfessionalProgramme data = getData();
                if (null != data) {
                    profProgContainer.add(data);
                    clearControlValues();
                }
            });
        }

        private
        void clearControlValues() {
            textCourseName.clear();
            textCourseDuration.clear();
            textInstituteName.clear();
            textInstitutePlace.clear();
            textYearOfPassing.clear();
            textQuestionOne.clear();
            textQuestionTwo.clear();
            //comboBoxIsDependen
        }

    }

    /*View to Professional Programme Attended*/
    private
    class WorkExperience {
        private
        TableColumn columnOrgName      = new TableColumn();
        private
        TableColumn columnOrgcontact   = new TableColumn();
        private
        TableColumn columnEmpBusiness   = new TableColumn();
        private
        TableColumn columnDesignation   = new TableColumn();
        private
        TableView   tblView            = new TableView();
        private
        DatePicker  dateDurationFrom   = new DatePicker();
        private
        DatePicker  dateDurationTo     = new DatePicker();
        private
        TextField   textLocation       = new TextField();
        private
        TextField   textOrgName        = new TextField();
        private
        TextField   textOrgContact     = new TextField();
        private
        TextField   textEmpBusiness    = new TextField();
        private
        TextField   textDesignation    = new TextField();
        private
        TextField   textJobDesc        = new TextField();
        private
        TextField   textSalaryStart    = new TextField();
        private
        TextField   textSalaryEnd      = new TextField();
        private
        TextField   textLeaveReason    = new TextField();
        private
        TextField   textDurationWorked = new TextField();
        private
        VBox        vBox               = new VBox();
        private
        HBox        hBox               = new HBox();
        private
        HBox        hBox2              = new HBox();
        private
        Button      btnAdd             = new Button();

        private
        void setComponentsID() {
            dateDurationFrom.setId("DurationFrom");
            dateDurationTo.setId("DurationTo");
            textLocation.setId("Location");
            textOrgName.setId("OrgName");
            textOrgContact.setId("OrgContact");
            textEmpBusiness.setId("EmpBusiness");
            textDesignation.setId("Designation");
            textJobDesc.setId("JobDesc");
            textSalaryStart.setId("SalaryStart");
            textSalaryEnd.setId("SalaryEnd");
            textLeaveReason.setId("LeaveReason");
            textDurationWorked.setId("DurationWorked");
            tblView.setId("WorkExpTableView");
        }

        private
        void setComponentsPromptText() {
            dateDurationFrom.setPromptText("Duration From");
            dateDurationTo.setPromptText("Duration To");
            textLocation.setPromptText("Location");
            textOrgName.setPromptText("Organisation Name");
            textOrgContact.setPromptText("Organisation Contact");
            textEmpBusiness.setPromptText("Employee Business");
            textDesignation.setPromptText("Designation");
            textJobDesc.setPromptText("Job Description");
            textSalaryStart.setPromptText("Salary Start");
            textSalaryEnd.setPromptText("Salary End");
            textLeaveReason.setPromptText("Leave Reason");
            textDurationWorked.setPromptText("DurationWorked");
        }

        private
        void setComponentsLayout() {
            hBox = new HBox();
            hBox.getChildren().addAll(dateDurationFrom, dateDurationTo);
            hBox.setSpacing(40);
            hBox.setPadding(new Insets(2, 10, 2, 10));

            hBox2 = new HBox();
            hBox2.getChildren().addAll(textSalaryStart, textSalaryEnd);
            hBox2.setSpacing(40);
            hBox2.setPadding(new Insets(2, 10, 2, 10));

            vBox.setSpacing(3);

            //Calling funtions to initialize Controls
            setComponentsID();
            setComponentsPromptText();
            setDefaultValues();
            setActions();

            vBox.getChildren().addAll(hBox, textLocation, textOrgName, textOrgContact,
                                      textEmpBusiness, textDesignation, textJobDesc, hBox2,
                                      textLeaveReason, textDurationWorked, btnAdd,tblView);
        }

        private
        void setDefaultValues() {
            //No control For Default Values
            btnAdd.setText("Add");

            //Column Values
            columnDesignation.setText("Designation");
            columnEmpBusiness.setText("Employee Business");
            columnOrgcontact.setText("Organisation Contact");
            columnOrgName.setText("Organisation Name");

            //Setting Column value Fatctory
            columnDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
            columnEmpBusiness.setCellValueFactory(new PropertyValueFactory<>("empBusiness"));
            columnOrgName.setCellValueFactory(new PropertyValueFactory<>("orgName"));
            columnOrgcontact.setCellValueFactory(new PropertyValueFactory<>("orgContact"));

            //Binding Data to Table Grid
            tblView.setItems(workExpContainer);

            //Adding all columns to Table View
            tblView.getColumns().addAll(columnOrgName,columnOrgcontact,columnDesignation,columnEmpBusiness);
        }

        protected
        VBox getComponentLayout() {
            setComponentsLayout();
            return vBox;
        }

        private
        GtpWorkExperience getData() {
            LocalDate dateFrom = dateDurationFrom.getValue();
            LocalDate dateTo = dateDurationTo.getValue();

            String durationFrom = dateFrom.toString();
            String durationTo = dateTo.toString();
            String location = textLocation.getText();
            String orgContact = textOrgContact.getText();
            String orgName = textOrgName.getText();
            String empBusiness = textEmpBusiness.getText();
            String designation = textDesignation.getText();
            String jobDesc = textJobDesc.getText();
            String salaryStarting = textSalaryStart.getText();
            String salaryLeaving = textSalaryEnd.getText();
            String reasonLeave = textLeaveReason.getText();
            String workDuration = textDurationWorked.getText();
            String expMonth = "Later";
            String expYear = "Later";
            GtpWorkExperience objData = null;

            //removes null value
            Function<Object, String> removeNull = (it) -> (it == null || it.toString().isEmpty()) ? "n/a"
                    : it.toString();

            durationFrom = (String) removeNull.apply(durationFrom);
            durationTo = (String) removeNull.apply(durationTo);
            location = (String) removeNull.apply(location);
            orgContact = (String) removeNull.apply(orgContact);
            orgName = (String) removeNull.apply(orgName);
            empBusiness = (String) removeNull.apply(empBusiness);
            designation = (String) removeNull.apply(designation);
            jobDesc = (String) removeNull.apply(jobDesc);
            salaryStarting = (String) removeNull.apply(salaryStarting);
            salaryLeaving = (String) removeNull.apply(salaryLeaving);
            reasonLeave = (String) removeNull.apply(reasonLeave);
            workDuration = (String) removeNull.apply(workDuration);
            expMonth = (String) removeNull.apply(expMonth);
            expYear = (String) removeNull.apply(expYear);

            if (!(durationFrom.equalsIgnoreCase("n/a") && durationTo.equalsIgnoreCase("n/a") &&
                  location.equalsIgnoreCase("n/a") && orgName.equalsIgnoreCase("n/a"))
                    ) {

                objData = new GtpWorkExperience(durationFrom, durationTo, location, orgContact, orgName,
                                                empBusiness, designation, jobDesc, salaryStarting,
                                                salaryLeaving, reasonLeave, workDuration, expMonth,
                                                expYear);

            }
            return objData;
        }

        private
        void setActions() {

            btnAdd.setOnAction((event) -> {
                GtpWorkExperience data = getData();
                if (null != data) {
                    workExpContainer.add(data);
                    clearControlValues();
                }
            });
        }

        private
        void clearControlValues() {
            //dateDurationFrom.setV;
            //dateDurationTo.clear();
            textDurationWorked.clear();
            textLeaveReason.clear();
            textSalaryEnd.clear();
            textSalaryStart.clear();
            textDesignation.clear();
            textEmpBusiness.clear();
            textJobDesc.clear();
            textLocation.clear();
            textOrgContact.clear();
            textOrgName.clear();;
            //comboBoxIsDependen
        }

    }

    /*View to Professional Programme Attended*/
    private
    class SalaryBifurcation {
        private
        TableColumn tblCol                    = new TableColumn();
        private
        TableView   tblView                   = new TableView();
        private
        Text        textLastSalaryBifurcation = new Text();
        private
        Text        textAnnualizedComponents  = new Text();
        private
        TextField   textFieldBasic            = new TextField();
        private
        TextField   textFieldDA               = new TextField();
        private
        TextField   textFieldSplPay           = new TextField();
        private
        TextField   textFieldPersonalPay      = new TextField();
        private
        TextField   textFieldConveyance       = new TextField();
        private
        TextField   textFieldOtherAllowance   = new TextField();
        private
        TextField   textFieldTotalAmount      = new TextField();
        private
        TextField   textFieldPerAnnum         = new TextField();
        private
        TextField   textFieldTotalPerAnnum    = new TextField();
        private
        TextField   textFieldLTC              = new TextField();
        private
        TextField   textFieldMedical          = new TextField();
        private
        TextField   textFieldBonus            = new TextField();
        private
        TextField   textFieldOtherComponents  = new TextField();
        private
        TextField   textFieldPF               = new TextField();
        private
        TextField   textFieldGratuity         = new TextField();
        private
        TextField   textFieldSuperannuation   = new TextField();
        private
        TextField   textFieldGross            = new TextField();
        private
        VBox        vBox                      = new VBox();
        private
        HBox        hBox                      = new HBox();

        private
        void setComponentsID() {
            textLastSalaryBifurcation.setId("LastBiFur");
            textAnnualizedComponents.setId("Annualized Components");
            textFieldBasic.setId("BasicSalary");
            textFieldDA.setId("DA");
            textFieldSplPay.setId("SplPay");
            textFieldPersonalPay.setId("Personal Pay");
            textFieldConveyance.setId("Conveyance");
            textFieldOtherAllowance.setId("OtherAllowance");
            textFieldTotalAmount.setId("TotalAmount");
            textFieldPerAnnum.setId("PerAnnum");
            textFieldTotalPerAnnum.setId("TotalPerAnnum");
            textFieldLTC.setId("LTC");
            textFieldMedical.setId("Medical");
            textFieldBonus.setId("Bonus");
            textFieldOtherComponents.setId("OtherCompo");
            textFieldPF.setId("PF");
            textFieldGratuity.setId("Gratuity");
            textFieldSuperannuation.setId("Superannuation");
            textFieldGross.setId("Gross");
        }

        private
        void setComponentsPromptText() {
            //   textLastSalaryBifurcation.setId("LastBiFur");   textAnnualizedComponents.setId("Annualized Components");
            textFieldBasic.setPromptText("BasicSalary");
            textFieldDA.setPromptText("DA");
            textFieldSplPay.setPromptText("SplPay");
            textFieldPersonalPay.setPromptText("Personal Pay");
            textFieldConveyance.setPromptText("Conveyance");
            textFieldOtherAllowance.setPromptText("OtherAllowance");
            textFieldTotalAmount.setPromptText("TotalAmount");
            textFieldPerAnnum.setPromptText("PerAnnum");
            textFieldTotalPerAnnum.setPromptText("TotalPerAnnum");

            textFieldLTC.setPromptText("LTC");
            textFieldMedical.setPromptText("Medical");
            textFieldBonus.setPromptText("Bonus");
            textFieldOtherComponents.setPromptText("OtherCompo");
            textFieldPF.setPromptText("PF");
            textFieldGratuity.setPromptText("Gratuity");
            textFieldSuperannuation.setPromptText("Superannuation");
            textFieldGross.setPromptText("Gross");
        }

        private
        void setComponentsLayout() {
            hBox = new HBox();
            hBox.getChildren().addAll(textLastSalaryBifurcation, textAnnualizedComponents);
            hBox.setSpacing(100);
            hBox.setPadding(new Insets(2, 10, 2, 10));

            vBox.getChildren().add(hBox);

            hBox = new HBox();
            hBox.getChildren().addAll(textFieldBasic, textFieldLTC);
            hBox.setSpacing(100);
            hBox.setPadding(new Insets(2, 10, 2, 10));

            vBox.getChildren().add(hBox);

            hBox = new HBox();
            hBox.getChildren().addAll(textFieldDA, textFieldMedical);
            hBox.setSpacing(100);
            hBox.setPadding(new Insets(2, 10, 2, 10));

            vBox.getChildren().add(hBox);

            hBox = new HBox();
            hBox.getChildren().addAll(textFieldPersonalPay, textFieldOtherComponents);
            hBox.setSpacing(100);
            hBox.setPadding(new Insets(2, 10, 2, 10));

            vBox.getChildren().add(hBox);


            hBox = new HBox();
            hBox.getChildren().addAll(textFieldConveyance, textFieldPF);
            hBox.setSpacing(100);
            hBox.setPadding(new Insets(2, 10, 2, 10));

            vBox.getChildren().add(hBox);

            hBox = new HBox();
            hBox.getChildren().addAll(textFieldOtherAllowance, textFieldGratuity);
            hBox.setSpacing(100);
            hBox.setPadding(new Insets(2, 10, 2, 10));

            vBox.getChildren().add(hBox);

            hBox = new HBox();
            hBox.getChildren().addAll(textFieldTotalAmount, textFieldSuperannuation);
            hBox.setSpacing(100);
            hBox.setPadding(new Insets(2, 10, 2, 10));

            vBox.getChildren().add(hBox);

            hBox = new HBox();
            hBox.getChildren().addAll(textFieldPerAnnum, textFieldGross);
            hBox.setSpacing(100);
            hBox.setPadding(new Insets(2, 10, 2, 10));

            vBox.getChildren().add(hBox);

            vBox.getChildren().add(textFieldTotalPerAnnum);


            hBox = new HBox();
            hBox.getChildren().addAll(textFieldSplPay, textFieldBonus);
            hBox.setSpacing(100);
            hBox.setPadding(new Insets(2, 10, 2, 10));

            vBox.getChildren().add(hBox);

            //vBox.setMaxHeight(300);
            vBox.setSpacing(2);

            //Calling functions to initialize Controls
            setComponentsID();
            setComponentsPromptText();
            setDefaultValues();

        }

        private
        void setDefaultValues() {
            textAnnualizedComponents.setText("Annualized Components");
            textLastSalaryBifurcation.setText("Last Salary Bifurcation");
        }

        protected
        VBox getComponentLayout() {
            setComponentsLayout();
            return vBox;
        }
        void addDataToContainer(){
            salBifurcateContainer.add(new GtpSalaryBifurcation(textFieldDA.getText(),textFieldSplPay.getText(),textFieldPersonalPay.getText(),
                                              textFieldConveyance.getText(),textFieldOtherAllowance.getText(),
                                              textFieldTotalAmount.getText(),textFieldPerAnnum.getText(),textFieldTotalPerAnnum.getText(),
                                              textFieldLTC.getText(),textFieldMedical.getText(),textFieldBonus.getText(),
                                              textFieldOtherComponents.getText(),textFieldPF.getText(),textFieldGratuity.getText(),
                                              textFieldSuperannuation.getText(),textFieldGross.getText()));
        }



    }

    private
    class Miscellaneous {
        private
        TableColumn tblCol            = new TableColumn();
        private
        TableView   tblView           = new TableView();
        private
        TextArea    textWeakness      = new TextArea();
        private
        TextField   textCTC           = new TextField();
        private
        TextArea    textStrength      = new TextArea();
        private
        TextField   textQuestionOne   = new TextField();
        private
        TextField   textQuestionTwo   = new TextField();
        private
        TextField   textQuestionThree = new TextField();
        private
        TextField   textQuestionFour  = new TextField();
        private
        TextField   textQuestionFive  = new TextField();
        private
        TextField   textQuestionSix   = new TextField();
        private
        VBox        vBox              = new VBox();
        private
        HBox        hBox              = new HBox();

        private
        void setComponentsID() {
            textCTC.setId("LastBiFur");
            textStrength.setId("Annualized Components");
            textWeakness.setId("BasicSalary");
            textQuestionOne.setId("DA");
            textQuestionTwo.setId("SplPay");
            textQuestionThree.setId("Personal Pay");
            textQuestionFour.setId("Conveyance");
            textQuestionFive.setId("OtherAllowance");
            textQuestionSix.setId("TotalAmount");
        }

        private
        void setComponentsPromptText() {
            textCTC.setPromptText("CTC");
            textStrength.setPromptText("Strength");
            textWeakness.setPromptText("Weakness");
            textQuestionOne.setPromptText("Reason to leave existing employment");
            textQuestionTwo.setPromptText("Why should we hire you?");
            textQuestionThree.setPromptText("Achievements till date...");
            textQuestionFour.setPromptText("Professional Goal...");
            textQuestionFive.setPromptText("Personal Goal...");
            textQuestionSix.setPromptText("How soon can you join?");
        }

        private
        void setComponentsLayout() {

            vBox.getChildren().addAll(textCTC, textStrength, textWeakness, textQuestionOne,
                                      textQuestionTwo, textQuestionThree, textQuestionFour,
                                      textQuestionFive, textQuestionSix);
            vBox.setSpacing(5);

            //Calling functions to initialize Controls
            setComponentsID();
            setComponentsPromptText();
            setDefaultValues();

        }

        private
        void setDefaultValues() {
//            textAnnualizedComponents.setText("Annualized Components");
//            textLastSalaryBifurcation.setText("Last Salary Bifurcation");
        }

        protected
        VBox getComponentLayout() {
            setComponentsLayout();
            return vBox;
        }

        void addDataToContainer(){
            miscContainer.add(new GtpMiscellaneous(textCTC.getText(),textStrength.getText(),textWeakness.getText(),
                                                   textQuestionOne.getText(),textQuestionTwo.getText(),textQuestionThree.getText(),
                                                  textQuestionFour.getText(),textQuestionFive.getText(),textQuestionSix.getText()));
        }


    }

}

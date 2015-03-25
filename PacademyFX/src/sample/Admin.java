package sample;

import DBHandler.DBActions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import sample.propertyClass.PAcadSearch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Created by anurag on 10/25/2014.
 */
public
class Admin implements Viewable {
    private final
    ObservableList<PAcadSearch> searchCollection = FXCollections.observableArrayList();

    //Button
    private final
    Button btnSearch  = new Button();
    private final
    Button btnDelete  = new Button();
    //layout
    private final
    VBox   vBox       = new VBox();
    private final
    VBox   vBoxParent = new VBox();

    //TableView
    TableView tableView = new TableView();

    //Columns
    TableColumn nameColumn             = new TableColumn();
    TableColumn memIDColumn            = new TableColumn();
    TableColumn mobileNumberColumn     = new TableColumn();
    TableColumn residenceNumbeColumn   = new TableColumn();
    TableColumn addressColumn          = new TableColumn();
    TableColumn emailColumn            = new TableColumn();
    TableColumn registrationDateColumn = new TableColumn();

    //Search Fields
    private final
    TextField textFieldName            = new TextField();
    private final
    TextField textFieldMobileNumber    = new TextField();
    private final
    TextField textFieldMembershipID    = new TextField();
    private final
    TextField textFieldResidenceNumber = new TextField();

    //Delete fields
    private final
    TextField textFieldDeleteMemID = new TextField();
    Label labelDelete = new Label();

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
        vBoxParent.setMaxSize(800, 500);
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
        btnDelete.setId("DeleteButton");
        labelDelete.setId("DeleteLabel");
/*----------------------Search------------------------------------------*/
        btnSearch.setId("Search");
        tableView.setId("SearchList");

    }

    private
    void setComponentsPromptText() {
    textFieldName.setPromptText("Enter Name");
        textFieldMembershipID.setPromptText("Enter Membership ID");
        textFieldMobileNumber.setPromptText("Enter Mobile Number");
        textFieldResidenceNumber.setPromptText("Enter Residence Number");

        /*------------------Delete----------*/
        textFieldDeleteMemID.setPromptText("Enter Member ID to delete");
    }

    private void setTooltip(){
        btnDelete.setTooltip(new Tooltip("Click To Delete"));

        /***********Search************/
        btnSearch.setTooltip(new Tooltip("Click To Search"));
        tableView.setTooltip(new Tooltip("Search List"));
    }

    private
    void setComponentsLayout() {


        //Calling functions to initialize Controls
        setComponentsID();
        setComponentsPromptText();
        setTooltip();
        setDefaultValues();
        setActions();

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(10);
        dropShadow.setOffsetY(10);
        dropShadow.setRadius(12);
        /*-------------DELETE-------------------*/
        textFieldDeleteMemID.setMaxWidth(350);
        textFieldDeleteMemID.setPrefWidth(350);

/*------------------------SEARCH------------------------------*/
        textFieldName.setPrefWidth(350);
        textFieldName.setMaxWidth(350);

        textFieldMembershipID.setPrefWidth(350);
        textFieldMembershipID.setMaxWidth(350);

        textFieldResidenceNumber.setPrefWidth(350);
        textFieldResidenceNumber.setMaxWidth(350);

        textFieldMobileNumber.setPrefWidth(350);
        textFieldMobileNumber.setMaxWidth(350);

        vBox.setAlignment(Pos.CENTER);
        tableView.setPrefWidth(600);

      //  vBox.setEffect(dropShadow);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(textFieldDeleteMemID,btnDelete,labelDelete,new Separator(),textFieldName,textFieldMobileNumber,
                                  textFieldMembershipID,textFieldResidenceNumber,btnSearch,tableView);
    }

    private
    void setActions() {
        btnDelete.setOnAction((event) -> {
            if (!textFieldDeleteMemID.getText().isEmpty()) {
                //Connecting to Database
                if (null == GreentechConstants.gtpConnectionObj) {
                    try {

                        objDBAction.openConnection();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (null != GreentechConstants.gtpConnectionObj) {
                    try {

                        if(EmpDetailsToDB.deleteMember(textFieldDeleteMemID.getText(), objGtpConstant, objDBAction )> 0){

                            labelDelete.setText("Member ID :- "+textFieldDeleteMemID.getText()+" deleted successfully...");
                            labelDelete.setTextFill(Color.GREEN);
                            textFieldDeleteMemID.clear();
                        }else{
                            labelDelete.setText("Failed to Delete Member ID :- "+textFieldDeleteMemID.getText());
                            labelDelete.setTextFill(Color.RED);
                        }

                    } catch (SQLException ex) {
                        Exception nextEx = null;
                        while ((nextEx = ex.getNextException()) != null) {
                            GreentechConstants.Global_Logger.log(Level.SEVERE, "SQLException occurred :-"
                                                                               + " [Message:-" + nextEx.getMessage() +
                                                                               "]");
                        }
                        GreentechConstants.Global_Logger.log(Level.SEVERE, "SQLException occurred :-"
                                                                           + " [Message:-" + ex.getMessage() + "]");
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        GreentechConstants.Global_Logger.log(Level.SEVERE, "Exception occurred :-"
                                                                           + " [Message:-" + ex.getMessage() + "]");
                    }

                }

            }
        });

        btnSearch.setOnAction((event) -> {

                                  //Connecting to Database
                                  if (null == GreentechConstants.gtpConnectionObj) {
                                      try {

                                          objDBAction.openConnection();
                                      } catch (SQLException e) {
                                          e.printStackTrace();
                                      }
                                  }

                                  if (null != GreentechConstants.gtpConnectionObj) {

                                      try {
                                          clearControlValues();
                                          searchCollection.clear();
                                          ResultSet resultSet = EmpDetailsToDB.getSearchResult(textFieldName.getText(),
                                                                                               textFieldMobileNumber
                                                                                                       .getText(),
                                                                                               textFieldResidenceNumber
                                                                                                       .getText(),
                                                                                               textFieldMembershipID
                                                                                                       .getText(),
                                                                                               objGtpConstant,
                                                                                               objDBAction);

                                          while (resultSet.next()) {
                                              searchCollection.add(new PAcadSearch(resultSet.getString("MembershipID"),
                                                                                   resultSet.getString("Name"),
                                                                                   resultSet.getString("Address"),
                                                                                   resultSet.getString("Mobile_Number"),
                                                                                   resultSet.getString(
                                                                                           "Residence_Number"),
                                                                                   resultSet.getString("EMail"),
                                                                                   resultSet.getString(
                                                                                           "Registration_Date")));
                                          }
                                      } catch (SQLException ex) {
                                          Exception nextEx = null;
                                          while ((nextEx = ex.getNextException()) != null) {
                                              GreentechConstants.Global_Logger.log(Level.SEVERE,
                                                                                   "SQLException occurred :-"
                                                                                   + " [Message:-" +
                                                                                   nextEx.getMessage() + "]");
                                          }
                                          GreentechConstants.Global_Logger.log(Level.SEVERE, "SQLException occurred :-"
                                                                                             + " [Message:-" +
                                                                                             ex.getMessage() + "]");
                                          ex.printStackTrace();
                                      } catch (Exception ex) {
                                          ex.printStackTrace();
                                          GreentechConstants.Global_Logger.log(Level.SEVERE, "Exception occurred :-"
                                                                                             + " [Message:-" +
                                                                                             ex.getMessage() + "]");
                                      }
                                  }
                              }
                             );
    }

    private
    void clearControlValues() {
        tableView.getItems().clear();
    }

    private
    void setDefaultValues() {
        btnDelete.setText("Delete");
        /*----------Search------------------*/
        btnSearch.setText("Search");
        memIDColumn.setText("Member ID");
        nameColumn.setText("Name");
        mobileNumberColumn.setText("Mobile Number");
        residenceNumbeColumn.setText("Residence Number");
        addressColumn.setText("Address of Member");
        emailColumn.setText("Email");
        registrationDateColumn.setText("Registration Date");
        //Columns
        memIDColumn.setCellValueFactory(new PropertyValueFactory<>("membershipNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mobileNumberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        residenceNumbeColumn.setCellValueFactory(new PropertyValueFactory<>("residenceNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        registrationDateColumn.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));

        //Table
        tableView.setItems(searchCollection);

        //Adding columns
        tableView.getColumns().addAll(memIDColumn,registrationDateColumn,nameColumn,mobileNumberColumn,residenceNumbeColumn,
                                      addressColumn,emailColumn);
    }

    protected
    VBox getComponentsLayout() {
        setComponentsLayout();
        return vBox;
    }

}

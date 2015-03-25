package sample;

import DBHandler.DBActions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sample.propertyClass.PAcadDefaulter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by anurag on 10/23/2014.
 */
public
class DefaulterView implements Viewable {

    private final
    ObservableList<PAcadDefaulter> defaulterCollection = FXCollections.observableArrayList();

    //Button
    private final
    Button btnRefresh = new Button();

    //layout
    private final
    VBox vBox       = new VBox();
    private final
    VBox vBoxParent = new VBox();

    //TableView
    TableView tableView = new TableView();

    //Columns
    TableColumn nameColumn  = new TableColumn();
    TableColumn memIDColumn = new TableColumn();
    TableColumn mobileNumberColumn = new TableColumn();
    TableColumn residenceNumbeColumn = new TableColumn();
    TableColumn addressColumn = new TableColumn();
    TableColumn emailColumn = new TableColumn();


    //Objects
    private final
    GreentechConstants objGtpConstant = GreentechConstants.getInstance();
    private final
    DBActions          objDBAction    = new DBActions();

    public
    Parent getParent() {
        return vBoxParent;
    }

    public
    void getParent(BorderPane borderPane) {
        borderPane.setCenter(vBoxParent);
        borderPane.setBottom(tableView);
    }

    private
    void addChildren() {
        vBoxParent.getChildren().addAll(getComponentsLayout());
        vBoxParent.setMaxSize(800, 500);
        vBoxParent.setPrefSize(700, 400);
    }

    @Override
    public
    Parent getView() {
        addChildren();
        return vBoxParent;
    }


    public
    void getView(BorderPane borderPane) {
        addChildren();
        borderPane.setCenter(vBoxParent);
        borderPane.setBottom(tableView);
    }


    private
    void setComponentsID() {
/*----------------------------------------------------------------*/
        btnRefresh.setId("RefreshDefaulters");
        tableView.setId("DefaulterList");

    }

    private
    void setComponentsPromptText() {
    }

    private void setTooltip(){
        btnRefresh.setTooltip(new Tooltip("Click To Refresh"));
        tableView.setTooltip(new Tooltip("Defaulter List"));
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

        vBox.setEffect(dropShadow);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(btnRefresh,tableView);
    }

    private
    void setActions() {
        //btnSubmit.setOnAction(()->);

        btnRefresh.setOnAction((event) -> {

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
                                              defaulterCollection.clear();
                                             ResultSet resultSet = EmpDetailsToDB.getDefaulters(objGtpConstant, objDBAction);

                                              while(resultSet.next()){
                                                  defaulterCollection.add(new PAcadDefaulter(resultSet.getString("MembershipID"),
                                                resultSet.getString("Name"),resultSet.getString("Address"),
                                                resultSet.getString("Mobile_Number"),resultSet.getString("Residence_Number"),
                                                resultSet.getString("EMail")));
                                              }

                                          } catch (SQLException ex) {
                                              ex.printStackTrace();
                                          }catch (Exception ex){
                                              ex.printStackTrace();
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
        btnRefresh.setText("Refresh");
        memIDColumn.setText("Member ID");
        nameColumn.setText("Name");
        mobileNumberColumn.setText("Mobile Number");
        mobileNumberColumn.setPrefWidth(100);
        residenceNumbeColumn.setText("Residence Number");
        residenceNumbeColumn.setPrefWidth(100);
        addressColumn.setText("Address of Member");
        addressColumn.setPrefWidth(200);
        emailColumn.setText("Email");
      //Columns
        memIDColumn.setCellValueFactory(new PropertyValueFactory<>("membershipNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mobileNumberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        residenceNumbeColumn.setCellValueFactory(new PropertyValueFactory<>("residenceNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

       //Table
      tableView.setItems(defaulterCollection);

        //Adding columns
           tableView.getColumns().addAll(memIDColumn,nameColumn,mobileNumberColumn,residenceNumbeColumn,
                                         addressColumn,emailColumn);

        tableView.setPrefWidth(600);
    }

    protected
    VBox getComponentsLayout() {
        setComponentsLayout();
        return vBox;
    }
}

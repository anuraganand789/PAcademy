package sample;

import DBHandler.DBActions;
import initializer.ReadPropFile;
import initializer.WriteLog;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.HashMap;
import java.util.logging.Level;

public class Main extends Application {

    /*Creating tabs for all DataEntry*/
    //Button
    private
    Button  btnMembershipForm;
    private
    Button  BtnHome;
    private
    Button btnMakePayment;
    private
    Button btnDefaulterView;
    private
    Button btnSearch;
    private
    Button btnUpdate;
    private
    Button  btnHelp;
    private
    Button  btnogout;
    private
    Button  btnIcon;
    //labels
    private final
    Label moveLable = new Label("");

 //Variables for movemnts
    double initX ;
    double initY;

    private
    HashMap<String, TextField> hMapSalesManager = new HashMap<String, TextField>();
    private
    HashMap<String, TextField> hMapSalesOfficer = new HashMap<String, TextField>();
    private
    Stage primaryStage;
    private
    Scene sceneDailySales;

    //Objects
    private
    GreentechConstants objconstData;
    private
    AccordionView      accordView;
    private
    MembershipForm     membershipForm;
    private
    UpdateMember updateMember;
    private
    PaymentView makePayment;
    private
    DefaulterView defaulterView;
    private
    Admin adminView;
    private final
    LoginForm loginForm = new LoginForm();

    private final
    DBActions objDBAction = new DBActions();

    private final
    Group                   root       = new Group();
    private
    BorderPane              borderPane = new BorderPane();
    private final
    HashMap<String, Object> viewMap    = new HashMap<>();

    public
    Parent createContent() {
        moveLable.setMaxSize(primaryStage.getWidth(),50);
        moveLable.setCursor(Cursor.MOVE);
        moveLable.setTooltip(new Tooltip("Move Window"));
        setStageMoveAction();

        btnMakePayment = new Button("Make Payment");
        btnMakePayment.setOnAction((event) -> setBtnMakePaymentAction());

        btnMembershipForm = new Button("Add Member");
        btnMembershipForm.setOnAction((event) -> setMembershipAction());

        btnDefaulterView =  new Button("Defaulter");
        btnDefaulterView.setOnAction((event) -> setDefaulterAction());

        btnHelp = new Button("Help");

        btnSearch = new Button("Search & Delete");
        btnSearch.setOnAction((event)->setAdminAction());

        btnUpdate = new Button("Update Member");
        btnUpdate.setOnAction((event)->setUpdateMembershipAction());
        btnogout = new Button("Logout");
        btnogout.setOnAction((event) -> setLogoutAction(event));

        btnIcon = new Button("Min");
        btnIcon.setTooltip(new Tooltip("Minimize"));
        btnIcon.setOnAction((event)->primaryStage.setIconified(true));

        ToolBar toolbar = new ToolBar();
        toolbar.getItems().addAll(btnMembershipForm,btnMakePayment,btnDefaulterView,btnSearch,btnUpdate, btnogout,btnIcon, btnHelp);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(moveLable,toolbar);
         borderPane.setTop(vBox);
        membershipForm = new MembershipForm();
        viewMap.put("MembershipForm",membershipForm);
       borderPane.setCenter(membershipForm.getView());

        borderPane.setPrefSize(800, 700);

        root.getChildren().add(borderPane);
        return root;
    }

   private void setLogoutAction(ActionEvent event){
       objDBAction.closeConnection();
       primaryStage.close();
   }

    private void setAdminAction(){
        if( ! viewMap.containsKey("Admin")){
            adminView = new Admin();
            viewMap.put("Admin",adminView);
            borderPane.setCenter(adminView.getView());
        }else{
            borderPane.setCenter(adminView.getParent());
        }
    }
    private
    void setMembershipAction() {
        if( ! viewMap.containsKey("MembershipForm")){
            membershipForm = new MembershipForm();
            viewMap.put("MembershipForm",membershipForm);
            borderPane.setCenter(membershipForm.getView());
        }else{
            borderPane.setCenter(membershipForm.getParent());
        }
    }

    private
    void setUpdateMembershipAction() {
        if( ! viewMap.containsKey("UpdateMembershipForm")){
            updateMember = new UpdateMember();
            viewMap.put("UpdateMembershipForm",updateMember);
            borderPane.setCenter(updateMember.getView());
        }else{
            borderPane.setCenter(updateMember.getParent());
        }
    }

    private void setDefaulterAction(){
        if( ! viewMap.containsKey("Defaulter")){
            defaulterView = new DefaulterView();
            viewMap.put("Defaulter",defaulterView);
            borderPane.setCenter(defaulterView.getView());
        }else{
            borderPane.setCenter(defaulterView.getParent());
        }
    }

    private
    void setBtnHomeAction() {
        if( ! viewMap.containsKey("Home")){
            accordView =  new AccordionView();
            borderPane.setCenter(accordView.getView());
        }else{
            borderPane.setCenter(accordView.getParent());
        }
    }

    private
    void setBtnMakePaymentAction() {
        if( ! viewMap.containsKey("Make Payment")){
            makePayment =  new PaymentView();
            viewMap.put("Make Payment",makePayment);
            borderPane.setCenter(makePayment.getView());
        }else{
            borderPane.setCenter(makePayment.getParent());
        }
    }

    public void setStageMoveAction(){
        moveLable.setOnMousePressed((event)->{
            moveLable.setCursor(Cursor.CLOSED_HAND);
            initX = event.getScreenX() - primaryStage.getX();
            initY = event.getScreenY() - primaryStage.getY();
           // System.out.println("MousePressed");
        });

        moveLable.setOnMouseDragged((MouseEvent event)->{
            moveLable.setCursor(Cursor.CLOSED_HAND);
            //System.out.println("Mouse Dragged X"+(event.getScreenX() - initX) + " Y :- " + (event.getScreenY() - initY));
            primaryStage.setX(event.getScreenX() - initX);
            primaryStage.setY(event.getScreenY() - initY);
        });

        moveLable.setOnMouseDragReleased((event)-> moveLable.setCursor(Cursor.MOVE));
        moveLable.setOnMouseReleased((event)-> moveLable.setCursor(Cursor.MOVE));

    }
    @Override
    public
    void start(Stage primaryStage) throws Exception {
        //Loading Property File
        ReadPropFile.init();

        //Initialize Logger
        GreentechConstants.Global_Logger = WriteLog.getLogger();

      //  primaryStage.sizeToScene();
        primaryStage.setScene(loginForm.getScene(this));
        //primaryStage.centerOnScreen();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setHeight(400);
        primaryStage.setWidth(500);
        primaryStage.setResizable(false);
        this.primaryStage = primaryStage;
        primaryStage.show();
    }

//On Successful Login switch Stage
    public void loginSuccess(){
        GreentechConstants.Global_Logger.log(Level.INFO,"Login Successful");
        primaryStage.hide();
        this.primaryStage.setScene(new Scene(createContent()));
        primaryStage.sizeToScene();
        primaryStage.setY(primaryStage.getY() - 100);
        primaryStage.show();

    }

    public void loginCancel(){
        GreentechConstants.Global_Logger.log(Level.INFO,"Login Canceled, Closing stage");

        primaryStage.close();
    }

    public static void main(String[] args) {

      launch(args);
    }


}

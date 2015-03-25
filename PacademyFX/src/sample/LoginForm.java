package sample;

import DBHandler.DBActions;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import validations.Validates;

/**
 * Created by anurag on 10/3/2014.
 */
public
class LoginForm implements Viewable{

    //Objects
    Validates validate = new Validates();
    private
    Main mainObj;

    //Shapes
    //Rectangles
    private final
    Rectangle rectangle1     = new Rectangle();
    private final
    Rectangle rectangle2     = new Rectangle();
    private
    String    loginTextValue = "P-Academy";
    //Root Element
    private final
    Stage     stage          = new Stage(StageStyle.TRANSPARENT);

    //layout
    private final
    GridPane  gridPane  = new GridPane();
    private final
    StackPane stackPane = new StackPane();
    private final
    VBox      vBox      = new VBox();
    private final
    VBox      vBox1     = new VBox();
    private final
    VBox      vBox2     = new VBox();
    private final
    Group     rootGroup = new Group();

    private final
    BorderPane borderPane = new BorderPane();

    //Labels
    private final
    Label userName  = new Label("Name");
    private final
    Label password  = new Label("Password");
    private
    Label loginText = new Label();

    //TextFields
    private final
    TextField     txtFldUserName = new TextField();
    private final
    PasswordField txtFldPassword = new PasswordField();

    private final
    Button btnLogin  = new Button();
    private final
    Button btnCancel = new Button();

    //For MouseDrag Event
    private double initX;
    private double initY;


    private
    void init() {
        setComponentsID();
        setComponentsPromptText();
        setDefaultValues();
        setComponentsLayout();
        setActions();
    }

    public
    LoginForm() {
        init();
    }

    public
    LoginForm(String loginTextValue) {
        this.setLoginTextValue(loginTextValue);
        init();
    }


    void setComponentsID() {
        //Labels
        userName.setId("LoginUserName");
        password.setId("LoginPassword");

        //TextFields
        txtFldUserName.setId("LoginUserNameTxtFld");
        txtFldPassword.setId("LoginPasswordTxtFld");

        //Buttons
        btnLogin.setId("LoginButton");
        btnCancel.setId("CancelButton");

    }

    void setComponentsPromptText() {

        //TextFields
        txtFldUserName.setPromptText("User Name");
        txtFldPassword.setPromptText("Password");
    }

    private
    void setComponentsLayout() {
        //VBox
        getvBox().getChildren().addAll(rectangle1,rectangle2);

        getvBox1().getChildren().addAll(getLoginText(),getUserName(),getTxtFldUserName(),getPassword(),getTxtFldPassword());
        getvBox2().getChildren().addAll(btnLogin,btnCancel);

        getStackPane().getChildren().addAll(getvBox(),getvBox1(),getvBox2());
        getStackPane().setMaxSize(500,400);
    }

    private void setDefaultValues(){
        btnLogin.setText("Login");
        btnLogin.setPrefSize(300, 40);
        btnLogin.setCursor(Cursor.HAND);

        btnCancel.setText("Cancel");
        btnCancel.setPrefSize(300,40);
        btnCancel.setCursor(Cursor.HAND);

        //rectangles
        rectangle1.setHeight(350);
        rectangle1.setWidth(500);
     //   rectangle1.set
        rectangle1.setFill(Color.web("b3b3b3",0.8));
        //rectangle1.setA

        rectangle2.setHeight(200);
        rectangle2.setWidth(500);
        rectangle2.setFill(Color.web("999999",0.8));
        //GridPane
        RowConstraints rowConstraints = new RowConstraints(350);
        RowConstraints rowConstraints1 = new RowConstraints(200);

        ColumnConstraints columnConstraints = new ColumnConstraints(500);

        getGridPane().getRowConstraints().addAll(rowConstraints,rowConstraints1);
        getGridPane().getColumnConstraints().addAll(columnConstraints);

        //Lables
        getLoginText().setText(getLoginTextValue());


    }

    private void setActions(){
        btnCancel.setOnAction((e)->{
            new DBActions().closeConnection();
            mainObj.loginCancel();
        });

        btnLogin.setOnAction((e)->{

            if((txtFldUserName != null || !txtFldUserName.getText().isEmpty())
                    && (txtFldPassword != null || !txtFldPassword.getText().isEmpty())){

                if(validate.validateLogin(txtFldUserName.getText(), txtFldPassword.getText())){
                    System.out.println("Login Successful");
                    clearControls();
                    mainObj.loginSuccess();
                }else{
                    System.out.println("Login Failed");
                    clearControls();
                }
            }

        });

//        getStackPane().setOnMousePressed((MouseEvent event)->{
//            System.out.println("InitX:-"+initX+" stage.getX() "+stage.getX());
//            initX = event.getScreenX() - stage.getX();
//            initY = event.getScreenY() - stage.getY();
//
//
//        });
//
//        getStackPane().setOnMouseDragged((MouseEvent event)->{
//            System.out.println("event.getScreenX() :-"+event.getScreenX());
//            stage.setX(event.getScreenX() - initX);
//            stage.setY(event.getScreenY() - initY);
//        });

    }

    @Override
    public
    Parent getView() {
        Group root =new Group();
        root.getChildren().addAll(getGridPane());
        return root;
    }


    public
    Stage getStage(Main mainObj) {
        this.mainObj = mainObj;
        stage.setScene(new Scene(getStackPane()));
        stage.setTitle("Login");

    //    stage.setAlwaysOnTop(true);
        stage.centerOnScreen();
        stage.setHeight(400);
        stage.setWidth(500);
        stage.setResizable(false);
        return stage;
    }

    public Scene getScene(Main mainObj){
        this.mainObj = mainObj;
       return new Scene(getStackPane());
    }

    public Stage closeStage(){
        this.stage.close();
        return new Stage();
    }

    public
    GridPane getGridPane() {
        RowConstraints rowConstraints = new RowConstraints(50);
        RowConstraints rowConstraints1 = new RowConstraints(50);
        RowConstraints rowConstraints2 = new RowConstraints(50);

        ColumnConstraints columnConstraints = new ColumnConstraints(50);

        gridPane.getRowConstraints().addAll(rowConstraints,rowConstraints1);
        gridPane.getColumnConstraints().addAll(columnConstraints);

        gridPane.setVgap(0.0);
        gridPane.setHgap(0.0);
        gridPane.setPrefSize(200,200);
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }


    public
    Label getUserName() {
        userName.setLabelFor(txtFldUserName);
        userName.setFont(Font.font(25));
        userName.setTextFill(Color.ALICEBLUE);
        userName.setCursor(Cursor.HAND);
        return userName;
    }

    public
    Label getPassword() {
        password.setLabelFor(txtFldPassword);
        password.setFont(Font.font(25));
        password.setTextFill(Color.ALICEBLUE);
        password.setCursor(Cursor.HAND);
        return password;
    }

    public
    TextField getTxtFldUserName() {
        txtFldUserName.setMaxSize(300,100);
        txtFldUserName.setFont(Font.font(26));
        //txtFldUserName.
        return txtFldUserName;
    }

    public
    PasswordField getTxtFldPassword() {
        txtFldPassword.setMaxSize(300, 100);
        txtFldPassword.setFont(Font.font(26));
        return txtFldPassword;
    }

    public
    StackPane getStackPane() {
        return stackPane;
    }

    public
    VBox getvBox() {
        return vBox;
    }

    public
    VBox getvBox1() {
        vBox1.setMaxSize(300,400);
        return vBox1;
    }

    private
    String getLoginTextValue() {
        return loginTextValue;
    }

    private
    void setLoginTextValue(String loginTextValue) {
        this.loginTextValue = loginTextValue;
    }

    private
    Label getLoginText() {
        loginText.setTextFill(Color.AZURE);
        loginText.setFont(Font.font(35));
        return loginText;
    }

    private
    void setLoginText(Label loginText) {
        this.loginText = loginText;
    }

    private
    BorderPane getBorderPane() {
        return borderPane;
    }

    private
    VBox getvBox2() {
        vBox2.setPadding(new Insets(280,0,0,100));
        vBox2.setSpacing(10);
        return vBox2;
    }

    private void clearControls(){
        txtFldPassword.clear();
        txtFldUserName.clear();
    }
}

package view.admin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdateEmployeeView {

    private TextField idTextField;
    private TextField usernameTextField;
    private PasswordField passwordField;
    private TextField rolesTextField;
    private Button updateButton;

    private Stage primaryStage;

    public UpdateEmployeeView() {
        this.primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        idTextField = new TextField();
        usernameTextField = new TextField();
        passwordField = new PasswordField();
        rolesTextField = new TextField();
        updateButton = new Button("Update Employee");

        gridPane.add(new Label("ID:"), 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(new Label("Username:"), 0, 1);
        gridPane.add(usernameTextField, 1, 1);
        gridPane.add(new Label("Password:"), 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(new Label("Roles:"), 0, 3);
        gridPane.add(rolesTextField, 1, 3);

        gridPane.add(updateButton, 0, 5, 2, 1);

        Scene scene = new Scene(gridPane, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Update Employee");
        primaryStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    public void close() {
        primaryStage.close();
    }

    public String getIdTextFieldText() {
        return idTextField.getText();
    }

    public String getUsernameTextFieldText() {
        return usernameTextField.getText();
    }

    public String getPasswordFieldText() {
        return passwordField.getText();
    }

    public String getRolesTextFieldText() {
        return rolesTextField.getText();
    }

    public void setUpdateButtonHandler(EventHandler<ActionEvent> updateButtonHandler) {
        updateButton.setOnAction(updateButtonHandler);
    }
}

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
import model.Right;
import model.Role;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddEmployeeView {

    private TextField usernameTextField;
    private PasswordField passwordField;
    private TextField rolesTextField;  // New TextField for roles
    private Button addButton;

    private Stage primaryStage;

    public AddEmployeeView() {
        this.primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        usernameTextField = new TextField();
        passwordField = new PasswordField();
        rolesTextField = new TextField();
        addButton = new Button("Add Employee");

        gridPane.add(new Label("Username:"), 0, 1);
        gridPane.add(usernameTextField, 1, 1);
        gridPane.add(new Label("Password:"), 0, 2);
        gridPane.add(passwordField, 1, 2);
        gridPane.add(new Label("Roles (comma-separated):"), 0, 3);
        gridPane.add(rolesTextField, 1, 3);

        gridPane.add(addButton, 0, 4, 2, 1);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Employee");
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

    public void addButtonListener(EventHandler<ActionEvent> addButtonListener) {
        addButton.setOnAction(addButtonListener);
    }

    public String getUsernameTextFieldText() {
        return usernameTextField.getText();
    }

    public String getPasswordFieldText() {
        return passwordField.getText();
    }

    public List<Role> getRolesTextFieldText() {
        String rolesText = rolesTextField.getText();
        String[] roleArray = rolesText.split(",");

        return Arrays.stream(roleArray)
                .map(String::trim)
                .map(roleName -> new Role(2L, roleName, Arrays.asList(new Right(2L, "SomeRight"))))
                .collect(Collectors.toList());
    }
}

package view.admin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

import java.util.List;

public class AdminView {

    private Button viewAllButton;
    private Button addButton;
    private Button updateButton;
    private Button deleteButton;
    private Button closeButton;
    private TableView<User> employeesTable;

    private TableColumn<User, Long> idColumn;
    private TableColumn<User, String> usernameColumn;
    private TableColumn<User, String> passwordColumn;

    private Stage primaryStage;

    public AdminView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);
        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setScene(scene);
        initializeSceneTitle(gridPane);
        initializeButtons(gridPane);
        initializeEmployeesTable(gridPane);

        primaryStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Admin View");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeButtons(GridPane gridPane) {
        viewAllButton = new Button("View All Employees");
        addButton = new Button("Add Employee");
        updateButton = new Button("Update Employee");
        deleteButton = new Button("Delete Employee");
        closeButton = new Button("Close");

        gridPane.add(viewAllButton, 0, 1);
        gridPane.add(addButton, 1, 1);
        gridPane.add(updateButton, 2, 1);
        gridPane.add(deleteButton, 3, 1);
        gridPane.add(closeButton, 4, 1);
    }

    private void initializeEmployeesTable(GridPane gridPane) {
        employeesTable = new TableView<>();

        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<User, Long>("id"));

        usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        employeesTable.getColumns().addAll(idColumn, usernameColumn, passwordColumn);
        employeesTable.setEditable(false);

        gridPane.add(employeesTable, 0, 2, 4, 1);
    }

    public Stage getPrimaryStageAdmin() {
        return primaryStage;
    }

    public void addViewAllButtonListener(EventHandler<ActionEvent> viewAllButtonListener) {
        viewAllButton.setOnAction(viewAllButtonListener);
    }

    public void addAddButtonListener(EventHandler<ActionEvent> addButtonListener) {
        addButton.setOnAction(addButtonListener);
    }

    public void addUpdateButtonListener(EventHandler<ActionEvent> updateButtonListener) {
        updateButton.setOnAction(updateButtonListener);
    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener) {
        deleteButton.setOnAction(deleteButtonListener);
    }

    public void addCloseButtonListener(EventHandler<ActionEvent> closeButtonListener) {
        closeButton.setOnAction(closeButtonListener);
    }

    public void setEmployeesInTable(List<User> employees) {
        ObservableList<User> employeeData = FXCollections.observableArrayList(employees);
        employeesTable.setItems(employeeData);
    }
}

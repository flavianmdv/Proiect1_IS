package view.employee;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.function.Consumer;

public class AddBookView {

    private TextField authorTextField;
    private TextField titleTextField;
    private DatePicker publishedDatePicker;
    private TextField quantityTextField;
    private TextField priceTextField;
    private Button addButton;

    private Stage primaryStage;

    public AddBookView() {
        this.primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        authorTextField = new TextField();
        titleTextField = new TextField();
        publishedDatePicker = new DatePicker();
        quantityTextField = new TextField();
        priceTextField = new TextField();
        addButton = new Button("Add Book");

        gridPane.add(new Label("Title:"), 0, 1);
        gridPane.add(titleTextField, 1, 1);
        gridPane.add(new Label("Author:"), 0, 2);
        gridPane.add(authorTextField, 1, 2);
        gridPane.add(new Label("Published Date:"), 0, 3);
        gridPane.add(publishedDatePicker, 1, 3);
        gridPane.add(new Label("Quantity:"), 0, 4);
        gridPane.add(quantityTextField, 1, 4);
        gridPane.add(new Label("Price:"), 0, 5);
        gridPane.add(priceTextField, 1, 5);

        gridPane.add(addButton, 0, 6, 2, 1);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Book");
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    public void show() {
        primaryStage.show();
    }

    public void close() {
        primaryStage.close();
    }

    public void addButtonListener(EventHandler<ActionEvent> addButtonListener) {
        addButton.setOnAction(addButtonListener);
    }

    public String getAuthorTextFieldText() {
        return authorTextField.getText();
    }

    public String getTitleTextFieldText() {
        return titleTextField.getText();
    }

    public LocalDate getPublishedDatePickerValue() {
        return publishedDatePicker.getValue();
    }

    public int getQuantityTextFieldValue() {
        return Integer.parseInt(quantityTextField.getText());
    }

    public int getPriceTextFieldValue() {
        return Integer.parseInt(priceTextField.getText());
    }
}

// UpdateBookView.java
package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class UpdateBookView {

    private TextField idTextField;
    private TextField titleTextField;
    private TextField authorTextField;
    private DatePicker publishedDatePicker;
    private TextField quantityTextField;
    private TextField priceTextField;
    private TextField extraTextField;
    private Button updateButton;

    private Stage primaryStage;

    public UpdateBookView() {
        this.primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        idTextField = new TextField();
        titleTextField = new TextField();
        authorTextField = new TextField();
        publishedDatePicker = new DatePicker();
        quantityTextField = new TextField();
        priceTextField = new TextField();
        extraTextField = new TextField();
        updateButton = new Button("Update Book");

        gridPane.add(new Label("ID:"), 0, 0);
        gridPane.add(idTextField, 1, 0);
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

        gridPane.add(updateButton, 0, 7, 2, 1);

        Scene scene = new Scene(gridPane, 400, 350);  x
        primaryStage.setScene(scene);
        primaryStage.setTitle("Update Book");
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

    public String getTitleTextFieldText() {
        return titleTextField.getText();
    }

    public String getAuthorTextFieldText() {
        return authorTextField.getText();
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

    public void setUpdateButtonHandler(EventHandler<ActionEvent> updateButtonHandler) {
        updateButton.setOnAction(updateButtonHandler);
    }
}

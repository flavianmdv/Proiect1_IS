package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeleteBookView {

    private TextField idTextField;
    private Button deleteButton;
    private Stage primaryStage;

    public DeleteBookView() {
        this.primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        idTextField = new TextField();
        deleteButton = new Button("Delete");

        gridPane.add(new Label("Book ID:"), 0, 1);
        gridPane.add(idTextField, 1, 1);
        gridPane.add(deleteButton, 0, 2, 2, 1);

        Scene scene = new Scene(gridPane, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Delete Book");
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

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener) {
        deleteButton.setOnAction(deleteButtonListener);
    }

    public Long getIdTextFieldValue() {
        return Long.parseLong(idTextField.getText());
    }
}

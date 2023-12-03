package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Book;

import java.util.List;

public class SellView {

    private TextField idTextField;
    private Button sellButton;
    private Button addButton;
    private TextArea addedBooksTextArea;
    private Stage primaryStage;

    public SellView() {
        this.primaryStage = new Stage();
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        idTextField = new TextField();
        sellButton = new Button("Sell");
        addButton = new Button("Add");

        addedBooksTextArea = new TextArea();
        addedBooksTextArea.setEditable(false);
        addedBooksTextArea.setPrefRowCount(10);

        gridPane.add(new Label("Book ID:"), 0, 1);
        gridPane.add(idTextField, 1, 1);
        gridPane.add(sellButton, 0, 2);
        gridPane.add(addButton, 1, 2);

        gridPane.add(addedBooksTextArea, 0, 3, 2, 1);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sell or Add Book");
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

    public void addSellButtonListener(EventHandler<ActionEvent> sellButtonListener) {
        sellButton.setOnAction(sellButtonListener);
    }

    public void addAddButtonListener(EventHandler<ActionEvent> addButtonListener) {
        addButton.setOnAction(addButtonListener);
    }

    public Long getIdTextFieldValue() {
        return Long.parseLong(idTextField.getText());
    }

    public void displayAddedBooks(List<Book> addedBooks) {
        StringBuilder booksText = new StringBuilder();

        for (Book book : addedBooks) {
            booksText.append(book.toString()).append("\n");
        }

        addedBooksTextArea.setText(booksText.toString());
    }
}

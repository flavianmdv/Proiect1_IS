package view;

import database.DatabaseConnectionFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;

import java.util.List;

public class CartView {

    private Button closeCartButton;
    private Button buyButton;
    private Text cartTitle;
    private Stage cartStage;
    private TextArea cartTextArea;
    private List<Book> cart_list;
    private  Stage stage;

    int total_price = 0;

    public CartView(List<Book> list_book) {
        cartStage = new Stage();
        this.cart_list = list_book;
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        initializeCartTitle(gridPane);

        initializeCloseCartButton(gridPane);

        initializeCartTextArea(gridPane);

        initializeBuyButton(gridPane);

        Label priceLabel = new Label("Total Price: " + String.valueOf(total_price));
        VBox vbox = new VBox();
        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(priceLabel);
        Scene scene = new Scene(vbox, 500, 400);
        cartStage.setScene(scene);

        cartStage.show();

    }



    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeCartTitle(GridPane gridPane) {
        cartTitle = new Text("Shopping Cart");
        cartTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(cartTitle, 0, 0, 2, 1);
    }

    private void initializeCloseCartButton(GridPane gridPane) {
        closeCartButton = new Button("Close Cart");
        HBox closeCartButtonHBox = new HBox(10);
        closeCartButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        closeCartButtonHBox.getChildren().add(closeCartButton);
        gridPane.add(closeCartButtonHBox, 1, 4);
    }
    private void initializeBuyButton(GridPane gridPane) {
        buyButton = new Button("Buy");
        HBox buyButtonHBox = new HBox(10);
        buyButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        buyButtonHBox.getChildren().add(buyButton);
        gridPane.add(buyButtonHBox, 1, 5);

    }
    private void initializeCartTextArea(GridPane gridPane) {
        cartTextArea = new TextArea();
        String afisare = new String();
        total_price = 0;
        for (Book book : this.cart_list)
        {
            total_price += book.getPret();
            afisare += book.toString();
        }
        cartTextArea.setText(afisare);
        cartTextArea.setEditable(false);
        gridPane.add(cartTextArea, 0, 1, 2, 1);
    }


    public void addCloseCartButtonListener(EventHandler<ActionEvent> closeCartButtonListener) {
        closeCartButton.setOnAction(closeCartButtonListener);
    }

    public void addBuyButtonListener(EventHandler<ActionEvent> buyButtonListener) {
        BookRepository bookRepository = new BookRepositoryCacheDecorator(
                            new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                            new Cache<>()
                    );
        for (Book selectedBook: this.cart_list) {
            bookRepository.update(selectedBook.getId(), -1);

        }
        buyButton.setOnAction(buyButtonListener);
        this.cart_list.clear();
    }
    public Stage getStage() {
        return cartStage;
    }



}

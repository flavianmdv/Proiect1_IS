package view;

import database.DatabaseConnectionFactory;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Book;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerView {

    private Button closeButton;
    private Button viewAllBooksButton;

    private Button cartButton;
    private Stage primaryStage;
    private TableView<Book> booksTable;
    private TableColumn<Book, Long> idColumn;

    private TableColumn<Book, String> titleColumn;

    private TableColumn<Book, String> authorColumn;
    private TableColumn<Book, LocalDate> publishedDate;
    private TableColumn<Book, Integer> quantityColumn;
    private TableColumn<Book, Integer> priceColumn;

    private List<Book> list_book;


    public CustomerView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);
        Scene scene = new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);
        initializeSceneTitle(gridPane);
        initializeButtons(gridPane);
        initializeBooksTable(gridPane);
        primaryStage.show();
        list_book = new ArrayList<>();
    }

    public Stage getPrimaryStageCustomer(){
        return primaryStage;
    }
    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));
    }

    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Customer View");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeButtons(GridPane gridPane) {
        closeButton = new Button("Close");
        HBox closeButtonHBox = new HBox(10);
        closeButtonHBox.setAlignment(Pos.BOTTOM_RIGHT);
        closeButtonHBox.getChildren().add(closeButton);
        gridPane.add(closeButtonHBox, 1, 4);

        viewAllBooksButton = new Button("View All Books");
        HBox viewAllBooksButtonHBox = new HBox(10);
        viewAllBooksButtonHBox.setAlignment(Pos.BOTTOM_LEFT);
        viewAllBooksButtonHBox.getChildren().add(viewAllBooksButton);
        gridPane.add(viewAllBooksButtonHBox, 0, 4);

        cartButton = new Button("Cart");
        HBox.setMargin(cartButton, new Insets(10, 0, 0, 0));
        HBox cartButtonHBox = new HBox(10);
        cartButtonHBox.setAlignment(Pos.TOP_RIGHT);
        cartButtonHBox.getChildren().add(cartButton);
        gridPane.add(cartButtonHBox, 1, 0);

    }

    private void initializeBooksTable(GridPane gridPane) {
        booksTable = new TableView<>();

        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Book, Long>("id"));

        titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        publishedDate = new TableColumn<>("PublishedDate");
        publishedDate.setCellValueFactory(new PropertyValueFactory<>("publishedDate"));

        quantityColumn = new TableColumn<>("Cantitate");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("cantitate"));

        priceColumn = new TableColumn<>("Pret");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pret"));

        TableColumn<Book, Void> addToCartColumn = new TableColumn<>("Add to Cart");
        addToCartColumn.setCellFactory(param -> new TableCell<>() {
            private final Button addToCartButton = new Button("Add to Cart");

            {
                addToCartButton.setOnAction(event -> {
                    Book selectedBook = getTableView().getItems().get(getIndex());
//                    BookRepository bookRepository = new BookRepositoryCacheDecorator(
//                            new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
//                            new Cache<>()
//                    );
//                    bookRepository.update(selectedBook.getId(), -1);
                    addToCart(selectedBook);
                    System.out.print(selectedBook.getTitle());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(addToCartButton);
                }
            }
        });

        booksTable.getColumns().addAll((TableColumn<Book, ?>) idColumn, titleColumn, authorColumn, publishedDate, quantityColumn, priceColumn, addToCartColumn);
        booksTable.setEditable(false);

        gridPane.add(booksTable, 0, 5, 2, 1);
    }

    private void addToCart(Book selectedBook) {

        list_book.add(selectedBook);

    }

    public List<Book> getList_book() {
        return list_book;
    }

    public void setList_book(List<Book> list_book) {
        this.list_book = list_book;
    }

    public void addCloseButtonListener(EventHandler<ActionEvent> closeButtonListener) {
        closeButton.setOnAction(closeButtonListener);
    }

    public void addViewAllBooksButtonListener(EventHandler<ActionEvent> viewAllBooksButtonListener) {
        viewAllBooksButton.setOnAction(viewAllBooksButtonListener);
    }

    public void addCartButtonListener(EventHandler<ActionEvent> cartButtonListener) {
        cartButton.setOnAction(cartButtonListener);
    }
    public void setBooksInTable(List<Book> books) {
        ObservableList<Book> bookData = FXCollections.observableArrayList(books);
        booksTable.setItems(bookData);
    }

    public TableView<Book> getBooksTable() {
        return booksTable;
    }


}

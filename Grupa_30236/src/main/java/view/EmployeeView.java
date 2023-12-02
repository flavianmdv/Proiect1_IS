package view;

import database.DatabaseConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;

import java.time.LocalDate;
import java.util.List;

public class EmployeeView {

    private Button addButton;
    private Button editButton;
    private Button deleteButton;
    private Button viewAllButton;
    private Button sellBookButton;
    private Button closeButton;
    private TableView<Book> booksTable;

    private TableColumn<Book, Long> idColumn;
    private TableColumn<Book, String> titleColumn;
    private TableColumn<Book, String> authorColumn;
    private TableColumn<Book, LocalDate> publishedDate;
    private TableColumn<Book, Integer> quantityColumn;
    private TableColumn<Book, Integer> priceColumn;

    private BookRepository bookRepository;
    private Stage primaryStage;

    public EmployeeView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);
        Scene scene = new Scene(gridPane, 720, 480);
        primaryStage.setScene(scene);
        initializeSceneTitle(gridPane);
        initializeButtons(gridPane);
        initializeBooksTable(gridPane);

        // Initialize the book repository
        this.bookRepository = new BookRepositoryCacheDecorator(
                new BookRepositoryMySQL(DatabaseConnectionFactory.getConnectionWrapper(true).getConnection()),
                new Cache<>()
        );

        primaryStage.show();



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

        quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("cantitate"));

        priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pret"));

        booksTable.getColumns().addAll(idColumn, titleColumn, authorColumn, publishedDate, quantityColumn, priceColumn);
        booksTable.setEditable(false);

        gridPane.add(booksTable, 0, 5, 2, 1);
    }
        private void initializeGridPane(GridPane gridPane) {
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(25, 25, 25, 25));
        }
    private void initializeSceneTitle(GridPane gridPane) {
        Text sceneTitle = new Text("Employee View");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(sceneTitle, 0, 0, 2, 1);
    }

    private void initializeButtons(GridPane gridPane) {
        addButton = new Button("Add Book");
        editButton = new Button("Edit Book");
        deleteButton = new Button("Delete Book");
        viewAllButton = new Button("View All Books");
        sellBookButton = new Button("Sell Book");
        closeButton = new Button("Close");


        gridPane.add(addButton, 0, 1);
        gridPane.add(editButton, 1, 1);
        gridPane.add(deleteButton, 2, 1);
        gridPane.add(viewAllButton, 3, 1);
        gridPane.add(sellBookButton, 4, 1);
        gridPane.add(closeButton, 5, 1);


    }

    public Stage getPrimaryStageEmployee() {
        return primaryStage;
    }

    public void addAddButtonListener(EventHandler<ActionEvent> addButtonListener) {
        addButton.setOnAction(addButtonListener);
    }

    public void addEditButtonListener(EventHandler<ActionEvent> editButtonListener) {
        editButton.setOnAction(editButtonListener);
    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener) {
        deleteButton.setOnAction(deleteButtonListener);
    }

    public void addViewAllButtonListener(EventHandler<ActionEvent> viewAllButtonListener) {
        viewAllButton.setOnAction(viewAllButtonListener);
    }

    public void addSellBookButtonListener(EventHandler<ActionEvent> sellBookButtonListener) {
        sellBookButton.setOnAction(sellBookButtonListener);
    }

    public void addCloseButtonListener(EventHandler<ActionEvent> closeButtonListener) {
        closeButton.setOnAction(closeButtonListener);
    }


    public void setBooksInTable(List<Book> books) {
        ObservableList<Book> bookData = FXCollections.observableArrayList(books);
        booksTable.setItems(bookData);
    }

    public TableView<Book> getBooksTable() {
        return booksTable;
    }
}

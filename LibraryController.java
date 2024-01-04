package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class LibraryController {

    @FXML
    private VBox root;

    @FXML
    private Button displayHistoryButton;

    @FXML
    private TextArea outputTextArea;

    private Library library;

    @FXML
    public void initialize() {
        ReferenceBook referenceBook = new ReferenceBook("Java Programming", "John Doe", "3rd Edition");
        FictionBook fictionBook = new FictionBook("The Adventure", "Jane Doe", "Adventure");

        library = new Library();

        library.borrowBook(referenceBook, "Alice", "2024-01-01");
        library.borrowBook(fictionBook, "Bob", "2024-01-05");

        displayHistoryButton.setOnAction(this::displayHistory);
    }

    @FXML
    private void displayHistory(ActionEvent event) {
        String borrowHistory = library.getBorrowHistoryAsString();
        outputTextArea.setText(borrowHistory);
    }
}

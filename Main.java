package com.example.demo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

interface Borrowable {
    void borrow();
    void returnBook();
}

abstract class Book implements Borrowable {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public void borrow() {
        System.out.println("Borrowing book: " + title);
    }

    @Override
    public void returnBook() {
        System.out.println("Returning book: " + title);
    }
}

class BorrowHistory {
    private Book book;
    private String borrower;
    private String borrowDate;

    public BorrowHistory(Book book, String borrower, String borrowDate) {
        this.book = book;
        this.borrower = borrower;
        this.borrowDate = borrowDate;
    }

    public String getHistoryAsString() {
        return "Book: " + book.getTitle() + "\n" +
                "Borrower: " + borrower + "\n" +
                "Borrow Date: " + borrowDate + "\n";
    }
}

class ReferenceBook extends Book {
    private String edition;

    public ReferenceBook(String title, String author, String edition) {
        super(title, author);
        this.edition = edition;
    }

    public String getEdition() {
        return edition;
    }
}

class FictionBook extends Book {
    private String genre;

    public FictionBook(String title, String author, String genre) {
        super(title, author);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}

class Library {
    private List<BorrowHistory> borrowHistories;

    public Library() {
        this.borrowHistories = new ArrayList<>();
    }

    public void borrowBook(Book book, String borrower, String borrowDate) {
        book.borrow();
        borrowHistories.add(new BorrowHistory(book, borrower, borrowDate));
    }

    public void returnBook(Book book) {
        book.returnBook();
    }

    public String getBorrowHistoryAsString() {
        StringBuilder historyString = new StringBuilder();
        historyString.append("Borrow History:\n");
        borrowHistories.forEach(history -> historyString.append(history.getHistoryAsString()).append("\n"));
        return historyString.toString();
    }
}

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ReferenceBook referenceBook = new ReferenceBook("Java Programming", "John Doe", "3rd Edition");
        FictionBook fictionBook = new FictionBook("The Adventure", "Jane Doe", "Adventure");

        Library library = new Library();

        library.borrowBook(referenceBook, "Alice", "2024-01-01");
        library.borrowBook(fictionBook, "Bob", "2024-01-05");

        library.returnBook(referenceBook);
        library.returnBook(fictionBook);

        // JavaFX UI
        VBox root = new VBox();
        Button displayHistoryButton = new Button("Display Borrow History");

        // TextArea untuk menampilkan output
        TextArea outputTextArea = new TextArea();
        outputTextArea.setEditable(false);  // Agar tidak bisa diedit oleh pengguna
        outputTextArea.setWrapText(true);   // Agar teks berjalan ke baris baru jika tidak muat

        // Menambahkan aksi tombol
        displayHistoryButton.setOnAction(event -> {
            String borrowHistory = library.getBorrowHistoryAsString();
            outputTextArea.setText(borrowHistory);
        });

        root.getChildren().addAll(displayHistoryButton, outputTextArea);

        primaryStage.setTitle("Library Management");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
}

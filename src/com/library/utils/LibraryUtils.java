package com.library.utils;

import com.library.models.Book;
import com.library.models.User;

public class LibraryUtils {

    // Check if a book is available for borrowing
    public static boolean isBookAvailable(Book book) {
        return book != null && book.isAvailable();
    }

    // Display book information
    public static void displayBook(Book book) {
        if (book != null) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));
            System.out.println("------------------------");
        }
    }

    // Display user information
    public static void displayUser(User user) {
        if (user != null) {
            System.out.println("User ID: " + user.getUserId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Books borrowed: " + user.getBorrowHistory().getBookCount());
            System.out.println("------------------------");
        }
    }

    // Display all books in a BookArray
    public static void displayAllBooks(BookArray books) {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.displayAll(); // Use the custom display method
        }
    }
}
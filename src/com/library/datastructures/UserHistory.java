package com.library.datastructures;

import com.library.utils.StringArray;

public class UserHistory {
    // Simple Node class for Linked List - like chain links
    private class HistoryNode {
        String bookTitle; // The book name
        String borrowDate; // When it was borrowed
        HistoryNode next; // Link to next book in history

        // Simple constructor
        public HistoryNode(String bookTitle, String borrowDate) {
            this.bookTitle = bookTitle;
            this.borrowDate = borrowDate;
            this.next = null; // No next book initially
        }
    }

    private HistoryNode head; // First book in history chain
    private int count; // How many books in history

    // Constructor - start with empty history
    public UserHistory() {
        head = null;
        count = 0;
        System.out.println("New user history created - ready to track books!");
    }

    // Add a book to user's history (with today's date)
    public void addBorrowedBook(String bookTitle) {
        // Get today's date automatically
        String borrowDate = java.time.LocalDate.now().toString();

        HistoryNode newRecord = new HistoryNode(bookTitle, borrowDate);
        System.out.println("Adding to history: " + bookTitle + " on " + borrowDate);

        // Add new record at beginning (most recent first)
        newRecord.next = head;
        head = newRecord;
        count++;
    }

    // Add a book with specific date
    public void addBorrowedBook(String bookTitle, String borrowDate) {
        HistoryNode newRecord = new HistoryNode(bookTitle, borrowDate);
        System.out.println("Adding to history: " + bookTitle + " on " + borrowDate);

        // Add new record at beginning (most recent first)
        newRecord.next = head;
        head = newRecord;
        count++;
    }

    // Get all borrowed books (for display purposes)
    public StringArray getBorrowedBooks() {
        StringArray books = new StringArray();
        HistoryNode current = head;

        while (current != null) {
            books.add(current.bookTitle);
            current = current.next;
        }

        return books;
    }

    // Get borrowed books with dates
    public StringArray getBorrowedBooksWithDates() {
        StringArray books = new StringArray();
        HistoryNode current = head;

        while (current != null) {
            books.add(current.bookTitle + " (Borrowed: " + current.borrowDate + ")");
            current = current.next;
        }

        return books;
    }

    // Check if user has borrowed any books
    public boolean hasBorrowedBooks() {
        return head != null;
    }

    // Get number of books borrowed
    public int getBookCount() {
        return count;
    }

    // Search for a specific book in history
    public boolean hasBorrowedBook(String bookTitle) {
        HistoryNode current = head;

        while (current != null) {
            if (current.bookTitle.equals(bookTitle)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    // Remove a book from history (simple version)
    public boolean removeBorrowedBook(String bookTitle) {
        System.out.println("Trying to remove: " + bookTitle);

        // If history is empty, nothing to remove
        if (head == null) {
            System.out.println("History is empty");
            return false;
        }

        // If first book matches, remove it
        if (head.bookTitle.equals(bookTitle)) {
            head = head.next;
            count--;
            System.out.println("Removed first book in history");
            return true;
        }

        // Look for book in rest of chain
        HistoryNode current = head;
        while (current.next != null) {
            if (current.next.bookTitle.equals(bookTitle)) {
                current.next = current.next.next; // Skip over the book
                count--;
                System.out.println("Removed book from middle/end of history");
                return true;
            }
            current = current.next;
        }

        System.out.println("Book not found in history");
        return false;
    }

    // Display complete history
    public void displayHistory() {
        if (head == null) {
            System.out.println("No borrowing history");
            return;
        }

        System.out.println("Borrowing History (most recent first):");
        HistoryNode current = head;
        int position = 1;

        while (current != null) {
            System.out.println(position + ". " + current.bookTitle +
                    " - Borrowed on: " + current.borrowDate);
            current = current.next;
            position++;
        }
    }

    // Clear all history
    public void clearHistory() {
        head = null;
        count = 0;
        System.out.println("All history cleared");
    }
}

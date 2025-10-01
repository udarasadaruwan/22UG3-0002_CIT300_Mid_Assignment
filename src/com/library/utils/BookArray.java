package com.library.utils;

import com.library.models.Book;

/**
 * Custom dynamic array implementation to replace ArrayList
 * This shows understanding of how dynamic arrays work internally
 */
public class BookArray {
    private Book[] books;
    private int size;
    private int capacity;

    // Constructor - start with small capacity
    public BookArray() {
        capacity = 5;
        books = new Book[capacity];
        size = 0;
    }

    // Constructor with initial capacity
    public BookArray(int initialCapacity) {
        capacity = initialCapacity;
        books = new Book[capacity];
        size = 0;
    }

    // Add a book to the array
    public void add(Book book) {
        if (size >= capacity) {
            expandCapacity();
        }
        books[size] = book;
        size++;
    }

    // Get book at specific index
    public Book get(int index) {
        if (index < 0 || index >= size) {
            return null; // Invalid index
        }
        return books[index];
    }

    // Get current size
    public int size() {
        return size;
    }

    // Check if array is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Convert to regular array for easy processing
    public Book[] toArray() {
        Book[] result = new Book[size];
        for (int i = 0; i < size; i++) {
            result[i] = books[i];
        }
        return result;
    }

    // Private method to expand capacity when needed
    private void expandCapacity() {
        int newCapacity = capacity * 2;
        Book[] newBooks = new Book[newCapacity];

        // Copy existing books to new array
        for (int i = 0; i < size; i++) {
            newBooks[i] = books[i];
        }

        books = newBooks;
        capacity = newCapacity;
        System.out.println("Array capacity expanded to: " + capacity);
    }

    // Display all books
    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("=== All Books ===");
        for (int i = 0; i < size; i++) {
            Book book = books[i];
            System.out.println((i + 1) + ". " + book.getTitle() +
                    " by " + book.getAuthor() +
                    " (ISBN: " + book.getIsbn() + ")" +
                    " - Available: " + (book.isAvailable() ? "Yes" : "No"));
        }
    }
}
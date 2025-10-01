package com.library.datastructures;

import com.library.models.Book;
import com.library.utils.BookArray;

public class ReturnStack {
    // Simple Node class for Stack - like books stacked on top of each other
    private class StackNode {
        Book book; // The book in this stack position
        StackNode next; // Link to book below this one

        // Simple constructor
        public StackNode(Book book) {
            this.book = book;
            this.next = null; // No book below initially
        }
    }

    private StackNode top; // The book on top of stack
    private int count; // How many books in stack

    // Constructor - start with empty stack
    public ReturnStack() {
        top = null;
        count = 0;
        System.out.println("New return stack created - ready for returned books!");
    }

    // Add a book to the stack (like putting book on top of pile)
    public void addReturnedBook(Book book) {
        StackNode newBook = new StackNode(book);
        System.out.println("Adding returned book to stack: " + book.getTitle());

        // Put new book on top of current stack
        newBook.next = top;

        // Update top pointer to new book
        top = newBook;

        count++;
        System.out.println("Stack size now: " + count);
    }

    // Get the last returned book (like taking book from top of pile)
    public Book getLastReturnedBook() {
        // If stack is empty, nothing to take
        if (top == null) {
            System.out.println("Stack is empty - no books to remove");
            return null;
        }

        // Get the book from top of stack
        Book book = top.book;
        System.out.println("Removing book from stack: " + book.getTitle());

        // Move top pointer to next book down
        top = top.next;

        count--;
        System.out.println("Stack size now: " + count);
        return book;
    }

    // Peek at the top book without removing it
    public Book peekLastReturnedBook() {
        if (top != null) {
            return top.book;
        }
        return null;
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // Get all returned books (for display purposes)
    public BookArray getAllReturnedBooks() {
        BookArray books = new BookArray();
        StackNode current = top;

        while (current != null) {
            books.add(current.book);
            current = current.next;
        }

        return books;
    }

    // Get stack size
    public int size() {
        return count;
    }

    // Clear the stack (remove all books)
    public void clear() {
        top = null;
        count = 0;
        System.out.println("Stack cleared - all books removed");
    }

    // Display stack contents (top to bottom)
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        System.out.println("Stack (top to bottom):");
        StackNode current = top;
        int position = 1;

        while (current != null) {
            System.out.println(position + ". " + current.book.getTitle() +
                    " (ISBN: " + current.book.getIsbn() + ")");
            current = current.next;
            position++;
        }
    }
}

package com.library.main;

import com.library.models.Book;
import com.library.models.BorrowRequest;
import com.library.datastructures.BookBST;
import com.library.datastructures.BorrowRequestQueue;
import com.library.datastructures.ReturnStack;
import com.library.utils.LibraryUtils;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static BookBST bookCollection;
    private static BorrowRequestQueue requestQueue;
    private static ReturnStack returnStack;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Initialize data structures
        bookCollection = new BookBST();
        requestQueue = new BorrowRequestQueue();
        returnStack = new ReturnStack();
        scanner = new Scanner(System.in);

        // Add some sample books
        addSampleBooks();

        // Start the library system
        System.out.println("=== Welcome to Library Management System ===");

        boolean running = true;
        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewAllBooks();
                    break;
                case 2:
                    addNewBook();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    processRequests();
                    break;
                case 6:
                    viewReturnedBooks();
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using Library Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            System.out.println(); // Add blank line
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("=== Library Menu ===");
        System.out.println("1. View All Books");
        System.out.println("2. Add New Book");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Process Borrow Requests");
        System.out.println("6. View Returned Books");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addSampleBooks() {
        bookCollection.addBook(new Book("Java Programming", "Kusal Mendis", "ISBN-001", true));
        bookCollection.addBook(new Book("Data Structures", "Janith Liyanage", "ISBN-002", true));
        bookCollection.addBook(new Book("Algorithms", "Kamal Perera", "ISBN-003", true));
        System.out.println("Sample books added to library.");
    }

    private static void viewAllBooks() {
        System.out.println("\n=== All Books in Library (Sorted by ISBN) ===");
        System.out.println("Books are displayed in sorted order using IN-ORDER TRAVERSAL of BST");
        LibraryUtils.displayAllBooks(bookCollection.getAllBooks());
    }

    private static void addNewBook() {
        System.out.println("\n=== Add New Book ===");
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        Book newBook = new Book(title, author, isbn, true);
        bookCollection.addBook(newBook);
        System.out.println("Book added successfully!");
    }

    private static void borrowBook() {
        System.out.println("\n=== Borrow Book ===");
        System.out.print("Enter your User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        Book book = bookCollection.findBook(isbn);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (book.isAvailable()) {
            book.setAvailable(false);
            System.out.println("Book borrowed successfully!");
            System.out.println("You borrowed: " + book.getTitle());
        } else {
            // ENQUEUE: Add to request queue
            BorrowRequest request = new BorrowRequest(userId, isbn);
            System.out.println("Book is currently borrowed by someone else");
            System.out.println("Adding your request to the borrow queue...");
            requestQueue.enqueue(request);  // Using proper enqueue method
            System.out.println("Your request has been added to the queue.");
            System.out.println("   Position in queue: " + requestQueue.size());
            System.out.println("   You will be notified when the book becomes available.");
        }
    }

    private static void returnBook() {
        System.out.println("\n=== Return Book ===");
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        Book book = bookCollection.findBook(isbn);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }

        if (!book.isAvailable()) {
            book.setAvailable(true);
            returnStack.addReturnedBook(book);
            System.out.println("Book returned successfully!");
            System.out.println("You returned: " + book.getTitle());

            // AUTO-PROCESS QUEUE: Check if anyone is waiting for this book
            System.out.println("\nChecking if anyone is waiting for this book...");
            autoProcessQueueForBook(isbn);

        } else {
            System.out.println("This book was not borrowed!");
        }
    }

    // Automatically process queue when a book is returned
    private static void autoProcessQueueForBook(String returnedBookIsbn) {
        if (requestQueue.isEmpty()) {
            System.out.println("No one is waiting for any books.");
            return;
        }

        // Check if the first person in queue wants this returned book
        BorrowRequest nextRequest = requestQueue.peekNextRequest();
        if (nextRequest != null && nextRequest.getBookIsbn().equals(returnedBookIsbn)) {
            System.out.println("Perfect! The first person in queue wants this book!");
            System.out.println("Automatically processing their request...");

            // Dequeue and issue the book immediately
            BorrowRequest request = requestQueue.dequeue();
            Book book = bookCollection.findBook(returnedBookIsbn);
            if (book != null && book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("AUTOMATIC BOOK ISSUING:");
                System.out.println("   User " + request.getUserId() + " automatically received: " + book.getTitle());
                System.out.println("   No waiting time! Book transferred immediately.");
            }
        } else {
            System.out.println("The first person in queue wants a different book.");
            System.out.println("This returned book is now available for new borrowers.");
        }
    }

    private static void processRequests() {
        System.out.println("\n=== Processing Borrow Requests (Dequeue Operation) ===");
        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests in queue.");
            return;
        }

        System.out.println("Pending requests in queue: " + requestQueue.size());

        // DEQUEUE: Remove request from front and issue book
        BorrowRequest request = requestQueue.dequeue();
        if (request != null) {
            System.out.println("\n--- BOOK ISSUING PROCESS ---");
            System.out.println("Processing request from User: " + request.getUserId());
            System.out.println("Requested Book ISBN: " + request.getBookIsbn());
            System.out.println("Request submitted: " + request.getRequestTime());

            // Find the book
            Book book = bookCollection.findBook(request.getBookIsbn());
            if (book != null) {
                if (book.isAvailable()) {
                    // Issue the book (mark as borrowed)
                    book.setAvailable(false);
                    System.out.println("BOOK ISSUED SUCCESSFULLY!");
                    System.out.println("   User " + request.getUserId() + " received: " + book.getTitle());
                    System.out.println("   Book is now marked as borrowed.");
                } else {
                    System.out.println("Book still not available - re-adding to queue");
                    // Re-enqueue if book still not available
                    requestQueue.enqueue(request);
                }
            } else {
                System.out.println("Book not found in library system");
            }

            System.out.println("Remaining requests in queue: " + requestQueue.size());
        }
    }

    private static void viewReturnedBooks() {
        System.out.println("\n=== Recently Returned Books (Stack - LIFO Order) ===");
        if (returnStack.isEmpty()) {
            System.out.println("No books have been returned recently.");
            return;
        }

        System.out.println("Returned books count: " + returnStack.size());
        LibraryUtils.displayAllBooks(returnStack.getAllReturnedBooks());
    }
}

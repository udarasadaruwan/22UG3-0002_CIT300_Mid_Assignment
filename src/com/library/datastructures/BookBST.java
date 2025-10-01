package com.library.datastructures;

import com.library.models.Book;
import com.library.utils.BookArray;

public class BookBST {

    // Simple Node class for BST each node contains a book and links to children
    private class BSTNode {
        Book book;
        BSTNode left;
        BSTNode right;

        // constructor
        public BSTNode(Book book) {
            this.book = book;
            this.left = null; // No children initially
            this.right = null;
        }
    }

    private BSTNode root; // The top of the tree

    // Constructor - start with empty tree
    public BookBST() {
        root = null;
    }

    // Add a book to the BST - public method
    public void addBook(Book book) {
        root = insertBook(root, book);
    }

    // Helper method to insert a book in the right place
    private BSTNode insertBook(BSTNode currentNode, Book book) {
        // If we reached an empty spot, create new node here
        if (currentNode == null) {
            System.out.println("Adding book: " + book.getTitle());
            return new BSTNode(book);
        }

        // Compare ISBN to decide where to put the book
        String newISBN = book.getIsbn();
        String currentISBN = currentNode.book.getIsbn();

        if (newISBN.compareTo(currentISBN) < 0) {
            // New ISBN is smaller - go left
            currentNode.left = insertBook(currentNode.left, book);
        } else if (newISBN.compareTo(currentISBN) > 0) {
            // New ISBN is larger - go right
            currentNode.right = insertBook(currentNode.right, book);
        }
        // If ISBNs are equal, don't add duplicate

        return currentNode;
    }

    // Find a book by ISBN - public method
    public Book findBook(String isbn) {
        System.out.println("Searching for ISBN: " + isbn);
        return searchBook(root, isbn);
    }

    // Helper method to search for a book
    private Book searchBook(BSTNode currentNode, String targetISBN) {
        // If we reached end or empty tree, book not found
        if (currentNode == null) {
            return null;
        }

        String currentISBN = currentNode.book.getIsbn();

        if (targetISBN.equals(currentISBN)) {
            // Found it!
            System.out.println("Book found: " + currentNode.book.getTitle());
            return currentNode.book;
        } else if (targetISBN.compareTo(currentISBN) < 0) {
            // Target is smaller - look in left subtree
            return searchBook(currentNode.left, targetISBN);
        } else {
            // Target is larger - look in right subtree
            return searchBook(currentNode.right, targetISBN);
        }
    }

    // Get all books in sorted order using in-order traversal
    public BookArray getAllBooks() {
        BookArray bookArray = new BookArray();
        collectAllBooks(root, bookArray);
        return bookArray;
    }

    // Helper method to collect books in sorted order (Left -> Root -> Right)
    // This is IN-ORDER TRAVERSAL which gives sorted results
    private void collectAllBooks(BSTNode currentNode, BookArray bookArray) {
        if (currentNode != null) {
            // First, get all books from left side (smaller ISBNs)
            collectAllBooks(currentNode.left, bookArray);

            // Then, add current book
            bookArray.add(currentNode.book);

            // Finally, get all books from right side (larger ISBNs)
            collectAllBooks(currentNode.right, bookArray);
        }
    }

    // Remove a book by ISBN - simplified version
    public boolean removeBook(String isbn) {
        int sizeBefore = size();
        root = deleteBook(root, isbn);
        int sizeAfter = size();
        return sizeAfter < sizeBefore; // True if book was removed
    }

    // Helper method to delete a book - simplified
    private BSTNode deleteBook(BSTNode currentNode, String targetISBN) {
        // If node is null, nothing to delete
        if (currentNode == null) {
            return null;
        }

        String currentISBN = currentNode.book.getIsbn();

        if (targetISBN.compareTo(currentISBN) < 0) {
            // Target is smaller - delete from left side
            currentNode.left = deleteBook(currentNode.left, targetISBN);
        } else if (targetISBN.compareTo(currentISBN) > 0) {
            // Target is larger - delete from right side
            currentNode.right = deleteBook(currentNode.right, targetISBN);
        } else {
            // Found the node to delete!
            System.out.println("Removing book: " + currentNode.book.getTitle());

            // Case 1: No children - just remove it
            if (currentNode.left == null && currentNode.right == null) {
                return null;
            }
            // Case 2: Only right child - replace with right child
            else if (currentNode.left == null) {
                return currentNode.right;
            }
            // Case 3: Only left child - replace with left child
            else if (currentNode.right == null) {
                return currentNode.left;
            }
            // Case 4: Both children - find replacement from right side
            else {
                BSTNode replacement = findSmallestNode(currentNode.right);
                currentNode.book = replacement.book;
                currentNode.right = deleteBook(currentNode.right, replacement.book.getIsbn());
            }
        }

        return currentNode;
    }

    // Find the smallest node (leftmost node)
    private BSTNode findSmallestNode(BSTNode startNode) {
        while (startNode.left != null) {
            startNode = startNode.left;
        }
        return startNode;
    }

    // Check if BST is empty
    public boolean isEmpty() {
        return root == null;
    }

    // Get the total number of books using manual counting
    public int size() {
        return countNodes(root);
    }

    // Helper method to count nodes in BST
    private int countNodes(BSTNode currentNode) {
        if (currentNode == null) {
            return 0;
        }
        return 1 + countNodes(currentNode.left) + countNodes(currentNode.right);
    }
}

package com.library.datastructures;

import com.library.models.BorrowRequest;
import com.library.utils.RequestArray;

public class BorrowRequestQueue {
    // Simple Node class for Queue - like people standing in line
    private class QueueNode {
        BorrowRequest request; // The request stored in this node
        QueueNode next; // Link to next person in line

        // Simple constructor
        public QueueNode(BorrowRequest request) {
            this.request = request;
            this.next = null; // No one behind initially
        }
    }

    private QueueNode front; // First person in line
    private QueueNode rear; // Last person in line
    private int count; // How many people in line

    // Constructor - start with empty queue
    public BorrowRequestQueue() {
        front = null;
        rear = null;
        count = 0;
        System.out.println("New queue created - ready for requests!");
    }

    // ENQUEUE: Add a request to the queue (FIFO - joins at rear)
    public void enqueue(BorrowRequest request) {
        QueueNode newNode = new QueueNode(request);
        System.out.println("ENQUEUE: Adding request from user: " + request.getUserId());

        // If queue is empty, this request is both front and rear
        if (rear == null) {
            front = rear = newNode;
        } else {
            // Add new request to the rear of queue
            rear.next = newNode;
            rear = newNode;
        }
        count++;
        System.out.println("Queue size after enqueue: " + count);
    }

    // DEQUEUE: Remove and return the first request (FIFO - serves from front)
    // This simulates book being issued to the first person in queue
    public BorrowRequest dequeue() {
        // If queue is empty, no one to serve
        if (front == null) {
            System.out.println("DEQUEUE: Queue is empty - no requests to process");
            return null;
        }

        // Get the request from front of queue
        BorrowRequest request = front.request;
        System.out.println("DEQUEUE: Processing request from user: " + request.getUserId());
        System.out.println("        → Book being issued: " + request.getBookIsbn());

        // Move front pointer to next request
        front = front.next;

        // If queue becomes empty, reset rear pointer too
        if (front == null) {
            rear = null;
        }

        count--;
        System.out.println("Queue size after dequeue: " + count);
        return request;
    }

    // Legacy method for backward compatibility
    public void addRequest(BorrowRequest request) {
        enqueue(request);
    }

    // Legacy method for backward compatibility
    public BorrowRequest getNextRequest() {
        return dequeue();
    }

    // Peek at the front request without removing it
    public BorrowRequest peekNextRequest() {
        if (front != null) {
            return front.request;
        }
        return null;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Get all requests (for display purposes)
    public RequestArray getAllRequests() {
        RequestArray requests = new RequestArray();
        QueueNode current = front;

        while (current != null) {
            requests.add(current.request);
            current = current.next;
        }

        return requests;
    }

    // Get queue size
    public int size() {
        return count;
    }

    // Clear the queue (everyone leaves the line)
    public void clear() {
        front = null;
        rear = null;
        count = 0;
        System.out.println("Queue cleared - all requests removed");
    }

    // Display queue contents
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        System.out.print("Queue: ");
        QueueNode current = front;
        while (current != null) {
            System.out.print("[User: " + current.request.getUserId() +
                    ", Book: " + current.request.getBookIsbn() + "] ");
            current = current.next;
        }
        System.out.println();
    }
}

package com.library.utils;

import com.library.models.BorrowRequest;

/**
 * Custom dynamic array for BorrowRequest objects
 */
public class RequestArray {
    private BorrowRequest[] requests;
    private int size;
    private int capacity;

    public RequestArray() {
        capacity = 5;
        requests = new BorrowRequest[capacity];
        size = 0;
    }

    public void add(BorrowRequest request) {
        if (size >= capacity) {
            expandCapacity();
        }
        requests[size] = request;
        size++;
    }

    public BorrowRequest get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return requests[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public BorrowRequest[] toArray() {
        BorrowRequest[] result = new BorrowRequest[size];
        for (int i = 0; i < size; i++) {
            result[i] = requests[i];
        }
        return result;
    }

    private void expandCapacity() {
        int newCapacity = capacity * 2;
        BorrowRequest[] newRequests = new BorrowRequest[newCapacity];

        for (int i = 0; i < size; i++) {
            newRequests[i] = requests[i];
        }

        requests = newRequests;
        capacity = newCapacity;
    }

    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No requests available.");
            return;
        }

        for (int i = 0; i < size; i++) {
            BorrowRequest request = requests[i];
            System.out.println((i + 1) + ". User: " + request.getUserId() +
                    ", Book ISBN: " + request.getBookIsbn() +
                    ", Time: " + request.getRequestTime());
        }
    }
}
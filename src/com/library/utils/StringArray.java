package com.library.utils;

/**
 * Custom dynamic array for String objects (used for history)
 */
public class StringArray {
    private String[] strings;
    private int size;
    private int capacity;

    public StringArray() {
        capacity = 5;
        strings = new String[capacity];
        size = 0;
    }

    public void add(String str) {
        if (size >= capacity) {
            expandCapacity();
        }
        strings[size] = str;
        size++;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return strings[index];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String[] toArray() {
        String[] result = new String[size];
        for (int i = 0; i < size; i++) {
            result[i] = strings[i];
        }
        return result;
    }

    private void expandCapacity() {
        int newCapacity = capacity * 2;
        String[] newStrings = new String[newCapacity];

        for (int i = 0; i < size; i++) {
            newStrings[i] = strings[i];
        }

        strings = newStrings;
        capacity = newCapacity;
    }

    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No items available.");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + strings[i]);
        }
    }
}
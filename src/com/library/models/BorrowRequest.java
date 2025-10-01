package com.library.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BorrowRequest {
    private String userId;
    private String bookIsbn;
    private String requestTime;

    public BorrowRequest(String userId, String bookIsbn) {
        this.userId = userId;
        this.bookIsbn = bookIsbn;
        this.requestTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public String toString() {
        return "BorrowRequest{" +
                "userId='" + userId + '\'' +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", requestTime='" + requestTime + '\'' +
                '}';
    }
}

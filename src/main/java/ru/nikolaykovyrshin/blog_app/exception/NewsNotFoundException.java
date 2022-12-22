package ru.nikolaykovyrshin.blog_app.exception;

public class NewsNotFoundException extends Exception{

    public NewsNotFoundException(long id) {
        System.out.println("News with id " + id + " is not found");
    }
}

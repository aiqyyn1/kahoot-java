package com.example.project2;

public class InvalidQuizFormatException extends Throwable {
    String s;

    public InvalidQuizFormatException(String s) {
        super(s);
    }
}


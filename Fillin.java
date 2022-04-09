package com.example.project2;

class Fillin extends Question {


    public String toString() {
        return getDescription().replace("{blank}", "_____");
    }
}
package com.hassan.jokes.backend;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String jokeData;

    public String getData() {
        return jokeData;
    }

    public void setJokeData(String data) {
        jokeData = data;
    }
}
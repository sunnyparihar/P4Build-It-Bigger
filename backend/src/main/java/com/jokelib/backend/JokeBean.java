package com.jokelib.backend;

/** The object model for the data we are sending through endpoints */
public class JokeBean {

    private String joke;

    public String getJoke() {
        return joke;
    }

    public void setJoke(String data) {
        joke = data;
    }
}
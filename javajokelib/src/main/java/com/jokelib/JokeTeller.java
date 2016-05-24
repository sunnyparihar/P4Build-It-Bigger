package com.jokelib;

import java.util.Random;

public class JokeTeller {

    private String joke;

    private String[] jokes = {"This is joke number 1", "This is joke number 2", "This is joke number 3", "This is joke number 4", "This is joke number 5"};

    Random random = new Random();

    public String getJoke() {

        return jokes[random.nextInt(5)];

    }


}

package com.udacity.gradle.builditbigger;

import com.udacity.backend.myApi.model.JokeBean;


public interface CallBack {

    public void getJoke(JokeBean jokeBean);
}

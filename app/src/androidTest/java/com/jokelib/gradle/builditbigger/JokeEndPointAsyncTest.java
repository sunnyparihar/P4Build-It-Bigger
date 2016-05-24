package com.jokelib.gradle.builditbigger;

import android.content.Context;
import android.test.AndroidTestCase;

import com.udacity.backend.myApi.model.JokeBean;
import com.udacity.gradle.builditbigger.CallBack;
import com.udacity.gradle.builditbigger.async.JokeEndPoint;

import java.util.concurrent.CountDownLatch;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class JokeEndPointAsyncTest extends AndroidTestCase {

    private CountDownLatch countDownLatch=null;
    private Context context;



     JokeBean jokeData;

    public void testJokeEndPointAsync() throws InterruptedException{
        new JokeEndPoint(null, new CallBack() {
            @Override
            public void getJoke(JokeBean jokeBean) {
                    jokeData=jokeBean;
                    countDownLatch.countDown();

            }
        }).execute();

        countDownLatch.await();

        assertNotNull(jokeData);
        assertTrue(jokeData.getJoke().toString().length()>0);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        countDownLatch=new CountDownLatch(1);


    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
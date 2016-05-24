package com.udacity.gradle.builditbigger.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.backend.myApi.MyApi;
import com.udacity.backend.myApi.model.JokeBean;
import com.udacity.gradle.builditbigger.CallBack;

import java.io.IOException;


public class JokeEndPoint extends AsyncTask<String, Void, JokeBean> {


    Context mContext;
    private CallBack passJokeData;
    private ProgressDialog progressDialog;


    public JokeEndPoint(Context context, CallBack responseInterface) {

        this.passJokeData = responseInterface;
        this.mContext = context;

        if (context != null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setCancelable(false);
        }

    }

    @Override
    public JokeBean doInBackground(String... params) {

        JokeBean jokeBean = null;


        try {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null);
            MyApi jokeApi = builder.build();

            jokeBean = jokeApi.printJoke().execute();

        } catch (IOException e1) {

            e1.printStackTrace();
            System.out.println("Inside the io exception*****/" + e1.getMessage());

        } catch (Exception e) {
            System.out.println("Inside the io exception*wdwd****/" + e.getMessage());
        }
        return jokeBean;
    }

    @Override
    public void onPreExecute() {

        super.onPreExecute();
        if (progressDialog != null) {
            progressDialog.show();
            progressDialog.setMessage(("Loading Jokes..."));
        }
    }

    @Override
    public void onPostExecute(JokeBean response) {
        super.onPostExecute(response);
        if (progressDialog != null) {
            progressDialog.dismiss();
        }

        passJokeData.getJoke(response);
    }
}

package com.jokelib.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.jokelib.JokeDisplayActivity;
import com.udacity.backend.myApi.model.JokeBean;
import com.udacity.gradle.builditbigger.CallBack;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.async.JokeEndPoint;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {



    private CallBack callBack;
    private Button tellJoke;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        tellJoke=(Button)root.findViewById(R.id.tell_joke);
        tellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tellJoke(view);
            }
        });


        callBack=new CallBack() {
            @Override
            public void getJoke(JokeBean jokeBean) {

                //System.out.println("getting joke from server is*****/"+jokeBean.getJoke());
                // JokeTeller jokeTeller=new JokeTeller();

                Intent intent=new Intent(getActivity(), JokeDisplayActivity.class);
                intent.putExtra("jokeData",jokeBean.getJoke());
                startActivity(intent);
            }
        };
        return root;
    }

    private void tellJoke(View view){


        new JokeEndPoint(getActivity(),callBack).execute();

    }

}

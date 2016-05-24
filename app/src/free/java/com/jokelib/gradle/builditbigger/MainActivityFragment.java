package com.jokelib.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.jokelib.JokeDisplayActivity;
import com.udacity.backend.myApi.model.JokeBean;
import com.udacity.gradle.builditbigger.CallBack;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.async.JokeEndPoint;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private InterstitialAd mInterstitialAd;
    private CallBack callBack;
    private Button tellJoke;
    private AdRequest adRequest;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        tellJoke = (Button) root.findViewById(R.id.tell_joke);
        tellJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mInterstitialAd.loadAd(adRequest);
                tellJoke(view);
            }
        });

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(adRequest);

        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                new JokeEndPoint(getActivity(), callBack).execute();
            }
        });


        callBack = new CallBack() {
            @Override
            public void getJoke(JokeBean jokeBean) {

                System.out.println("getting joke from server is*****/" + jokeBean.getJoke());
                // JokeTeller jokeTeller=new JokeTeller();

                Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
                intent.putExtra("jokeData", jokeBean.getJoke());
                startActivity(intent);
            }
        };
        return root;
    }

    private void tellJoke(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else
            new JokeEndPoint(getActivity(), callBack).execute();
    }

}

package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.jokedisplaying.JokeActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    private InterstitialAd mInterstitialAd;
    private View progressBar;
    private Button jokeButton;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                loadJoke();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        progressBar = root.findViewById(R.id.pb_progress);

        jokeButton = root.findViewById(R.id.b_joke);
        jokeButton.setOnClickListener(this);

        AdView mAdView = root.findViewById(R.id.adView);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    @Override
    public void onClick(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else{
            loadJoke();
        }
    }

    private void loadJoke(){
        progressBar.setVisibility(View.VISIBLE);
        jokeButton.setEnabled(false);
        new EndpointsAsyncTask().execute(new EndpointsAsyncTask.Callback() {
            @Override
            public void resultJoke(String joke) {
                Intent jokeIntent = new Intent(getContext(), JokeActivity.class);
                jokeIntent.putExtra(JokeActivity.JOKE_EXTRA, joke);
                getContext().startActivity(jokeIntent);
                progressBar.setVisibility(View.GONE);
                jokeButton.setEnabled(true);
            }
        });
    }

}

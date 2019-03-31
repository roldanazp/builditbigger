package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.udacity.gradle.jokedisplaying.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button jokeButton = root.findViewById(R.id.b_joke);
        jokeButton.setOnClickListener(this);
        return root;
    }

    public void tellJoke(View view) {
        new EndpointsAsyncTask().execute(new EndpointsAsyncTask.Callback() {
            @Override
            public void resultJoke(String joke) {
                Intent jokeIntent = new Intent(getContext(), JokeActivity.class);
                jokeIntent.putExtra(JokeActivity.JOKE_EXTRA, joke);
                getContext().startActivity(jokeIntent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        new EndpointsAsyncTask().execute(new EndpointsAsyncTask.Callback() {
            @Override
            public void resultJoke(String joke) {
                Intent jokeIntent = new Intent(getContext(), JokeActivity.class);
                jokeIntent.putExtra(JokeActivity.JOKE_EXTRA, joke);
                getContext().startActivity(jokeIntent);
            }
        });
    }
}
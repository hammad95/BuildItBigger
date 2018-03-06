package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.hassan.jokes.backend.myApi.MyApi;
import com.hassan.lib.android.jokesLibAndroid.JokeActivity;
import com.hassan.lib.jokesLib.JokesLibClass;

import java.io.IOException;

/**
 * Created by Hassan on 2/10/2017.
 */

public class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Get a joke from the java jokeslib
        final JokesLibClass jokesLibClass = new JokesLibClass();
        final String JOKE = jokesLibClass.getJoke();

        // Launch JokeActivity in jokeslibandroid and pass the joke
        // from the java library as an intent extra
        Intent jokeActivityIntent = new Intent(context, JokeActivity.class);
        jokeActivityIntent.putExtra(JokeActivity.INTENT_EXTRA_KEY_JOKE, JOKE);
        context.startActivity(jokeActivityIntent);
    }
}

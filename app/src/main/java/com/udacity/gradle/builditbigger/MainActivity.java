package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hassan.lib.jokesLib.JokesLibClass;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        /* STEP 1 */
        // Get a joke from the java jokeslib and display as a toast
        final JokesLibClass jokesLibClass = new JokesLibClass();
        final String JOKE = jokesLibClass.getJoke();
        Toast.makeText(this, JOKE, Toast.LENGTH_SHORT).show();

/* STEP 2 */
//        // Launch JokeActivity in jokeslibandroid and pass the joke
//        // from the java library as an intent extra
//        Intent jokeActivityIntent = new Intent(this, JokeActivity.class);
//        jokeActivityIntent.putExtra(JokeActivity.INTENT_EXTRA_KEY_JOKE, JOKE);
//        startActivity(jokeActivityIntent);

        /* STEP 3 */
        // Run EndpointsAsyncTask to fetch a joke from the java library and launch
        // JokeActivity inside the android library to display the joke
        new EndpointsAsyncTask().execute(this);
    }
}

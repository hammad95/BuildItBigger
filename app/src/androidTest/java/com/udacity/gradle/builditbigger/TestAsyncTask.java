package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.test.mock.MockContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

/**
 * Created by Hassan on 2/10/2017.
 */

@RunWith(AndroidJUnit4.class)
public class TestAsyncTask {
    private EndpointsTestAsyncTask endpointsTestAsyncTask;
    private Context context;

    // Creates a task instance
    @Before
    public void createAsyncTask () {
        // Create a mock context for the task
        context = new MockContext();
        // Create a new AsyncTask
        endpointsTestAsyncTask = new EndpointsTestAsyncTask();
    }

    // Runs the task and checks the output
    @Test
    public void runTest() {
        // Run the task
        endpointsTestAsyncTask.execute(context);

        // Wait for the task to complete and then get the result
        try {
            String result = endpointsTestAsyncTask.get();
            // Make sure that the task returns a non-empty string
            if(result != null)
                Assert.assertNotEquals(result, "");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // This class extends our AsyncTask class but does not provide
    // any functionality inside the onPostExecute() method since
    // it is not required
    class EndpointsTestAsyncTask extends EndpointsAsyncTask {
        @Override
        public void onPostExecute(String result) {}
    }
}

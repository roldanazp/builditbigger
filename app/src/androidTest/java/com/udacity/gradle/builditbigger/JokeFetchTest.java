package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyString;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class JokeFetchTest {

    @Test
    public void useAppContext() throws InterruptedException {
        final CountDownLatch signal = new CountDownLatch(1);
        new EndpointsAsyncTask().execute(new EndpointsAsyncTask.Callback() {
            @Override
            public void resultJoke(String joke) {
                assertNotNull(joke);
                assertThat(joke, not(isEmptyString()));
                signal.countDown();
            }
        });
        signal.await();
    }
}

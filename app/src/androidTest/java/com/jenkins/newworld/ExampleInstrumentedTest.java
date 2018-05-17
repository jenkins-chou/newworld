package com.jenkins.newworld;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.jenkins.newworld.presenter.movie.MoviePresenter;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        MoviePresenter presenter = new MoviePresenter();
        Map<String,Object> params = new HashMap<>();
        params.put("start",10);
        presenter.getAllMovies(params);
    }
}

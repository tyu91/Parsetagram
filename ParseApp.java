package com.codepath.chattyboi.parsetagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("tiffo")
                .clientKey("password")
                .server("http://parsetagrambytiffo.herokuapp.com/parse/")
                .build();

        Parse.initialize(configuration);
    }
}

package rs.raf.projekatjun.ognjen_boskovic_rn9518.application;

import android.app.Application;

import timber.log.Timber;

public class EventManager extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}

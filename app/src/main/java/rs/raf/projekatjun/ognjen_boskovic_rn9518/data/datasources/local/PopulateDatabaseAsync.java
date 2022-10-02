package rs.raf.projekatjun.ognjen_boskovic_rn9518.data.datasources.local;

import android.os.AsyncTask;

public class PopulateDatabaseAsync extends AsyncTask<Void, Void, Void> {

    private final EventDao eventDao;

    PopulateDatabaseAsync(MyRoomDatabase myRoomDatabase) {
        eventDao = myRoomDatabase.eventDao();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }
}

package rs.raf.projekatjun.ognjen_boskovic_rn9518.data.datasources.local;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.models.local.Event;

@Database(version = 1,
        entities = {
                Event.class,
        },
        exportSchema = false
)
public abstract class MyRoomDatabase extends RoomDatabase {

    private static MyRoomDatabase singletonInstance;

    public abstract EventDao eventDao();

    public static MyRoomDatabase getDatabase(final Context context) {
        if (singletonInstance == null) {
            synchronized (MyRoomDatabase.class) {
                if (singletonInstance == null) {
                    singletonInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MyRoomDatabase.class,
                            "my_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return singletonInstance;
    }

    private static Callback callback =
            new Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDatabaseAsync(singletonInstance).execute();
                }
            };

}

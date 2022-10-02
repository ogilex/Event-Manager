package rs.raf.projekatjun.ognjen_boskovic_rn9518.data.datasources.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.models.local.Event;

@Dao
public interface EventDao {

    @Insert
    public long insert(Event event);

    @Query("SELECT * FROM events")
    public LiveData<List<Event>> getAll();

    @Delete
    void delete(Event... events);

}

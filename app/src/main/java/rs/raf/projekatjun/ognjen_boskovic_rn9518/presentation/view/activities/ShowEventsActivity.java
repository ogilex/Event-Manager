package rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import java.util.List;

import rs.raf.projekatjun.ognjen_boskovic_rn9518.R;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.datasources.local.MyRoomDatabase;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.models.local.Event;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.recycler.adapter.EventAdapter;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.recycler.diff.EventDiffItemCallback;

public class ShowEventsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);
        final MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(this);
        init(myRoomDatabase);
    }

    private void init(MyRoomDatabase myRoomDatabase){
        initView();
        initRoomDb(myRoomDatabase);
        initRecyclerView(myRoomDatabase);
    }

    private void initView(){
        recyclerView = findViewById(R.id.eventsRvShowEvents);
    }

    private void initRoomDb(MyRoomDatabase myRoomDatabase){

        myRoomDatabase.eventDao()
                .getAll().observe(
                ShowEventsActivity.this, new Observer<List<Event>>() {
                    @Override
                    public void onChanged(List<Event> events) {
                        eventAdapter.submitList(events);
                    }
                }
        );
    }

    private void initRecyclerView(MyRoomDatabase myRoomDatabase){
        eventAdapter = new EventAdapter(new EventDiffItemCallback(),
                v -> {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            myRoomDatabase.eventDao().delete(v);
                        }
                    }).start();
                    return null;
                },
                v -> {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT,
                            "Event name: " + v.getName()
                                    + "\n" + "Event description: " + v.getDescription()
                                    + "\n" + "Event time: " + v.getTime()
                                    +"\n" + "Event URL: " + v.getUrl());
                    intent.setType("text/plain");
                    PackageManager packageManager = getPackageManager();
                    ComponentName componentName = intent.resolveActivity(packageManager);
                    if(componentName != null){
                        Intent newIntent = Intent.createChooser(intent, "Choose a program");
                        startActivity(newIntent);
                    }
                    return null;
                });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(eventAdapter);
    }
}
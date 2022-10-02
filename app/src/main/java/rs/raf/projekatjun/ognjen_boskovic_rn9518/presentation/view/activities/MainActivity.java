package rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import rs.raf.projekatjun.ognjen_boskovic_rn9518.R;

public class MainActivity extends AppCompatActivity {

    private Button addEventBtn;
    private Button showEventsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        initView();
        initListeners();
    }

    private void initView(){
        addEventBtn = findViewById(R.id.addNewEventBtnMainActivity);
        showEventsBtn = findViewById(R.id.showEventsBtnMainActivity);
    }

    private void initListeners(){
        addEventBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEventActivity.class);
            startActivity(intent);
        });

        showEventsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ShowEventsActivity.class);
            startActivity(intent);
        });
    }
}
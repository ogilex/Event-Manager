package rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import rs.raf.projekatjun.ognjen_boskovic_rn9518.R;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.datasources.local.MyRoomDatabase;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.models.local.Event;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.data.models.remote.EasternStandardTimeModel;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.fragments.DatePickerFragment;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.fragments.TimePickerFragment;
import rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.viewmodel.WorldClockViewModel;

public class AddEventActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    public static final String REGION = "Europe";
    private EditText nameEt;
    private EditText descEt;
    private Button checkTimeBtn;
    private AutoCompleteTextView autoCompleteTextView;
    private Button setDateBtn;
    private Button setTimeBtn;
    private Spinner prioritySpinner;
    private EditText urlEt;
    private Button saveEventBtn;

    private WorldClockViewModel worldClockViewModel;
    private String priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        worldClockViewModel = ViewModelProviders.of(this).get(WorldClockViewModel.class);
        final MyRoomDatabase myRoomDatabase = MyRoomDatabase.getDatabase(this);

        init(myRoomDatabase);
    }

    private void init(MyRoomDatabase myRoomDatabase){
        initView();
        initListeners();
        initObservers();
        initRoomDb(myRoomDatabase);
    }

    private void initView(){
        nameEt = findViewById(R.id.eventNameEtAddEvent);
        descEt = findViewById(R.id.eventDescEtAddEvent);
        checkTimeBtn = findViewById(R.id.checkTimeBtnAddEvent);
        autoCompleteTextView = findViewById(R.id.autoCompleteTvAddEvent);
        setDateBtn = findViewById(R.id.setDateBtnAddEvent);
        setTimeBtn = findViewById(R.id.setTimeBtnAddEvent);
        prioritySpinner = findViewById(R.id.prioritySpinnerAddEvent);
        urlEt = findViewById(R.id.urlEtAddEvent);
        saveEventBtn = findViewById(R.id.saveEventBtnAddEvent);

        String[] cities = getResources().getStringArray(R.array.cities);
        ArrayAdapter<String> autoCompleteAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, cities);
        autoCompleteTextView.setAdapter(autoCompleteAdapter);
        
        
        String[] priorities = getResources().getStringArray(R.array.priorities);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, priorities);
        prioritySpinner.setAdapter(spinnerAdapter);
        prioritySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                priority = parent.getItemAtPosition(position).toString();
                //((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initListeners(){

        checkTimeBtn.setOnClickListener(v -> {
            worldClockViewModel.invokeCityService(REGION, autoCompleteTextView.getText().toString());
        });

        setDateBtn.setOnClickListener(v -> {
            DialogFragment dialogFragment = new DatePickerFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            dialogFragment.show(fragmentManager, "date_picker");
        });

        setTimeBtn.setOnClickListener(v -> {
            DialogFragment dialogFragment = new TimePickerFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            dialogFragment.show(fragmentManager, "time_picker");
        });
    }

    private void initObservers(){
        worldClockViewModel.getEasternStandardTime().observe(this, new Observer<EasternStandardTimeModel>() {
            @Override
            public void onChanged(EasternStandardTimeModel easternStandardTimeModel) {
                checkTimeBtn.setText(easternStandardTimeModel.getDatetime());
            }
        });
    }

    private void initRoomDb(MyRoomDatabase myRoomDatabase){


        saveEventBtn.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    long eventId = myRoomDatabase.eventDao().insert(new Event(
                            nameEt.getText().toString(),
                            descEt.getText().toString(),
                            setDateBtn.getText().toString(),
                            setTimeBtn.getText().toString(),
                            urlEt.getText().toString(),
                            priority));
                }
            }).start();
            nameEt.getText().clear();
            descEt.getText().clear();
            autoCompleteTextView.getText().clear();
            checkTimeBtn.setText(R.string.check_time_for_location);
            setDateBtn.setText(R.string.set_date);
            setTimeBtn.setText(R.string.set_time);
            urlEt.getText().clear();

        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int monthToDisplay = month + 1;
        setDateBtn.setText(dayOfMonth + "." + monthToDisplay + "." + year);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        setTimeBtn.setText("Hours: " + hourOfDay + ", minutes: " + minute );
    }
}
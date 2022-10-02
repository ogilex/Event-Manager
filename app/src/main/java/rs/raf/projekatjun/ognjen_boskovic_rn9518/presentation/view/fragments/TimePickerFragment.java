package rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import rs.raf.projekatjun.ognjen_boskovic_rn9518.R;

public class TimePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener listener =
                (TimePickerDialog.OnTimeSetListener) getActivity();
        return new TimePickerDialog(getActivity(), R.style.DialogFragmentStyle, listener, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }
}

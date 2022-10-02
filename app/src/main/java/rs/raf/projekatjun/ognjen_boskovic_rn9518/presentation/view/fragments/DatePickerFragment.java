package rs.raf.projekatjun.ognjen_boskovic_rn9518.presentation.view.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

import rs.raf.projekatjun.ognjen_boskovic_rn9518.R;

public class DatePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener listener =
                (DatePickerDialog.OnDateSetListener) getActivity();

        return new DatePickerDialog(getActivity(), R.style.DialogFragmentStyle, listener, year, month, day);
    }
}

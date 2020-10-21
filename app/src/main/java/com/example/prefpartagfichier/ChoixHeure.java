
package com.example.prefpartagfichier;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class ChoixHeure extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){

        return new TimePickerDialog(getActivity(), this, 14, 9, true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        TextView tvDate = getActivity().findViewById(R.id.txtDate);
        tvDate.setText(i + " " + i1);
    }
}

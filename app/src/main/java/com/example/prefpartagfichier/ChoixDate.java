
package com.example.prefpartagfichier;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ChoixDate extends DialogFragment implements DatePickerDialog.OnDateSetListener{




    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState){

        return new DatePickerDialog(getActivity(), this, 2020, 9, 30);
    }




    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        TextView tvDate = getActivity().findViewById(R.id.txtDate);
        tvDate.setText(i + " " + i1 + " " + i2);
    }





}

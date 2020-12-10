package com.example.aidoorganizer.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
//import androidx.support.annotation.RequiresApi;

import com.example.aidoorganizer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class ButtonsFragment extends Fragment {

    FloatingActionButton mrem, mcamera, maudio, mhand, mnote;
    TextView trem,tcam,taudio,thand,tnote, myCustomMessage;
    EditText etTime,etDate;
    private Button datePickerAlertDialog;
    private Button timePickerAlertDialog;
    Calendar calSet,calNow;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_buttons, container, false);
        mrem = view. findViewById(R.id.add_reminder);
        mcamera = view.findViewById(R.id.add_camera);
        maudio = view.findViewById(R.id.add_audio);
        mhand = view.findViewById(R.id.add_handwriting);
        mnote = view.findViewById(R.id.add_note);

        trem = view.findViewById(R.id.add_reminder_text);
        tcam = view.findViewById(R.id.add_camera_text);
        taudio = view.findViewById(R.id.add_audio_text);
        thand = view.findViewById(R.id.add_hand_text);
        tnote = view.findViewById(R.id.add_note_text);
        myCustomMessage = view.findViewById(R.id.myCustommessage);
        calNow = Calendar.getInstance();
        calSet = (Calendar) calNow.clone();

        mrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_showMessage(view);
            }
        });
        return view;
        // Inflate the layout for this fragment
    }
    public void btn_showMessage(View view){
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        View mView = getLayoutInflater().inflate(R.layout.remind_dialog,null);
        final EditText txt_inputText = (EditText)mView.findViewById(R.id.txt_input);
        EditText etDate = (EditText)mView.findViewById(R.id.txt_date);
        EditText etTime = (EditText) mView.findViewById(R.id.txt_time);
        Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel);
        Button btn_okay = (Button)mView.findViewById(R.id.btn_okay);
        Button btn_time = (Button)mView.findViewById(R.id.btn_time);
        Button btn_date = (Button)mView.findViewById(R.id.btn_date);
        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        btn_date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                etDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                etTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCustomMessage.setText(txt_inputText.getText().toString());
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
package com.example.aidoorganizer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class ButtonsActivity extends AppCompatActivity {

    FloatingActionButton mrem, mcamera, maudio, mhand, mnote;
    TextView trem,tcam,taudio,thand,tnote, myCustomMessage,etTime;
    Calendar calSet,calNow;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String date_time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_buttons);

        mrem = findViewById(R.id.add_reminder);
        mcamera = findViewById(R.id.add_camera);
        maudio = findViewById(R.id.add_audio);
        mhand = findViewById(R.id.add_handwriting);
        mnote = findViewById(R.id.add_note);

        calNow = Calendar.getInstance();
        calSet = (Calendar) calNow.clone();
//        myCustomMessage = (TextView)findViewById(R.id.myCustommessage);

        mrem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_showMessage(view);
            }
        });
        mnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDrugInfo = new Intent(ButtonsActivity.this, NoteActivity.class);
                startActivity(intentDrugInfo);
            }
        });
    }
    public void btn_showMessage(View view){
        final AlertDialog.Builder alert = new AlertDialog.Builder(ButtonsActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.remind_dialog,null);
        final EditText txt_inputText = (EditText)mView.findViewById(R.id.txt_input);
        Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel);
        Button btn_okay = (Button)mView.findViewById(R.id.btn_okay);
        EditText etDate = (EditText)mView.findViewById(R.id.txt_date);
        EditText etTime = (EditText) mView.findViewById(R.id.txt_time);
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
                DatePickerDialog datePickerDialog = new DatePickerDialog(ButtonsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                etDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                                date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
//                                //*************Call Time Picker Here ********************
//                                tiemPicker();

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
                TimePickerDialog timePickerDialog = new TimePickerDialog(ButtonsActivity.this,
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
//                myCustomMessage.setText(txt_inputText.getText().toString());
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}

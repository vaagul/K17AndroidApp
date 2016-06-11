package com.k17.vagul.displayinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.text.InputType;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

    //UI References
    private EditText fromDateEtxt;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;

    void disp()
    {
        Intent myIntent = new Intent( this, Dashboard.class);
        final EditText iname = (EditText)findViewById(R.id.editText);
        final EditText iage = (EditText)findViewById(R.id.editText2);
        final EditText idob = (EditText)findViewById(R.id.etxt_fromdate);
        String n = iname.getText().toString();
        String a = iage.getText().toString();
        String d = idob.getText().toString();
        myIntent.putExtra("sendName",n);
        myIntent.putExtra("sendAge", a);
        myIntent.putExtra("sendDob",d);
        startActivity(myIntent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();

        Button button=(Button) findViewById(R.id.button);
        final EditText name = (EditText)findViewById(R.id.editText);
        final EditText age = (EditText)findViewById(R.id.editText2);
        final EditText dob = (EditText)findViewById(R.id.etxt_fromdate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* String n = name.getText().toString();
                String a = age.getText().toString();
                String d = dob.getText().toString();

                Toast.makeText(MainActivity.this,  "hey "+n+", your age is "+a , Toast.LENGTH_SHORT).show();
                */
                disp();
            }

        });
    }

    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.etxt_fromdate);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        }
    }
}
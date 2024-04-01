package com.zybooks.clockapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView textDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create calendar object, grab current time, and implement date format Month-Day-Year.
        textDate = (TextView) findViewById(R.id.editTextDate);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM - dd - yyyy");
        String dateTime = dateFormat.format(calendar.getTime()).toString();
        textDate.setText(dateTime);
    }
}


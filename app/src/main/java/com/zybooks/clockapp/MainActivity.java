package com.zybooks.clockapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView textDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create calendar object, grab current time and date, and implement date format "Day, Month Day(numerical)".
        textDate = (TextView) findViewById(R.id.editTextDate);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMM d");
        String dateTime = dateFormat.format(calendar.getTime()).toString();
        textDate.setText(dateTime);
    }

    public void onTimerClick(View view) {
        Intent intent = new Intent(this, Timer.class);
        startActivity(intent);
    }
}


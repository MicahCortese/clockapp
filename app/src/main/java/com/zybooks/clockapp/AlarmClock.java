package com.zybooks.clockapp;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.zybooks.clockapp.databinding.ActivityMainBinding;

import java.util.Calendar;

public class AlarmClock extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MaterialTimePicker picker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        createNotificationChannel();
    }
        // Radio button to call showTimePicker when the selectedTime button is pressed
//        binding.selectedTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//                showTimePicker();

//            }
//        });

        // Radio button to call setAlarm when the setAlarmBtn is pressed
//        binding.setAlarmBtn.setOnclickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//                setAlarm();

//           }
//        });

        // Radio button to call cancelAlarm when the cancelAlarmBtn is pressed
//        binding.cancelAlarmBtn.setOnlickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//                cancelAlarm();

//            }
//        });
//    }

    // Handles the creation of alarm depending on current values in showTimePicker
        private void setAlarm() {

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,pendingIntent);

        Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT).show();
    }

    // Method to display time picker function
    private void showTimePicker() {

        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();

        picker.show(getSupportFragmentManager(), "Group2alarm");

        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (picker.getHour() > 12) {

                    //binding.selectedTime.setText(
                    //       String.format("%02d",(picker.getHour()-12) + " : " + String.format("%02d",picker.getMinute()) + " PM")
                    //);

                } else {

                    //binding.selectedTime.setText(picker.getHour() + " : " + picker.getMinute() + " AM");

                }

                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar.set(Calendar.MINUTE,picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);

            }
        });

    }

    // Method called when user wishes to cancel a set alarm
    private void cancelAlarm() {

        Intent intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        if (alarmManager == null) {

            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        }

        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();

    }

    // Method that handles the creation of notification channels when creating an alarm notification.
    private void createNotificationChannel() {

        //if  (Build.VERSION.SDK_INT >= Build.VERSION_CODES.0) {
            CharSequence name = "Group2ReminderChannel";
            String description = "Channel for Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("Group2alarm",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        //}

    }
}
package com.example.demouser.dining;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.widget.TextView;

import java.sql.Time;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private TextView countDownTextView;
    CountDownTimer countDownTimer;

    private int i;
    private LocalTime current;
    private LocalDate currentDate;
    String day;

    HashMap<String, ArrayList<Pair<Time,Time>>> newDorm;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataCreate();

        current = LocalTime.now();
        String timeNow = current.toString();

        currentDate = LocalDate.now();
        day = currentDate.getDayOfWeek().toString().toLowerCase();

        Log.d("Maria",day.toString());
        Log.d("Vinty", timeNow);


        ArrayList<Pair<Time,Time>> timesforDay = newDorm.get(day);




        int minutes = 1;
        int milliseconds = minutes * 60 * 1000;

        countDownTimer = new CountDownTimer(milliseconds, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                countDownTextView.setText(String.format("%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

            }
            public void onFinish() {
                countDownTextView.setText("Time Up!");
            }
        };

    }



    private void dataCreate(){ // creating hashmaps of schedule

        //NEW DORM
        newDorm = new HashMap<>();
        ArrayList<Pair<Time,Time>> list1 = new ArrayList<>();
        ArrayList<Pair<Time,Time>> list2 = new ArrayList<>();
        ArrayList<Pair<Time,Time>> list3 = new ArrayList<>();

        Pair<Time, Time> pair1 = new Pair<>(new Time(12,00,00),new Time(20,00,00)); //Weekday
        Pair<Time, Time> pair2 = new Pair<>(new Time(12,00,00),new Time(18,30,00)); // Saturday
        Pair<Time, Time> pair3 = new Pair<>(new Time(11,00,00),new Time(18,30,00)); // Sunday
        list1.add(pair1);
        list2.add(pair2);
        list3.add(pair3);
        newDorm.put("monday",list1);
        newDorm.put("tuesday",list1);
        newDorm.put("wednesday",list1);
        newDorm.put("thursday",list1);
        newDorm.put("friday",list2);
        newDorm.put("saturday",list3);
        newDorm.put("sunday",list3);




    }

}

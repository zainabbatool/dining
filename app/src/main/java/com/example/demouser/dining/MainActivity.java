package com.example.demouser.dining;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Pair;
import android.widget.TextView;

import java.sql.Time;
import java.time.Clock;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private int i;
<<<<<<< HEAD
    //private LocalTime current;
=======
>>>>>>> 0ca6c973145f230cc1db31f2145a1989816170d4


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataCreate();
        LocalTime currentTime = LocalTime.now();
        String timeNow = currentTime.toString();
        Log.d("Vinty", timeNow);

    }

    public void dataCreate(){
        HashMap<String, ArrayList<Pair<Time,Time>>> newDorm = new HashMap<>();
        ArrayList<Pair<Time,Time>> list1 = new ArrayList<>();
        ArrayList<Pair<Time,Time>> list2 = new ArrayList<>();
        ArrayList<Pair<Time,Time>> list3 = new ArrayList<>();

        Pair<Time, Time> pair1 = new Pair<>(new Time(12,00,00),new Time(20,00,00));
        Pair<Time, Time> pair2 = new Pair<>(new Time(12,00,00),new Time(18,30,00));
        Pair<Time, Time> pair3 = new Pair<>(new Time(11,00,00),new Time(18,30,00));
        list1.add(pair1);
        list2.add(pair2);
        list3.add(pair3);
        newDorm.put("Monday",list1);
        newDorm.put("Tuesday",list1);
        newDorm.put("Wednesday",list1);
        newDorm.put("Thursday",list1);
        newDorm.put("Friday",list2);
        newDorm.put("Saturday",list3);
        newDorm.put("Sunday",list3);

    }

}

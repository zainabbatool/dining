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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static android.telephony.PhoneNumberUtils.formatNumber;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private int i;
    private int toCount;

    private LocalDate currentDate; //
    String day;
    HashMap<String, ArrayList<Pair<String,String>>> newDorm;
    TextView bla;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataCreate();
        bla = (TextView) findViewById(R.id.countdown);

        LocalTime currentTime = LocalTime.now();
        String timeNow = currentTime.toString();

        currentDate = LocalDate.now();
        day = currentDate.getDayOfWeek().toString().toLowerCase(); // current date

        Pair<String,String> timesforDay = (newDorm.get(day)).get(0);


        Long timeDifference = checkRange(timeNow,timesforDay.first,timesforDay.second); // current time difference
        countDown(timeDifference);
        Log.d("blah",timeDifference.toString());




        //Log.d("Vinty", timeNow);
        //get day, get corresponding time,

        /*new CountDownTimer(number, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextField.setText( + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTextField.setText("done!");
            }
        }.start();*/






    }



    //Pair<String,String> timesforDay = (newDorm.get(day)).get(0);

       public void dataCreate(){
        newDorm = new HashMap<>();
        ArrayList<Pair<String,String>> list1 = new ArrayList<>();
        ArrayList<Pair<String,String>> list2 = new ArrayList<>();
        ArrayList<Pair<String,String>> list3 = new ArrayList<>();

        Pair<String, String> pair1 = new Pair<>("12:00:00","20:00:00");
        Pair<String, String> pair2 = new Pair<>("12:00:00","18:30:00");
        Pair<String, String> pair3 = new Pair<>("11:00:00","18:30:00");
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
    private void countDown (long timeRemaining){
        final String closeOrOpen = (String) bla.getText();

//        int hours= (int)(timeRemaining/1000/60/60);
//        int minutes= (int)((timeRemaining-(hours*60*60*1000))/1000/60);
//        int seconds= (int)((timeRemaining-(hours*60*60*1000)-(minutes*60*1000))/1000);

        CountDownTimer timer = new CountDownTimer(timeRemaining, 1000) {

//            public void onTick(long millisUntilFinished) {
//                bla.setText("Time remaining: " + String.format("%d hours: %d min: %d sec",
//                        TimeUnit.MICROSECONDS.toMinutes( millisUntilFinished),
//                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
//                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
//            }
//        });

//            @Override
//            public void onTick(long millisUntilFinished) {
//                bla.setText(String.format("%02d:%02d:%02d",
//                //bla.setText(String.format("%02d:%02d:%02d",
//                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
//                        //,
//                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
//                        (TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
//                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))));
//            }


            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;//convert to seconds
                long minutes = seconds / 60;//convert to minutes
                long hours = minutes / 60;//convert to hours

                if (minutes > 0)//if we have minutes, then there might be some remainder seconds
                {
                    seconds = seconds % 60;//seconds can be between 0-60, so we use the % operator to get the remainder
                }
                if (hours > 0) {
                    minutes = minutes % 60;//similar to seconds
                }
                String time = formatNumber(hours) + ":" + formatNumber(minutes) + ":" +
                        formatNumber(seconds);
                //TextView getCountDown = (TextView) findViewById(R.id.countdown);
                String newCountDown = closeOrOpen + "\n" + time;
                bla.setText(newCountDown);//set value to text
            }
            private String formatNumber(long value){
                if(value < 10)
                    return "0" + value;
                return value + "";
            }

            public void onFinish() {
            }
        }.start();
    }

    public long checkRange(String valueToCheck, String startTime, String endTime) {
        //boolean isBetween = false;
        long toCount = 0;
        try {
            Date end = new SimpleDateFormat("HH:mm:ss").parse(endTime);

            Date start = new SimpleDateFormat("HH:mm:ss").parse(startTime);

            Date currentTime = new SimpleDateFormat("HH:mm:ss").parse(valueToCheck);

            if (end.after(currentTime) && start.before(currentTime)) {
                toCount =end.getTime()-currentTime.getTime();
                bla.setText("Time until New Dorm closes");
                //isBetween = true;
            }
            else{
                toCount = start.getTime()-currentTime.getTime();
                if(toCount<0){
                    toCount= 86400000 - (-1 * toCount);
                    bla.setText("Time until New Dorm opens");
                }
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return toCount;
    }

}

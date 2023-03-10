package edu.scu.kawa.advancebmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        ArrayList<String> dateList = new ArrayList<>();
//        dateList.add("2020/01/01 01:23");
//        dateList.add("2020/01/02 02:34");
//        dateList.add("2020/01/03 03:45");
//        dateList.add("2020/01/04 04:56");


        ArrayList<String> bmiList = new ArrayList<>();
//        bmiList.add("123.1");
//        bmiList.add("123.2");
//        bmiList.add("123.3");
//        bmiList.add("123.4");

        SharedPreferences settings = getSharedPreferences("INFO", MODE_PRIVATE);
        String allHistoryString = settings.getString("BMI_HISTORY", null);

        if (allHistoryString != null) {
            String[] historyList = allHistoryString.split(";");

            for (String historyString : historyList) {
                String[] history = historyString.split(",");
                String date = history[0];
                String bmi = history[1];
                dateList.add(date);
                bmiList.add(bmi);
            }
        }

        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, dateList, bmiList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}
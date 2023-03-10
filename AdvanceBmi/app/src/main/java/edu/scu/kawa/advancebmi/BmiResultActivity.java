package edu.scu.kawa.advancebmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BmiResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);

        Bundle bundle = getIntent().getExtras();
        double height = bundle.getDouble("height");
        double weight = bundle.getDouble("weight");

        double bmi = calculateBmi(height, weight);
        updateBmiResult(bmi);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putDouble("bmi", bmi);

                Intent intent = new Intent();
                intent.putExtras(bundle);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Button backButton = findViewById(R.id.backButton);
        backButton.performClick();
    }

    double calculateBmi(double height, double weight) {
        double heightMeter = height / 100;

        double bmi = weight / Math.pow(heightMeter, 2);

        bmi = bmi * 100;
        bmi = Math.round(bmi);
        bmi = bmi / 100;

        Log.d("Kawa", "BMI為" + bmi);
        return bmi;
    }

    void updateBmiResult(double bmi) {

        saveToHistory(bmi);

        TextView resultTextView = findViewById(R.id.resultTextView);
        ImageView resultImageView = findViewById(R.id.resultImageView);

        if (bmi < 18.5) {
            resultTextView.setText("BMI值為：" + bmi + "(過輕)");
            resultImageView.setImageDrawable(ContextCompat.getDrawable(BmiResultActivity.this, R.drawable.bmi_level_01));
        } else if (18.5 <= bmi && bmi < 25) {
            resultTextView.setText("BMI值為：" + bmi + "(正常)");
            resultImageView.setImageDrawable(ContextCompat.getDrawable(BmiResultActivity.this, R.drawable.bmi_level_02));
        } else if (25 <= bmi && bmi < 30) {
            resultTextView.setText("BMI值為：" + bmi + "(過重)");
            resultImageView.setImageDrawable(ContextCompat.getDrawable(BmiResultActivity.this, R.drawable.bmi_level_03));
        } else if (30 <= bmi && bmi < 35) {
            resultTextView.setText("BMI值為：" + bmi + "(肥胖)");
            resultImageView.setImageDrawable(ContextCompat.getDrawable(BmiResultActivity.this, R.drawable.bmi_level_04));
        } else {
            resultTextView.setText("BMI值為：" + bmi + "(太胖啦)");
            resultImageView.setImageDrawable(ContextCompat.getDrawable(BmiResultActivity.this, R.drawable.bmi_level_05));
        }
    }

    void saveToHistory(double bmi) {
        String nowDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String historyString = nowDate + "," + bmi;

        SharedPreferences settings = getSharedPreferences("INFO", MODE_PRIVATE);
        String allHistoryString = settings.getString("BMI_HISTORY", "");

        if (allHistoryString.length() == 0) {
            allHistoryString = historyString;
        }
        else {
            allHistoryString = allHistoryString + ";" + historyString;
        }

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("BMI_HISTORY", allHistoryString);
        editor.commit();
    }
}
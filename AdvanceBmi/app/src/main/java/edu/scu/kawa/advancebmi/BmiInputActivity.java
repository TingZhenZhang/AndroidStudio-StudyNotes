package edu.scu.kawa.advancebmi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class BmiInputActivity extends AppCompatActivity {

    public static int FUNCTION_BMI_RESULT = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_input);

        EditText heightEditText = findViewById(R.id.heightEditText);
        EditText weightEditText = findViewById(R.id.weightEditText);

        double defaultHeight = loadHeight();
        double defaultWeight = loadWeight();

        if (defaultHeight != -1) {
            heightEditText.setText(defaultHeight + "");
        }

        if (defaultWeight != -1) {
            weightEditText.setText(defaultWeight + "");
        }

        Button bmiButton = findViewById(R.id.bmiButton);

        bmiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String heightString = heightEditText.getText().toString();
                String weightString = weightEditText.getText().toString();

                double height = Double.parseDouble(heightString);
                double weight = Double.parseDouble(weightString);

                save(height, weight);

                hideKeyboard();

                Bundle bundle = new Bundle();
                bundle.putDouble("height", height);
                bundle.putDouble("weight", weight);

                Intent intent = new Intent();
                intent.setClass(BmiInputActivity.this, BmiResultActivity.class);

                intent.putExtras(bundle);
                //startActivity(intent);
                startActivityForResult(intent, FUNCTION_BMI_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FUNCTION_BMI_RESULT){
            if (resultCode == RESULT_OK){

                Bundle bundle = data.getExtras();
                double bmi = bundle.getDouble("bmi");

                Log.d("Kawa", "計算出來的BMI結果為：" + bmi);
            }else{
                finish();
            }
        }

    }

    void hideKeyboard() {
        try {
            InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        catch (Exception ex) {
            Log.d("Kawa", "沒有鍵盤可以收起來喔！");
        }

   }

    void save(double height, double weight) {
        SharedPreferences settings = getSharedPreferences("BMI", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat("height", (float)height);
        editor.putFloat("weight", (float)weight);

        editor.commit();
    }

    double loadHeight() {
        SharedPreferences settings = getSharedPreferences("BMI", MODE_PRIVATE);
        double height = settings.getFloat("height", -1);
        return height;
    }

    double loadWeight() {
        SharedPreferences settings = getSharedPreferences("BMI", MODE_PRIVATE);
        double weight = settings.getFloat("weight", -1);
        return weight;
    }
}
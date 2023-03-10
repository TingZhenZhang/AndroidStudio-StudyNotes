package edu.scu.kawa.advancebmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button function1Button = findViewById(R.id.function1Button);

        function1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, BmiInputActivity.class);
                startActivity(intent);
            }
        });

        Button function2Button = findViewById(R.id.function2Button);

        function2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ScrollViewDemoActivity.class);
                startActivity(intent);
            }
        });

        Button function3Button = findViewById(R.id.function3Button);

        function3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ScrollViewDemo2Activity.class);
                startActivity(intent);
            }
        });

        Button function4Button = findViewById(R.id.function4Button);

        function4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SharedPreferencesDemoActivity.class);
                startActivity(intent);
            }
        });

        Button function5Button = findViewById(R.id.function5Button);

        function5Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
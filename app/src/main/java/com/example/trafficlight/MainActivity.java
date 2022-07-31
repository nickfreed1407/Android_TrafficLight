package com.example.trafficlight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Button;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private LinearLayout Lamp1, Lamp2, Lamp3;
    private boolean startStop = false;
    private Button butt1;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Lamp1 = findViewById(R.id.Lamp1);
        Lamp2 = findViewById(R.id.Lamp2);
        Lamp3 = findViewById(R.id.Lamp3);
        butt1 = findViewById(R.id.button4);
    }
    public void onStart(View view){
        if(!startStop){
            startStop = true;
            butt1.setText("STOP");
            new Thread((Runnable) ()->{
                while(startStop){
                    counter++;
                    switch(counter){
                        case 1:
                            Lamp1.setBackgroundColor(getResources().getColor(R.color.Red));
                            Lamp3.setBackgroundColor(getResources().getColor(R.color.Default));
                            break;
                        case 2:
                            Lamp2.setBackgroundColor(getResources().getColor(R.color.Yellow));
                            Lamp1.setBackgroundColor(getResources().getColor(R.color.Default));
                            break;
                        case 3:
                            Lamp3.setBackgroundColor(getResources().getColor(R.color.Green));
                            Lamp2.setBackgroundColor(getResources().getColor(R.color.Default));
                            counter = 0;
                            break;
                    }
                    try {
                        Thread.sleep(300);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }else {
            startStop = false;
            butt1.setText("START");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startStop = false;
    }
}
package com.jk0myn0.dynsys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button launchSchiabo = (Button) findViewById(R.id.launchSchiabo);
        launchSchiabo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SchiaboMainActivity.class);
                startActivity(intent);
            }
        });

        Button launchOwski = (Button) findViewById(R.id.launchOwski);
        launchOwski.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), OwskiMainActivity.class);
                startActivity(intent);
            }
        });

        Button launchSapo = (Button) findViewById(R.id.launchSapo);
        launchSapo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SapoMainActivity.class);
                startActivity(intent);
            }
        });
    }

}

package com.example.ry6d3.myapplication;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.wowwee.robome.RoboMe;
import com.wowwee.robome.RoboMeCommands;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends WearableActivity implements RoboMe.RoboMeListener {

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;
    private TextView mTextView;
    private TextView mClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);
        mTextView = (TextView) findViewById(R.id.text);
        mClockView = (TextView) findViewById(R.id.clock);
        Button wearButton = (Button) findViewById(R.id.wearButton);
        wearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                int notificationId = 100;
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.common_plus_signin_btn_text_dark)
                        .setContentTitle("First Android Notification")
                        .setContentText("Testing Wearable Notifcation");
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity.this);

                notificationManagerCompat.notify(notificationId,notificationBuilder.build());

            }
        });


    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mTextView.setTextColor(getResources().getColor(android.R.color.white));
            mClockView.setVisibility(View.VISIBLE);

            mClockView.setText(AMBIENT_DATE_FORMAT.format(new Date()));
        } else {
            mContainerView.setBackground(null);
            mTextView.setTextColor(getResources().getColor(android.R.color.black));
            mClockView.setVisibility(View.GONE);
        }
    }

    @Override
    public void commandReceived(RoboMeCommands.IncomingRobotCommand incomingRobotCommand) {

    }

    @Override
    public void roboMeConnected() {

    }

    @Override
    public void roboMeDisconnected() {

    }

    @Override
    public void headsetPluggedIn() {

    }

    @Override
    public void headsetUnplugged() {

    }

    @Override
    public void volumeChanged(float v) {

    }

}

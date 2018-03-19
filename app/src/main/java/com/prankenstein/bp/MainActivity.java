package com.prankenstein.bp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    ImageView iv_fingure_scan, iv_bar, iv_scanner;
    long then = 0;
    GifImageView gview;
    CountDownTimer timer;
    InterstitialAd mInterstitialAd; // Full-screen banner
    ProgressDialog pd;
    Vibrator mVibrate;
    LinearLayout loading_layout;
    ImageView iv_loading1, iv_loading2, iv_loading3, iv_loading4, iv_loading5;
    TextView tv;
    int I = 60;
    int TimeCounter = 0;
    String soundCheck = "check", vibrationCheck = "check";
    MediaPlayer mPlayer;
    ToggleButton soundToggleButton;
    ToggleButton vibrationToggleButton;
    SessionManagement pref;
    long pattern[] = {0, 500, 100, 200, 100, 200, 300, 500, 100, 200, 100, 200, 200, 500, 100, 200, 100, 200, 300, 500, 100, 200, 300, 400};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundToggleButton = (ToggleButton) findViewById(R.id.toggleButton2);
        vibrationToggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
        pref = new SessionManagement(getApplicationContext());

        AdView mAdView = (AdView) findViewById(R.id.adView);    //Banner Ads
        AdRequest adRequest = new AdRequest.Builder().build();  //Banner Ads
        mAdView.loadAd(adRequest);                              //Banner Ads

        if (pref.issoundCheckedd()) {
            soundToggleButton.setChecked(false);

        } else {
            soundToggleButton.setChecked(true);

        }
        if (pref.isvibCheckedd()) {
            vibrationToggleButton.setChecked(false);

        } else {
            vibrationToggleButton.setChecked(true);

        }
        // Toast.makeText(getApplicationContext(),soundCheck+ "  "+vibrationCheck,Toast.LENGTH_SHORT).show();


        soundToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundToggleButton.isChecked()) {
                    pref.setsoundCheckedd(false);
                } else {
                    pref.setsoundCheckedd(true);
                }
                //Button is OFF.
            }
        });

        vibrationToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vibrationToggleButton.isChecked()) {
                    pref.setvibCheckedd(false);
                } else {
                    pref.setvibCheckedd(true);
                }
                //Button is OFF

            }
        });

        mVibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        tv = (TextView) findViewById(R.id.tv);
        loading_layout = (LinearLayout) findViewById(R.id.loading_layout);
        iv_loading1 = (ImageView) findViewById(R.id.iv_loading1);
        iv_loading2 = (ImageView) findViewById(R.id.iv_loading2);
        iv_loading3 = (ImageView) findViewById(R.id.iv_loading3);
        iv_loading4 = (ImageView) findViewById(R.id.iv_loading4);
        iv_loading5 = (ImageView) findViewById(R.id.iv_loading5);

// 2nd argument is for repetition pass -1 if you do not want to repeat the Vibrate

        //Full ads
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5780541699123399/5415458656");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });


        iv_fingure_scan = (ImageView) findViewById(R.id.iv_fingure_scan);
        iv_bar = (ImageView) findViewById(R.id.iv_bar);

        gview = (GifImageView) findViewById(R.id.gif_viewww);
        gview.setImageResource(R.drawable.scanner_gif);

        iv_scanner = (ImageView) findViewById(R.id.iv_scanner);
        //  gifImageView.setGifImageResource(R.drawable.scanner_gif);
        final Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fingerscan);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv_bar.setVisibility(View.GONE);
            }


            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
//        iv_fingure_scan.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                iv_bar.setVisibility(View.VISIBLE);
//                iv_bar.startAnimation(animation);
//                return false;
//            }
//        });

        timer = new CountDownTimer(6200, 1000) {
            public void onTick(long millisUntilFinished) {

                runOnUiThread(new Runnable() {
                    public void run() {
                        if (TimeCounter == I) {
                            timer.cancel();
                            return;
                        }
                        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.ecg);

                        //tv.setText("timer=" + String.valueOf(TimeCounter));
                        TimeCounter++;
                        if (TimeCounter == 2) {

                            iv_loading1.setImageResource(R.drawable.loading_enable);
                            if (soundToggleButton.isChecked()) {
                                mPlayer.start();
                            }

                        } else if (TimeCounter == 3) {

                            iv_loading2.setImageResource(R.drawable.loading_enable);
                            if (soundToggleButton.isChecked()) {
                                mPlayer.start();
                            }
                        } else if (TimeCounter == 4) {

                            iv_loading3.setImageResource(R.drawable.loading_enable);
                            if (soundToggleButton.isChecked()) {
                                mPlayer.start();
                            }
                        } else if (TimeCounter == 5) {

                            iv_loading4.setImageResource(R.drawable.loading_enable);
                            if (soundToggleButton.isChecked()) {
                                mPlayer.start();
                            }
                        } else if (TimeCounter == 6) {

                            iv_loading5.setImageResource(R.drawable.loading_enable);
                            if (soundToggleButton.isChecked()) {
                                mPlayer.start();
                            }
                        }
                    }
                });


            }

            public void onFinish() {


                if (mInterstitialAd.isLoaded()) {    //Show Full ads
                    mInterstitialAd.show();
                    requestNewInterstitial();
                }

                startActivity(new Intent(MainActivity.this, ResultActivity.class));
                finish();
                //Next Activity

            }
        };
        iv_fingure_scan.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mPlayer = MediaPlayer.create(MainActivity.this, R.raw.start);
                    if (soundToggleButton.isChecked()) mPlayer.start();
                    iv_fingure_scan.setImageResource(R.drawable.finger_pressed);
                    tv.setVisibility(View.GONE);
                    loading_layout.setVisibility(View.VISIBLE);
                    // Toast.makeText(getApplicationContext(),"Down",Toast.LENGTH_SHORT).show();
                    then = (Long) System.currentTimeMillis();
                    iv_bar.setVisibility(View.VISIBLE);
                    iv_scanner.setVisibility(View.GONE);
                    gview.setVisibility(View.VISIBLE);
                    iv_bar.startAnimation(animation);
                    if (vibrationToggleButton.isChecked()) mVibrate.vibrate(pattern, -1);
                    timer.start();
                    return true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    mPlayer = MediaPlayer.create(MainActivity.this, R.raw.end);
                    if (TimeCounter < 5) {
                        if (soundToggleButton.isChecked()) mPlayer.start();
                    }
                    TimeCounter = 0;
                    tv.setVisibility(View.VISIBLE);
                    loading_layout.setVisibility(View.GONE);
                    iv_fingure_scan.setImageResource(R.drawable.finger);
                    //Toast.makeText(getApplicationContext(),"Up",Toast.LENGTH_SHORT).show();
                    mVibrate.cancel();
                    gview.setVisibility(View.GONE);
                    iv_scanner.setVisibility(View.VISIBLE);
                    iv_bar.clearAnimation();
                    iv_bar.setVisibility(View.GONE);
                    timer.cancel();
                    iv_loading1.setImageResource(R.drawable.loading_disable);
                    iv_loading2.setImageResource(R.drawable.loading_disable);
                    iv_loading3.setImageResource(R.drawable.loading_disable);
                    iv_loading4.setImageResource(R.drawable.loading_disable);
                    iv_loading5.setImageResource(R.drawable.loading_disable);
                    return true;
                }
                return false;
            }
        });

//        iv_fingure_scan.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//
//                return false;
//            }
//        });

    }

    private void requestNewInterstitial() {           //For Full Banner
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_HASH")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    public void onBackPressed(){
        mInterstitialAd.show();
        requestNewInterstitial();
        finish();

    }
}

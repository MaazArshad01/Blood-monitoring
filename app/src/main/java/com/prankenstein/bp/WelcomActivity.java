package com.prankenstein.bp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codemybrainsout.ratingdialog.RatingDialog;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class WelcomActivity extends AppCompatActivity implements View.OnClickListener{


    ImageView btn_bp, btn_shareApp, btn_rate;
    InterstitialAd mInterstitialAd; // Full-screen banner
    ImageView iv_ad,iv_install;
    View dialoglayout;
    ViewAnimator viewAnimator;
    AlertDialog lala;
    TextView ct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);



        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest_2 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest_2);

        //Full ads
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5780541699123399/5415458656");

        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
               // mInterstitialAd.show();
            }

            @Override
            public void onAdClosed() {
                // requestNewInterstitial();
            }
        });
        requestNewInterstitial();

        btn_bp = (ImageView) findViewById(R.id.button_play);
        btn_shareApp = (ImageView) findViewById(R.id.button_share);
        btn_rate = (ImageView) findViewById(R.id.button_rate);
        iv_ad = (ImageView) findViewById(R.id.iv_ad);
        iv_install = (ImageView) findViewById(R.id.iv_install);
        iv_install.setOnClickListener(this);
        iv_ad.setOnClickListener(this);

        btn_bp.setOnClickListener(this);
        btn_rate.setOnClickListener(this);
        btn_shareApp.setOnClickListener(this);

        ViewAnimator viewAnimator = ViewAnimator
                .animate(iv_install)
                .flash().repeatCount(444)
                .start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_play:
                if (mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    mInterstitialAd.setAdListener(new AdListener() {

                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                            // mInterstitialAd.show();
                        }

                        @Override
                        public void onAdClosed() {
                            startActivity(new Intent(WelcomActivity.this, GenderActivity.class));
                        }
                    });}
                else {

                    startActivity(new Intent(WelcomActivity.this, GenderActivity.class));
                    requestNewInterstitial();
                }


                break;

            case R.id.button_share:

                String Sendd = ("Check you blood pressure: Stay healthy...!");

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, Sendd);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                //mInterstitialAd.show();
                //requestNewInterstitial();
                break;

            case R.id.button_rate:



                final RatingDialog ratingDialog = new RatingDialog.Builder(this)
                        .threshold(3)
                        .session(1)
                        .ratingBarColor(R.color.yello)
                        .positiveButtonTextColor(R.color.black)
                        .feedbackTextColor(R.color.black)
                        .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                            @Override
                            public void onFormSubmitted(String feedback) {

                            }
                        }).build();

                ratingDialog.show();
//                startActivity(new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("https://play.google.com/store/apps/details?id=com.bezruk.bloodpressure&hl=en")));
//                mInterstitialAd.show();
                //requestNewInterstitial();
                break;
            case R.id.iv_ad:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.orcinus.recover.pictures")));
                break;

            case R.id.iv_install:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.orcinus.recover.pictures")));

                break;
        }
    }

    private void requestNewInterstitial() {           //For Full Banner
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_HASH")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


//    public void onBackPressed() {
//
//        if (isFinishing()) {
//
//            Toast.makeText(getApplicationContext(), "kndnlvdbhhh", Toast.LENGTH_LONG).show();
//
//        } else {
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyDialogTheme);
//            builder.setTitle(("Rate for us"))
//                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int id) {
//                            lala.dismiss();
//
//                        }
//                    });
//
//            LayoutInflater inflater = getLayoutInflater();
//
//            if (dialoglayout != null) {
//                ViewGroup parent = (ViewGroup) dialoglayout.getParent();
//                if (parent != null) {
//                    parent.removeView(dialoglayout);
//                }
//            }
//            try {
//                dialoglayout = inflater.inflate(R.layout.layout_diloge, (ViewGroup) getCurrentFocus(), false);
//            } catch (InflateException e) {
//
//            }
//
//            // dialoglayout = inflater.inflate(R.layout.layout_diloge, (ViewGroup) getCurrentFocus());
//            //final int id_poiii=p.getId_poi();
//
//            builder.setView(dialoglayout);
//
//            lala = builder.create();
//
//            RatingBar rat = (RatingBar) dialoglayout.findViewById(R.id.dialogRatingBar);
//            ct = (TextView) dialoglayout.findViewById(R.id.dialogTextView);
//
//            rat.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//
//                public void onRatingChanged(RatingBar ratingBar, float rating,  boolean fromUser) {
//
//
//                    //   Toast.makeText(getApplicationContext(),"star " +i,Toast.LENGTH_LONG);
//                    if (rating<=3){
//                        ct.setText("Thanks, we will work hard to improve this app");
//                        ct.setVisibility(View.VISIBLE);
//
//
//
//                    }else {
//                        ct.setVisibility(View.INVISIBLE);
//                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
//                        finish();
//                    }
//                }
//            });
//
//
//            lala.show();
//
//
////            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
////            builder.setTitle("Rate This App");
////            builder.setMessage("If you enjoy this app, would you mind taking a moment to rate it? It won't take more than a minute. Thank you for your support.");
////
////            String positiveText = ("RATE NOW");
////            builder.setPositiveButton(positiveText,
////                    new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=gpaddy.com.restoreimage")));
////                            finish();
////                        }
////                    });
////
////            String negativeText = "No, THANKS";
////            builder.setNegativeButton(negativeText,
////                    new DialogInterface.OnClickListener() {
////                        @Override
////                        public void onClick(DialogInterface dialog, int which) {
////                            finish();
////                        }
////                    });
////
////            AlertDialog dialog = builder.create();
////            // display dialog
////            dialog.show();
//
//            //
//        }
//    }


}

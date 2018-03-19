package com.prankenstein.bp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class GenderActivity extends AppCompatActivity implements View.OnClickListener {
    InterstitialAd mInterstitialAd; // Full-screen banner
    ImageView iv_boy, iv_girl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);




        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest_3 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest_3);

        //Full ads
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5780541699123399/5415458656");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
              //  mInterstitialAd.show();
            }

            @Override
            public void onAdClosed() {
               // requestNewInterstitial();
            }
        });

        iv_boy = (ImageView) findViewById(R.id.iv_boy);
        iv_girl = (ImageView) findViewById(R.id.iv_girl);

        iv_boy.setOnClickListener(this);
        iv_girl.setOnClickListener(this);
        requestNewInterstitial();

    }

    private void requestNewInterstitial() {           //For Full Banner
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("YOUR_DEVICE_HASH")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_boy:
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
                            startActivity(new Intent(GenderActivity.this, MainActivity.class));
                        }
                    });}
                else {

                    startActivity(new Intent(GenderActivity.this, MainActivity.class));
                    requestNewInterstitial();
                }
                break;


            case R.id.iv_girl:
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
                            startActivity(new Intent(GenderActivity.this, MainActivity.class));
                        }
                    });}
                else {

                    startActivity(new Intent(GenderActivity.this, MainActivity.class));
                    requestNewInterstitial();
                }

                break;
        }
    }

    public void onBackPressed(){
            mInterstitialAd.show();
            requestNewInterstitial();
            finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instruction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        onBackPressed();
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        if(id==android.R.id.home)
//            InstructionActivity.this.finish();

        return super.onOptionsItemSelected(item);
    }
}

package com.prankenstein.bp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back;

    TextView sys;
    TextView dia;
    TextView pulse;
    double sys_res;
    String str_sys_res;
    double dia_res;
    String str_dia_res;
    double pulse_res;
    String str_pulse_res;
    String Sendd;
    MediaPlayer mPlayer;
    SessionManagement pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        pref = new SessionManagement(getApplicationContext());

        AdView mAdView = (AdView) findViewById(R.id.adView);    //Banner Ads
        AdRequest adRequest = new AdRequest.Builder().build();  //Banner Ads
        mAdView.loadAd(adRequest);
        mPlayer = MediaPlayer.create(ResultActivity.this, R.raw.khatam);
        if (!pref.issoundCheckedd())
        mPlayer.start();
        sys = (TextView) findViewById(R.id.SYS_res);
        dia = (TextView) findViewById(R.id.DIA_res);
        pulse = (TextView) findViewById(R.id.PULSE_res);

        sys_res = 110 + Math.random()*20;  //Random number from a predetermined range
        dia_res = 65 + Math.random()*15;   //Random number from a predetermined range
        pulse_res = 65 + Math.random()*10; //Random number from a predetermined range

        str_sys_res = Integer.toString((int)sys_res);     //Converting to string
        str_dia_res = Integer.toString((int)dia_res);     //Converting to string
        str_pulse_res = Integer.toString((int)pulse_res); //Converting to string

        sys.setText(str_sys_res);
        dia.setText(str_dia_res);
        pulse.setText(str_pulse_res);

        back = (ImageView) findViewById(R.id.back_res);
        back.setOnClickListener(this);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.back_res){
            ResultActivity.this.finish();
        }

    }
}

package cl.naty.timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by pedrofranz on 19-04-15.
 */
public class MainClass extends Activity{

    Button btnTimer;
    Button btnChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        btnTimer = (Button) findViewById(R.id.btnTimer);
        btnChronometer = (Button) findViewById(R.id.btnChronometer);
    }

    public void ToTimer(View v) {
        Intent Timer = new Intent(this, MyActivity.class);
        startActivity(Timer);
    }

    public void ToChronometer(View v) {
        Intent Chronometer = new Intent(this, Chrono.class);
        startActivity(Chronometer);
    }

}

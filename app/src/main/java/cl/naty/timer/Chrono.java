package cl.naty.timer;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.app.Activity;

public class Chrono extends Activity implements OnClickListener {
    private Chronometer chronometer;
    long timeWhenStopped = 0;
    boolean itsStopped;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.chronometer);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        ((Button) findViewById(R.id.start_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.reset_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.pause_button)).setOnClickListener(this);
        itsStopped=true;
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.start_button:
                if(itsStopped){
                ((Button) findViewById(R.id.start_button)).setText("Start");
                chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                chronometer.start();
                itsStopped=false;
                }
                break;
            case R.id.reset_button:
                ((Button) findViewById(R.id.start_button)).setText("Start");
                timeWhenStopped=0;
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                itsStopped=true;
                break;
            case R.id.pause_button:
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                chronometer.stop();
                ((Button) findViewById(R.id.start_button)).setText("Resume");
                itsStopped=true;
                break;
        }
    }
}
/**
 * Created by pedrofranz on 19-04-15.
 */
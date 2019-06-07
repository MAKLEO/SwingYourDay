package manish.khullar.swingyourday;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
    SoundPool sp;
    MediaPlayer mm,mnm;
    int explosion = 0;
    Random crazy = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        v.setOnTouchListener(this);
        setContentView(v);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        explosion = sp.load(this, R.raw.explosion, 1);
        //priority provides the higher priority level in the process stack if it is set to 1 and low if it is set to 0(ONLY 0 or 1 WORKS)
        mm = MediaPlayer.create(this, R.raw.backgroundmusic);
        mnm = MediaPlayer.create(this, R.raw.soundtrack);
        v.setBackgroundColor(Color.rgb(crazy.nextInt(256), crazy.nextInt(265), crazy.nextInt(265)));
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getApplicationContext(), "ON CLICK",Toast.LENGTH_SHORT).show();
        mnm.stop();
        if (explosion != 0) {
            sp.play(explosion, 1, 1, 0, 0, 1);
            v.setBackgroundColor(Color.rgb(crazy.nextInt(256), crazy.nextInt(265), crazy.nextInt(265)));
        }
    }

    @Override
    public boolean onLongClick(View v) {
        mnm.stop();
        mm.start();
        Toast.makeText(getApplicationContext(), "LONG CLICK",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Toast.makeText(getApplicationContext(), "ON TOUCH", Toast.LENGTH_SHORT).show();
        mm.stop();
        mnm.start();
        return false;
    }

    @Override
    protected void onPause() {
        super.onPause();//if cal comes and onpause is not initiated then both the music will play
        mnm.release();
    }
}

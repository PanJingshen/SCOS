package es.source.code.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class SCOSEntry extends AppCompatActivity {

    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);


        gestureDetector = new GestureDetector(SCOSEntry.this, onGestureListener);
    }


    private GestureDetector.OnGestureListener onGestureListener
            = new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e2.getX() - e1.getX();
            if(x < 0){
                Intent i=new Intent(SCOSEntry.this, MainScreen.class);
                i.putExtra("toMainScreen", "FromEntry");
                startActivity(i);
            }
            return true;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}

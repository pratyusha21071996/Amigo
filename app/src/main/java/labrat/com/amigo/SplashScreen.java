package labrat.com.amigo;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread background = new Thread() {
            public void run() {

                try {
                    sleep(2000);

                    Intent i=new Intent(getBaseContext(),choice.class);
                    startActivity(i);

                    finish();

                } catch (Exception e) {
                    System.out.print(e);
                }
            }
        };

        background.start();



    }
}

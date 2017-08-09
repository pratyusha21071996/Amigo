package labrat.com.amigo;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import static android.R.id.button1;
import static android.R.id.button2;
import static android.R.id.button3;
import static android.provider.AlarmClock.EXTRA_MESSAGE;
/**
 * Created by garvi on 5/6/2017.
 */

public class choice extends AppCompatActivity {
    ImageButton myImageButton,myImageButton2,myImageButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        myImageButton = (ImageButton) findViewById(R.id.imageView4);
        myImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(choice.this, chatbot.class);
                startActivity(intentLoadNewActivity);
            }
        });
        myImageButton2 = (ImageButton) findViewById(R.id.imageView2);
        myImageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(choice.this, login_choice.class);
                startActivity(intentLoadNewActivity);
            }
        });
        myImageButton3 = (ImageButton) findViewById(R.id.imageView3);
        myImageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(choice.this, MainActivity.class);
                startActivity(intentLoadNewActivity);
            }
        });

    }
}
package labrat.com.amigo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by garvi on 6/8/2017.
 */

public class login_choice extends AppCompatActivity {
    private Button b4,b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_choice);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user==null) {
            b4 = (Button) findViewById(R.id.button4);
            b5 = (Button) findViewById(R.id.button5);

            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(login_choice.this, Signup.class);
                    startActivity(i);
                }
            });

            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(login_choice.this, login.class);
                    startActivity(i);
                }
            });


        }
        else{
            Intent i = new Intent(login_choice.this,group.class);
            startActivity(i);
        }

    }
}

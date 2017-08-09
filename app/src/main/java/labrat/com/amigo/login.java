package labrat.com.amigo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by garvi on 5/6/2017.
 */

public class login  extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button b2;
    private EditText e7,e8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){
            if(user.isEmailVerified()){
                Intent i = new Intent(login.this,group.class);
                startActivity(i);
            }else {
                loginData();
            }
        }
        else {
            loginData();
        }

    }
    private void loginData(){
        e7 = (EditText)findViewById(R.id.editText8);
        e8 = (EditText)findViewById(R.id.editText9);
        b2 = (Button)findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,pass;
                email = e7.getText().toString();
                pass = e8.getText().toString();

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if(user.isEmailVerified()){
                            Intent intent = new Intent(login.this,group.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Please Verify Your email",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}

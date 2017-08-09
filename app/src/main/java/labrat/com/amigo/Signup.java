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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by garvi on 5/6/2017.
 */

public class Signup extends AppCompatActivity {
    private Button b;
    private EditText e1,e2,e3,e4,e5,e6;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("centers");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            Intent i = new Intent(Signup.this,group.class);
            startActivity(i);
        } else {

            e1 = (EditText)findViewById(R.id.editText);
            e2 = (EditText)findViewById(R.id.editText2);
            e3 = (EditText)findViewById(R.id.editText3);
            e4 = (EditText)findViewById(R.id.editText4);
            e5 = (EditText)findViewById(R.id.editText5);
            e6 = (EditText)findViewById(R.id.editText6);

            b = (Button)findViewById(R.id.button);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String name,des,add,phno,email,pass;
                    name = e1.getText().toString();
                    des = e2.getText().toString();
                    add = e3.getText().toString();
                    phno = e4.getText().toString();
                    email = e5.getText().toString();
                    pass = e6.getText().toString();
                    if(pass.trim().length()>0 && pass.trim().length()<20){
                        data dataObj = new data(name,des,add,phno);
                        Log.d("bmb","kjb");

                        myRef.push().setValue(dataObj).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(Signup.this, "Failed",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    sendVerification();

                                }
                            }
                        });
                    }

                }
            });
        }

    }
    private void sendVerification(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Signup.this,"Verification Mail send",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Signup.this,login.class);
                            startActivity(i);
                        }
                    }
                });
    }

}

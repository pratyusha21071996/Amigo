package labrat.com.amigo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Group_Posts extends AppCompatActivity {

    private ImageButton imageButton;
    private EditText editText7,editText11;
    private Button button3;
    private Uri mImageUri = null;
    private static final int GALLERY_REQUEST = 1;
    private StorageReference mStorage;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group__posts);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null){

            String uid = user.getUid();

            mStorage = FirebaseStorage.getInstance().getReference("Posts").child(uid);
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("Posts").child(uid).push();
            mProgress = new ProgressDialog(this);

            imageButton = (ImageButton)findViewById(R.id.imageButton);
            editText7 = (EditText)findViewById(R.id.editText7);
            editText11 = (EditText)findViewById(R.id.editText11);
            button3 = (Button)findViewById(R.id.button3);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent,GALLERY_REQUEST);
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    posting();
                }
            });


        }else {
            Intent i = new Intent(Group_Posts.this,login.class);
            startActivity(i);
        }
    }

    private void posting() {

        mProgress.setMessage("Posting to app...");
        mProgress.show();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        final String title_val = editText7.getText().toString().trim();
        final String desc_val = editText11.getText().toString().trim();

        if (!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && mImageUri != null){

            StorageReference filepath = mStorage.child(uid).child(mImageUri.getLastPathSegment());

            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    databaseReference.child("Title").setValue(title_val);
                    databaseReference.child("Description").setValue(desc_val);
                    databaseReference.child("Image Url").setValue(downloadUrl.toString());
                    mProgress.dismiss();

                    Intent intent = new Intent(Group_Posts.this,group.class);
                    startActivity(intent);

                }
            });

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

            mImageUri = data.getData();
            imageButton.setImageURI(mImageUri);
        }
    }
}




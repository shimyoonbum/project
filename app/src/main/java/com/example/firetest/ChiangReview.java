package com.example.firetest;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChiangReview extends AppCompatActivity implements View.OnClickListener {
    private FirebaseStorage storage;
    private FirebaseDatabase database;

    Button btn_Insert, b;

    ImageView imageView;
    private static final int GALLERY_CODE = 10;
    private String imagePath;

    EditText e1, e2, e3;

    RatingBar ratingBar;

    Float rating;

    private List<FirebasePost> fires = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);

        /*권한*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();

        btn_Insert = (Button) findViewById(R.id.btn_insert);
        btn_Insert.setOnClickListener(this);
        b = (Button)findViewById(R.id.btn2);
        b.setOnClickListener(this);
        ratingBar = (RatingBar) findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rate, boolean b) {
                rating = rate;
            }
        });

        imageView = (ImageView) findViewById(R.id.img1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

                startActivityForResult(intent, GALLERY_CODE);
            }
        });

        e1 = (EditText) findViewById(R.id.name);
        e2 = (EditText) findViewById(R.id.country);
        e3 = (EditText) findViewById(R.id.contents);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert:
                try{
                    upload(imagePath);
                    Toast.makeText(getApplicationContext(), "등록 완료!", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.btn2:
                Intent i = new Intent(this, BoardActivity2.class);
                startActivity(i);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_CODE) {

            imagePath = getPath(data.getData());
            File f = new File(imagePath);
            imageView.setImageURI(Uri.fromFile(f));
        }
    }
    public String getPath(Uri uri){
        String [] proj = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(this,uri,proj,null,null,null);

        Cursor cursor = cursorLoader.loadInBackground();
        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(index);
    }

    private void upload(String uri){
        StorageReference storageRef = storage.getReferenceFromUrl("gs://firetest-74702.appspot.com");

        Uri file = Uri.fromFile(new File(uri));
        StorageReference riversRef = storageRef.child("chiangmai/"+file.getLastPathSegment());
        UploadTask uploadTask = riversRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri d = taskSnapshot.getDownloadUrl();

                FirebasePost f = new FirebasePost();
                f.imageUrl = d.toString();
                f.name = e1.getText().toString();
                f.title = e2.getText().toString();
                f.content = e3.getText().toString();
                f.rate = Float.toString(rating);
                database.getReference().child("chiangmai").push().setValue(f);
            }
        });
    }
}



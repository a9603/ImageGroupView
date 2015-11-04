package com.loopeer.android.librarys.imagegroupview;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

public class UserCameraActivity extends AppCompatActivity {

    private String photoUrl;
    private final String EXTRA_PHOTO_URL = NavigatorImage.EXTRA_PHOTO_URL;


    private final int RSULT_IMAGE_CAPTURE = 2002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            photoUrl = savedInstanceState.getString(EXTRA_PHOTO_URL);

            File mFile = new File(photoUrl);
            if (mFile.exists()) {
                Intent rsl = new Intent();
                rsl.putExtra(EXTRA_PHOTO_URL, photoUrl);
                setResult(Activity.RESULT_OK, rsl);
                finish();
            } else {
            }
        }

        if (savedInstanceState == null) {
            showCamera();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA_PHOTO_URL, photoUrl);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (RSULT_IMAGE_CAPTURE == requestCode && resultCode == Activity.RESULT_OK) {
            Intent rsl = new Intent();
            rsl.putExtra(EXTRA_PHOTO_URL, photoUrl);
            setResult(Activity.RESULT_OK, rsl);
            finish();
        } else {
            finish();
        }
    }

    private void showCamera() {
        Intent intent1 =
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ContentValues values = new ContentValues();
        Uri photoUri = getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        photoUrl = photoUri.toString();
        intent1.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent1, RSULT_IMAGE_CAPTURE);
    }

}

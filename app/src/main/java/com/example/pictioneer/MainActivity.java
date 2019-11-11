package com.example.pictioneer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {

    private static final String EXTRA_AUCTION_ID = "com.example.android.pictioneer.auction_id";
    private static int REQUEST_CODE_TAKE_PICTURE = 0;

    // Display specific crime
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_AUCTION_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_AUCTION_ID);
        return AuctionFragment.newInstance(crimeId);
    }

    public void takeThumbnailPicture() {
        // Intent to open an app which can take a picture
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (pictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(pictureIntent, REQUEST_CODE_TAKE_PICTURE);
        } else {
            Toast.makeText(this, "Your device does not have a camera app", Toast.LENGTH_LONG).show();
        }
    }


}
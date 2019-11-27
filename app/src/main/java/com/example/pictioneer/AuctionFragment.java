package com.example.pictioneer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class AuctionFragment extends Fragment {

    private static final String ARG_AUCTION_ID = "auction_id";
    private static int REQUEST_CODE_TAKE_PICTURE = 0;

    private static String TAG = "AUCTION_FRAGMENT";

    private AuctionItem mAuctionItem;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSoldCheckBox;
    private Button mTakePictureButton;
    private ImageView mCameraPicture;


    // TODO is this where the thumbnail should go?
    public void setThumbnailPicture(Bitmap bitmap) {
        mCameraPicture.setImageBitmap(bitmap);
    }


    interface AuctionFragmentListener {
        void onTakePictureRequest();
    }

    private AuctionFragmentListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (AuctionFragmentListener) context;
    }

    //Creates crime fragment
    public static AuctionFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_AUCTION_ID, crimeId);

        AuctionFragment fragment = new AuctionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Fetching the crime
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_AUCTION_ID);
        // This is returning null
        // mAuctionItem = AuctionArchive.get(getActivity()).getAuction(crimeId);
        // So making a placeholder
        mAuctionItem = new AuctionItem("Test", true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_auction, container, false);


        // Displaying crime data
        mTitleField = v.findViewById(R.id.auction_title);
        mTitleField.setText(mAuctionItem.getTitle());    // This is throwing a NullPointer
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mAuctionItem.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSoldCheckBox = v.findViewById(R.id.auction_sold);
        mSoldCheckBox.setChecked(mAuctionItem.isSold());
        mSoldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mAuctionItem.setSold(isChecked);
            }
        });

        mDateButton = v.findViewById(R.id.auction_date);
        mDateButton.setText(mAuctionItem.getDate().toString());
        mDateButton.setEnabled(false);

        mCameraPicture = v.findViewById(R.id.camera_thumbnail_picture);
        mTakePictureButton = v.findViewById(R.id.take_picture_button);

        mTakePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTakePictureRequest();
            }
        });

        return v;
    }




// The main activity will launch the camera, so the main activity will receive the onActivityResult message

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_CODE_TAKE_PICTURE && resultCode == RESULT_OK) {
//            Bitmap thumbnail = data.getParcelableExtra("data");
//            mCameraPicture.setImageBitmap(thumbnail);
//        }
//    }
}





package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.List;

public class BodyPartFragment extends Fragment  {
    private static final String TAG = "BodyPartFragment";

    private List<Integer> imageIDs;
    private int imageIndex;

    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.contentImageView);

        if(imageIDs != null) {
            imageView.setImageResource(imageIDs.get(imageIndex));
        }
        else{
            Log.v(TAG,"This fragment has a null list image id's");
        }
        return rootView;
    }

    public void setImageIDs(List<Integer> imageIDs) {
        this.imageIDs = imageIDs;
    }

    public void setImageIndex(int imageIndex) {
        this.imageIndex = imageIndex;
    }
}

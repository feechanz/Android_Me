package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean twoPane;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.android_me_linear_layout) != null){
            twoPane = true;

            GridView gridView = (GridView) findViewById(R.id.imagesGridView);
            gridView.setNumColumns(2);

            Button nextButton = (Button) findViewById(R.id.nextButton);
            nextButton.setVisibility(View.GONE);

            if(savedInstanceState == null) {
                // TODO (5) Create a new BodyPartFragment instance and display it using the FragmentManager
                BodyPartFragment headFragment = new BodyPartFragment();
                headFragment.setImageIDs(AndroidImageAssets.getHeads());

                int headIndex = getIntent().getIntExtra("headIndex", 0);
                headFragment.setImageIndex(headIndex);

                BodyPartFragment bodyFragment = new BodyPartFragment();
                bodyFragment.setImageIDs(AndroidImageAssets.getBodies());

                int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
                bodyFragment.setImageIndex(bodyIndex);

                BodyPartFragment legFragment = new BodyPartFragment();
                legFragment.setImageIDs(AndroidImageAssets.getLegs());

                int legIndex = getIntent().getIntExtra("legIndex", 0);
                legFragment.setImageIndex(legIndex);

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .add(R.id.headContainerFrameLayout, headFragment)
                        .add(R.id.bodyContainerFrameLayout, bodyFragment)
                        .add(R.id.legContainerFrameLayout, legFragment)
                        .commit();
            }
            else{
                twoPane = false;
            }
        }
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Position clicked = "+position,Toast.LENGTH_SHORT).show();

        int bodyPartNumber = position/12;
        int imagesIndex = position-12*bodyPartNumber;

        if(twoPane){
            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            switch (bodyPartNumber) {
                case 0:
                    bodyPartFragment.setImageIDs(AndroidImageAssets.getHeads());
                    bodyPartFragment.setImageIndex(imagesIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.headContainerFrameLayout, bodyPartFragment)
                            .commit();
                    break;
                case 1:
                    bodyPartFragment.setImageIDs(AndroidImageAssets.getBodies());
                    bodyPartFragment.setImageIndex(imagesIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.bodyContainerFrameLayout, bodyPartFragment)
                            .commit();
                    break;
                case 2:
                    bodyPartFragment.setImageIDs(AndroidImageAssets.getLegs());
                    bodyPartFragment.setImageIndex(imagesIndex);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.legContainerFrameLayout, bodyPartFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        }else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = imagesIndex;
                    break;
                case 1:
                    bodyIndex = imagesIndex;
                    break;
                case 2:
                    legIndex = imagesIndex;
                    break;
                default:
                    break;
            }

            Bundle bundle = new Bundle();
            bundle.putInt("headIndex", headIndex);
            bundle.putInt("bodyIndex", bodyIndex);
            bundle.putInt("legIndex", legIndex);

            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(bundle);

            Button nextButton = (Button) findViewById(R.id.nextButton);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        }
    }
}

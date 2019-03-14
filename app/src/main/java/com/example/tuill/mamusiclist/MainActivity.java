package com.example.tuill.mamusiclist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check if we got permission to read storage
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            // Permission is not granted
            // Should we show an explanation?
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                //show explanation of why you need permission
                AllowStorageReadin maReadingDialog = new AllowStorageReadin();
                maReadingDialog.show(getSupportFragmentManager(),"ASS");
            }
            else {
                //no explanation needed request the permission
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }
        else{
            //permission has already been granted
            getThemSongs();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // Storage-related task you need to do.
                    getThemSongs();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    public void getThemSongs(){

        ViewPager maviewpager = (ViewPager) findViewById(R.id.view_pager);
        FixedTabsPagerAdapter mapageradapter = new FixedTabsPagerAdapter(getSupportFragmentManager());
        maviewpager.setAdapter(mapageradapter);

        TabLayout matablayout = (TabLayout) findViewById(R.id.sliding_tabs);
        matablayout.setupWithViewPager(maviewpager);

    }
}

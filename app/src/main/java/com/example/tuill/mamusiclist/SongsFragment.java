package com.example.tuill.mamusiclist;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class SongsFragment extends Fragment {

    public SongsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         RecyclerView mRecyclerView;
         RecyclerView.Adapter mAdapter;
         RecyclerView.LayoutManager mLayoutManager;

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_songs_fragments, container, false);

        //Check file path see if there are sum user files already
        File sdCard = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.d("ASSSS","ASSS");
        Log.d("directory is",sdCard.getPath());
        File dir = new File(sdCard.getPath()+"/Music");
        ArrayList<String>  maMusicSongNames = new ArrayList<>();

        if(dir.exists()){

                File[] arrayoffiles = dir.listFiles();
                for(int i=0;i<arrayoffiles.length;i++){
                    Log.d("List", arrayoffiles[i].getPath());
                }
                List<File> listofFiles = new LinkedList<File>(Arrays.asList(dir.listFiles()));

                for (int i = 0; i < listofFiles.size(); i++) {
                    maMusicSongNames.add(listofFiles.get(i).getName());
                }
            }

        mRecyclerView = rootView.findViewById(R.id.maRecyclerView);
        //use setting to improve performance
        mRecyclerView.setHasFixedSize(true);

        //use Linear Layout
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //specify Adapter
        mAdapter = new MyAdapter(maMusicSongNames,dir);
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

}

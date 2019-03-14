package com.example.tuill.mamusiclist;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private ArrayList <String> maDataset;
    private File maDir;
    private Context thisMofoContext;
    //private final View.OnClickListener mOnClickListener = new MyOnClickListener();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mTextView;
        public View mButton;
        public MyViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.textView);
            mButton = v.findViewById(R.id.PlayButton);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList myDataset, File dir) {
        maDir = dir;
        maDataset = myDataset;
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
       //create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        //make items clickable not sure if this is most efficient way
        thisMofoContext=parent.getContext();
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(thisMofoContext,"ASSSS", Toast.LENGTH_LONG).show();
            }
        });
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView maTextView = (TextView) holder.mTextView;
        maTextView.setText(maDataset.get(position));
        Button playButton = (Button) holder.mButton;
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapath = maDir.getAbsolutePath();
                mapath = mapath+"/"+maDataset.get(position);
                File maFile = new File(mapath);
                MediaPlayer mediaPlayer = (MediaPlayer) MediaPlayer.create(thisMofoContext, Uri.fromFile(maFile));
                Log.d("playing",maFile.getAbsolutePath());
                mediaPlayer.start();

            }
        });
    }

    @Override
    public int getItemCount() {
        return maDataset.size();
    }
}

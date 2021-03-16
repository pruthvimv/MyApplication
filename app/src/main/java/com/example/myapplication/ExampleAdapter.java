package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
private Context mContext;
private ArrayList<ExampleItem> mExampleList;
private onItemClickListner listner;

public interface onItemClickListner {
    void onItemClick(int position);
}

public void setOnItemClickListener(onItemClickListner listner) {
    this.listner = listner;
}
public ExampleAdapter(Context context, ArrayList<ExampleItem> items) {
    this.mContext = context;
    this.mExampleList = items;
}
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
    ExampleItem item = mExampleList.get(position);
    String imageUrl = item.getmImageUrl();
    String creator = item.getmCreator();
    int likes = item.getmLikes();
    holder.mTextViewCreator.setText(creator);
    holder.mLikes.setText("Likes: " + likes);
        Glide.with(mContext).load(imageUrl).apply(new RequestOptions().override(2000, 400)).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
public ImageView mImageView;
public TextView mTextViewCreator;
public TextView mLikes;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageview);
            mTextViewCreator = itemView.findViewById(R.id.textview);
            mLikes = itemView.findViewById(R.id.text_view_downloads);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner !=null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listner.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

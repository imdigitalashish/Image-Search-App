package com.imdigitalashish.imagesearchapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private ArrayList<OneItem> mOneAdapter;

    public ItemAdapter(Context context, ArrayList<OneItem> itemList){
        this.context = context;
        mOneAdapter = itemList;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        OneItem currentItem = mOneAdapter.get(position);
        String imageUrl = currentItem.getmImageUrl();
        String creator = currentItem.getmCreator();
        int likeCount = currentItem.getmLikes();

        holder.mTextViewCreator.setText(creator);
        holder.mTextViewLikes.setText("Likes: " + likeCount );
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mOneAdapter.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes);
        }
    }



}

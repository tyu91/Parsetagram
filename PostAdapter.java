package com.codepath.chattyboi.parsetagram;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> mPosts;
    Context context;

    public PostAdapter(List<Post> posts) {
        mPosts = posts;
    }

    //only called when need to create a new row; otherwise, onBindViewHolder called
    //inflates
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get data according to position
        Post post;

        post = mPosts.get(position);

        //populate views according to data
        holder.tvUsername.setText(post.getUser().getUsername());
        holder.tvPost.setText(post.getDescription());
        Glide.with(context).load(post.getImage().getUrl()).into(holder.ivImage);

        holder.tvTime.setText(getRelativeTimeAgo(post.getCreatedAt().toString()));

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    //for each row, inflate the layout and cache references into ViewHolder

    //bind values based on position of the element

    //create ViewHolder class

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView ivImage;
        public TextView tvUsername;
        public TextView tvPost;
        public TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);

            //perform findViewById lookups
            ivImage = (ImageView) itemView.findViewById(R.id.d_ivImage);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvPost = (TextView) itemView.findViewById(R.id.tvPost);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);

            itemView.setOnClickListener(this);
        }

        //onClick itemView, go to PostDetailsActivity
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            //check if position is valid, aka actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                //get movie at position if position exists
                Post post = mPosts.get(position);
                //create intent for new activity
                Intent intent = new Intent(context, PostDetailsActivity.class);
                //put post and time in intent
                intent.putExtra("post", Parcels.wrap(post));
                intent.putExtra("time", Parcels.wrap(getRelativeTimeAgo(post.getCreatedAt().toString())));
                //show the activity
                context.startActivity(intent);
            }
        }
    }

    // getRelativeTimeAgo to format time created in a nice way
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;

    }

    // Clean all elements of the recycler
    public void clear() {
        mPosts.clear();
        notifyDataSetChanged();
    }


}


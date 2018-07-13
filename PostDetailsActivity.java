package com.codepath.chattyboi.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

public class PostDetailsActivity extends AppCompatActivity {

    private TextView tvUsername;
    private TextView tvTime;
    private TextView tvPost;
    private ImageView ivImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        //assign values for views in details page
        tvUsername = findViewById(R.id.d_tvUsername);
        tvTime = findViewById(R.id.d_tvTime);
        tvPost = findViewById(R.id.d_tvPost);
        ivImage = findViewById(R.id.d_ivImage);

        Intent intent = getIntent();

        Post post = Parcels.unwrap(intent.getParcelableExtra("post"));
        String time = Parcels.unwrap(intent.getParcelableExtra("time"));

        tvUsername.setText(post.getUser().getUsername().toString());
        tvTime.setText(time.toString());
        tvPost.setText(post.getDescription().toString());

        Glide.with(this).load(post.getImage().getUrl()).into(ivImage);
    }

}

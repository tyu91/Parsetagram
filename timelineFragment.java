package com.codepath.chattyboi.parsetagram;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class timelineFragment extends Fragment {

    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPosts;
    ParseUser user;
    SwipeRefreshLayout swipeContainer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment and store it in view v
        View v = inflater.inflate(R.layout.fragment_timeline, container, false);

        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postAdapter.clear();

                swipeContainer.setRefreshing(false);
            }
        });

        rvPosts = (RecyclerView) v.findViewById(R.id.rvItems);

        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts);

        rvPosts.setAdapter(postAdapter);

        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        populateTimeline();

        return v;
    }

    private void populateTimeline() {
        //store current user in field user
        user = ParseUser.getCurrentUser();

        //creates query
        ParseQuery<Post> query = new Post.Query().getTop().withUser();
        /* String username = user.getUsername().toString();
        query.whereEqualTo("username", username);*/

        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    for (int i = objects.size() - 1; i >= 0; i--) {
                        Post post = objects.get(i);
                        posts.add(post);
                        postAdapter.notifyItemInserted(posts.size() - 1);
                    }
                }
            }
        });

    }
}

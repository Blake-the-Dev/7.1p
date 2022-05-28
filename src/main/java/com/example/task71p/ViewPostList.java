package com.example.task71p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.task71p.data.DatabaseHelper;
import com.example.task71p.model.Post;

import java.util.ArrayList;
import java.util.List;

public class ViewPostList extends AppCompatActivity {

    ListView postsListView ;
    ArrayList<String> postsArrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_found_items);

        postsListView = findViewById(R.id.listPost);
        postsArrayList = new ArrayList<>();
        DatabaseHelper db = new DatabaseHelper(ViewPostList.this);

        List<Post> postList = db.showAllPosts();

        for (Post post :postList)
        {
            postsArrayList.add(post.getPost_name());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postsArrayList);
        postsListView.setAdapter(adapter);

       postsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Cursor cursor = db.fetchPostDetails();
               cursor.moveToPosition(position);

               Intent intent = new Intent(ViewPostList.this, RemoveItem.class);
               intent.putExtra("post_id", cursor.getInt((0)));
               intent.putExtra("post_name", cursor.getString(1));
               intent.putExtra("post_date", cursor.getString(2));
               intent.putExtra("post_location", cursor.getString(3));

               startActivity(intent);
           }
       });
    }
}
package com.example.task71p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.task71p.data.DatabaseHelper;

public class RemoveItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_item);

        TextView postName = findViewById(R.id.txtPostName);
        TextView postDate = findViewById(R.id.txtPostDate);
        TextView postLocation = findViewById(R.id.txtPostLocation);
        Button postRemove = findViewById(R.id.btnRemove);

        DatabaseHelper db = new DatabaseHelper(RemoveItem.this);

        String name = getIntent().getStringExtra("post_name");
        String date = getIntent().getStringExtra("post_date");
        String location = getIntent().getStringExtra("post_location");
        int id = getIntent().getIntExtra("post_id", 1);

        postName.setText(name);
        postDate.setText(date);
        postLocation.setText(location);

        postRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteRow(id);
            }
        });


    }
}
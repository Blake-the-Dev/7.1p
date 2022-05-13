package com.example.task71p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.task71p.data.DatabaseHelper;
import com.example.task71p.model.Post;


public class CreateNewAdvert extends AppCompatActivity {

    DatabaseHelper db;
    RadioGroup rgPostType;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_advert2);

        rgPostType = findViewById(R.id.typeRadioGroup);

        EditText entName = findViewById(R.id.txtName);
        EditText entPhone = findViewById(R.id.txtPhone);
        EditText entDescription = findViewById(R.id.txtDescription);
        EditText entDate = findViewById(R.id.txtDate);
        EditText entLocation = findViewById(R.id.txtLocation);
        Button btnSavePost = findViewById(R.id.btnSave);
        db = new DatabaseHelper(this);


        btnSavePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String post_radioGroup = radioButton.getText().toString();
                String post_name = entName.getText().toString();
                String post_phone = entPhone.getText().toString();
                String post_description = entDescription.getText().toString();
                String post_date = entDate.getText().toString();
                String post_location = entLocation.getText().toString();

                if(post_radioGroup.isEmpty() || post_name.isEmpty() || post_phone.isEmpty() ||
                        post_description.isEmpty() || post_date.isEmpty() || post_location.isEmpty())
                {
                    Toast.makeText(CreateNewAdvert.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    db.insertPost(new Post(post_radioGroup, post_name, post_phone, post_description, post_date, post_location));
                    Toast.makeText(CreateNewAdvert.this, "Post submitted!", Toast.LENGTH_SHORT).show();
                    startActivity( new Intent(CreateNewAdvert.this, MainActivity.class));
                }
            }
        });
    }

    public void checkButton(View v)
    {
        int radioId = rgPostType.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}
package com.android.train.task.json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTxtTitle = findViewById(R.id.editTxtTitle);

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTxtTitle.getText().toString();

                Intent intent = new Intent(MainActivity.this, Json2RecylcerAcc.class);
                intent.putExtra("title", title);

                startActivity(intent);
            }
        });
    }
}

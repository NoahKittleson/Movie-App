package com.example.guest.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.titleButton) Button mTitleButton;
    @Bind(R.id.titleEditText) EditText mTitleEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTitleButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mTitleButton) {
            Log.v("whatever", "watever");
            String title = mTitleEditText.getText().toString();
            Intent titleIntent = new Intent(MainActivity.this, ResultsActivity.class);
            titleIntent.putExtra("title", title);
            startActivity(titleIntent);
        }
    }
}

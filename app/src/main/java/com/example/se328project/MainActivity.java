package com.example.se328project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    Button bfb;
    Button bsql;
    Button bwt;
    Button bex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bfb = findViewById(R.id.bfb);
        bsql = findViewById(R.id.bsql);
        bwt = findViewById(R.id.bwt);
        bex = findViewById(R.id.bex);

        bfb.setOnClickListener(this);
        bsql.setOnClickListener(this);
        bwt.setOnClickListener(this);
        bex.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.bfb)
            startActivity(new Intent(MainActivity.this,ActfbActivity.class));
        else if (view.getId() == R.id.bsql)
            startActivity(new Intent(MainActivity.this,actsqlList.class));
        else if (view.getId() == R.id.bwt)
            startActivity(new Intent(MainActivity.this,ActwtActivity.class));
        else
        {
            MainActivity.this.finish();
            System.exit(0);
        }
    }
}
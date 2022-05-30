package com.example.se328project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActfbActivity extends AppCompatActivity
{
    private Button addCarB;
    private Button updateB;
    private Button deleteB;

    ////https://se328projectfinal-default-rtdb.asia-southeast1.firebasedatabase.app/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actfb);

        addCarB = findViewById(R.id.addB);
        updateB = findViewById(R.id.updateB);
        deleteB = findViewById(R.id.deletB);

        addCarB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ActfbActivity.this, addCar.class);
                startActivity(intent);
            }
        });

        updateB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ActfbActivity.this,activity_update_car.class);
                startActivity(intent);
            }
        });

        deleteB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(ActfbActivity.this,activity_delete_car.class);
                startActivity(intent);
            }
        });
    }
}
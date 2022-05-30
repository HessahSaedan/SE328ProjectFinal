package com.example.se328project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class activity_delete_car extends AppCompatActivity
{
    private Button deleteB;
    private EditText plateET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_car);

        deleteB = findViewById(R.id.DeleteBDeleteAct);
        plateET = findViewById(R.id.ed1Delete);

        deleteB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://se328projectfinal-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference ref = database.getReference("Cars");

                String target = plateET.getText().toString().trim();

                Query query = ref.orderByChild("plate").equalTo(target);

                query.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot ds)
                    {
                        if (ds.getValue() == null)
                        {
                            Toast.makeText(activity_delete_car.this,"Not Found",Toast.LENGTH_LONG).show();
                            return;
                        }

                        for(DataSnapshot ds1 :ds.getChildren())
                        {
                            for(DataSnapshot dsw:ds1.getChildren())
                            {
                                ref.child(ds1.getKey()).setValue(null);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) { }
                });
            }
        });

    }
}
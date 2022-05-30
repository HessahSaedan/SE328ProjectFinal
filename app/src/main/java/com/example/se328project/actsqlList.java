package com.example.se328project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class actsqlList extends AppCompatActivity
{
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;

    ArrayList<String> book_isbn, book_title, book_author,book_type, book_price;
    CustomAdapter customAdapter;

    FloatingActionButton addB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_list);

        addB = findViewById(R.id.sqlBAdd);
        addB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(actsqlList.this,ActsqlAddActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.sqllistrv1);
        myDB = new MyDatabaseHelper(actsqlList.this);

        book_isbn   = new ArrayList<String>();
        book_title  = new ArrayList<String>();
        book_author = new ArrayList<String>();
        book_price  = new ArrayList<String>();
        book_type  = new ArrayList<String>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(actsqlList.this,actsqlList.this,book_isbn, book_title, book_author,book_type,book_price);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(actsqlList.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            recreate();
        }
    }

    public void storeDataInArrays()
    {
        Cursor cursor = myDB.readAllData();

        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "No books in the library", Toast.LENGTH_LONG).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                book_isbn.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_type.add(cursor.getString(4));
                book_price.add(cursor.getString(5));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == R.id.delete_all)
        {
            Toast.makeText(this,"Delete",Toast.LENGTH_LONG);
            MyDatabaseHelper myDB = new MyDatabaseHelper(this);
            myDB.deleteAllData();
            recreate();
        }
        return super.onOptionsItemSelected(item);
    }
}
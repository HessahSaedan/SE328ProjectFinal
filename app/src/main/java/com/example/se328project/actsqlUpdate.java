package com.example.se328project;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class actsqlUpdate extends AppCompatActivity
{
    private EditText titleTxt;
    private EditText authorTxt;
    private EditText priceTxt;
    private CheckBox cb1,cb2,cb3;
    private RadioGroup rg1;
    private RadioButton rb1,rb2,rb3;
    private FloatingActionButton updateB,deleteB;
    private String title,author,isbn,price,type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actsql_update);

        titleTxt  = findViewById(R.id.sqlupdateetTitle);
        authorTxt = findViewById(R.id.sqlupdateetAuthor);
        priceTxt  = findViewById(R.id.sqlupdateetPrice);
        updateB   = findViewById(R.id.sqlBUpdate);
        deleteB   = findViewById(R.id.sqlBDelete);

        getAndSetIntentData();

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setTitle("Book with title: " + title);

        updateB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                MyDatabaseHelper myDB = new MyDatabaseHelper(actsqlUpdate.this);
                title = titleTxt.getText().toString().trim();
                author = authorTxt.getText().toString().trim();
                price = priceTxt.getText().toString().trim();
                myDB.updateData(isbn,title,author,price);
            }
        });

        deleteB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                confirmDialog();
            }
        });
    }

    public void getAndSetIntentData()
    {
        if(getIntent().hasExtra("isbn") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("type")
                && getIntent().hasExtra("price"))
        {
            isbn   = getIntent().getStringExtra("isbn");
            title  = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            type   = getIntent().getStringExtra("type");
            price  = getIntent().getStringExtra("price");

            titleTxt.setText(title);
            authorTxt.setText(author);
            priceTxt.setText(price);
        }
        else
        {
            Toast.makeText(this,"Empty Data",Toast.LENGTH_LONG).show();
        }
    }

    void confirmDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                MyDatabaseHelper myDB = new MyDatabaseHelper(actsqlUpdate.this);
                myDB.deleteOneRow(isbn);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {

            }
        });

        builder.create().show();
    }
}
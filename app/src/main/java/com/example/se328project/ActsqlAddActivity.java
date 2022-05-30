package com.example.se328project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActsqlAddActivity extends AppCompatActivity
{
    private EditText titleTxt;
    private EditText authorTxt;
    private EditText priceTxt;
    private CheckBox cb1,cb2,cb3;
    private RadioGroup rg1;
    private RadioButton rb1,rb2,rb3;
    Button addB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actsql_add);

        titleTxt = findViewById(R.id.sqletTitle);
        authorTxt = findViewById(R.id.sqletAuthor);
        priceTxt = findViewById(R.id.sqletPrice);
        rg1 = findViewById(R.id.sqlrg);
        rb1 = findViewById(R.id.sqlrbPaper);
        rb2 = findViewById(R.id.sqlrbDigital);
        rb3 = findViewById(R.id.sqlrbBoth);
        cb1 = findViewById(R.id.sqlcbCat1);
        cb2 = findViewById(R.id.sqlcbCat2);
        cb3 = findViewById(R.id.sqlcbCat3);
        addB = findViewById(R.id.sqladdbSubmit);

        addB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                MyDatabaseHelper db = new MyDatabaseHelper(ActsqlAddActivity.this);

                String tit = titleTxt.getText().toString().trim();
                String aut = authorTxt.getText().toString().trim();
                double pr = Double.valueOf(priceTxt.getText().toString().trim());

                String cat = "";

                if (cb1.isChecked())
                    cat += cb1.getText().toString().trim() + ",";

                if (cb2.isChecked())
                    cat += cb2.getText().toString().trim() + ",";

                if (cb3.isChecked())
                    cat += cb3.getText().toString().trim();

                int selectedId = rg1.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = (RadioButton)findViewById(selectedId);
                String ty = selectedRadioButton.getText().toString().trim();

                db.addBookRecord(tit,aut,cat,ty,pr);
            }
        });
    }
}
package com.example.se328project;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
    private Context context;
    private ArrayList book_isbn, book_title, book_author,book_type, book_price;
    private Activity activity;

    public CustomAdapter(Activity activity,Context context,ArrayList book_isbn, ArrayList book_title, ArrayList book_author, ArrayList book_type, ArrayList book_price)
    {
        this.context = context;
        this.activity = activity;

        this.book_isbn   = book_isbn;
        this.book_title  = book_title;
        this.book_author = book_author;
        this.book_type   = book_type;
        this.book_price  = book_price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.book_isbn_txt.setText(String.valueOf(book_isbn.get(position)));
        holder.book_title_txt.setText(String.valueOf(book_title.get(position)));
        holder.book_author_txt.setText(String.valueOf(book_author.get(position)));
        holder.book_type_txt.setText(String.valueOf(book_type.get(position)));
        holder.book_price_txt.setText(String.valueOf(book_price.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(context,actsqlUpdate.class);

                intent.putExtra("isbn",   String.valueOf(book_isbn.get(position)));
                intent.putExtra("title",  String.valueOf(book_title.get(position)));
                intent.putExtra("author", String.valueOf(book_author.get(position)));
                intent.putExtra("type",   String.valueOf(book_type.get(position)));
                intent.putExtra("price",  String.valueOf(book_price.get(position)));

                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return book_isbn.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView book_isbn_txt, book_title_txt, book_author_txt,book_type_txt,book_price_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            book_isbn_txt = itemView.findViewById(R.id.sqllistbook_id_txt);
            book_title_txt = itemView.findViewById(R.id.sqllistbook_title_txt);
            book_author_txt = itemView.findViewById(R.id.sqllistbook_author_txt);
            book_price_txt = itemView.findViewById(R.id.sqllistbook_price_txt);
            book_type_txt = itemView.findViewById(R.id.sqllistbook_type_txt);

            mainLayout = itemView.findViewById(R.id.mainLayoutMyRow);
        }
    }
}
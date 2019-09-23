package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class rv_adapter extends RecyclerView.Adapter<rv_adapter.view_holder> {

   public ArrayList<String> data = new ArrayList<>();//data set
    Context context;


    @NonNull
    @Override
    public view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(context).inflate(R.layout.single_todo_design,parent,false);
      view_holder l = new view_holder(v);
      return l;
    }

    @Override
    public void onBindViewHolder(@NonNull view_holder holder, int position) {

           holder.tv.setText(data.get(position));

    }

    rv_adapter(ArrayList<String> data,Context context)
    {

         this.data = data;
         this.context=context;

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void move(int from, int to) {
        //for rearranging items in rv
         String tmp = data.get(from);
         data.remove(from);
         data.add(to,tmp);
         notifyItemMoved(from,to);
        }

    public void remove(int position) {
        //for removing items from rv
        data.remove(position);
        notifyItemRemoved(position);


    }


    class view_holder extends RecyclerView.ViewHolder {

        TextView tv;
        RadioButton rb;

        public view_holder(@NonNull final View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            rb = itemView.findViewById(R.id.rb);
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  int index =  data.indexOf(tv.getText().toString());
                   data.remove(index);
                   notifyItemRemoved(index);
                }
            });
        }
    }


}

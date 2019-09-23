package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class add_item extends AppCompatActivity {

    public EditText task;
    public String et_data;
    public boolean update_mode = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        task = findViewById(R.id.task_name);

       et_data =  getIntent().getStringExtra("value");
       Toast.makeText(this,et_data,Toast.LENGTH_LONG).show();
       if(et_data!=null)
       {
           task.append(et_data);
           update_mode=true;
       }
    }


        public void ok(View view) {
            String message = task.getText().toString();
            Boolean flag;
            if(!message.isEmpty())
            {
                sql_helper lp = new sql_helper(this);

                if(update_mode){
                    boolean isupdated = lp.update(et_data,message);
                    Toast.makeText(this,"update done ::"+isupdated,Toast.LENGTH_LONG).show();
                }
                else {

                    flag = lp.add(task.getText().toString());
                    Toast.makeText(this, flag + "::", Toast.LENGTH_LONG).show();

                    //adding new task to db
                }


                finish();


            }
            else
                Toast.makeText(this,"The field is empty",Toast.LENGTH_LONG).show();

        }

    public void cancel(View view) {
        finish();
    }
}
//Todo:possibility of duplicate entries



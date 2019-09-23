package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<String> data_set;
    rv_adapter adptr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);

        update_ui();


    }

    @Override
    protected void onStart() {
       super.onStart();
        load_data();
        System.out.println("onrestrt");
        System.out.println(data_set);
        adptr.put_data(data_set);
      Toast.makeText(this,"onstrt",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sql_helper obj = new sql_helper(this);
        obj.put_data(adptr.get_data());
        Toast.makeText(this,"onstop",Toast.LENGTH_LONG).show();

    }



    private void update_ui() {
        load_data();
        adptr = new rv_adapter(data_set,getApplicationContext());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adptr);

        itm_tch_helper obj = new itm_tch_helper();
        new ItemTouchHelper(obj).attachToRecyclerView(rv);
    }

    private void load_data() {
        sql_helper obj = new sql_helper(this);

        Cursor reslt =  obj.get_data();
        data_set = new ArrayList<>();
        while (reslt.moveToNext())
        {
                String tmp = reslt.getString(reslt.getColumnIndexOrThrow("items"));
                data_set.add(tmp);

        }

        System.out.println("load data");
        System.out.println(data_set);


    }


    private void load_fake_data() {}
    public void add_newtsk(View view) {

        Intent add = new Intent(this,add_item.class);
        startActivity(add);
    }


    class itm_tch_helper extends ItemTouchHelper.Callback{


      @Override
      public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
          return makeMovementFlags(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT);
      }

      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
          adptr.move(source.getAdapterPosition(),target.getAdapterPosition());

          return true;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
      {

        adptr.remove(viewHolder.getAdapterPosition(),viewHolder);


      }
  }

}

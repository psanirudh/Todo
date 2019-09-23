package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
        load_fake_data();

        adptr = new rv_adapter(data_set,getApplicationContext());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adptr);

        itm_tch_helper obj = new itm_tch_helper();
        new ItemTouchHelper(obj).attachToRecyclerView(rv);

    }

    private void load_fake_data() {
        data_set = new ArrayList<String>();
        for(int i=0;i<6;i++)
            data_set.add(""+i);

        Toast.makeText(this,data_set.get(4),Toast.LENGTH_LONG).show();
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

        adptr.remove(viewHolder.getAdapterPosition());


      }
  }

}

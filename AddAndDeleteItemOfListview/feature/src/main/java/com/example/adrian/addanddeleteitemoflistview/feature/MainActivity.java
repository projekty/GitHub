package com.example.adrian.addanddeleteitemoflistview.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> itemList;

    ArrayAdapter<String> adapter;

    EditText itemText;

    Button addButton;

    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);
        itemText = (EditText) findViewById(R.id.addtext);
        addButton = (Button) findViewById(R.id.addbutton);


        itemList = new ArrayList<>();

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_multiple_choice, itemList);

        View.OnClickListener addlistner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.add(itemText.getText().toString());
                itemText.setText("");
                adapter.notifyDataSetChanged();
            }
        };


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                SparseBooleanArray positionchecker = lv.getCheckedItemPositions();
                int count = lv.getCount();
                for (int item = count - 1; item >= 0; item--) {
                    if (positionchecker.get(item)) {
                        adapter.remove(itemList.get(item));


                    }
                }

                positionchecker.clear();

                adapter.notifyDataSetChanged();

                return false;
            }
        });


        addButton.setOnClickListener(addlistner);
        lv.setAdapter(adapter);


    }
}

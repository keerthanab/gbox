package com.example.gbox;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ProductFinderActivity extends Activity {

    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_upcoming_birthday);

        Model.LoadModel();
        listView = (ListView) findViewById(R.id.upcoming_birthday_listview);
        String[] ids = new String[Model.Items.size()];
        for (int i= 0; i < ids.length; i++){

            ids[i] = Integer.toString(i+1);
        }

        ItemAdapter adapter = new ItemAdapter(this, R.layout.list_row_item, ids);
        listView.setAdapter(adapter);

    }
}
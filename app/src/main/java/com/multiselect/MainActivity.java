package com.multiselect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    int preSelectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = (ListView) findViewById(R.id.listview);


        final List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(false, "Dharm"));
        users.add(new UserModel(false, "Mark"));
        users.add(new UserModel(false, "Singh"));
        users.add(new UserModel(false, "The Mobile Era"));

        final CustomAdapter adapter = new CustomAdapter(this, users);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserModel model = users.get(i); //changed it to model because viewers will confused about it

                model.setSelected(true);

                users.set(i, model);

                if (preSelectedIndex > -1){

                    UserModel preRecord = users.get(preSelectedIndex);
                    preRecord.setSelected(false);

                    users.set(preSelectedIndex, preRecord);

                }

                preSelectedIndex = i;

                //now update adapter so we are going to make a update method in adapter
                //now declare adapter final to access in inner method

                adapter.updateRecords(users);
            }
        });

    }
}

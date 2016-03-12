package com.example.admin.homework05;
/*
* Assignment - Homework Assignment-5
* File Name -  MainActivity.java
 * Team Members - Indraneel Bende
 *                Priyanka Mehta
 *                Shamalee Narkhede
* */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> cities=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.plus){
            Intent i=new Intent(MainActivity.this,Addcity.class);
            startActivityForResult(i, 178);
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==178)
        {
            if(resultCode==RESULT_OK)
            {
                String city=data.getStringExtra("city");
                String state=data.getStringExtra("state");

                city=city.concat(",");
                city=city.concat(state);
                Log.d("city",city);
                cities.add(city);
                TextView t = (TextView) findViewById(R.id.default1);
                t.setVisibility(View.INVISIBLE);
                ListView l = (ListView) findViewById(R.id.listView);
                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, cities);

                l.setAdapter(arrayAdapter);

                l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String[] selectedItem=cities.get(position).split(",");
                        String selectedCity=selectedItem[0];
                        String selectedState=selectedItem[1];
                        String url="http://api.wunderground.com/api/c42612782f41f98b/hourly/q/" + selectedState + "/"
                                + selectedCity + ".xml";
                        Intent intent=new Intent(MainActivity.this,HourlyDataActivity.class);
                        intent.putExtra("URL",url);
                        intent.putExtra("City",selectedCity);
                        intent.putExtra("State",selectedState);
                        startActivity(intent);
                    }
                });



                l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        cities.remove(position);
                        if(cities.isEmpty())
                        {
                            TextView t = (TextView) findViewById(R.id.default1);
                            t.setVisibility(View.VISIBLE);
                        }
                        arrayAdapter.notifyDataSetChanged();
                        return false;
                    }
                });
            }
        }
    }
}

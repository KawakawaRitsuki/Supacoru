package com.kawakawaplanning.supacoru;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {


    public static ListView listView;
    public static Context context;
    int mCardLayoutIndex = 0;
    CustomAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        adapter = new CustomAdapter(this,R.layout.card_item);

        listView = (ListView)findViewById(R.id.listView);
        listView.setDivider(null);
        listView.setVerticalScrollBarEnabled(false);
        listView.setSelector(android.R.color.transparent);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Item item = adapter.getItem(position);

                Intent intent=new Intent();
                intent.setClassName("com.kawakawaplanning.supacoru", "com.kawakawaplanning.supacoru.Show");
                intent.putExtra("title", item.title);
                intent.putExtra("url", item.url);
                startActivity(intent);

            }
        });

        if (mCardLayoutIndex > 0) {
            listView.addFooterView(LayoutInflater.from(this).inflate(
                    R.layout.card_footer, listView, false));
        }

        new GetTask(this,listView,adapter).execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.kawakawaplanning.supacoru;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{


    public ListView listView;
    public Context context;
    int mCardLayoutIndex = 0;
    public static CustomAdapter adapter ;
    public static SwipeRefreshLayout mSwipeRefreshLayout;
    public static int max = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = this;

        adapter = new CustomAdapter(context,R.layout.card_item);

        listView = (ListView)findViewById(R.id.listView);
        listView.setDivider(null);
        listView.setVerticalScrollBarEnabled(false);
        listView.setSelector(android.R.color.transparent);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent();
                intent.setClassName("com.kawakawaplanning.supacoru", "com.kawakawaplanning.supacoru.Show");

                intent.putExtra("pos", position);
                startActivity(intent);

            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.pink);
        mSwipeRefreshLayout.setOnRefreshListener(this);

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

    @Override
    public void onRefresh() {
        new GetTask(this,listView,adapter).execute();
    }
}

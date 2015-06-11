package com.kawakawaplanning.supacoru;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{


    public ListView listView;
    public Context context;
    int mCardLayoutIndex = 0;
    public static CustomAdapter adapter ;
    public static SwipeRefreshLayout mSwipeRefreshLayout;
    public static int max = 0;

    public static final int MENU_SELECT_A = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        context = this;

        Toast.makeText(this,this.getCacheDir().toString(),Toast.LENGTH_SHORT).show();

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
        mSwipeRefreshLayout.setRefreshing(true);
        new GetTask(this,listView,adapter).execute();

    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0, MENU_SELECT_A, 0, "オープンソースライセンス");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SELECT_A:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                LayoutInflater inflater = (LayoutInflater)this.getSystemService(
                        LAYOUT_INFLATER_SERVICE);
                View view =  inflater.inflate(R.layout.opensourcelicense,
                        (ViewGroup)findViewById(R.id.rootLayout));
                alertDialogBuilder.setTitle("オープンソースライセンス");
                alertDialogBuilder.setView(view);
                alertDialogBuilder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                alertDialogBuilder.setCancelable(true);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
        }
        return false;
    }

    @Override
    public void onRefresh() {
        new GetTask(this,listView,adapter).execute();
    }
}

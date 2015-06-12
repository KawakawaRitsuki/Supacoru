package com.KawakawaPlanning.supacoru;

import android.app.AlertDialog;
import android.content.Context;
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

public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{


    public ListView listView;
    public Context context;
    int mCardLayoutIndex = 0;
    public static MainListAdapter adapter ;
    public static SwipeRefreshLayout mSwipeRefreshLayout;
    public static int max = 0;

    public static final int MENU_SELECT_A = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        new GetTask(this,listView,adapter).execute();

    }

    public void init(){

        /*

            初期化メソッド

        - MainActivityのContextを定義。
        - ListViewの定義、マテリアル化。
        - SwipeRefresの定義。

         */

        context = this;//外部からアクセスする用のコンテキスト:もっとスマートな方法がある気しかしない。
        adapter = new MainListAdapter(context,R.layout.card_item);//リスト用のカスタムなアダプタ

        listView = (ListView)findViewById(R.id.listView);
        listView.setDivider(null);
        listView.setVerticalScrollBarEnabled(false);
        listView.setSelector(android.R.color.transparent);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent();
                intent.setClassName("com.KawakawaPlanning.supacoru", "com.KawakawaPlanning.supacoru.Show");
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipelayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.pink);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        if (mCardLayoutIndex > 0)
            listView.addFooterView(LayoutInflater.from(this).inflate(R.layout.card_footer, listView, false));
        mSwipeRefreshLayout.setRefreshing(true);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0, MENU_SELECT_A, 0, "オープンソースライセンス");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_SELECT_A:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View view =  inflater.inflate(R.layout.opensourcelicense,(ViewGroup)findViewById(R.id.rootLayout));
                alertDialogBuilder.setTitle("オープンソースライセンス");
                alertDialogBuilder.setView(view);
                alertDialogBuilder.setPositiveButton("OK",null);
                alertDialogBuilder.setCancelable(true);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
        }
        return false;
    }
    
    @Override
    public void onRefresh() {
        //SwipeRefreshのリスナ
        new GetTask(this,listView,adapter).execute();
    }
}

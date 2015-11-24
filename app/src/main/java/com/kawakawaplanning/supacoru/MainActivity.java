package com.KawakawaPlanning.supacoru;

import android.app.AlertDialog;
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

import com.KawakawaPlanning.supacoru.http.HttpConnector;
import com.KawakawaPlanning.supacoru.http.OnHttpErrorListener;
import com.KawakawaPlanning.supacoru.http.OnHttpResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{


    public ListView listView;
    public int mCardLayoutIndex = 0;
    public MainListAdapter adapter ;
    public SwipeRefreshLayout mSwipeRefreshLayout;

    public ArrayList<String> mTitles = new ArrayList<>();
    public ArrayList<String> mUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        get();
    }

    public void init(){

        /*
            初期化メソッド
        - ListViewの定義、マテリアル化。
        - SwipeRefreshの定義。
         */

        adapter = new MainListAdapter(this,R.layout.card_item);//リスト用のカスタムなアダプタ

        listView = (ListView)findViewById(R.id.listView);
        listView.setDivider(null);
        listView.setVerticalScrollBarEnabled(false);
        listView.setSelector(android.R.color.transparent);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Show.class);
                intent.putExtra("position", position);
                intent.putExtra("titles",mTitles);
                intent.putExtra("urls",mUrls);
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
        menu.add(0, 0, 0, "オープンソースライセンス");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.opensourcelicense,(ViewGroup)findViewById(R.id.rootLayout));
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
        get();
    }

    public void get(){
        HttpConnector httpConnector = new HttpConnector("http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=100&q=http://pronama-api.azurewebsites.net/feed/spaco");
        httpConnector.setOnHttpResponseListener(new OnHttpResponseListener() {
            @Override
            public void onResponse(String response) {
                try {
                    mSwipeRefreshLayout.setRefreshing(false);
                    JSONObject json = new JSONObject(response);
                    JSONArray data = json.getJSONObject("responseData").getJSONObject("feed").getJSONArray("entries");
                    mTitles.clear();
                    mUrls.clear();
                    for (int i = 0; i != data.length(); i++) {
                        JSONObject object = data.getJSONObject(i);
                        JSONObject ob = object.getJSONArray("mediaGroups").getJSONObject(0).getJSONArray("contents").getJSONObject(0);

                        adapter.add(new Item(object.getString("title"), ob.getString("url")));
                        mTitles.add(object.getString("title"));
                        mUrls.add(ob.getString("url"));
                    }

                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        httpConnector.setOnHttpErrorListener(new OnHttpErrorListener() {
            @Override
            public void onError(int error) {
                mSwipeRefreshLayout.setRefreshing(false);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("ネットワークエラー");
                alertDialogBuilder.setMessage("ネットワークエラーが発生しました。インターネットの接続状態を確認して、再読み込み（上から下にスライド）してください。");
                alertDialogBuilder.setPositiveButton("OK",null);
                alertDialogBuilder.setCancelable(true);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        httpConnector.getWithHandler();
    }
}

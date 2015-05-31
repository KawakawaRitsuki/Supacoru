package com.kawakawaplanning.supacoru;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.service.GistService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by KP on 15/05/31.
 */
public class GetTask extends AsyncTask {

    ListView listView;
    Context context;
    StringBuffer getDataBuffer;
    String[] getLine;
    CustomAdapter adapter;

    public GetTask(Context context,ListView listView,CustomAdapter adapter){
        this.context = context;
        this.listView = listView;
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listView.setAdapter(null);
        getDataBuffer = new StringBuffer();
    }

    @Override
    protected Object doInBackground(Object[] params) {

        try {
            GistService service = new GistService();
            for (Gist gist : service.getGists("kawakawaritsuki")){

                if (gist.getFiles().get("Data.csv") != null){
                    System.out.println(gist.getFiles().get("Data.csv").getRawUrl());

                    URLConnection connection = new URL(gist.getFiles().get("Data.csv").getRawUrl()).openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                    String str;
                    while ((str = reader.readLine()) != null) {
                        getDataBuffer.append(str + "\n");
                    }
                    reader.close();
                    getLine = getDataBuffer.toString().split("\n");

                    Log.v("KP", getLine.length +"");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);



        for (String str : getLine){
            String data[] = str.split(",");
            adapter.add(new Item(data[0],data[1]));
        }

        listView.setAdapter(adapter);

    }
}

package com.androwep.androidjsondataparsing.JsondataRecycleyerVIew;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.androwep.androidjsondataparsing.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class JsonRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<DataList> arrayList;
    String name,depertment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_recycler_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<DataList>();
        Jsontask jsontask = new Jsontask();
        jsontask.execute();


    }


    public class  Jsontask extends AsyncTask<String, String, String>{
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream;
        BufferedReader bufferedReader;
        StringBuffer stringBuffer;
        String fullfile;
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("https://api.myjson.com/bins/oruz8");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                stringBuffer = new StringBuffer();
                String line = "";
                while ((line=bufferedReader.readLine()) != null){
                    stringBuffer.append(line);

                }

                fullfile = stringBuffer.toString();
                JSONObject jsonObject = new JSONObject(fullfile);
                JSONObject jsonObjectchild = jsonObject.getJSONObject("autorjson");
                for (Iterator key = jsonObjectchild.keys(); key.hasNext();){
                    JSONObject autor = (JSONObject) jsonObjectchild.get((String) key.next());

                    String name = autor.getString("name");
                    String dep = autor.getString("depertment");
                    arrayList.add(new DataList(name,depertment));
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            DataAdapter dataAdapter = new DataAdapter(arrayList,getApplicationContext());
            recyclerView.setAdapter(dataAdapter);

        }
    }
}

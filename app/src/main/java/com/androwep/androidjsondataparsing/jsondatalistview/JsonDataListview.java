package com.androwep.androidjsondataparsing.jsondatalistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.androwep.androidjsondataparsing.MainActivity;
import com.androwep.androidjsondataparsing.R;

import org.json.JSONArray;
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
import java.util.List;

public class JsonDataListview extends AppCompatActivity {
    private ListView listView;
    //private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_data_listview);

      listView = (ListView) findViewById(R.id.l1);
       // recyclerView = (RecyclerView) findViewById(R.id.l1);

        JsonTask jsonTask = new JsonTask();
        jsonTask.execute();

    }
    private class  JsonTask extends AsyncTask<String,String,List<DemoStudent>>{

        @Override
        protected List<DemoStudent> doInBackground(String... strings) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;
            String JsonFile;

            try {

                URL url = new URL("https://api.myjson.com/bins/ui3z8");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line);
                }

                //return stringBuffer.toString();
                JsonFile = stringBuffer.toString();
                JSONObject mainObject = new JSONObject(JsonFile);
                JSONArray studentArray = mainObject.getJSONArray("studentjson");

                //for ListView or ReyclerView

                List<DemoStudent> demoStudentList = new ArrayList<>();
               for (int i=0; i<studentArray.length(); i++){
                    JSONObject ArrayObject = studentArray.getJSONObject(i);
                    DemoStudent demoStudent = new DemoStudent();

                    demoStudent.setName(ArrayObject.getString("name"));
                    demoStudent.setDepartment(ArrayObject.getString("department"));
                    demoStudent.setCountry(ArrayObject.getString("country"));

                    demoStudentList.add(demoStudent);
               }

                return demoStudentList;



            } catch (MalformedURLException e) {


                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {
                httpURLConnection.disconnect();

                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }


        @Override
        protected void onPostExecute(List<DemoStudent> s) {
            super.onPostExecute(s);
            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),R.layout.sample,s);
            
            listView.setAdapter(customAdapter);

           // recyclerView.setAdapter(customAdapter);


        }
    }
     public void onBackPressed() {

        startActivity(new Intent(JsonDataListview.this,MainActivity.class));

    }
}

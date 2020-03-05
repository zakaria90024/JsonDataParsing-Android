package com.androwep.androidjsondataparsing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androwep.androidjsondataparsing.JsonImgParsing.JsonImgParsingActivity;
import com.androwep.androidjsondataparsing.jsondatalistview.JsonDataListview;

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

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button btn;
    private Button imgbtn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textViewData);
        btn = (Button) findViewById(R.id.openBtn);
        imgbtn = (Button) findViewById(R.id.btnImagStart);

        jsonTask jtask = new jsonTask();
        jtask.execute();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, JsonDataListview.class);
                startActivity(i);
                finish();
            }
        });

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, JsonImgParsingActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    public class jsonTask extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;

            String name;
            Integer age;
            String Discreption;

            try {

                URL url = new URL("https://api.myjson.com/bins/zwxv8");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line = "";
                StringBuffer lastBuffer =new StringBuffer();

                while ((line = bufferedReader.readLine())!= null){
                    stringBuffer.append(line);

                }

                //for access file
                String file = stringBuffer.toString();
                JSONObject fileObject = new JSONObject(file);
                JSONArray jsonArray = fileObject.getJSONArray("studentinfo");

                for(int i=0; i<jsonArray.length(); i++){
                    JSONObject arrayObject = jsonArray.getJSONObject(i);

                    name = arrayObject.getString("name");
                    age = arrayObject.getInt("age");
                    Discreption = arrayObject.getString("Discreption");
                    lastBuffer.append(i+". "+name+"\n" +age+ "\n" +Discreption+"\n\n\n");
                }



                return lastBuffer.toString();



            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            textView.setText(s);


        }
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Are you Want to Exit?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.super.onBackPressed();
            }
        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        // super.onBackPressed();
    }
}

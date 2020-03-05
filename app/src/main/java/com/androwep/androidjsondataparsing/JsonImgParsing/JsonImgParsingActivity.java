package com.androwep.androidjsondataparsing.JsonImgParsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androwep.androidjsondataparsing.MainActivity;
import com.androwep.androidjsondataparsing.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

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

public class JsonImgParsingActivity extends AppCompatActivity {
    private ListView listView;
    private TextView textView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_img_parsing);
        textView = (TextView)findViewById(R.id.t1);
        imageView = (ImageView) findViewById(R.id.imgId);

        //  displayImage(...) call if no options will be passed to this method

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()

           .cacheInMemory(true)
                .cacheOnDisk(true)

           .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())

           .defaultDisplayImageOptions(defaultOptions)

           .build();
        ImageLoader.getInstance().init(config);

        // Do it on Application start





        listView = (ListView)findViewById(R.id.l1);
        JsonWork jsonWork = new JsonWork();
        jsonWork.execute();
    }

    public class  JsonWork extends AsyncTask<String, String, List<CarModule>>{
        HttpURLConnection httpURLConnection  = null;
        BufferedReader bufferedReader = null;
        String myFile;
        @Override
        protected List<CarModule> doInBackground(String... strings) {
            try {

                URL url = new URL("https://api.myjson.com/bins/yev9g");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer stringBuffer = new StringBuffer();
                String line = "";


                while ((line = bufferedReader.readLine()) != null){
                    stringBuffer.append(line);
                }

                List<CarModule> carModuleList = new ArrayList<>();

                myFile = stringBuffer.toString();
                JSONObject FileObject  = new JSONObject(myFile);
                JSONArray cars = FileObject.getJSONArray("cars");

                for(int i = 0; i <cars.length(); i++){
                    JSONObject innerObj = cars.getJSONObject(i);
                    CarModule carModule = new CarModule();

                    carModule.setName(innerObj.getString("name"));
                    carModule.setImg(innerObj.getString("img"));

                    carModuleList.add(carModule);
                }

                return carModuleList;

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
        protected void onPostExecute(List<CarModule> s) {
            super.onPostExecute(s);

            CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),R.layout.car_sample, s);
            listView.setAdapter(customAdapter);
        }

    }

    //For back press
    public void onBackPressed() {

        startActivity(new Intent(JsonImgParsingActivity.this, MainActivity.class));

    }
}

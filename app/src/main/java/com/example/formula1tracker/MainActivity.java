package com.example.formula1tracker;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    int counter = 0;
    String url = "https://www.formula1.com/en/results.html/2019/drivers.html";
    Document document = null;
    TextView output = null;
    Elements elements = null;



    class driver{
        public int position;
        public String firstname;
        public String lastname;
        public String shorthandname;
        public String nationality;
        public String team;
        public int points;
    }

    ArrayList<String> list_of_driver = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);
        FloatingActionButton fab = findViewById(R.id.fab);



        /*Elements elements = document.select("div.limiter");

        String driver = "LEWIS";


        output.setText(document.title());*/

        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                output.setText("WORKING");
                new Content().execute();

                for (String s : list_of_driver){
                    output.setText(s);
                    pause(1000);
                }

                /*counter ++;
                output.setText(Integer.toString(counter));*/
            }
        });
    }
    private class Content extends AsyncTask<Void, Void, Void>  {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String output;
            try{
                document = Jsoup.connect(url).get();
                elements = document.select("tr");
            }catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (document != null){
                int skip = 0;
                for (Element e : elements){
                    if (skip == 0){
                        skip = 1;
                        continue;
                    }

                    list_of_driver.add(e.text());

                }

                for (String s : list_of_driver){
                    output.setText(s);
                    pause(1000);
                }
            }else{
                output.setText("CONNECTION ERROR.");
            }
        }
    }

    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("IOException: %s%n", e);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


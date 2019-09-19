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
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

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


        public driver(){

        }

        public driver(String firstn, String lastn, String shorthandn, String nation, String t){
            firstname = firstn;
            lastname = lastn;
            shorthandname = shorthandn;
            nationality = nation;
            team = t;

        }
    }

    ArrayList<String> list_of_driver = new ArrayList<String>();
    driver HAM = new driver("Lewis", "Hamilton", "HAM", "GBR", "Mercedes");
    driver BOT = new driver("Valtteri", "Bottas", "BOT", "FIN", "Mercedes");
    driver VER = new driver("Max", "Verstappen", "VER", "NED", "Red Bull Racing Honda");
    driver LEC = new driver("Charles", "Leclerc", "LEC", "MON", "Ferrari");
    driver VET = new driver("Sebastian", "Vettel", "VET", "GER", "Ferrari");
    driver GAS = new driver("Pierre", "Gasly", "GAS", "FRA", "Scuderia Toro Rosso Honda");
    driver SAI = new driver("Carlos", "Sainz", "SAI", "ESP", "McLaren Renault");
    driver RIC = new driver("Daniel", "Ricciardo", "RIC", "AUS", "Renault");
    driver ALB = new driver("Alexander", "Albon", "ALB", "THA", "Red Bull Racing Honda");
    driver KVY = new driver("Daniil", "Kvyat", "KVY", "RUS", "Scuderia Toro Rosso Honda");
    driver HUL = new driver("Nico", "Hulkenberg", "HUL", "GER", "Renault");
    driver RAI = new driver("Kimi", "Räikkönen", "RAI", "FIN", "Alfa Romeo Racing Ferrari");
    driver PER = new driver("Sergio", "Perez", "PER", "MEX", "Racing Point BWT Mercedes");
    driver NOR = new driver("Lando", "Norris", "NOR", "GBR", "McLaren Renault");
    driver STR = new driver("Lance", "Stroll", "STR", "CAN", "Racing Point BWT Mercedes");
    driver MAG = new driver("Kevin", "Magnussen", "MAG", "DEN", "Haas Ferrari");
    driver GRO = new driver("Romain", "Grosjean", "GRO", "FRA", "Haas Ferrari");
    driver GIO = new driver("Antonio", "Giovinazzi", "GIO", "ITA", "Alfa Romeo Racing Ferrari");
    driver KUB = new driver("Robert", "Kubica", "KUB", "POL", "Williams Mercedes");
    driver RUS = new driver("George", "Russell", "RUS", "POL", "Williams Mercedes");
    List<driver> drivers = Arrays.asList(HAM, BOT, VER, LEC, VET, GAS, SAI, RIC, ALB, KVY, HUL, RAI, PER, NOR, STR, MAG, GRO, GIO, KUB, RUS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);
        FloatingActionButton fab = findViewById(R.id.fab);

        output.setText("WORKING");
        new Content().execute();

        for (String s : list_of_driver){
            for (driver d : drivers){
                if (s.contains(d.firstname)){
                    String lastWord = s.substring(s.lastIndexOf(" ")+1);
                    int points = Integer.parseInt(lastWord);
                    d.points = points;
                }
            }
        }
        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

            output.setText(VER.points);

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


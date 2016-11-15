package com.example.jahnavi.dataretrieval;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView text;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              new MyTask().execute();
            }
        });
    }

    public class MyTask extends AsyncTask<Void, Void, Void> {
        String words;

        @Override
        protected Void doInBackground(Void... voids) {
            Document doc,doc2;
            String html = "https://www.iiitd.ac.in/about";
            try {

                doc = Jsoup.connect(html).get();
                Log.d("IN try","hello");
              Element content = doc.getElementById("node-10");
               Elements p= content.getElementsByTag("p");

               words = p.get(2).text();

                doc2=Jsoup.connect(html).get();
               String all=doc2.text();
                Log.d("Content from About page",all);

            } catch (IOException ex) {
                System.out.println("err: " + ex);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            text.setText(words);
          
        }
    }

}


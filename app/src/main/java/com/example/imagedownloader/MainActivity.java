package com.example.imagedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    EditText myUrl;
    String s;
    public void downloadImage(View view){
        s= myUrl.getText().toString();
        ImageDownloader download= new ImageDownloader();
        try {
            Bitmap myImage = download.execute(s).get();
            image.setImageBitmap(myImage);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image= findViewById(R.id.image);
        myUrl= findViewById(R.id.editText);
    }
    public class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myBitmap= BitmapFactory.decodeStream(in);
                return myBitmap;
            } catch (Exception e) {
                return null;
            }
        }
    }
}

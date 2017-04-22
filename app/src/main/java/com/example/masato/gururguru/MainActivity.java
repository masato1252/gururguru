package com.example.masato.gururguru;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_menu1;
    private Button btn_menu2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        btn_menu1 = (Button) this.findViewById(R.id.btn_menu1);
        btn_menu1.setOnClickListener(this);

        btn_menu2 = (Button) this.findViewById(R.id.btn_menu2);
        btn_menu2.setOnClickListener(this);

     }



    @Override
    public void onClick(View view) {

        if(view == btn_menu1){

            Intent intent = new Intent(this, TestWebActivity.class);
            intent.putExtra("mode", 1);
            startActivity(intent);
            Log.d("intent", "1");
        }else if(view == btn_menu2){

            Intent intent = new Intent(this, TestWebActivity.class);
            intent.putExtra("mode", 2);
            startActivity(intent);
            Log.d("intent", "2");
        }


    }

}



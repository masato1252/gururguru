package com.example.masato.gururguru;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private Button btn_start;
    private AsyncConnect asyncConnect;

    private static final String testUrl = "http://smt.docomo.ne.jp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(getApplicationContext());
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            CookieManager cm = CookieManager.getInstance();
//            cm.setAcceptCookie(true);
//            cm.setCookie(testUrl, "A");
//
//        }

        btn_start = (Button) this.findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        WebView webView = (WebView)findViewById(R.id.wv_main);
        webView.setWebViewClient(new MyWVClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 4.1.1; Nexus 7 Build/JRO03S) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Safari/535.19");
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        //webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        webView.loadUrl("http://smt.docomo.ne.jp/");

        //webView.getSettings().LoadsImagesAutomatically = true;
        //webView.Settings.AllowFileAccessFromFileURLs = true;
        //webView.Settings.AllowUniversalAccessFromFileURLs = true;
    }



    @Override
    public void onClick(View view) {

        asyncConnect = new AsyncConnect(this);
        asyncConnect.execute(testUrl);

    }

}


class MyWVClient extends WebViewClient {
//    private static final String LOGIN_URL = "https://www.hatena.ne.jp/login";
//    private static final String JS_SETVALUE =
//            "javascript:document.querySelector('%s').value='%s';void 0;";
    private static final String JS_CLICKANCHOR =
            "javascript:document.querySelector('%s').click();";

    private static final String JS_CODE1 =
            "javascript:document.querySelector('div#boxPoint').querySelector('a').click();";

    private static final String JS_CODE2 =
            "javascript:document.querySelector('div#no-login').querySelector('a').click();";

    private static final String JS_CODE3_1 =
            "javascript:document.querySelector('input[name=authid]').value='09090841258';void 0;";

    private static final String JS_CODE3_2 =
            "javascript:document.querySelector('input[name=authpass]').value='MATSUURA8';void 0;";

    private static final String JS_CODE3_3 =
            "javascript:document.querySelector('input[name=subForm]').click();";

//        private static final String JS_CODE1 =
//            "javascript:document.querySelector('li[class=customer]').querySelector('a').click();";
//
//        private static final String JS_CODE2 =
//            "javascript:document.querySelector('p[id=dcmoto_K01-07]').querySelector('a').click();";
//
//        private static final String JS_CODE3_1 =
//            "javascript:document.querySelector('input[name=authid]').value='09090841258';void 0;";
//
//        private static final String JS_CODE3_2 =
//            "javascript:document.querySelector('input[name=authpass]').value='MATSUURA8';void 0;";
//
//        private static final String JS_CODE3_3 =
//            "javascript:document.querySelector('input[name=subForm]').click();";

    private Integer pageCount = 0;

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        pageCount++;
        try {
            if (pageCount == 1) {
                //Thread.sleep(2000);
                view.loadUrl(JS_CODE1);
            } else if (pageCount == 2) {
                Thread.sleep(2000);
                view.loadUrl(JS_CODE2);
            } else if (pageCount == 3) {
                view.loadUrl(JS_CODE3_1);
                view.loadUrl(JS_CODE3_2);
                view.loadUrl(JS_CODE3_3);
            }

        }catch(InterruptedException e){

        }


//        if (url.startsWith(this.LOGIN_URL)) {
//            view.loadUrl(String.format(JS_SETVALUE, "#login-name", "username"));
//            view.loadUrl(String.format(JS_SETVALUE, "input.password", "password"));
//            view.loadUrl(String.format(JS_CLICKANCHOR, "input.submit-button"));
//        }



    }
}
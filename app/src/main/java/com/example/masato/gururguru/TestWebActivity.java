package com.example.masato.gururguru;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by masato on 2017/03/29.
 */

public class TestWebActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_start;
    private Button btn_back;

    private AsyncConnect asyncConnect;
    private Integer mode = 1;

    private static final String testUrl = "http://smt.docomo.ne.jp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        mode = intent.getIntExtra("mode", 1);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WebView.setWebContentsDebuggingEnabled(true);
//        } else

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(getApplicationContext());
            cookieSyncMngr.startSync();
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager cm = CookieManager.getInstance();
            cm.setAcceptCookie(true);
            //cm.setCookie(BuildConfig.BUILD_TYPE, "abc");
        }


        btn_start = (Button) this.findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
        btn_back = (Button) this.findViewById(R.id.btn_test_back);
        btn_back.setOnClickListener(this);

        WebView webView = (WebView) findViewById(R.id.wv_main);
        MyWVClient mvc = new MyWVClient();
        mvc.setMode(mode);
        mvc.preProcess();
        webView.setWebViewClient(mvc);
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

        if(view == btn_back){
            Intent intent = new Intent(this, MainActivity.class);
            //intent.putExtra("mode", 1);
            startActivity(intent);
        }
        //asyncConnect = new AsyncConnect(this);
        //asyncConnect.execute(testUrl);

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

        //private static final String JS_CODE2 =
        //        "javascript:document.querySelector('div#no-login').querySelector('a').click();";

        private static final String JS_CODE2 =
                "javascript:document.querySelector('ul[class=cmn-footer__loginout]').querySelector('a').click();";

        private static final String JS_CODE3_1 =
                "javascript:document.querySelector('input[name=authid]').value='09090841258';void 0;";

        private static final String JS_CODE3_2 =
                "javascript:document.querySelector('input[name=authpass]').value='MATSUURA8';void 0;";

        private static final String JS_CODE3_3 =
                "javascript:document.querySelector('input[name=subForm]').click();";


    private static final String JS_1 =
            "javascript:document.querySelector('a[swid=dm_toplink]').click();";

    private static final String JS_2 =
            "javascript:document.querySelector('dl[id=docomoid_block_daccountlogin]').querySelector('a').click();";

    private static final String JS_3_1 =
            "javascript:document.querySelector('input[name=authid]').value='09090841258';void 0;";

    private static final String JS_3_2 =
            "javascript:document.querySelector('input[name=authpass]').value='MATSUURA8';void 0;";

    private static final String JS_3_3 =
            "javascript:document.querySelector('input[name=subForm]').click();";


    //private static final String JS_4 =
    //        "javascript:document.querySelector('li[class=detail]').querySelector('a').click();";

//        private static final String JS_CODE1 =
//            "javascript:document.querySelector('li[class=customer]').querySelector('a').click();";
//
//        private static final String JS_CODE2 =
//            "javascript:document.querySelector('ul[class=cmn-footer__loginout]').querySelector('a').click();";
//
//        private static final String JS_CODE3_1 =
//            "javascript:document.querySelector('input[name=authid]').value='09090841258';void 0;";
//
//        private static final String JS_CODE3_2 =
//            "javascript:document.querySelector('input[name=authpass]').value='MATSUURA8';void 0;";
//
//        private static final String JS_CODE3_3 =
//            "javascript:document.querySelector('input[name=subForm]').click();";

        private Integer mode = 0;
        private Integer pageCount = 0;

        public void setMode(Integer mode){
            this.mode = mode;
        }

        public void preProcess() {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().flush();
            } else {

            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                CookieManager cm = CookieManager.getInstance();
                cm.setAcceptCookie(true);
                //cm.setCookie(BuildConfig.BUILD_TYPE, "abc");
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("mode", mode.toString());
            pageCount++;
            try {

                if(mode==2) {
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

                }else if(mode==1){

                    if (pageCount == 1) {
                        view.loadUrl(JS_1);
                    } else if (pageCount == 2) {
                        view.loadUrl(JS_2);
                    } else if (pageCount == 3) {
                        view.loadUrl(JS_3_1);
                        view.loadUrl(JS_3_2);
                        view.loadUrl(JS_3_3);
                    }
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

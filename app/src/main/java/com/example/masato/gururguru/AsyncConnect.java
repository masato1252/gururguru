package com.example.masato.gururguru;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by masato on 2017/03/12.
 */

public class AsyncConnect extends AsyncTask<String, Integer, String> {

    private Activity activity;
    private ArrayList<Object> array;
    private ProgressDialog dialog;



    public AsyncConnect(Activity act) {

        this.activity = act;

    }


    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(this.activity);
        dialog.setTitle("Please wait");
        dialog.setMessage("Connected Test URL...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(true);
        //dialog.setOnCancelListener(this.mainActivity);
        dialog.setMax(100); dialog.setProgress(0);
        dialog.show();

        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        dialog.setProgress(values[0].intValue());
        super.onProgressUpdate(values);
    }


    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub

        Document str = getHtml(params[0]);

        return "";
    }

    @Override
    protected void onPostExecute(String result) {

        dialog.setProgress(100);
        dialog.dismiss();


    }


    private Document getHtml(String url) {

        String ua = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36 ";

        try{
            Document document = Jsoup.connect(url).userAgent(ua).get();
            //System.out.println(document.html());

            //Elements elements = document.select("div #boxPoint");
            Elements elements = document.select("[swid=pd_area]");

            System.out.println(elements.outerHtml());

            System.out.println("elemetnts[0]:" + elements.get(0).attr("href"));
            //String a = URLDecoder.decode(elements.get(0).attr("href").toString(), "UTF-8");
            String a = URLDecoder.decode(elements.get(0).attr("href").toString(), "UTF-8");

            System.out.println("elemetnts[0]:" + a);
            Document document2 = Jsoup.connect(a).get();

            //Document document2 = Jsoup.connect(a).userAgent(ua).get();
            System.out.println(document2.html());


            return document;
        }catch(IOException e){

        }
            return null;
    }

//    public SyncInfo getSyncInfo(String str){
//
//        //ArrayList<Object> array = new ArrayList<Object>();
//        SyncInfo si = new SyncInfo();
//
//        try {
//
//            JSONObject jo = new JSONObject(str);
//            Log.d("JSONParse", jo.toString());
//
//            Integer state = jo.getInt("state");
//
//            if(state==1){
//
//                si.setState(state);
//                si.setStartDate(jo.getString("start_date"));
//                si.setNowDate(jo.getString("now_date"));
//
//            }else{
//
//                si.setState(state);
//            }
//
//            return si;
//
//            //JSONArray jArray = jo.getJSONArray("places");
//            //Log.d("x", jArray2.getJSONObject("places").toString());
////            int a = jArray.length();
////            for(int i=0; i<a; i++){
////                p = new PlaceData();
////                //j = jArray.getJSONObject(i);
////                //jArray2 = jArray
////                JSONObject tmp = jArray.getJSONObject(i);
////                Iterator<String> keys = tmp.keys();
////                JSONObject tmp2 = tmp.getJSONObject(keys.next());
////
////                JSONObject tmp3 = tmp2.getJSONObject("source_info");
////                Log.d("xx", tmp3.toString());
////
////                ((PlaceData) p).setName(tmp3.getString("name"));
////                ((PlaceData) p).setPlace_id(Integer.valueOf(a-i));
////                ((PlaceData) p).setStation(tmp3.getString("station"));
////                ((PlaceData) p).setUrl(tmp3.getString("url"));
////                ((PlaceData) p).setTel(tmp3.getString("tel"));
////                this.array.add(p);
////                //this.array.add(p);
////                onProgressUpdate(Math.round((float)((float)i/(float)a)));
////            }
//
//        } catch (JSONException e) {
//            //e.printStackTrace();
//            return null;
//        }
//
//    }


//
//    public String GetData(String URL){
//
//        String xml = null;
//        HttpClient httpClient = new DefaultHttpClient();
//
//        StringBuilder uriBuilder = null;
//
//        uriBuilder = new StringBuilder(URL);
//
//        HttpGet request = new HttpGet(uriBuilder.toString());
//        HttpResponse result = null;
//
//        try {
//            request.setHeader( "User-Agent", "Mozilla/5.0" );
//            result = httpClient.execute(request);
//
//            InputStream content = result.getEntity().getContent();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
//            StringBuilder buf = new StringBuilder();
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//
//                buf.append(line);
//
//            }
//            //Log.d("test",buf.toString());
//            xml = buf.toString();
//
//
//        }catch (ClientProtocolException e) {
//            //throw new RuntimeException(e); //FIXME
//            return "-1";
//        } catch (IOException e) {
//            //throw new RuntimeException(e); //FIXME
//            return "-1";
//        } finally {
//            httpClient.getConnectionManager().shutdown();
//        }
//
//        return xml;
//
//    }



//    @Override
//    protected void onPostExecute(SyncInfo result) {
//
//        Integer y,mo,d;
//        Integer h_s, m_s, s_s, mm_s;
//        Integer h_n, m_n, s_n, mm_n;
//
//        if(result==null){
//            dialog.setProgress(100);
//            dialog.dismiss();
//
//            AlertDialog.Builder alertDlg = new AlertDialog.Builder(activity);
//            alertDlg.setTitle("通信エラー");
//            alertDlg.setMessage("同期できませんでした");
//            alertDlg.setPositiveButton(
//                    "OK",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            // OK ボタンクリック処理
//                        }
//                    });
//            // 表示
//            alertDlg.create().show();
//
//            return;
//        }
//        dialog.setProgress(80);
//
//        //startDate
//        String[] s_arr = result.getStartDate().split(" ", 0);
//        String[] s_arr_ = s_arr[0].split("-", 0);
//        y = Integer.parseInt(s_arr_[0]);
//        mo = Integer.parseInt(s_arr_[1]);
//        d = Integer.parseInt(s_arr_[2]);
//
//        String[] s_arr__ = s_arr[1].split(":", 0);
//        h_s = Integer.parseInt(s_arr__[0]);
//        m_s = Integer.parseInt(s_arr__[1]);
//        s_s = Integer.parseInt(s_arr__[2].substring(0,2));
//
//        mm_s = Integer.parseInt(s_arr__[2].substring(3,6));
//
//        //nowDate
//        String[] n_arr = result.getNowDate().split(" ", 0);
//
//        String[] n_arr__ = n_arr[1].split(":", 0);
//        h_n = Integer.parseInt(n_arr__[0]);
//        m_n = Integer.parseInt(n_arr__[1]);
//        s_n = Integer.parseInt(n_arr__[2].substring(0,2));
//
//        mm_n = Integer.parseInt(n_arr__[2].substring(3,6));
//
//        //conv_millSeconds
//        Calendar cal_s = Calendar.getInstance();
//        cal_s.set(y, mo, d, h_s, m_s, s_s);
//
//        Calendar cal_n = Calendar.getInstance();
//        cal_n.set(y, mo, d, h_n, m_n, s_n);
//
//        Long s_long = cal_s.getTimeInMillis() + mm_s;
//        Long n_long = cal_n.getTimeInMillis() + mm_n;
//
//        Long past = n_long - s_long;
//
//        Integer a_day = 24 * 60 * 60 * 1000;
//        Long past_mili = past % a_day;
//        Integer past_sec = (int)Math.floor((double)past_mili / (double)1000);
//
//        Integer times = (int)Math.floor((double)past_sec/(double)unitSec) + 1;
//        Log.d("times", times.toString());
//        final Integer sync_time = unitSec * times * 1000;
//
//        Integer sleep_time = sync_time - Integer.parseInt(past_mili.toString());
//
//        mp = MediaPlayer.create(activity, R.raw.test1);
//        //mp.prepare();
//
//
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//
//                Log.d("seek", sync_time.toString());
//                mp.seekTo(sync_time+150);
//                mp.start();
//
//                dialog.setProgress(100);
//                dialog.dismiss();
//
//            }
//        }, sleep_time);
//
//
//
//        super.onPostExecute(result);
//    }
}

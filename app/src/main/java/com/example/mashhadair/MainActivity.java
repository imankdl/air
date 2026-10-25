package com.example.mashhadair;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView w = new WebView(this);
        setContentView(w);
        WebSettings s = w.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        try {
            InputStream is = getAssets().open("index.html");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            int n;
            while ((n = is.read(buf)) > 0) {
                out.write(buf, 0, n);
            }
            is.close();
            String html = new String(out.toByteArray(), "UTF-8");
            w.loadDataWithBaseURL("https://mashhad-air.app/", html, "text/html", "UTF-8", null);
        } catch (Exception e) {
            w.loadData("error", "text/plain", "UTF-8");
        }
    }
}

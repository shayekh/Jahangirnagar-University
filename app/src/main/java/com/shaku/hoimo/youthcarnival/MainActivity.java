package com.shaku.hoimo.youthcarnival;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
private WebView myWebView;
private ProgressBar progressBar;
//private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myWebView = (WebView)findViewById(R.id.webView);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        //textView=(TextView)findViewById(R.id.text);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo==null)
        {
            //textView.setText("Check Internet Connection");
            Intent intent=new Intent(MainActivity.this,Custom.class);
            startActivity(intent);
        }

        //if (networkInfo != null && networkInfo.isConnected()) {
            myWebView.setWebViewClient(new WebViewClient(){

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    super.onPageStarted(view, url, favicon);
                    progressBar.setVisibility(View.VISIBLE);
                    //textView.setText("Loading...");

                    //setTitle("Loading....");
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    //Fun Part will be here :)
                    progressBar.setVisibility(View.GONE);
                    //textView.setText(View.GONE);
                     //setTitle(view.getTitle());
                    myWebView.loadUrl("javascript:(function() { " +
                            "var foot = document.getElementsByClassName('container-fluid')[0].style.display='none'; " +
                            "var head = document.getElementsByClassName('container')[0].style.display='none'; " +
                            "})()");
                }
            });

            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            myWebView.loadUrl("http://juniv.edu/");

        myWebView.setDownloadListener(new DownloadListener()
        {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength)
            {
                //download file using web browser
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        //}

    }
    @Override
    public void onBackPressed() {
        if(myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
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

        if(id==R.id.home)
        {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        if (id == R.id.query) {
            String phone = "01708527323";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        }
        if (id == R.id.location) {
            Uri gmmIntentUri = Uri.parse("geo:23.8823635,90.2667756");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if (mapIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(mapIntent);
            }
        }

        return super.onOptionsItemSelected(item);
    }

}


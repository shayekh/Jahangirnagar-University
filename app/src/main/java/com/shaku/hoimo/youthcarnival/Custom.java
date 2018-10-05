package com.shaku.hoimo.youthcarnival;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Custom extends AppCompatActivity{
       private TextView textView;

       @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.custom);
        textView=(TextView)findViewById(R.id.customtext);
        textView.setText("Check Internet Connection");
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
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
            finish();
            return true;
        }


        if(id==R.id.ho)
        {
            Intent intent=new Intent(this,MainActivity.class);
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

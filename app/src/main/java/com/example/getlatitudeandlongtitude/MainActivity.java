package com.example.getlatitudeandlongtitude;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edInput;
    Button btnsubmit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edInput=findViewById(R.id.edInput);
        btnsubmit=findViewById(R.id.btnsubmit);

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Address=edInput.getText().toString();
                GeoLocationAddress geoLocationAddress=new GeoLocationAddress();
                geoLocationAddress.getAddress(Address,getApplicationContext(),new GeoHandler());

            }
        });
    }

    private class GeoHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            String Latitude;
            String Longtude;
            switch (msg.what){
                case 1:
                    Bundle bundle=msg.getData();
                    Latitude=bundle.getString("latitude");
                    Longtude=bundle.getString("longtiude");

                    break;
                default:
                    Latitude=null;
                    Longtude=null;

            }

            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setCancelable(false);
            builder.setMessage("Latitude = "+Latitude+"\n"+"Longtitude = "+Longtude);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }
}
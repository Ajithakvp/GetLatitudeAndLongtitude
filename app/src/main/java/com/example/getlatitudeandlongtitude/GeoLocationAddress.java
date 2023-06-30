package com.example.getlatitudeandlongtitude;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeoLocationAddress {

    public  void getAddress(String LocationAddreess, Context context, Handler handler){

        Thread thread=new Thread(){
            @Override
            public void run() {
                Geocoder geocoder=new Geocoder(context, Locale.getDefault());
                String Latitude=null;
                String Longtiude=null;
                try {
                    List addressList=geocoder.getFromLocationName(LocationAddreess,1);
                    if (addressList !=null && addressList.size()>0){
                        Address address=(Address) addressList.get(0);
                        StringBuilder strLatitude=new StringBuilder();
                        StringBuilder strLongitude=new StringBuilder();

                        strLatitude.append(address.getLatitude()).append("\n");
                        strLongitude.append(address.getLongitude()).append("\n");
                        Latitude=strLatitude.toString();
                        Longtiude=strLongitude.toString();


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                   Message message=Message.obtain();
                   message.setTarget(handler);
                   if (Latitude !=null &&  Longtiude !=null ){
                       message.what=1;
                       Bundle bundle=new Bundle();
                       Latitude=Latitude;
                       Longtiude=Longtiude;

                       bundle.putString("latitude",Latitude);
                       bundle.putString("longtiude",Longtiude);

                       message.setData(bundle);
                   }
                   message.sendToTarget();
                }
            }
        };
        thread.start();
    }


}

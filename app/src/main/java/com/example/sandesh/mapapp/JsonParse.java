
package com.example.sandesh.mapapp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sandesh on 7/4/17.
 */

public class JsonParse extends AsyncTask<String, String, String> {


    private final GoogleMap mMap;
    Context context;
    String requrl = "http://www.mocky.io/v2/58e87786120000171159b67f";
    String response = null;
    String TAG = HttpHandler.class.getSimpleName();
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;

    public JsonParse(GoogleMap mMap) {
        this.mMap = mMap;
    }




    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String response) {


        List<String> idList = new ArrayList<String>();
        String name, id, lat, lng, currentlat, currentng;
        double v1, v2, currlat, currlng;
        String palcename;
        GoogleApiClient googleApiClient;
        LocationManager locationManager;



        if(response!=null){
            try{
                JSONObject jsonObject=new JSONObject(response);
                JSONArray jsonArray=jsonObject.getJSONArray("places");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject obj = jsonArray.getJSONObject(i);
                    id = obj.getString("id");
                    name = obj.getString("name");
                    lat = obj.getString("latitude");
                    lng = obj.getString("longitude");

                    v1=Double.parseDouble(lat);
                    v2=Double.parseDouble(lng);

                    LatLng place = new LatLng(v1, v2);
                    mMap.addMarker(new MarkerOptions().position(place).title(name));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(place));


                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }


    }

    @Override
    protected String doInBackground(String... params) {

        HttpHandler ht=new HttpHandler();
        response=ht.getResponse(requrl);

        return response;
    }
}

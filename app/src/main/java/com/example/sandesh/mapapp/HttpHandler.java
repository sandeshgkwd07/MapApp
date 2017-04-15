
package com.example.sandesh.mapapp;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;

/**
 * Created by sandesh on 8/4/17.
 */
public class HttpHandler {

        String response;
        String TAG=HttpHandler.class.getSimpleName();
        public String getResponse(String requrl){

            try{

                URL url=new URL(requrl);
                HttpURLConnection con=(HttpURLConnection) url.openConnection();
                con.connect();
                int responsecode=con.getResponseCode();
                if(responsecode==200){

                    con.setRequestMethod("GET");
                    Log.e(TAG,"Connected Sucessfully");
                    InputStream in = new BufferedInputStream(con.getInputStream());
                    response = convertStreamToString(in);
                    //Log.e(TAG,"Response:"+response);
                }

            }catch (MalformedInputException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }

            return response;
        }

    private String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}

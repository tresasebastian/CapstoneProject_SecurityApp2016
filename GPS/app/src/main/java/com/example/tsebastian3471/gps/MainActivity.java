package com.example.tsebastian3471.gps;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView testViewStatus;
    TextView textViewLatitude;
    TextView textViewLongitude;
    LocationManager myLocationManager;
    String PROVIDER = LocationManager.GPS_PROVIDER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testViewStatus = (TextView)findViewById(R.id.status);
        textViewLatitude = (TextView)findViewById(R.id.latitude);
        textViewLongitude = (TextView)findViewById(R.id.longitude);
        myLocationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);   //get last known location, if available   Location location = myLocationManager.getLastKnownLocation(PROVIDER);   showMyLocation(location);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        myLocationManager.removeUpdates(myLocationListener);
    }
    @Override  protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        myLocationManager.requestLocationUpdates(
                PROVIDER,     //provider
                0,       //minTime
                0,      //minDistance
                myLocationListener); //LocationListener
    }
    private void showMyLocation(Location l){
        if(l == null){
            testViewStatus.setText("No Location!");
        }
        else{
            textViewLatitude.setText("Latitude: " + l.getLatitude());
            textViewLongitude.setText("Longitude: " + l.getLongitude());
        }
    }
    private LocationListener myLocationListener  = new LocationListener(){
        @Override
        public void onLocationChanged(Location location) {
            showMyLocation(location);
        }
        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    };
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


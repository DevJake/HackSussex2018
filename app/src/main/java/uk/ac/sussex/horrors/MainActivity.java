package uk.ac.sussex.horrors;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {
    private MapView mapView;
    private GoogleMap map;
    private LatLng loc = null;


    public void start_game(View v) {
        setContentView(R.layout.start_adventure);
    }

    public void back_to_map(View v) {
        setContentView(R.layout.activity_main);
    }

    public void main_menu(View v) {
        setContentView(R.layout.main_menu);
    }

    public void not_yet(View v) {
        Toast.makeText(this, "Feature not yet implemented", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync(new OnMapReadyCallback() {
            @SuppressLint("MissingPermission")
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                map.getUiSettings().setMyLocationButtonEnabled(true);


                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                        },
                        1);

                map.setMyLocationEnabled(true);


                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        loc = new LatLng(
                                location.getLatitude(),
                                location.getLongitude());

                        map.addMarker(new MarkerOptions().position(loc).title("Current Location"));
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                                loc,
                                17);
                        map.animateCamera(cameraUpdate);
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                        System.out.println("Provider status change=" + provider + ", status=" + status);
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                    }
                };


                LocationManager locationManager = ((LocationManager) getSystemService(LOCATION_SERVICE));
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

                Bitmap bmp0 = BitmapFactory.decodeResource(getResources(), R.drawable.clue);
                Bitmap bmp1 = BitmapFactory.decodeResource(getResources(), R.drawable.id);
                Bitmap bmp2 = BitmapFactory.decodeResource(getResources(), R.drawable.key);
                Bitmap bmp3 = BitmapFactory.decodeResource(getResources(), R.drawable.semi_clue);
                Bitmap bmp4 = BitmapFactory.decodeResource(getResources(), R.drawable.ticket);
                Bitmap bmp5 = BitmapFactory.decodeResource(getResources(), R.drawable.tape);
                // Updates the location and zoom of the MapView


                map.addMarker(new MarkerOptions().position(new LatLng(50.8657078, -0.0873014)).icon(BitmapDescriptorFactory.fromBitmap(bmp0)));
                map.addMarker(new MarkerOptions().position(new LatLng(50.8652786, -0.0895481)).icon(BitmapDescriptorFactory.fromBitmap(bmp1)));
                map.addMarker(new MarkerOptions().position(new LatLng(50.8651628, -0.0878964)).icon(BitmapDescriptorFactory.fromBitmap(bmp2)));
                map.addMarker(new MarkerOptions().position(new LatLng(50.8645464, -0.0880857)).icon(BitmapDescriptorFactory.fromBitmap(bmp3)));
                map.addMarker(new MarkerOptions().position(new LatLng(50.8621349, -0.0870025)).icon(BitmapDescriptorFactory.fromBitmap(bmp4)));
                map.addMarker(new MarkerOptions().position(new LatLng(50.8673016, -0.0895657)).icon(BitmapDescriptorFactory.fromBitmap(bmp5)));

                onResume();
            }
        });
    }
    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
package uk.ac.sussex.horrors;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }
    public void start_game(View v){
        setContentView(R.layout.start_adventure);
    }

    public void back_to_map(View v){
        setContentView(R.layout.activity_main);
    }

    public void main_menu(View v){
        setContentView(R.layout.main_menu);
    }

    public void not_yet(View v){
        Toast.makeText(this, "Feature not yet implemented", Toast.LENGTH_SHORT).show();
    }
}

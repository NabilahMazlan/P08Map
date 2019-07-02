package com.example.p08_map;

import android.Manifest;
import android.graphics.Camera;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btnEast, btnCentral, btnNorth;
    Spinner spn;
    TextView tvDesc;
    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.mapFragment);
        btnCentral = findViewById(R.id.btnCentral);
        btnEast = findViewById(R.id.btnEast);
        btnNorth = findViewById(R.id.btnNorth);
        tvDesc = findViewById(R.id.tvInfo);
        spn = findViewById(R.id.spinnerItems);


        tvDesc.setText("ABC Pte Ltd\n\nWe now have 3 branches. Look below for the address and info");

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gMap = googleMap;

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

                if(permissionCheck == PermissionChecker.PERMISSION_GRANTED){
                    gMap.setMyLocationEnabled(true);
                }else{
                    Log.e("GMAP - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                final LatLng admiratly = new LatLng(1.424450, 103.829850);
                final LatLng orchard = new LatLng(1.2976615, 103.8474856);
                final LatLng tampines = new LatLng(1.3680067, 103.9517366);
                final LatLng singapore = new LatLng(1.3521, 103.8198);

                Marker admiraltyMarker = gMap.addMarker(new MarkerOptions().position(admiratly).title("North - HQ").snippet("Operating Hours: 10am-5pm \n Tel: 65433456").icon(BitmapDescriptorFactory.fromResource(R.drawable.minishop_vector)));
                Marker orchardMarker = gMap.addMarker(new MarkerOptions().position(orchard).title("Central").snippet("Operating Hours: 11am-8pm \n Tel: 67788652").icon(BitmapDescriptorFactory.fromResource(R.drawable.minishop_vector)));
                Marker tampinesMarker = gMap.addMarker(new MarkerOptions().position(tampines).title("East").snippet("Operating Hours: 9am-5pm \n Tel: 66776677").icon(BitmapDescriptorFactory.fromResource(R.drawable.minishop_vector)));

                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore,11));
                UiSettings settings = gMap.getUiSettings();
                settings.setCompassEnabled(true);
                settings.setZoomControlsEnabled(true);

                gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Toast.makeText(getBaseContext(), marker.getTitle(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

                btnNorth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Marker admiraltyMarker = gMap.addMarker(new MarkerOptions().position(admiratly).title("North - HQ").snippet("Operating Hours: 10am-5pm \n Tel: 65433456").icon(BitmapDescriptorFactory.fromResource(R.drawable.minishop_vector)));
                        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(admiratly,15));

                    }
                });

                btnCentral.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Marker orchardMarker = gMap.addMarker(new MarkerOptions().position(orchard).title("Central").snippet("Operating Hours: 11am-8pm \n Tel: 67788652").icon(BitmapDescriptorFactory.fromResource(R.drawable.minishop_vector)));
                        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(orchard,15));

                    }
                });

                btnEast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Marker tampinesMarker = gMap.addMarker(new MarkerOptions().position(tampines).title("East").snippet("Operating Hours: 9am-5pm \n Tel: 66776677").icon(BitmapDescriptorFactory.fromResource(R.drawable.minishop_vector)));
                        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tampines,15));

                    }
                });

                spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i){
                            case 0:
                                if(gMap != null) {
                                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore, 11));
                                }
                                break;
                            case 1:
                                if(gMap != null) {
                                    Marker admiraltyMarker = gMap.addMarker(new MarkerOptions().position(admiratly).title("North - HQ").snippet("Operating Hours: 10am-5pm \n Tel: 65433456").icon(BitmapDescriptorFactory.fromResource(R.drawable.minishop_vector)));
                                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(admiratly, 15));
                                }
                                break;
                            case 2:
                                if(gMap != null) {
                                    Marker orchardMarker = gMap.addMarker(new MarkerOptions().position(orchard).title("Central").snippet("Operating Hours: 11am-8pm \n Tel: 67788652").icon(BitmapDescriptorFactory.fromResource(R.drawable.minishop_vector)));
                                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(orchard, 15));
                                }
                                break;
                            case 3:
                                if(gMap != null) {
                                    Marker tampinesMarker = gMap.addMarker(new MarkerOptions().position(tampines).title("East").snippet("Operating Hours: 9am-5pm \n Tel: 66776677").icon(BitmapDescriptorFactory.fromResource(R.drawable.minishop_vector)));
                                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tampines, 15));
                                }
                                break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });





            }
        });
    }
}

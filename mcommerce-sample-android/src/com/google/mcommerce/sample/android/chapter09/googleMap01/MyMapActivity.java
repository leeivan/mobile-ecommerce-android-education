package com.google.mcommerce.sample.android.chapter09.googleMap01;

import android.os.Bundle;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.mcommerce.sample.android.R;

public class MyMapActivity extends MapActivity {
	private MapView map;
	private MapController controller;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.c09_map_layout);
		initMapView();
		initMyLocation();
	}

	/** Find and initialize the map view. */
	private void initMapView() {
		map = (MapView) findViewById(R.id.map);
		controller = map.getController();
		map.setSatellite(true);
		map.setBuiltInZoomControls(true);
	}

	/** Start tracking the position on the map. */
	private void initMyLocation() {
		final MyLocationOverlay overlay = new MyLocationOverlay(this, map);
		overlay.enableMyLocation();
		// overlay.enableCompass(); // does not work in emulator
		overlay.runOnFirstFix(new Runnable() {
			public void run() {
				// Zoom in to current location
				controller.setZoom(8);
				controller.animateTo(overlay.getMyLocation());
			}
		});
		map.getOverlays().add(overlay);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// Required by MapActivity
		return false;
	}
}

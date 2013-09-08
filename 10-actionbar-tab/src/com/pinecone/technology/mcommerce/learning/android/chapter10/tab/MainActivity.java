package com.pinecone.technology.mcommerce.learning.android.chapter10.tab;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends Activity implements ActionBar.TabListener {

	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current tab position.
	 */

	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar to show tabs.
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// For each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(actionBar.newTab().setText(R.string.title_section1)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_section2)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_section3)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_section3)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_section3)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.title_section3)
				.setTabListener(this));
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current tab position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current tab position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, show the tab contents in the
		// container view.
		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER,
				tab.getPosition() + 1);
		fragment.setArguments(args);
		getFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A dummy fragment representing a section of the app
	 */

	public static class DummySectionFragment extends Fragment {
		public static final String ARG_SECTION_NUMBER = "placeholder_text";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setText("TAB"+Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return textView;
		}
	}

}

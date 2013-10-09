package com.pinecone.technology.mcommerce.learning.android.chapter03;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentTransaction;

public class MainActivity extends Activity implements
		MyListFragment.OnItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (findViewById(R.id.fragment_container) != null) {

			// However, if we're being restored from a previous state,
			// then we don't need to do anything and should return or else
			// we could end up with overlapping fragments.
			if (savedInstanceState != null) {
				return;
			}
			// Create an instance of ExampleFragment
			MyListFragment listFragment = new MyListFragment();

			// In case this activity was started with special instructions from
			// an Intent,
			// pass the Intent's extras to the fragment as arguments
			listFragment.setArguments(getIntent().getExtras());

			// Add the fragment to the 'fragment_container' FrameLayout
			getFragmentManager().beginTransaction()
					.add(R.id.fragment_container, listFragment).commit();
		}
	}

	// if the wizard generated an onCreateOptionsMenu you can delete
	// it, not needed for this tutorial
	@Override
	public void onRssItemSelected(String link) {
		DetailFragment newFragment = new DetailFragment();
		Bundle args = new Bundle();
		args.putString(DetailFragment.CURRENT_TIME, link);
		newFragment.setArguments(args);
		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();

		// Replace whatever is in the fragment_container view with this
		// fragment,
		// and add the transaction to the back stack so the user can navigate
		// back
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
		transaction.replace(R.id.fragment_container, newFragment);
		transaction.addToBackStack(null);
		// Commit the transaction
		transaction.commit();
	}

}
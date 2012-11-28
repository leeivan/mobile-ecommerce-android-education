package com.google.mcommerce.sample.android.chapter04.actionBar;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.mcommerce.sample.android.R;

public class SearchResultsActivity 
extends DebugActivity 
{
	private static String tag=
	  "Search Results Activity";
	
	public SearchResultsActivity()
	{
		super(R.menu.c04_basemenu,
			R.layout.main,
			R.id.textViewId,
			tag);
	}
    protected boolean 
    onMenuItemSelected(MenuItem item)
    {
    	//nothing to do
    	//base class handles base menu of clear
    	return true;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        final Intent queryIntent = getIntent();
        doSearchQuery(queryIntent);
    }
    @Override
    public void onNewIntent(final Intent newIntent) 
    {
        super.onNewIntent(newIntent);
        final Intent queryIntent = getIntent();
        doSearchQuery(queryIntent);
    }
    private void doSearchQuery(final Intent queryIntent) 
    {
        final String queryAction = queryIntent.getAction();
        if (!(Intent.ACTION_SEARCH.equals(queryAction))) 
        {
            Log.d(tag,"intent NOT for search");
            return;
        }
        final String queryString = 
            queryIntent.getStringExtra(SearchManager.QUERY);
        this.reportBack(tag, queryString);
    }
}//eof-class
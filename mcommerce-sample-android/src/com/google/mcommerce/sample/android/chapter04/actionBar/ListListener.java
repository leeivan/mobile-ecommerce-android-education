package com.google.mcommerce.sample.android.chapter04.actionBar;

import android.app.ActionBar;
import android.content.Context;

public class ListListener 
extends BaseListener
implements ActionBar.OnNavigationListener
{
	public ListListener(
	Context ctx, IReportBack target)
	{
		super(ctx, target);
	}
	public boolean onNavigationItemSelected(
	int itemPosition, long itemId) 
	{
		this.mReportTo.reportBack(
	      "list listener","ItemPostion:" + itemPosition);
		return true;
	}
}

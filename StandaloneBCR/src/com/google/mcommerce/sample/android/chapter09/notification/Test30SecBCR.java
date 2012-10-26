package com.google.mcommerce.sample.android.chapter09.notification;

public class Test30SecBCR
extends ALongRunningReceiver
{
	@Override
	public Class getLRSClass() 
	{
		Utils.logThreadSignature("Test30SecBCR");
		return Test30SecBCRService.class;
	}
}

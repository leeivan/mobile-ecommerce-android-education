package com.google.mcommerce.sample.android.chapter09.notification;

public class Test60SecBCR
extends ALongRunningReceiver
{
	@Override
	public Class getLRSClass() 
	{
		Utils.logThreadSignature("Test60SecBCR");
		return Test60SecBCRService.class;
	}
}

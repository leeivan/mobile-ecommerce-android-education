package com.google.mcommerce.sample.android.chapter09.notification;

public class TestLRBCR
extends ALongRunningReceiver
{
	@Override
	public Class getLRSClass() 
	{
		Utils.logThreadSignature("TestLRBCR");
		return TestLRBCRService.class;
	}
}

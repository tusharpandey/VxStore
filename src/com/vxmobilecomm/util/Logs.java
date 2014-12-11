package com.vxmobilecomm.util;

import android.util.Log;

public class Logs 
{
	boolean isShowLogs = true ;
	String className ;
	
	public Logs ( String className )
	{
		this.className = className ;
	}
	
	public void showLogs ( String msg )
	{
		if ( isShowLogs )
		{
			Log.e(className, msg) ;
		}
	}
}

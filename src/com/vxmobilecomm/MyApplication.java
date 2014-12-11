package com.vxmobilecomm;

import org.acra.ACRA;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import android.app.Application;

import com.vxappstore.R;

@ReportsCrashes(formKey = "", 
mailTo = "tushar.2april@gmail.com",
mode=ReportingInteractionMode.SILENT,
resToastText = R.string.crash_toast_text)

public class MyApplication extends Application 
{
	@Override
    public void onCreate() 
	{
        super.onCreate();
//        ACRA.init(this);
    }
}

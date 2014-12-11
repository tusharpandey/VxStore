package com.vxmobilecomm.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vxappstore.R;
import com.vxmobilecomm.util.CustomExceptionHandler;
import com.vxmobilecomm.util.Logs;
import com.vxmobilecomm.util.MySharedPref;

public class SplashScreen extends Activity 
{
	private Logs logs;
	private ImageView scene;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);
		logs = new Logs("SplashScreen");
		
		if(!(Thread.getDefaultUncaughtExceptionHandler() instanceof CustomExceptionHandler)) {
		    Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler(this));
		}	
		
		final LinearLayout layout_linearLayout = (LinearLayout) findViewById(R.id.lay_splashscreen);
		layout_linearLayout.post(new Runnable() {
			public void run() {
				int height = layout_linearLayout.getHeight();
				int width = layout_linearLayout.getWidth();

				logs.showLogs(height+"");
				logs.showLogs(width+"");

				MySharedPref.getInstance(SplashScreen.this).setString(
						"sendScreenHeight_SPLASHSCREEN", height + "");
				MySharedPref.getInstance(SplashScreen.this).setString(
						"sendScreenWidth_SPLASHSCREEN", width + "");
				
				initView();

				runSplashScreenHandler();
				setImageViewHeightAndWidth(scene, height*3/4);
			}
		});	
	}
	
	public void startTransitionAnimation()
	{
		new Handler().postDelayed(new Runnable() {
	       	 
            @Override
            public void run() {
            	initView();
            }
        }, 600);
	}
	
	public void setImageViewHeightAndWidth(View view , int height)
	{
		LinearLayout.LayoutParams layoutParams  = new LinearLayout.LayoutParams((int)(height*(.2808)),height);
		view.setLayoutParams(layoutParams);
	}

	private void initView() {
		scene = (ImageView) findViewById(R.id.lightgraphic);
		TransitionDrawable sceneDrawable =(TransitionDrawable) scene.getDrawable();
		sceneDrawable.reverseTransition(2100);				
	}
	
	public void runSplashScreenHandler()
	{
		new Handler().postDelayed(new Runnable() {
       	 
            @Override
            public void run() {
            	Intent intentVXMainPage = new Intent ( SplashScreen.this , VXMobileComm.class) ;
				startActivity(intentVXMainPage) ;
 
                finish();
            }
        }, 2400);
	}
	
}

package com.vxmobilecomm.activity;

import android.app.Activity;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.vxappstore.R;

public class splash_dresschange extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashdresschange);
		
		ImageView scene = (ImageView) findViewById(R.id.lightgraphic);
		TransitionDrawable sceneDrawable =(TransitionDrawable) scene.getDrawable();
		sceneDrawable.reverseTransition(3000);				
	}
}

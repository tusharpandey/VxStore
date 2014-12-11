package com.vxmobilecomm.activity;

import java.util.Dictionary;
import java.util.Hashtable;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import com.vxappstore.R;
import com.vxmobilecomm.BaseActivity;
import com.vxmobilecomm.adapter.ProductListingAdapter;
import com.vxmobilecomm.util.CustomExceptionHandler;
import com.vxmobilecomm.util.Logs;

public class VXMobileComm extends BaseActivity {

	private Logs logs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vxmobile_comm);
		logs = new Logs("VXMobileComm");
		
		if(!(Thread.getDefaultUncaughtExceptionHandler() instanceof CustomExceptionHandler)) {
		    Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler(this));
		}	

		initView();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		addToolBar(true,true);
	}
	
	public void initView() 
	{
		ListView list_productlist = ( ListView ) findViewById(R.id.list_productlist) ;
		
		Dictionary<String, Dictionary<String, String>> dictionary = new Hashtable<String, Dictionary<String,String>>() ;
		Dictionary<String, String> innerDictionary = new Hashtable<String, String>() ;
		innerDictionary.put("name", "name") ;
		
		dictionary.put(Integer.toString(0), innerDictionary);
		dictionary.put(Integer.toString(1), innerDictionary);
		dictionary.put(Integer.toString(2), innerDictionary);
		dictionary.put(Integer.toString(3), innerDictionary);
		dictionary.put(Integer.toString(4), innerDictionary);
		
		ProductListingAdapter listingAdapter = new ProductListingAdapter(this, dictionary) ;
		list_productlist.setAdapter(listingAdapter);
	}
	
}

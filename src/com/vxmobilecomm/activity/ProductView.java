package com.vxmobilecomm.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.vxappstore.R;
import com.vxmobilecomm.util.CustomExceptionHandler;
import com.vxmobilecomm.util.Logs;

public class ProductView extends ActionBarActivity implements OnClickListener 
{
	private Logs logs;
	private ImageView img_productimage;
	private FrameLayout lay_framelayout;
	private TextView txt_productName;
	private ImageView img_shareThisProduct;
	private ImageView img_addToWishList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);		
	    setContentView(R.layout.activity_productview);
		logs = new Logs("ProductView");
		
		if(!(Thread.getDefaultUncaughtExceptionHandler() instanceof CustomExceptionHandler)) 
		{
		    Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler(this));
		}	

		initView();
	}
	
	public void initView()
	{
		lay_framelayout = ( FrameLayout ) findViewById(R.id.lay_framelayout) ;
		
		img_productimage = ( ImageView ) findViewById(R.id.img_productimage) ;
		img_productimage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				if ( lay_framelayout.getVisibility() == View.GONE )
				{
					visibility_yes();
				}
				else if ( lay_framelayout.getVisibility() == View.VISIBLE )
				{
					visibility_no();
				}
			}
		});
	}
	
	public void initFrameView(boolean visibility)
	{
		if ( visibility )
		{
			lay_framelayout.setVisibility(View.VISIBLE) ;
			enableScrolledViews(true);
		}
		else
		{
			lay_framelayout.setVisibility(View.GONE) ;
			enableScrolledViews(false);
		}
	}
	
	public void enableScrolledViews ( boolean visibility )
	{
		txt_productName = ( TextView ) findViewById(R.id.txt_productName) ;
		txt_productName.setOnClickListener(this);
		
		img_shareThisProduct = ( ImageView ) findViewById(R.id.btn_shareThisProduct) ;
		img_shareThisProduct.setOnClickListener(this);
		
		img_addToWishList = ( ImageView ) findViewById(R.id.btn_addToWishList) ;
		img_addToWishList.setOnClickListener(this);
		
		if ( visibility )
		{
			txt_productName.setVisibility(View.VISIBLE);
			img_shareThisProduct.setVisibility(View.VISIBLE);
			img_addToWishList.setVisibility(View.VISIBLE);
		}
		else
		{
			txt_productName.setVisibility(View.GONE);
			img_shareThisProduct.setVisibility(View.GONE);
			img_addToWishList.setVisibility(View.GONE);
		}
		
	}
	
	public void visibility_yes ()
	{
		logs.showLogs("visibility_yes");
		initFrameView(true);
		
		Animation animContentDown = AnimationUtils
				.loadAnimation(getApplicationContext(),
						R.anim.slide_down_service_1);
		animContentDown
				.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(
							Animation animation) {
					}

					@Override
					public void onAnimationRepeat(
							Animation animation) {
					}

					@Override
					public void onAnimationEnd(
							Animation animation) 
					{
							
					}
				});
		
		lay_framelayout.startAnimation(animContentDown);
	}
	
	public void visibility_no ()
	{
		logs.showLogs("visibility_no");
		
		Animation animContentDown = AnimationUtils
				.loadAnimation(getApplicationContext(),
						R.anim.slide_up_service_1);
		animContentDown
				.setAnimationListener(new AnimationListener() {
					@Override
					public void onAnimationStart(
							Animation animation) {
					}

					@Override
					public void onAnimationRepeat(
							Animation animation) {
					}

					@Override
					public void onAnimationEnd(
							Animation animation) 
					{
						initFrameView(false);
					}
				});
		
		lay_framelayout.startAnimation(animContentDown);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_shareThisProduct:
				showLogs("img_shareThisProduct clicked");
			break;
		case R.id.btn_addToWishList:
			showLogs("img_addToWishList clicked");
			break;

		default:
			break;
		}
	}
	
	public void showLogs ( String msg )
	{
		logs.showLogs(msg);
	}

}

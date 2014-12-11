package com.vxmobilecomm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vxappstore.R;
import com.vxmobilecomm.BaseActivity;
import com.vxmobilecomm.activity.SplashScreen;
import com.vxmobilecomm.activity.VXMobileComm;
import com.vxmobilecomm.util.MySharedPref;

public class SlideDrawerAdapter extends BaseAdapter
{
	String [] navigationArray ;
	BaseActivity context ;
	private String screenHeight_str;
	private int screenHeight_int;
	
	public SlideDrawerAdapter ( String [] navigationArray , BaseActivity context)
	{
		this.navigationArray = navigationArray ;
		this.context = context ;
		
		screenHeight_str = 	MySharedPref.getInstance(context).getString("sendScreenHeight_SPLASHSCREEN");
		screenHeight_int = (Integer.parseInt(screenHeight_str) - context.dpToPx(52) ) / 2 ;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return navigationArray.length ;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position ;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.adapter_slidingdrawer, parent, false);
		
		TextView txt_itemName = ( TextView ) view.findViewById(R.id.txt_itemName) ;
		txt_itemName.setText(navigationArray[position]);
		
		return view;
	}
	
	public void setLayoutHeight (View view)
	{
		AbsListView.LayoutParams parms = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,screenHeight_int);
		view.setLayoutParams(parms);
	}

}

package com.vxmobilecomm.adapter;

import java.util.Dictionary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.vxappstore.R;
import com.vxmobilecomm.BaseActivity;
import com.vxmobilecomm.activity.ProductView;
import com.vxmobilecomm.util.MySharedPref;

public class ProductListingAdapter extends BaseAdapter {
	
	BaseActivity context;
	private String screenHeight_str;
	private int screenHeight_int;
	Dictionary<String, Dictionary<String, String>> dictionary ;
	
	public ProductListingAdapter(BaseActivity context, Dictionary<String, Dictionary<String, String>> dictionary) {
		this.context = context;
		this.dictionary = dictionary ;

		screenHeight_str = MySharedPref.getInstance(context).getString(
				"sendScreenHeight_SPLASHSCREEN");
		screenHeight_int = (Integer.parseInt(screenHeight_str) - context
				.dpToPx(52)) ;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dictionary.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder_group;


		if (convertView == null) {
			viewHolder_group = new ViewHolder();

			LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.adapter_productlisting, parent,false);

			viewHolder_group.image = ( ImageView ) convertView.findViewById(R.id.image) ;
			viewHolder_group.btn_buynow = (Button) convertView.findViewById(R.id.btn_buynow);
			viewHolder_group.btn_view = (Button) convertView.findViewById(R.id.btn_view);
			
			convertView.setTag(viewHolder_group);
		} else 
		{
			viewHolder_group = (ViewHolder) convertView.getTag();
		}
		
		viewHolder_group.btn_view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent ( context , ProductView.class ) ;
				context.startActivity(intent) ;
			}
		});
		
		if ( position % 2 == 0 )
		{
			viewHolder_group.image.setImageDrawable(context.getResources().getDrawable(R.drawable.image_1));
		}

		setLayoutHeight(viewHolder_group.image);
		return convertView ;
	}

	public void setLayoutHeight(View view) {
		FrameLayout.LayoutParams parms = new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, screenHeight_int/3 );
		view.setLayoutParams(parms);
	}
	
	static class ViewHolder {
		ImageView image ;
		Button btn_buynow ; 
		Button btn_view ;
	}

}

package com.vxmobilecomm.activity;

import java.util.List;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.vxappstore.R;
import com.vxmobilecomm.util.CustomExceptionHandler;
import com.vxmobilecomm.util.Logs;

public class Search extends ActionBarActivity implements OnClickListener {

	private Logs logs;
	private List<String> items ;
	private EditText edt_searchtxt;
	private ImageView img_eraser;
	private ImageView img_search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		logs = new Logs("Search");

		if (!(Thread.getDefaultUncaughtExceptionHandler() instanceof CustomExceptionHandler)) {
			Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler(
					this));
		}
		
		addToolBar();
		initView();
	}
	
	public void addToolBar() {
		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("");
	}
	
	public void initView ()
	{
		edt_searchtxt = ( EditText ) findViewById(R.id.edt_searchtxt) ;
		
		img_eraser = ( ImageView ) findViewById(R.id.img_eraser) ;
		img_eraser.setOnClickListener(this);
		img_search = ( ImageView ) findViewById(R.id.img_search) ;
		img_search.setOnClickListener(this);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.img_eraser:
				edt_searchtxt.setText("");
			break;
			
		case R.id.img_search:
				Toast.makeText(this, edt_searchtxt.getText().toString(), Toast.LENGTH_SHORT).show();
			break;
			
			

		default:
			break;
		}
	}
		
}

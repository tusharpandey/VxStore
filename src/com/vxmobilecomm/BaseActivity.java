package com.vxmobilecomm;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.vxappstore.R;
import com.vxmobilecomm.activity.Search;
import com.vxmobilecomm.adapter.SlideDrawerAdapter;
import com.vxmobilecomm.util.CustomExceptionHandler;
import com.vxmobilecomm.util.Logs;

public class BaseActivity extends ActionBarActivity {

	public ColorStateList list ;
	public int[][] states ;
	public int[] colors ;
	public DrawerLayout mDrawerLayout;
	public ActionBarDrawerToggle mDrawerToggle;
	Menu menu ;
	private Logs logs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		logs = new Logs("BaseActivity");

		if(!(Thread.getDefaultUncaughtExceptionHandler() instanceof CustomExceptionHandler)) {
		    Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler(this));
		}	
	}

	public int dpToPx(int dp) {
		DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
		int px = Math.round(dp
				* (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
		return px;
	}
	
	boolean isSearchShown ;
	boolean isWishListShown ;
	
	public void addToolBar(final boolean isSearchShown , final boolean isWishListShown ) {
		
		logs.showLogs("addToolBar");
		this.isSearchShown = isSearchShown ;
		this.isWishListShown = isWishListShown ;
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

		setSupportActionBar(mToolbar);
		getSupportActionBar().setTitle(
				getResources().getString(R.string.app_name));

		addItemsInNavigationDrawer();

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				mToolbar, R.string.app_name, R.string.app_name){
			public void onDrawerClosed(View view) 
			{
				toolBarIcons(isWishListShown, isWishListShown);
			}

			public void onDrawerOpened(View drawerView) 
			{
				toolBarIcons(false, false);
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}
	
	public void toolBarIcons( boolean isSearchShown , boolean isWishListShown )
	{
		logs.showLogs("toolBarIcons");
		
		menu.getItem(0).setVisible(isSearchShown);
		menu.getItem(1).setVisible(isWishListShown);
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		logs.showLogs("onPrepareOptionsMenu");
		toolBarIcons(isSearchShown, isWishListShown);
		
		return super.onPrepareOptionsMenu(menu);
	}
	
	public void addItemsInNavigationDrawer() {
		logs.showLogs("addItemsInNavigationDrawer");

		ListView listView = (ListView) findViewById(R.id.left_drawer);

		String[] navigationArray = new String[4];
		navigationArray[0] = getString(R.string.VXStore);
		navigationArray[1] = getString(R.string.Setting);
		navigationArray[2] = getString(R.string.MyAccount);
		navigationArray[3] = getString(R.string.Logout);

		SlideDrawerAdapter adapter = new SlideDrawerAdapter(navigationArray,
				this);
		listView.setAdapter(adapter);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		logs.showLogs("onCreateOptionsMenu");

		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.vxmobile_comm, menu);
		
		this.menu = menu ;
		
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		logs.showLogs("onOptionsItemSelected");

		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		
		switch (item.getItemId()) 
		{
			case R.id.action_search:
					Intent intentSearch = new Intent ( this , Search.class) ;
					startActivity(intentSearch) ;
				return true;
			case R.id.action_wishlist:

				return true;
			
			default :
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onPostResume()
	{
		logs.showLogs("onPostResume");

		super.onPostResume();
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		logs.showLogs("onConfigurationChanged");

		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(Gravity.START | Gravity.LEFT)) {
			mDrawerLayout.closeDrawers();
			return;
		}
		super.onBackPressed();
	}

}

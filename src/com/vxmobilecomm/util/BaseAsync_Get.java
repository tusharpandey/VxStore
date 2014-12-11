package com.vxmobilecomm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class BaseAsync_Get extends AsyncTask<String, String, InputStream>
{
	Context context ;
	private HttpPost httppost;
	String url ;

	public BaseAsync_Get(Context context ,String url) {

		this.url = url ;
		this.context = context;
	}
	
	@Override
	protected InputStream doInBackground(String... params) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		InputStream is = null;
		
		showLog("doInBackGround") ;

		HttpClient httpclient = new DefaultHttpClient();
			
		if ( url.contains(" "))
		{
			String url_new = url.replaceAll(" ", "%20");
			httppost = new HttpPost(url_new);
		}
		else
		{
			httppost = new HttpPost(url);
		}
		

		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(6);

			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse response = httpclient.execute(httppost);

			HttpEntity entity1 = response.getEntity();

			BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity1);

			is = bufHttpEntity.getContent();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while ( isCancelled() )
		{
			showLog("AsyncTask is Cancelled") ;
			break;
		}

		return is;
	}
	
	public void showLog ( String msg )
	{
		Log.e("BaseAsync_Get", "AsyncTask is cancelled");
	}
	
	public String getString(JSONObject json, String TAG) {
		String returnParseData = "";

		try {
			Object aObj = json.get(TAG);
			if (aObj instanceof Boolean) {
				returnParseData = Boolean.toString(json.getBoolean(TAG));
			} else if (aObj instanceof Integer) {
				returnParseData = Integer.toString(json.getInt(TAG));
			} else if (aObj instanceof String) {
				returnParseData = json.getString(TAG);
			} else {
				returnParseData = json.getString(TAG);
			}
		} catch (Exception err) {
			err.printStackTrace();
			returnParseData = "";
		}

		return returnParseData;
	}
	
	public String getIntintoString(JSONObject json, String TAG) {
		int returnParseData = -1;

		try {
			returnParseData = json.getInt(TAG) ;
		} catch (Exception err) {
			err.printStackTrace();
			returnParseData = -1;
		}

		return Integer.toString(returnParseData) ;
	}
	
	public JSONArray getJSONArrayData (JSONObject json, String TAG)
	{
		JSONArray jsonArray ;
		
		try {
			jsonArray = json.getJSONArray(TAG) ;
		} catch (Exception err) {
			err.printStackTrace();
			jsonArray = new JSONArray() ;
		}
		
		return jsonArray ;  
	}

}

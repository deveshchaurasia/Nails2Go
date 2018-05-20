/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iw.nails2go.bussinessprocess.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.iw.nails2go.utils.UTILConstants;


public class Util {
	public static String getDatafromServerUsingUrl(String apiURL) throws Exception {
		String response 					= "";
		HttpGet httpGet 					= null;
		InputStream content 				= null;
		DefaultHttpClient defaultHttpClient = null;
		BufferedReader buffer 				= null;

		try {
			defaultHttpClient 		= new DefaultHttpClient();
			httpGet 				= new HttpGet(apiURL);
			httpGet.setHeader("Content-Type", "application/json");
			HttpResponse execute 	= defaultHttpClient.execute(httpGet);
			content 				= execute.getEntity().getContent();
			buffer 					= new BufferedReader(new InputStreamReader(content));
			String json 			= "";
			while ((json = buffer.readLine()) != null) {
				response += json;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				content.close();
			} catch (Exception e) {
			}
			try {
				buffer.close();
			} catch (Exception e) {
			}

		}
		return response;
	}
	
	public static String getDataFromServer(String content,String requestMethod,String serverUrl,String contentType)  {
		String response = "",responseMessage = ""; 
		Boolean success = false;
		DataOutputStream outputStream = null;
		try {

			URL new_url = new URL(serverUrl);
			HttpURLConnection connection = (HttpURLConnection) new_url
					.openConnection();

			// Allow Inputs & Outputs
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(true);
			// Enable PUT method
			connection.setRequestMethod(requestMethod);
			connection.setRequestProperty("Connection", "Keep-Alive");

			connection.setRequestProperty("Content-Type",  contentType);                    
						
			outputStream = new DataOutputStream(connection.getOutputStream());
		
			byte[] BytesToBeSent = content.getBytes();
			if (BytesToBeSent != null) {					
				outputStream.write(BytesToBeSent, 0, BytesToBeSent.length);
			}
			int responseCode = connection.getResponseCode();
			
			responseMessage = connection.getResponseMessage();

			if (responseCode	== 200  || responseCode	== 202) 
			{
				success = true;

			}
			
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader =  null;
			try
			{
				inputStreamReader = new InputStreamReader(connection.getInputStream());
				bufferedReader = new BufferedReader(inputStreamReader);
				
				StringBuilder responseContent = new StringBuilder();
				
				String temp = null;
				
				boolean isFirst = true;
				
				while((temp = bufferedReader.readLine())!=null)
				{
					if(!isFirst)
						responseContent.append(UTILConstants.NEW_LINE);
					responseContent.append(temp);
					isFirst = false;
				}
				
				response = responseContent.toString();
				
			}
			catch(Exception e){}
			finally
			{
				try{
				inputStreamReader.close();
				}catch (Exception e) {}
				try{
				bufferedReader.close();
				}catch (Exception e) {}

			}
			
	

		} catch (Exception ex) {
			success = false;
		} finally {
			try {
				outputStream.flush();
				outputStream.close();
			} catch (Exception e) {
			}
		}
		
		return response;
	}
	
	
	public static String checkNullOrEmpty(String input) {
		if (null == input)
			return null;
		input = input.trim();
		if (input.length() == 0) {
			return null;
		}
		return input;
	}

	public static String getEmptyIfNull(String input) {
		if (null == input)
			return "";

		input = input.trim();

		if (input.length() == 0) {
			return "";
		}

		return input;
	}

	public static boolean isNotNull(Map<String, Object> map, String key) {
		try {
			boolean flag = false;
			if (map.get(key) != null && map.get(key) != JSONObject.NULL) {
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			return true;
		}
	}

	public static boolean isNullOrEmpty(String value) {
		if (null == value) {
			return true;
		}

		value = value.trim();

		if ("null".equalsIgnoreCase(value) || value.length() == 0) {
			return true;
		}

		return false;
	}

	public static boolean isOnline(Activity activity) {
		try {
			ConnectivityManager cm = (ConnectivityManager) activity
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni = cm.getActiveNetworkInfo();
			if (ni != null && ni.isAvailable() && ni.isConnected()) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public static void loadUrl(Activity activity, String url) {

		Intent showContent = new Intent(Intent.ACTION_VIEW);
		showContent.setData(Uri.parse(url));
		activity.startActivity(showContent);

	}

	
	
	

}

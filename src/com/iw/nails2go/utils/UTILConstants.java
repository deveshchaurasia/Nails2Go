package com.iw.nails2go.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UTILConstants {

	public static Activity CURRENT_ACTIVITY			= null;
	public static final String BG_SERVICE_CONTENT_TYPE_OCTET_STREAM = 	"binary/octet-stream";
	public static final String BG_SERVICE_CONTENT_TYPE_JSON 		= 	"application/json";
	public final static String NEW_LINE 		= "\n";
	public final static String HTTP_PUT				=	"PUT";
	public final static String HTTP_POST			=	"POST";
	public static String name = "";
	public static String email = "";
	public static String password = "";
	public static Boolean notificationState = false;
	public static Boolean appointmentState = false;

	public static int byteArrayToInt(byte[] byteArray) {
		int tempInt = 0,byteArrayLen = 0;
		if(byteArray != null)
			byteArrayLen = byteArray.length;
		for (int i = 0; i < byteArrayLen; i++) {
			tempInt = (tempInt << 8) + (byteArray[i] & 0xff);
		}
		return tempInt;
	}

	public static String getStringFromJSONObjectUsingKey(JSONObject data,String key){
		String response= "";
		try {
			if(data != null)
				response = data.getString(key);
		} catch (JSONException e) {
			response = "";
		}
		return response;
	}
	
	public static Boolean getBoolFromJSONObjectUsingKey(JSONObject data,String key){
		Boolean response= false;
		try {
			if(data != null)
				response = data.getBoolean(key);
		} catch (JSONException e) {
			response = false;
		}
		return response;
	}
	
	public static int getIntFromJSONObjectUsingKey(JSONObject data,String key){
		int response= 0;
		try {
			if(data != null)
				response = data.getInt(key);
		} catch (JSONException e) {
			response = 0;
		}
		return response;
	}

	public static byte[] intToByteArray(int value) {
		//byte[] bytes = ByteBuffer.allocate(4).putInt(1695609641).array();
		byte[] b = new byte[4];
		for (int i = 0; i < 4; i++) {
			int offset = (b.length - 1 - i) * 8;
			b[i] = (byte) ((value >>> offset) & 0xFF);
		}
		return b;
	}

	
public static void toastMsg(Handler handler,final Context context,final String msg) {

		handler.post(new Runnable() {
			public void run() {
				Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
			}
		});
		//Log.e("Error", "MsgType Doesnt Match. Received: " + msg);
	}

	public static void toastMsg(Context context,final String msg) {
		if(context != null)
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException ex) {
			ex.printStackTrace();
		}
		return null;
	}


	public static Boolean isWIFION(Context context){
		if(context != null){
			ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			android.net.NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if (wifi.isConnected()) return true;		
			return false;
		}else
			return false;

	}

	public static String getMacId(Context context){
		String macAddr = "";
		if (context   != null) {
			WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			WifiInfo wifiInfo 	= manager.getConnectionInfo();
			macAddr 			= wifiInfo.getMacAddress();
		}
		return macAddr;
	}


	public static void setTypeFaceCustom(String typeFace,TextView tvToSet,Activity activity,int size){
		if (activity != null && tvToSet != null) {
			Typeface typeFaceMain = Typeface.createFromAsset(activity.getAssets(), typeFace);
			if(typeFaceMain != null)
				tvToSet.setTypeface(typeFaceMain);
			tvToSet.setTextSize(size);
		}
	}

	public static void setTypeFaceCustom(String typeFace,Button btToSet,Activity activity,int size){
		if (activity != null && btToSet != null) {
			Typeface typeFaceMain = Typeface.createFromAsset(activity.getAssets(),typeFace);
			if(typeFaceMain != null)
				btToSet.setTypeface(typeFaceMain);
			btToSet.setTextSize(size);
		}
	}

	public static void setTypeFaceCustom(String typeFace,EditText tvToSet,Activity activity,int size){
		if (activity != null && tvToSet != null) {
			Typeface typeFaceMain = Typeface.createFromAsset(activity.getAssets(),typeFace);
			if(typeFaceMain != null)
				tvToSet.setTypeface(typeFaceMain);
			tvToSet.setTextSize(size);
		}
	}

	public static Context getCurrentContext(){
		if(UTILConstants.CURRENT_ACTIVITY != null){
			return ((Activity)UTILConstants.CURRENT_ACTIVITY).getBaseContext();
		}
		return null;
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

	public static boolean isInternetAvailable(Context context){
		boolean isConnected 			 	 = false;
		if(context != null){
			ConnectivityManager cm 			 = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);	
			if(cm != null){
				NetworkInfo activeNetwork 	 = cm.getActiveNetworkInfo();
				isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
			}
		}
		return isConnected;
	}
}

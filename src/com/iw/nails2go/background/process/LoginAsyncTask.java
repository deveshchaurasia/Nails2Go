package com.iw.nails2go.background.process;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.iw.nails2go.MainActivity;
import com.iw.nails2go.bussinessprocess.util.Util;
import com.iw.nails2go.utils.UTILConstants;

public class LoginAsyncTask extends AsyncTask<Void, Void, String>{
ProgressDialog pd;
Activity appContext;
String email="", pwd = "",response = "";
	

public LoginAsyncTask(Activity appContext,String email,String pwd){
	this.appContext = appContext;
	this.email = email;
	this.pwd = pwd;
}
	@Override
	protected void onPostExecute(String result) {		
		super.onPostExecute(result);
		if(pd != null){
			pd.cancel();
		}
		if(!UTILConstants.isNullOrEmpty(response)){
			if(response.contains("Invalid Credentials")){
				//show alert invalid credentials
				if(appContext instanceof MainActivity)
					((MainActivity)appContext).showToast("Invalid Credentials");
			}else if(response.contains("Invalid Request")){
				//show alert invalid request
				if(appContext instanceof MainActivity)
					((MainActivity)appContext).showToast("Invalid Request");
			}else{
				saveData();
			}
		}else{
			//show some error at server
			if(appContext instanceof MainActivity)
				((MainActivity)appContext).showToast("Oops some error at server.. !");
		}
	}
	
	private void saveData() {
		JSONObject fields = null;
		try{
			response = response.replace("[", "").replace("]", "");
		    fields = (JSONObject) (new JSONObject(response)).get("fields");
			Log.v("", "");
		}catch(Exception e){
			
		}
		
		if(!UTILConstants.isNullOrEmpty(response) && fields != null){
			UTILConstants.name = UTILConstants.getStringFromJSONObjectUsingKey(fields, "name");
			UTILConstants.email = UTILConstants.getStringFromJSONObjectUsingKey(fields, "email");
			UTILConstants.password =  UTILConstants.getStringFromJSONObjectUsingKey(fields, "password");
			UTILConstants.notificationState = UTILConstants.getBoolFromJSONObjectUsingKey(fields, "notification");
			UTILConstants.appointmentState = UTILConstants.getBoolFromJSONObjectUsingKey(fields, "appointment");
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(appContext);
		Editor editor = pref.edit();
		editor.putBoolean("appointment",UTILConstants.appointmentState );
		editor.putBoolean("notification",UTILConstants.notificationState );
		editor.putString("email", UTILConstants.email);
		editor.putString("name",UTILConstants.name );
        editor.putString("password",UTILConstants.password);
        editor.commit();
        
        ((MainActivity)appContext).showScreenBasedOnNo(2);
        if(UTILConstants.appointmentState){
        	
        }
		}
	}

	@Override
	protected void onPreExecute() {		
		super.onPreExecute();		
		pd =  ProgressDialog.show(appContext,"","loging in");
	}

	@Override
	protected String doInBackground(Void... params) {
		 response = Util.getDataFromServer("email="+email.trim()+"&password="+pwd.trim(), "POST", "http://laric2go.herokuapp.com/client_login", "application/x-www-form-urlencoded");
		
		
		return null;
	}

}

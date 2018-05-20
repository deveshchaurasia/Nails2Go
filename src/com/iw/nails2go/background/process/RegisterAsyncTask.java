package com.iw.nails2go.background.process;

import org.json.JSONObject;

import com.iw.nails2go.MainActivity;
import com.iw.nails2go.bussinessprocess.util.Util;
import com.iw.nails2go.utils.UTILConstants;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

public class RegisterAsyncTask extends AsyncTask<Void, Void, String>{
ProgressDialog pd;
Activity appContext;
String email="", pwd = "",name = "",response = "";
	

public RegisterAsyncTask(Activity appContext,String email,String name,String pwd){
	this.appContext = appContext;
	this.email = email;
	this.name = name;
	this.pwd = pwd;
}
@Override
protected void onPostExecute(String result) {		
	super.onPostExecute(result);
	if(pd != null){
		pd.cancel();
	}
	if(!UTILConstants.isNullOrEmpty(response)){
		if(response.contains("Registration successful")){
			saveData();
		}else 
			//show alert invalid request
			if(appContext instanceof MainActivity)
				((MainActivity)appContext).showToast(response);
		
	}else{
		//show some error at server
		if(appContext instanceof MainActivity)
			((MainActivity)appContext).showToast("Oops some error at server.. !");
	}
}

	private void saveData() {

		UTILConstants.name = name;
		UTILConstants.email = email;
		UTILConstants.password = pwd;
		UTILConstants.notificationState = false;
		UTILConstants.appointmentState = false;
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(appContext);
		Editor editor = pref.edit();
		editor.putBoolean("appointment", UTILConstants.appointmentState);
		editor.putBoolean("notification", UTILConstants.notificationState);
		editor.putString("email", UTILConstants.email);
		editor.putString("name", UTILConstants.name);
		editor.putString("password", UTILConstants.password);
		editor.commit();

	}


	@Override
	protected void onPreExecute() {		
		super.onPreExecute();		
		pd =  ProgressDialog.show(appContext,"","registering..");
	}

	@Override
	protected String doInBackground(Void... params) {
	 response = Util.getDataFromServer("email="+email.trim()+"&password="+pwd.trim()+"&name="+name.trim(), "POST", "http://laric2go.herokuapp.com/client", "application/x-www-form-urlencoded");
		//[{"model": "saloonapp.userclient", "pk": 70, "fields": {"password": "abcd123", "notification": false, "email": "k2gadu@gmail.com", "name": "kartik", "appointment": false}}]
		return null;
	}

}

package com.iw.nails2go;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iw.nails2go.background.process.LoginAsyncTask;
import com.iw.nails2go.background.process.RegisterAsyncTask;
import com.iw.nails2go.utils.UTILConstants;

public class MainActivity extends FragmentActivity implements OnClickListener{
EditText loginEmailIdBox,loginPwdBox,registrationEmailIdBox,registrationPwdBox,registrationNameBox,registrationConfirmPwdBox;
String loginEmailId ="",loginPwd = "",registrationEmailId="",registrationPwd="",registrationConfirmPwd="",registrationName="";
int screens [] = {R.id.login_parent,R.id.registraion_parent,R.id.main_screen_parent,R.id.settings_screen_parent};
TextView settingsUserEmailTxt,mainScreenRegTv,mainScreenLocTv,loginNotRegTv,loginSkipRegTv,loginWelcomeTv,RegistrationTitleTv,RegistrationCancelTv;
int current_screen = 0;
SharedPreferences pref;
public ViewPager mViewPager;
ADPTViewPager adptViewPager;
ImageView dot1,dot2;
Button loginButton,registerButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		dot1 = (ImageView)findViewById(R.id.dot1);
		dot2 = (ImageView)findViewById(R.id.dot2);
		adptViewPager 	= new ADPTViewPager(this,getSupportFragmentManager());      
		mViewPager 		= (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(adptViewPager);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				if(arg0 == 0){
					dot1.setImageDrawable(getResources().getDrawable(R.drawable.black_dot));
					dot2.setImageDrawable(getResources().getDrawable(R.drawable.grey_dot));
				}else {
					dot1.setImageDrawable(getResources().getDrawable(R.drawable.grey_dot));
					dot2.setImageDrawable(getResources().getDrawable(R.drawable.black_dot));
				}
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
		 
		pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		if(pref != null){
			UTILConstants.name 				= pref.getString("name","");
			UTILConstants.email 			= pref.getString("email","");
			UTILConstants.password 			= pref.getString("password","");
			UTILConstants.notificationState = pref.getBoolean("notification",false);
			UTILConstants.appointmentState 	= pref.getBoolean("appointment",false);
		}
		loginEmailIdBox 					= (EditText)findViewById(R.id.login_email_id_tv);
		loginPwdBox     					= (EditText)findViewById(R.id.login_pwd_tv);
		registrationEmailIdBox 				= (EditText)findViewById(R.id.registration_email_box);
		registrationNameBox 				= (EditText)findViewById(R.id.registration_name_box);
		registrationPwdBox     				= (EditText)findViewById(R.id.registration_pwd_box);		
		registrationConfirmPwdBox     		= (EditText)findViewById(R.id.registration_confirm_pwd_box);
		settingsUserEmailTxt				= (TextView)findViewById(R.id.settings_user_email);
		mainScreenRegTv						= (TextView)findViewById(R.id.main_screen_setup_n_info_tv);
		mainScreenLocTv						= (TextView)findViewById(R.id.main_screen_show_location_on_the_map_tv);
		loginNotRegTv						= (TextView)findViewById(R.id.login_not_registered_tv);
		loginSkipRegTv						= (TextView)findViewById(R.id.login_skip_registration_tv);
		loginWelcomeTv						= (TextView)findViewById(R.id.login_welcome_tv);
		RegistrationCancelTv				= (TextView)findViewById(R.id.registration_cancel);
		RegistrationTitleTv					= (TextView)findViewById(R.id.registration_page_title);
		registerButton						= (Button)findViewById(R.id.registration_register_button);
		loginButton							= (Button)findViewById(R.id.login_login_button);
		
		findViewById(R.id.login_login_button).setOnClickListener(this);
		findViewById(R.id.login_not_registered_tv).setOnClickListener(this);
		findViewById(R.id.registration_cancel).setOnClickListener(this);
		findViewById(R.id.registration_register_button).setOnClickListener(this);
		findViewById(R.id.main_screen_setup_n_info_tv).setOnClickListener(this);
		findViewById(R.id.settings_close).setOnClickListener(this);
		findViewById(R.id.settings_logout).setOnClickListener(this);
		
		Typeface avenir_book			= Typeface.createFromAsset(getAssets(),"avenir_book.ttf");
		Typeface avenir_lt45_book		= Typeface.createFromAsset(getAssets(),"avenir_lt45_book.ttf");
		Typeface avenir_lt65_medium		= Typeface.createFromAsset(getAssets(),"avenir_lt65_medium.ttf");
		Typeface avenir_next_ultralight	= Typeface.createFromAsset(getAssets(),"avenir_next_ultralight.ttf");
		Typeface avenir_lt_std_light	= Typeface.createFromAsset(getAssets(),"avenir_lt_std_light.ttf");
				
    	mainScreenLocTv.setTypeface(avenir_lt45_book);
    	mainScreenRegTv.setTypeface(avenir_lt45_book);
    	registerButton.setTypeface(avenir_lt65_medium,Typeface.BOLD);
    	RegistrationCancelTv.setTypeface(avenir_lt45_book);
    	loginButton.setTypeface(avenir_lt65_medium,Typeface.BOLD);
    	loginNotRegTv.setTypeface(avenir_lt45_book);
    	loginSkipRegTv.setTypeface(avenir_lt45_book);    	
    	/*loginEmailIdBox.setTypeface(avenir_book);
    	loginPwdBox.setTypeface(avenir_book); */	
    	RegistrationTitleTv.setTypeface(avenir_lt_std_light);
    	loginWelcomeTv.setTypeface(avenir_lt_std_light);
    	
		if(!UTILConstants.isNullOrEmpty(UTILConstants.email)){
			showScreenBasedOnNo(2);
		}else{
			showScreenBasedOnNo(0);
		}
	}
	
	public void showScreenBasedOnNo(int pos) {
		current_screen = pos;
		for (int i = 0; i < 4; i++) {
			if (i == pos) {				
				findViewById(screens[i]).setVisibility(View.VISIBLE);
			} else {
				findViewById(screens[i]).setVisibility(View.GONE);
			}
		}
	}
	
	public void launchAction(){    	
    	if(mViewPager != null){
    		mViewPager.setVisibility(View.VISIBLE);
			mViewPager.setCurrentItem(1, true);
    	}
    }
	
	public void launchMain(){    	
    	if(mViewPager != null){
    		mViewPager.setVisibility(View.VISIBLE);
			mViewPager.setCurrentItem(0, true);
    	}
    }
	
	@Override
	public void onBackPressed() {
		if (current_screen == 3) {
			showScreenBasedOnNo(2);
			clearLogin();
		}else if (current_screen == 1) {
			showScreenBasedOnNo(0);
			clearRegistration();
		} else {
			super.onBackPressed();
		}
	}
	
	public void clearLogin() {
		if (loginEmailIdBox != null)
			loginEmailIdBox.setText("");
		if (loginPwdBox != null)
			loginPwdBox.setText("");
	}

	public void clearRegistration(){
		if(registrationEmailIdBox != null)
			registrationEmailIdBox.setText("");
		if(registrationNameBox != null)
			registrationNameBox.setText("");
		if(registrationPwdBox != null)
			registrationPwdBox.setText("");
		if(registrationConfirmPwdBox != null)
			registrationConfirmPwdBox.setText("");
	}
	
	public void logout() {
		UTILConstants.name = "";
		UTILConstants.email = "";
		UTILConstants.password = "";
		UTILConstants.notificationState = false;
		UTILConstants.appointmentState = false;

		Editor editor = pref.edit();
		editor.putBoolean("appointment", UTILConstants.appointmentState);
		editor.putBoolean("notification", UTILConstants.notificationState);
		editor.putString("email", UTILConstants.email);
		editor.putString("name", UTILConstants.name);
		editor.putString("password", UTILConstants.password);
		editor.commit();
		showScreenBasedOnNo(0);
	}
	public void showSettingsScreen(){
		showScreenBasedOnNo(3);
		settingsUserEmailTxt.setText(UTILConstants.email);
	}
	public void showToast(final String msg){
		MainActivity.this.runOnUiThread(new Runnable(){
		    public void run(){
		        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		    }
		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_login_button:
			if(loginEmailIdBox != null){
				loginEmailId = loginEmailIdBox.getText().toString().trim();
				if(!UTILConstants.isNullOrEmpty(loginEmailId)){
					if(loginPwdBox != null){
						loginPwd = loginPwdBox.getText().toString().trim();
						if(!UTILConstants.isNullOrEmpty(loginPwd)){
							
							if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
								new LoginAsyncTask(MainActivity.this, loginEmailId, loginPwd).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
				 			} else {
				 				new LoginAsyncTask(MainActivity.this, loginEmailId, loginPwd).execute();
				 			}
						}else //enter valid password
							Toast.makeText(getApplicationContext(), "Geben Sie eine gültige Passwort", Toast.LENGTH_SHORT).show();
					
					}
				}else//enter valid email id
					Toast.makeText(getApplicationContext(), "Geben Sie eine gültige E-MAIL-ADRESSE", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.registration_register_button:
			if(registrationEmailIdBox != null){
				registrationEmailId = registrationEmailIdBox.getText().toString().trim();
				if(!UTILConstants.isNullOrEmpty(registrationEmailId)){
					if(registrationNameBox != null){
						registrationName = registrationNameBox.getText().toString().trim();
						if(!UTILConstants.isNullOrEmpty(registrationName)){
							if(registrationPwdBox != null && registrationConfirmPwdBox !=  null){
								registrationPwd = registrationPwdBox.getText().toString().trim();
								registrationConfirmPwd = registrationConfirmPwdBox.getText().toString().trim();
								if(!UTILConstants.isNullOrEmpty(registrationPwd)){
									if(!UTILConstants.isNullOrEmpty(registrationConfirmPwd)){
										if(registrationPwd.equals(registrationConfirmPwd))
											if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
												new RegisterAsyncTask(MainActivity.this, registrationEmailId,registrationName, registrationPwd).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
								 			} else {
								 				new RegisterAsyncTask(MainActivity.this, registrationEmailId,registrationName, registrationPwd).execute();
								 			}
										else //enter valid password
											Toast.makeText(getApplicationContext(), "Passwort stimmt nicht überein", Toast.LENGTH_SHORT).show();
									}else //enter valid password
										Toast.makeText(getApplicationContext(), "Geben Sie eine gültige Passwort", Toast.LENGTH_SHORT).show();
								}else //enter valid password
									Toast.makeText(getApplicationContext(), "Geben Sie eine gültige Passwort", Toast.LENGTH_SHORT).show();
							
							}
						}else //enter valid name
							Toast.makeText(getApplicationContext(), "Geben Sie eine gültige Name", Toast.LENGTH_SHORT).show();					
					}
					
				}else//enter valid email id
					Toast.makeText(getApplicationContext(), "Geben Sie eine gültige E-MAIL-ADRESSE", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.login_not_registered_tv:
			showScreenBasedOnNo(1);
			break;
		case R.id.registration_cancel:
			clearRegistration();
			showScreenBasedOnNo(0);
			break;
		case R.id.main_screen_setup_n_info_tv:			
			showSettingsScreen();
			break;
		case R.id.settings_close:			
			showScreenBasedOnNo(2);
			break;	
		case R.id.settings_logout:			
			logout();
			break;
		}
		
	}

}

package com.iw.nails2go;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Action extends Fragment {

   
    View rootView = null, rootView1 = null;
   
	ListView alertsLv;
	Activity appContext ;
	RelativeLayout emptyAlertsDilog;
	TextView offerTitle,offerTxt;
	
	public Action(Activity appContext) {
		this.appContext = appContext;
		
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	rootView = inflater.inflate(R.layout.action_page, container, false);  
    	Typeface typeFace=Typeface.createFromAsset(appContext.getAssets(),"avenir_lt_std_light.ttf");
    	TextView title = (TextView)rootView.findViewById(R.id.action_page_title);
    	title.setTypeface(typeFace);
    	Typeface avenirNextMedium=Typeface.createFromAsset(appContext.getAssets(),"avenir_next_medium.ttf");
    	offerTitle = (TextView)rootView.findViewById(R.id.action_page_discount_title);    	
    	offerTitle.setTypeface(avenirNextMedium);
    	offerTxt = (TextView)rootView.findViewById(R.id.action_page_discount_desc);
    	  Typeface avenirBook=Typeface.createFromAsset(appContext.getAssets(),"avenir_lt45_book.ttf");
    	offerTxt.setTypeface(avenirBook);
    	rootView.findViewById(R.id.action_page_next).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((MainActivity)appContext).launchMain();				
			}
		});
    	return rootView;
    }
    
   public View GetView(){
	    return rootView;
   }
   
	@Override
	public void onResume() {
		super.onResume();
		show();
	}

	public void showEmptyAlertsDilog(){
		if(emptyAlertsDilog != null)
			emptyAlertsDilog.setVisibility(View.VISIBLE);
	}
	
	public void hideEmptyAlertsDilog(){
		if(emptyAlertsDilog != null)
			emptyAlertsDilog.setVisibility(View.GONE);
	}
	
	public void show() {
		
			
		}
	}

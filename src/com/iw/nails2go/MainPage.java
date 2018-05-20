package com.iw.nails2go;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainPage extends Fragment implements OnClickListener{

    public static final String ARG_OBJECT = "step";
    View rootView = null;
    Button b;
    EditText name;
    public int normalWait = 0,lowWait = 1,wait = 0;
    Activity appContext;
    ImageView img;
    TextView title;
    public MainPage(Activity appContext) {
		this.appContext = appContext;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	rootView = inflater.inflate(R.layout.main_page, container, false);
    	img = (ImageView)rootView.findViewById(R.id.main_page_img);
    	img.setOnClickListener(this);
    	Typeface typeFace=Typeface.createFromAsset(appContext.getAssets(),"avenir_lt_std_light.ttf");
    	title = (TextView)rootView.findViewById(R.id.main_page_title);
    	title.setTypeface(typeFace);
    	rootView.findViewById(R.id.main_page_next).setOnClickListener(this);
    	return rootView;
    }
    
    public View getMyView(){
	   return rootView;
    }
	public void show(){
		
	}

	public void setImage(){
		if(wait == 0){
			img.setImageDrawable(appContext.getResources().getDrawable(R.drawable.low_wait));
			wait = 1;
		}else{
			img.setImageDrawable(appContext.getResources().getDrawable(R.drawable.normal_wait));
			wait = 0;
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_page_img:
			setImage();
			break;
		case R.id.main_page_next:
			((MainActivity)appContext).launchAction();
			break;
		
		}
		
	}

}

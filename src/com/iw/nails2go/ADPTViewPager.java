package com.iw.nails2go;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;


public class ADPTViewPager extends FragmentStatePagerAdapter {
	public Fragment page1,page2;
   

   public ADPTViewPager(Activity appContext,FragmentManager fm) {
   	 	super(fm);
   	 	page1 = new MainPage(appContext);
        page2 = new Action(appContext);
      	}

	@Override
   public Fragment getItem(int i) {
  	Fragment page = null;
   	switch (i) {
		case 0:
			page = page1;
			break;
		case 1:
			page = page2;
			break;
				
		}
    return page;
   }

   @Override
   public int getCount() {
      return 2 ;
   }
   
   public View getViewByPosition(int position){
   	View view = getItem(position).getView();
   	
   	return view;
   	}
  
}
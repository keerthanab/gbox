package com.example.gbox;

import com.gbox.adapters.TabsPagerAdapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;

@TargetApi(14)
public class ListViewActivity extends FragmentActivity implements ActionBar.TabListener {
	
	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Upcoming Bday", "Close Friends", "Everyone" };
 
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.gbox.R.layout.activity_list_swipe);

        viewPager = (ViewPager) findViewById(com.example.gbox.R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);        
 
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }


     		/**
     		 * on swiping the viewpager make respective tab selected
     		 * */
     		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
     			
     			public void onPageSelected(int position) {
     				// on changing the page
     				// make respected tab selected
     				actionBar.setSelectedNavigationItem(position);
     			}

     			public void onPageScrolled(int arg0, float arg1, int arg2) {
     			}

     			public void onPageScrollStateChanged(int arg0) {
     			}
     		});       
	
    }
    
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
		
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
    
   
	

}

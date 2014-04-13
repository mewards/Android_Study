package com.example.actionbartab;

import android.os.Bundle;
import android.support.v
import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar ab = getActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		for (int i = 0; i < 3; i++) {
			ActionBar.Tab tab = ab.newTab();
			String cap = "TAB" + (i + 1);
			tab.setText(cap);
			TabFragment frag = TabFragment.newInstance(cap);
			tab.setTabListener(new TabListener(frag));
			ab.addTab(tab);
		}

		if (savedInstanceState != null) {
			int seltab = savedInstanceState.getInt("seltab");
			ab.setSelectedNavigationItem(seltab);
		}
	}

	
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		outState.putInt("seltab", getActionBar().getSelectedNavigationIndex());
	}
	
	public class TabListener implements ActionBar.TabListener{
		private Fragment mFragment;

		public TabListener(Fragment fragment){
			mFragment = fragment;
		}

		@Override
		public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			ft.a
			
		}

		@Override
		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
	}
}

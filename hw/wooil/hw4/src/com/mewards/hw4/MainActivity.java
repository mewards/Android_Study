package com.mewards.hw4;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {
	int currentFragment;
	public final static int FRAGMENT_JOIN = 0;
	public final static int FRAGMENT_CHECK = 1;
	Fragment joinFragment = new Join();
	Fragment chkFragment = new Check();
	TextView checkTxt;
	TextView joinTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		currentFragment = FRAGMENT_JOIN;
		checkTxt = (TextView) findViewById(R.id.chk);
		joinTxt = (TextView) findViewById(R.id.join);
		checkTxt.setOnClickListener(this);
		joinTxt.setOnClickListener(this);
		fragmentReplace(currentFragment);
	}

	public void fragmentReplace(int reqNewFragmentIndex) {
		Fragment newFragment = null;
		newFragment = getFragment(reqNewFragmentIndex);
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.addToBackStack(null);
		transaction.replace(R.id.fragment, newFragment);
		transaction.commit();
	}

	
	public Fragment getFragment(int idx) {
		Fragment curFragment = null;

		switch (idx) {
		case FRAGMENT_JOIN:
			if (joinFragment == null)
				joinFragment = new Join();
			else
				curFragment = joinFragment;
			break;
		case FRAGMENT_CHECK:
			if (chkFragment == null)
				chkFragment = new Check();
			else
				curFragment = chkFragment;
			break;

		}
		return curFragment;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.chk:
			currentFragment = FRAGMENT_CHECK;
			fragmentReplace(currentFragment);
			break;
		case R.id.join:
			currentFragment = FRAGMENT_JOIN;
			fragmentReplace(currentFragment);
			break;
		}

	}
}

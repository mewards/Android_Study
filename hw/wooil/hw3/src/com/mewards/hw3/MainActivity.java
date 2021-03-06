package com.mewards.hw3;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener {

	int currentFragment;
	public final static int FRAGMENT_CM = 0;
	public final static int FRAGMENT_INCH = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView cmText = (TextView) findViewById(R.id.cm);
		cmText.setOnClickListener(this);
		TextView inchText = (TextView) findViewById(R.id.inch);
		inchText.setOnClickListener(this);
		currentFragment = FRAGMENT_CM;
		fragmentReplace(currentFragment);
	}

	public void fragmentReplace(int reqNewFragmentIndex) {

		Fragment newFragment = null;

		newFragment = getFragment(reqNewFragmentIndex);

		// replace fragment
		final FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();

		transaction.replace(R.id.fragment, newFragment);

		// Commit the transaction
		transaction.commit();

	}

	private Fragment getFragment(int idx) {
		Fragment newFragment = null;

		switch (idx) {
		case FRAGMENT_CM:
			newFragment = new CmFragment();
			break;
		case FRAGMENT_INCH:
			newFragment = new InchFragment();
			break;
		}
		return newFragment;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mode:
			new AlertDialog.Builder(this)
					.setTitle("방향을 선택하시오.")
					.setPositiveButton("가로 ",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
								}
							})
					.setNegativeButton("세로 ",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
									setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
								}
							}).show();

			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.cm:
			currentFragment = FRAGMENT_CM;
			fragmentReplace(currentFragment);
			break;
		case R.id.inch:
			currentFragment = FRAGMENT_INCH;
			fragmentReplace(currentFragment);
			break;

		}

	}

}

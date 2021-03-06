package com.example.example;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	int mValue, gValue;
	TextView mText;
	TextView gText;
	ProgressDialog mProgress, gProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mText = (TextView) findViewById(R.id.text);
		gText = (TextView) findViewById(R.id.gtext);
	}

	public void mOnClick(View v) {
		new TestThread().start();
	}

	class TestThread extends Thread {

		@Override
		public void run() {

			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Log.e("run", "run?");
					new AccumulateTask2().execute(50);
					new AccumulateTask().execute(100);
				}
			});

		}

	}

	class AccumulateTask2 extends AsyncTask<Integer, Integer, Integer> {
		@SuppressWarnings("deprecation")
		protected void onPreExecute() {
			gValue = 0;
			gProgress = new ProgressDialog(MainActivity.this);
			gProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			gProgress.setTitle("Updating");
			gProgress.setMessage("Wait...");
			gProgress.setCancelable(false);
			gProgress.setProgress(0);
			gProgress.setButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							cancel(true);
						}
					});
			gProgress.show();
		}

		@Override
		protected Integer doInBackground(Integer... params) {
			while (isCancelled() == false) {
				gValue++;
				if (gValue <= params[0]) {
					publishProgress(gValue);
				} else {
					break;
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					;
				}
			}
			return gValue;
		}

		@Override
		protected void onCancelled() {
			gProgress.dismiss();
		}

		@Override
		protected void onPostExecute(Integer result) {
			gProgress.dismiss();
			mProgress.show();
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			gProgress.setProgress(progress[0]);
			gText.setText(Integer.toString(gValue));
		}
	}

	class AccumulateTask extends AsyncTask<Integer, Integer, Integer> {
		@SuppressWarnings("deprecation")
		protected void onPreExecute() {
			mValue = 0;
			mProgress = new ProgressDialog(MainActivity.this);
			mProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgress.setTitle("Updating");
			mProgress.setMessage("Wait...");
			mProgress.setCancelable(false);
			mProgress.setProgress(0);
			mProgress.setButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							cancel(true);
						}
					});

		}

		@Override
		protected Integer doInBackground(Integer... params) {
			while (isCancelled() == false) {
				mValue++;
				if (mValue <= params[0]) {
					publishProgress(mValue);
				} else {
					break;
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					;
				}
			}
			return mValue;
		}

		@Override
		protected void onCancelled() {
			mProgress.dismiss();
		}

		@Override
		protected void onPostExecute(Integer result) {
			mProgress.dismiss();
		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			mProgress.setProgress(progress[0]);
			mText.setText(Integer.toString(mValue));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

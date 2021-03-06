package com.mewards.hw5;

import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	int no1 = 0, no2 = 0, no3 = 0, no4 = 0;
	TextView txt1, txt2, txt3, txt4;
	IncThread thr1, thr2, thr3, thr4;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txt1 = (TextView) findViewById(R.id.no1);
		txt2 = (TextView) findViewById(R.id.no2);
		txt3 = (TextView) findViewById(R.id.no3);
		txt4 = (TextView) findViewById(R.id.no4);

		txt1.setText(no1 + "");
		txt2.setText(no2 + "");
		txt3.setText(no3 + "");
		txt4.setText(no4 + "");

		Button btn = (Button) findViewById(R.id.button1);
		btn.setOnClickListener(this);

		thr1 = new IncThread(no1, txt1, 1);
		thr2 = new IncThread(no2, txt2, 2);
		thr3 = new IncThread(no3, txt3, 3);
		thr4 = new IncThread(no4, txt4, 4);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class IncThread extends Thread {
		int no;
		TextView txt;
		int val;
		String a;

		public IncThread(int no, TextView txt, int val) {
			this.no = no;
			this.txt = txt;
		}

		public void run() {
			while (true) {
				no++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						a = txt.getText().toString();
						Log.e("testtest : ",a);
						txt.setText(no+"");
						
					}
				});
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:

			thr1.start();
			thr2.start();
			thr3.start();
			thr4.start();
			break;
		}
	}
}

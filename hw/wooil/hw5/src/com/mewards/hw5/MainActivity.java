package com.mewards.hw5;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Activity;
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

		thr1 = new IncThread(no1, txt1);
		thr2 = new IncThread(no2, txt2);
		thr3 = new IncThread(no3, txt3);
		thr4 = new IncThread(no4, txt4);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class IncThread extends Thread {

		int no;
		TextView txt;

		public IncThread(int no, TextView txt) {
			this.no = no;
			this.txt = txt;
		}

		public void run() {
			while (true) {
				no++;
				handler.sendEmptyMessage(0);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					;
				}
			}
		}

		Handler handler = new Handler() {
			public void handleMsg(Message msg) {
				if (msg.what == 0) {
					txt.setText(no + "");
				}
			}
		};

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:

			thr1.run();
			// thr2.run();
			// thr3.run();
			// thr4.run();
			break;
		}
	}
}

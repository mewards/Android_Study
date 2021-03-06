package com.mewards.hw1;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.*;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener,
		View.OnTouchListener {
	private MyImageView myImageView;
	float x, y;
	float[] loc;
	float radius = 50;
	float x1 = 0, y1 = 0, x2 = 0, y2 = 0;
	public static boolean action = false;
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Paint mBitmapPaint;
	Drawer mDrawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button msgBtn = (Button) findViewById(R.id.messageBtn);
		Button canvasBtn = (Button) findViewById(R.id.canvasBtn);
		msgBtn.setOnClickListener(this);
		canvasBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		LinearLayout frame;
		final LinearLayout menu = (LinearLayout) findViewById(R.id.menu);
		final LinearLayout content = (LinearLayout) findViewById(R.id.content);
		switch (v.getId()) {
		case R.id.messageBtn:
			frame = (LinearLayout) View.inflate(MainActivity.this,
					R.layout.message, null);
			content.removeAllViews();
			menu.removeAllViews();
			menu.addView(frame);

			Button enterBtn = (Button) findViewById(R.id.enterBtn);
			enterBtn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					EditText input = (EditText) findViewById(R.id.inputText);
					final String query = input.getText().toString();
					TextView userInput = new TextView(getApplicationContext());
					userInput.setText(query);
					userInput.setTextColor(Color.BLACK);
					userInput.setTextSize(30);
					userInput.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							Toast toast = Toast.makeText(MainActivity.this,
									query, Toast.LENGTH_SHORT);
							toast.show();
						}
					});
					content.addView(userInput);

					// TODO Auto-generated method stub

				}
			});
			break;

		case R.id.canvasBtn:
			frame = (LinearLayout) View.inflate(MainActivity.this,
					R.layout.canvas, null);
			LinearLayout sketch = (LinearLayout) View.inflate(
					MainActivity.this, R.layout.sketch, null);
			menu.removeAllViews();
			content.removeAllViews();
			content.addView(sketch);
			menu.addView(frame);
			Drawer drawer = new Drawer(this);
			sketch.addView(drawer);
			MyImageView page = (MyImageView) findViewById(R.id.page);
			page.setImageResource(R.drawable.ic_launcher);
			Intent i = getIntent();
			loc = i.getFloatArrayExtra("Location");
			content.setOnTouchListener(MainActivity.this);

			break;
		}
	}

	public class MyImageView extends ImageView {

		public MyImageView(Context context) {
			super(context);
		}

		// Constructor for inflating via XML
		public MyImageView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			Paint p = new Paint();
			x = loc[0];
			y = loc[1];
			p.setColor(Color.RED);
			p.setStrokeWidth(2);
			canvas.drawCircle(x, y, radius, p);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x1 = event.getX();
			y1 = event.getY();
			action = false;
			// v.invalidate();
			return true;
		case MotionEvent.ACTION_MOVE:
			x2 = event.getX();
			y2 = event.getY();
			v.invalidate();
			return true;
		case MotionEvent.ACTION_UP:
			x2 = event.getX();
			y2 = event.getY();
			v.invalidate();
			action = true;
			return true;

		}
		return false;
	}

	public class Drawer extends View {

		public Drawer(Context context) {
			super(context);
			mBitmap = Bitmap.createBitmap(400, 800, Bitmap.Config.ARGB_8888);
			mCanvas = new Canvas(mBitmap);
			mBitmapPaint = new Paint(Paint.DITHER_FLAG);
			mBitmapPaint.setColor(Color.MAGENTA);
			invalidate();
		}

		protected void onDraw(Canvas canvas) {
			Paint p = new Paint();
			Canvas mCanvas1 = new Canvas(mBitmap);
			p.setColor(Color.parseColor("#7CFC00"));
			canvas.drawBitmap(mBitmap, 0, 0, p);
			canvas.drawLine(x1, y1, x2, y2, p);
			p.setColor(Color.RED);
			mCanvas1.drawLine(x1, y1, x2, y2, p);
			mCanvas.drawLine(x1, y1, x2, y2, mBitmapPaint);
			if (action) {
				x1 = 0;
				x2 = 0;
				y2 = 0;
				y1 = 0;
			}// invalidate();
				// invalidate();
		}

	}

}

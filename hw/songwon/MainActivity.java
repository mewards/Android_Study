package com.example.test;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
	View page1, page2;
	Context con;
	public MyView mv;
	public class Vertex {
		Vertex(float ax, float ay, boolean ad) {
			x = ax;
			y = ay;
			Draw = ad;
		}
		float x;
		float y;
		boolean Draw;
	}

	ArrayList<Vertex> arVertex;
	
	public class MyView extends View {
		Paint mPaint;
		public MyView(Context context) { //컨텍스트와 mainView의 개념
			super(context);
			
			// Paint 객체 미리 초기화
			mPaint = new Paint();
			mPaint.setColor(Color.RED); //선 색
			mPaint.setStrokeWidth(3); // 선 두께
			mPaint.setAntiAlias(true); //?
		}
		public void onDraw(Canvas canvas) {      // 그리기
			canvas.drawColor(Color.WHITE);    // 배경

			// 정점을 순회하면서 선분으로 잇는다.
			for (int i=0;i<arVertex.size();i++) {
				if (arVertex.get(i).Draw) {     // get(i)하여서 Draw를 실시?
					canvas.drawLine(arVertex.get(i-1).x, arVertex.get(i-1).y, 
							arVertex.get(i).x, arVertex.get(i).y, mPaint);
				}
			}
		}
		public boolean onTouchEvent(MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				arVertex.add(new Vertex(event.getX(), event.getY(), false));
				return true;
			}
			if (event.getAction() == MotionEvent.ACTION_MOVE) {
				arVertex.add(new Vertex(event.getX(), event.getY(), true));
				invalidate();
				return true;
			}
			return false;
		}
	}
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		con = this;
		page1 = findViewById(R.id.page1);
		page2 = findViewById(R.id.page2);
		
		Button btn = (Button)findViewById(R.id.btnwrite);
		
		findViewById(R.id.ms).setOnClickListener(mClickListener);
		findViewById(R.id.cv).setOnClickListener(mClickListener);
		
		btn.setOnClickListener(this);
	}
	View.OnClickListener mClickListener = new View.OnClickListener() {
		public void onClick(View v) {
			page1.setVisibility(View.INVISIBLE);
			page2.setVisibility(View.INVISIBLE);
			
			switch (v.getId()) {
			case R.id.ms:
				page1.setVisibility(View.VISIBLE);
				break;
			case R.id.cv:
				page2.setVisibility(View.VISIBLE);
				mv = new MyView(con);
				//setContentView(mv);
				break;
			}
		}
	};
	
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btnwrite:
			RelativeLayout rel = (RelativeLayout)View.inflate(MainActivity.this, R.layout.message, null);
			TextView tv = (TextView)rel.findViewById(R.id.message);
			EditText edit = (EditText)findViewById(R.id.writing);
			String str = edit.getText().toString();
			if(str.equals("")) {
				Toast.makeText(this, "input the text", Toast.LENGTH_SHORT).show();
				break;
			}
			else {
				tv.setText(str);
				edit.setText("");
				
				final String a = tv.getText().toString();
				tv.setOnClickListener(new View.OnClickListener() {
					@Override
					
					public void onClick(View v2) {
						Toast.makeText(MainActivity.this, a, Toast.LENGTH_SHORT).show();
					}
				});
				
				LinearLayout linear = (LinearLayout)findViewById(R.id.linear);
				linear.addView(rel);
				break;
			}
//		case R.id.linear:  //인플레이트는 뷰만?
//			RelativeLayout rel2 = (RelativeLayout)View.inflate(MainActivity.this, R.layout.message, null);
//			TextView tv2 = (TextView)rel2.findViewById(R.id.message);
//			String str2 = tv2.getText().toString();
//			Toast.makeText(this, str2, Toast.LENGTH_SHORT).show();
//			break;
		}
	}
}
package com.mewards.hw2;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	TextView no;
	EditText input;
	Button enterBtn;
	Button deleteBtn;
	ListView listView;
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<Integer> chkList = new ArrayList<Integer>();
	MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		no = (TextView) findViewById(R.id.no);
		input = (EditText) findViewById(R.id.inputText);
		enterBtn = (Button) findViewById(R.id.enterBtn);
		deleteBtn = (Button) findViewById(R.id.deleteBtn);
		listView = (ListView) findViewById(R.id.listView);
		enterBtn.setOnClickListener(this);
		deleteBtn.setOnClickListener(this);
		no.setTextSize(20);
		adapter = new MyAdapter(this,
				android.R.layout.simple_list_item_single_choice, list);
		listView.setAdapter(adapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				CheckBox chkBox = (CheckBox) view.findViewById(R.id.check);
				chkBox.toggle();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.enterBtn:
			list.add(input.getText().toString());
			adapter.notifyDataSetChanged();
			break;

		case R.id.deleteBtn:
			int count = listView.getCount();
			chkList.clear();
			for (int i = 0; i < count; i++) {
				ViewGroup row = (ViewGroup) listView.getChildAt(i);
				CheckBox check = (CheckBox) row.findViewById(R.id.check);
				if (check.isChecked()) {
					chkList.add(i);
					check.toggle();
				}
			}
			for (int i = chkList.size(); i > 0; i--) {
				int index = chkList.get(i - 1);
				list.remove(index);
			}
			adapter.notifyDataSetChanged();
			break;
		}
	}

	
	class MyAdapter extends ArrayAdapter<String> {
		Context context;
		LayoutInflater inf;
		ArrayList<String> list;
		ArrayList<Integer> counts;
		int a;

		public MyAdapter(Context context, int textViewResourceId,
				ArrayList<String> items) {
			super(context, textViewResourceId, items);
			this.list = items;
			this.a = 1;
			this.counts = new ArrayList<Integer>();
			for (int i = 1; i < 100; i++) {
				counts.add(i);
			}
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {

				LayoutInflater vi = (LayoutInflater) getContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.each, null);
			}
			String now = list.get(position);
			String no = counts.get(position) + "";
			if (now != null) {
				// 하나의 이미지뷰와 2개의 텍스트뷰 정보를 받아온다.
				TextView num = (TextView) v.findViewById(R.id.count);
				TextView text = (TextView) v.findViewById(R.id.text);
				CheckBox chkBox = (CheckBox) v.findViewById(R.id.check);
				chkBox.setFocusable(false);
				num.setTextSize(20);
				text.setTextSize(20);
				num.setText(no);
				text.setText(now);
			}
			return v;
		}
	}
}

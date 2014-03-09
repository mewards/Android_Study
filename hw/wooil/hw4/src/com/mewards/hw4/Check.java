package com.mewards.hw4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Check extends Fragment {
	static String id;
	static TextView idTxt;
	

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.check, container, false);
		System.out.println("LOG");
		idTxt = (TextView) v.findViewById(R.id.idChk);
		if (getArguments() != null) {
			id = getArguments().getString("id");
			idTxt.setText(id);
		}
		else{
			idTxt.setText(this.id);
		}
		return v;
	}
}

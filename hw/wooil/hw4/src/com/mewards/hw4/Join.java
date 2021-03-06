package com.mewards.hw4;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.TransactionTooLargeException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Join extends Fragment implements OnClickListener {

	EditText idEdit;
	EditText pwEdit;
	EditText pwcEdit;
	Button joinBtn;
	String id;
	String pw;
	String pwc;
	FragmentManager fm;
	Check check;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.join, container, false);
		idEdit = (EditText) v.findViewById(R.id.id_edit);
		pwEdit = (EditText) v.findViewById(R.id.pw_edit);
		pwcEdit = (EditText) v.findViewById(R.id.pwc_edit);
		joinBtn = (Button) v.findViewById(R.id.join_btn);
		joinBtn.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.join_btn:
			id = idEdit.getText().toString();
			pw = pwEdit.getText().toString();
			pwc = pwcEdit.getText().toString();
			check = (Check) getFragmentManager().findFragmentById(
					(R.layout.check));
			if (check == null) {
				check = new Check();
				System.out.println("LOG2");
			}
			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			Bundle args = new Bundle();
			args.putString("id", id);
			check.setArguments(args);
			transaction.replace(R.id.fragment, check);
			transaction.commit();
			break;

		}
	}
}

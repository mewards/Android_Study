package andexam.ver4_1.c12_adapterview;

import java.util.*;

import andexam.ver4_1.*;
import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class ListAddDelMulti3 extends Activity {
	ArrayList<String> Items;
	ArrayAdapter<String> Adapter;
	ListView list;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listadddel);

		Items = new ArrayList<String>();
		Items.add("First");
		Items.add("Second");
		Items.add("Third");
		Items.add("Fourth");
		Items.add("Fifth");

		Adapter = new ArrayAdapter<String>(this, android.R.layout.
				simple_list_item_checked, Items);
		list = (ListView)findViewById(R.id.list);
		list.setAdapter(Adapter);
		list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	}

	public void mOnClick(View v) {
		EditText ed = (EditText)findViewById(R.id.newitem);
		switch (v.getId()) {
		case R.id.add:
			String text = ed.getText().toString();
			if (text.length() != 0) {
				Items.add(text);
				ed.setText("");
				Adapter.notifyDataSetChanged();
			}
			break;
		case R.id.delete:
			SparseBooleanArray sb = list.getCheckedItemPositions();
			if (sb.size() != 0) {
				for (int i = list.getCount() - 1; i >= 0 ; i--) {
					if (sb.get(i)) {
						Items.remove(i);
					}
				}
				list.clearChoices();
				Adapter.notifyDataSetChanged();
			}
			break;
		}
	}
}

package fr.esstin.ru;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	private ListView lv_carpoolers, lv_going;
	private CheckBox cb_carpooling, cb_going;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		lv_carpoolers = (ListView) findViewById(R.id.lv_carpoolers);
		lv_going = (ListView) findViewById(R.id.lv_going);
		cb_carpooling = (CheckBox) findViewById(R.id.cb_carpooling);
		cb_going = (CheckBox) findViewById(R.id.cb_going);

		cb_carpooling.setEnabled(false);
		fillCarpoolers();
		fillGoing();
	}

	private void fillCarpoolers() {
		ArrayList<HashMap<String, String>> al_carpoolers = new ArrayList<HashMap<String, String>>(
				0);
		HashMap<String, String> map_carpoolers = new HashMap<String, String>();

		map_carpoolers.put("nickname", "Ch4r0n");
		map_carpoolers.put("seats", "4");
		map_carpoolers.put("times", "2");
		al_carpoolers.add(map_carpoolers);

		SimpleAdapter sa_carpoolers = new SimpleAdapter(this.getBaseContext(),
				al_carpoolers, R.layout.carpoolers, new String[] { "nickname",
						"seats", "times" }, new int[] { R.id.tv_lv_nickname,
						R.id.tv_lv_seats, R.id.tv_lv_times });
		lv_carpoolers.setAdapter(sa_carpoolers);
		sa_carpoolers.notifyDataSetChanged();
	}

	private void fillGoing() {
		ArrayList<HashMap<String, String>> al_going = new ArrayList<HashMap<String, String>>(
				0);
		HashMap<String, String> map_going = new HashMap<String, String>();

		map_going.put("going", "Phil");
		al_going.add(map_going);

		SimpleAdapter sa_going = new SimpleAdapter(this.getBaseContext(),
				al_going, R.layout.going, new String[] { "going" },
				new int[] { R.id.tv_lv_going });
		lv_going.setAdapter(sa_going);
		sa_going.notifyDataSetChanged();
	}

	public void cb_going_click(View v) {
		if (cb_going.isChecked()) {
			cb_carpooling.setEnabled(true);
		} else {
			cb_carpooling.setChecked(false);
			cb_carpooling.setEnabled(false);
		}
	}

	public void cb_carpooling_click(View v) {

	}
	
	public void ll_infos_click(View v) {
		
	}
}
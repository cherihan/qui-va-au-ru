package fr.esstin.ru;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv_nickname, tv_seats, tv_carpools;
	private ListView lv_carpoolers, lv_going;
	private CheckBox cb_carpooling, cb_going;
	private SharedPreferences pref;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tv_nickname = (TextView) findViewById(R.id.tv_nickname);
		tv_seats = (TextView) findViewById(R.id.tv_seats);
		tv_carpools = (TextView) findViewById(R.id.tv_carpools);
		lv_carpoolers = (ListView) findViewById(R.id.lv_carpoolers);
		lv_going = (ListView) findViewById(R.id.lv_going);
		cb_carpooling = (CheckBox) findViewById(R.id.cb_carpooling);
		cb_going = (CheckBox) findViewById(R.id.cb_going);

		pref = getSharedPreferences("fr.esstin.ru_preferences",
				Context.MODE_PRIVATE);
		if (pref.getBoolean("firstStart", false)) {
			pref.edit().putBoolean("firstStart", true);
		}

		cb_carpooling.setEnabled(false);
		fillCarpoolers();
		fillGoing();

		try {
			connectWS();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void connectWS() throws IOException, XmlPullParserException {
		String NAMESPACE = "http://calculator/";
		String METHOD_NAME = "add";
		String SOAP_ACTION = "http://calculator/Calculator/add";
		String URL = "http://127.0.0.1/Calc/Calculator?WSDL";

		SoapObject so = new SoapObject(NAMESPACE, METHOD_NAME);
		so.addProperty("arg0", new Double(1.4));
		so.addProperty("arg1", new Double(2.6));

		SoapSerializationEnvelope env = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		env.setOutputSoapObject(so);
		HttpTransportSE httpTransport = new HttpTransportSE(URL);
		httpTransport.call(SOAP_ACTION, env);
		SoapObject result = (SoapObject) env.getResponse();

		tv_nickname.setText(result.toString());
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		pref = this.getSharedPreferences("fr.esstin.ru_preferences",
				Context.MODE_PRIVATE);
		if (pref != null) {
			tv_nickname.setText(pref.getString("user_name", null));
			if (pref.getBoolean("car", false)) {
				tv_seats.setText("Voiture : "
						+ pref.getString("pref_list_seats", null));
				findViewById(R.id.cb_carpooling).setVisibility(View.VISIBLE);
			} else {
				tv_seats.setText("Pas de voiture.");
				findViewById(R.id.cb_carpooling).setVisibility(View.INVISIBLE);
			}
		} else {

		}
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
		Bundle bundle = new Bundle();
		Intent intent = new Intent(MainActivity.this, Profile.class);
		intent.putExtras(bundle);
		startActivityForResult(intent, 0);
	}
}
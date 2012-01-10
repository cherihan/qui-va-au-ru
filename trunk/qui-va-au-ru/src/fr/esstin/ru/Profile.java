package fr.esstin.ru;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Profile extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {
	private static final int ACTIVITY_NUMBER = 2;
	private static SharedPreferences pref;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.profile);
		pref = getPreferenceManager().getSharedPreferences();
		pref.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		pref = getPreferenceManager().getSharedPreferences();
	}

	public SharedPreferences getPreferences() {
		return pref;
	}
}
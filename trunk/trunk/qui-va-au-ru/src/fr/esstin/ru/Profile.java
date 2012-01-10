package fr.esstin.ru;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Profile extends PreferenceActivity {
	SharedPreferences pref;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.profile);
		pref = getPreferenceManager().getSharedPreferences();
	}
}

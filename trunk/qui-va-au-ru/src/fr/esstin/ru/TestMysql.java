package fr.esstin.ru;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class TestMysql {

	public static void truc() {
		String result = "";
		//the year data to send
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("year","1980"));

		//http post
		Log.i("info","pokemon");
		InputStream is = null;
		try{
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://192.168.0.6/getAllPeopleBornAfter.php");
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			Log.i("info","le");
			HttpResponse response = httpclient.execute(httppost);
			Log.i("info","film");
			HttpEntity entity = response.getEntity();
			/*InputStream*/ is = entity.getContent();
		}catch(Exception e){
			Log.e("log_tag", "Error in http connection "+e.toString());
		}
		//convert response to string
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();

			result=sb.toString();
		}catch(Exception e){
			Log.e("log_tag", "Error converting result "+e.toString());
		}

		Log.i("info",result);
		
		//parse json data
		try{
			JSONArray jArray = new JSONArray(result);
			for(int i=0;i<jArray.length();i++){
				JSONObject json_data = jArray.getJSONObject(i);
				Log.i("log_tag","id: "+json_data.getInt("id")+
						", name: "+json_data.getString("name")+
						", sex: "+json_data.getInt("sex")+
						", birthyear: "+json_data.getInt("birthyear")
						);
			}
		}catch(JSONException e){
			Log.e("log_tag", "Error parsing data "+e.toString());
		}

	}

}

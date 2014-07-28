package com.example.test;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;



import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		findViewById(R.id.my_button).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Button b = (Button) findViewById(R.id.my_button);

		b.setClickable(false);
		new LongRunningGetIO().execute();
	}
	
	private class LongRunningGetIO extends AsyncTask <Void, Void, String> {
		protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
		InputStream in = entity.getContent();


		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n>0) {
		byte[] b = new byte[4096];
		n =  in.read(b);


		if (n>0) out.append(new String(b, 0, n));
		}


		return out.toString();
		}


		@Override


		protected String doInBackground(Void... params) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
//		HttpPost httpGet = new HttpPost("http://157.227.156.169:8080/SampleRestFulProject/rest/ioe");
		HttpGet httpGet = new HttpGet("http://157.227.156.169:8080/SampleRestFulProject/rest/ioe");
		String text = null;
		try {
//		StringEntity reqEntity = new StringEntity("Test String");
//		httpGet.setEntity(reqEntity);
		HttpResponse response = httpClient.execute(httpGet, localContext);

		HttpEntity entity = response.getEntity();
		text = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
		return e.getLocalizedMessage();
		}


		return text;
		}


		protected void onPostExecute(String results) {
		if (results!=null) {
		EditText et = (EditText)findViewById(R.id.my_edit);


		et.setText(results);


		}


		Button b = (Button)findViewById(R.id.my_button);


		b.setClickable(true);
		}


		}

}

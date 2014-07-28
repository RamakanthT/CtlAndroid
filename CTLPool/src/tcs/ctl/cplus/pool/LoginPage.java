package tcs.ctl.cplus.pool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginPage extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Button bLogin=(Button)findViewById(R.id.bLogin);
		bLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(LoginPage.this, "Button Clicked", Toast.LENGTH_SHORT).show();
				Intent openPoolScreen = new Intent("tcs.ctl.cplus.pool.PoolScreen");
				startActivity(openPoolScreen);
			}
		});
		
	}

	
}

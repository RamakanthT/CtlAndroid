package tcs.ctl.cplus.pool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class SigninRegPage extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
		setContentView(R.layout.signin_reg);
		
		Button bLogin=(Button)findViewById(R.id.bLogin);
		Button bSignup=(Button)findViewById(R.id.bSignup);
		
		bLogin.setOnClickListener(myOnClickListener);
		bSignup.setOnClickListener(myOnClickListener);
		
	}
	
	private OnClickListener myOnClickListener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
			case R.id.bLogin: 
				Toast.makeText(SigninRegPage.this, "Login Button Clicked", Toast.LENGTH_SHORT).show();				
				Intent openLoginPage = new Intent("tcs.ctl.cplus.pool.LoginPage");
				startActivity(openLoginPage);			
				break;
				
			case R.id.bSignup:	
				Toast.makeText(SigninRegPage.this, "Signup Button Clicked", Toast.LENGTH_SHORT).show();				
				Intent openRegisterPage = new Intent("tcs.ctl.cplus.pool.RegisterPage");
				startActivity(openRegisterPage);				
				break;
			}
		}
	};

}

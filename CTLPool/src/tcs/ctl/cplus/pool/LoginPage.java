package tcs.ctl.cplus.pool;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class LoginPage extends Activity{

	EditText etUname,etPwd;
	Button bLogin;
	
	UserBean UBOBJ2;
	
	String loginuname,loginpwd;
	
	 public static final String MyPREFERENCES = "MyPrefs" ;	   
	 SharedPreferences sharedpreferences;
	 public static final String SessUname = "nameKey"; 
	 public static final String SessUpwd = "passwordKey";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
		setContentView(R.layout.login);
		
		initialize();	
	}

		protected void initialize()
		{
			etUname=(EditText)findViewById(R.id.etUname);
			etPwd=(EditText)findViewById(R.id.etPwd);
			bLogin=(Button)findViewById(R.id.bLogin);
			
			
			bLogin.setOnClickListener(loginPageOnClickListener);
				
				
			
		}
		
		private OnClickListener loginPageOnClickListener =new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(v.getId())
				{
				case R.id.bLogin: 
					fetchData();
					if(validate()&&businessLogic())
					{								
						 Editor editor = sharedpreferences.edit();
					     
					      editor.putString(SessUname,UBOBJ2.getName());
					      editor.putString(SessUpwd, UBOBJ2.getPassword());
					      editor.commit();
					      
						Intent openPoolScreenPage = new Intent(getApplicationContext(), PoolScreen.class);
						openPoolScreenPage.putExtra("globalUbObject",UBOBJ2);
						startActivity(openPoolScreenPage);						
					}				
					break;			
				}
			}
		};
		
		@Override
		   protected void onResume() {
		      sharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		      if (sharedpreferences.contains(SessUname))
		      {
		      if(sharedpreferences.contains(SessUpwd)){
		    	  loginuname=sharedpreferences.getString(SessUname, null);
		    	loginpwd=sharedpreferences.getString(SessUpwd, null);;
		         Intent i = new Intent(this,PoolScreen.class);
		         startActivity(i);
		      }
		      }
		      super.onResume();
		   }
		
		protected void fetchData()
		{
			
			loginuname=etUname.getText().toString();
			loginpwd=etPwd.getText().toString();
		}
		
		protected boolean validate()
		{
			if(loginuname.length()<1)
			{
				Toast.makeText(LoginPage.this, "Mobile no should not be empty", Toast.LENGTH_SHORT).show();
				return false;
			}
			else if(loginpwd.length()<1)
			{
				Toast.makeText(LoginPage.this, "Password should not be empty", Toast.LENGTH_SHORT).show();
				return false;
			}
			
			else if(validateMobile(loginuname) && validatePwd(loginpwd))
			{
				return true;
			}
			
			return false;
		}	
	
		public boolean validateMobile(String loginuname)
		{
			Pattern pattern;
			Matcher matcher; 
			
			String USERNAME_PATTERN = "^[0-9]{10,10}$"; 
			
			pattern = Pattern.compile(USERNAME_PATTERN);
			
			matcher = pattern.matcher(loginuname);
			
			if(!matcher.matches())
			{
				Toast.makeText(LoginPage.this, "Mobile should be in 0-9 & 10 digits ", Toast.LENGTH_SHORT).show();
				return matcher.matches();
			}
			
			return matcher.matches();
		}
		
		public boolean validatePwd(String loginpwd)
		{
			Pattern pattern;
			Matcher matcher; 
			
			String USERNAME_PATTERN = "^[0-9a-zA-Z_@]{5,25}$"; 
			
			pattern = Pattern.compile(USERNAME_PATTERN);
			
			matcher = pattern.matcher(loginpwd);
			
			if(!matcher.matches())
			{
				Toast.makeText(LoginPage.this, "Password should be in a-z0-9_@ & min 5 digits ", Toast.LENGTH_SHORT).show();
				return false;
			}
			
			return matcher.matches();
		}
		
		protected boolean businessLogic()
		{
			UserBean ubObj=new UserBean();			
			ubObj.setMobile(loginuname);
			ubObj.setPassword(loginpwd);
			
			
			PoolDb pdbObj=new PoolDb(this);
			UBOBJ2=pdbObj.validateUser(ubObj);
			
			if(UBOBJ2 == null){
				Toast.makeText(LoginPage.this, "Mobile num & password mismatch", Toast.LENGTH_SHORT).show();
				return false;
			}
			else
			{
				Toast.makeText(LoginPage.this, "Login Succesful", Toast.LENGTH_SHORT).show();
				return true;
			}
			
		}
}

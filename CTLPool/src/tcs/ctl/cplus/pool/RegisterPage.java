package tcs.ctl.cplus.pool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterPage extends Activity {

	EditText etName,etEmpid,etMobile;
	RadioGroup rgGender;
	RadioButton rbGender;
	Button bRegister;
	int checkedRadio;
	
	String regName, regEmpid, regMobile, regGender;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		initialize();		
	}
	

	protected void initialize()
	{
		etName=(EditText)findViewById(R.id.etName);
		etEmpid=(EditText)findViewById(R.id.etEmpid);
		etMobile=(EditText)findViewById(R.id.etMobile);
		rgGender=(RadioGroup)findViewById(R.id.rgGender);
		bRegister=(Button)findViewById(R.id.bRegister);
		
		
		bRegister.setOnClickListener(regPageeOnClickListener);
		
	}
	
	
private OnClickListener regPageeOnClickListener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
			case R.id.bRegister: 
				fetchData();
				if(validate())
				{
				businessLogic();
				Toast.makeText(RegisterPage.this, "Register Succesful", Toast.LENGTH_SHORT).show();
				}								
//				Intent openLoginPage = new Intent("tcs.ctl.cplus.pool.LoginPage");
//				startActivity(openLoginPage);			
				break;			
			}
		}
	};
	
	
	protected void fetchData()
	{
		
		regName=etName.getText().toString();
		regMobile=etMobile.getText().toString();
		regEmpid=etEmpid.getText().toString();
		
			checkedRadio=rgGender.getCheckedRadioButtonId();
			rbGender=(RadioButton)findViewById(checkedRadio);
		
		regGender=rbGender.getText().toString();
		
		
		System.out.println(regName);
		System.out.println(regMobile);
		System.out.println(regEmpid);
		System.out.println(regGender);
	}
	
	protected boolean validate()
	{
		if(regName.length()<1)
		{
			Toast.makeText(RegisterPage.this, "Name should not be empty", Toast.LENGTH_SHORT).show();
			return false;
		}
		else if(regMobile.length()<1)
		{
			Toast.makeText(RegisterPage.this, "Mobile should not be empty", Toast.LENGTH_SHORT).show();
			return false;
		}
		else if(regEmpid.length()<1)
		{
			Toast.makeText(RegisterPage.this, "Empid should not be empty", Toast.LENGTH_SHORT).show();
			return false;
		}
		else if(validateUserName(regName) && validateMobile(regMobile) && validateEmpid(regEmpid))
		{
			return true;
		}
		
		return false;
	}	
	
	public boolean validateUserName(String regName)
	{
		Pattern pattern;
		Matcher matcher; 
		
		String USERNAME_PATTERN = "^[a-zA-Z ]{3,25}$"; 
		
		pattern = Pattern.compile(USERNAME_PATTERN);
		
		matcher = pattern.matcher(regName);
		
		if(!matcher.matches())
		{
			Toast.makeText(RegisterPage.this, "Name should be in a-zA-Z[space]& max 25chars ", Toast.LENGTH_SHORT).show();
			return matcher.matches();
		}
		
		return matcher.matches();
	    		
	}
	
	public boolean validateMobile(String regMobile)
	{
		Pattern pattern;
		Matcher matcher; 
		
		String USERNAME_PATTERN = "^[0-9]{10,10}$"; 
		
		pattern = Pattern.compile(USERNAME_PATTERN);
		
		matcher = pattern.matcher(regMobile);
		
		if(!matcher.matches())
		{
			Toast.makeText(RegisterPage.this, "Mobile should be in 0-9 & 10 digits ", Toast.LENGTH_SHORT).show();
			return matcher.matches();
		}
		
		return matcher.matches();
	}
	
	public boolean validateEmpid(String regEmpid)
	{
		Pattern pattern;
		Matcher matcher; 
		
		String USERNAME_PATTERN = "^[0-9]{6,6}$"; 
		
		pattern = Pattern.compile(USERNAME_PATTERN);
		
		matcher = pattern.matcher(regEmpid);
		
		if(!matcher.matches())
		{
			Toast.makeText(RegisterPage.this, "Empid should be in 0-9 & 6 digits ", Toast.LENGTH_SHORT).show();
			return matcher.matches();
		}
		
		return matcher.matches();
	}
	
	protected void businessLogic()
	{
		
	}
	
}

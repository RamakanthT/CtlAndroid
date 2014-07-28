package tcs.ctl.cplus.pool;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class PoolCreate extends Activity implements OnClickListener {

	/** Called when the activity is first created. */

	private Spinner spinFrom, spinTo, spinVia;	
	private EditText etDate, etTime, etVehicle, etSeats;
	private Button bCreatePool;
	
	String strSpinFrom,strSpinTo,strSpinVia,strEtDate,strEtTime,strEtVehicle,strEtSeats;
	
	private ImageButton ib;
	private Calendar cal;
	private int day;
	private int month;
	private int year;
	private EditText et;

	private EditText etDisplayTime;
	private ImageButton btnChangeTime;

	private int hour;
	private int minute;

	static final int TIME_DIALOG_ID = 999;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pool_create);

		initialize();

		setSpinnerValues();
		setCurrentDateOnView();
		setCurrentTimeOnView();
		addListenerOnButton();
	}

	protected void initialize() {
		
		spinFrom=(Spinner)findViewById(R.id.spinFrom);
		spinTo=(Spinner)findViewById(R.id.spinTo);
		spinVia=(Spinner)findViewById(R.id.spinVia);
		etDate=(EditText)findViewById(R.id.etDate);
		etTime=(EditText)findViewById(R.id.etTime);
		etVehicle=(EditText)findViewById(R.id.etVehicle);
		etSeats=(EditText)findViewById(R.id.etSeats);
		bCreatePool=(Button)findViewById(R.id.bCreatePool);
		
		bCreatePool.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub				
				fetchValues();
				if(validateValues())
				{
					Toast.makeText(PoolCreate.this, "Create Clicked", Toast.LENGTH_SHORT).show();
					System.out.println("strSpinFrom "+strSpinFrom);
					System.out.println("strSpinTo "+strSpinTo);
					System.out.println("strSpinVia "+strSpinVia);
					System.out.println("strEtDate "+strEtDate);
					System.out.println("strEtTime "+strEtTime);
					System.out.println("strEtVehicle "+strEtVehicle);
					System.out.println("strEtSeats "+strEtSeats);
				}
			}
		});

	}

	protected void fetchValues()
	{
		strSpinFrom=spinFrom.getSelectedItem().toString();
		strSpinTo=spinTo.getSelectedItem().toString();
		strSpinVia=spinVia.getSelectedItem().toString();
		strEtDate=etDate.getText().toString();
		strEtTime=etTime.getText().toString();
		strEtVehicle=etVehicle.getText().toString();
		strEtSeats=etSeats.getText().toString();		
		
	}
	
	protected boolean validateValues()
	{
		
		if(strEtVehicle.length()<1)
		{
			Toast.makeText(PoolCreate.this, "Vehicle should not be empty", Toast.LENGTH_SHORT).show();
			return false;
		}
		else if(strEtSeats.length()<1)
		{
			Toast.makeText(PoolCreate.this, "Seats should not be empty", Toast.LENGTH_SHORT).show();
			return false;
		}
		else if(validateVehicle(strEtVehicle) && validateSeats(strEtSeats))
		{
			return true;
		}
		
		return false;
	}
	
	public boolean validateSeats(String strEtSeats)
	{
		Pattern pattern;
		Matcher matcher; 
		
		String USERNAME_PATTERN = "^[0-9]{1,2}$"; 
		
		pattern = Pattern.compile(USERNAME_PATTERN);
		
		matcher = pattern.matcher(strEtSeats);
		
		if(!matcher.matches()|strEtSeats.equals("0")|strEtSeats.equals("00"))
		{
			Toast.makeText(PoolCreate.this, "Seats should be digits & max 2 digits ", Toast.LENGTH_SHORT).show();
			return false;
		}
		
		return matcher.matches();
	}
	
	
	public boolean validateVehicle(String strEtVehicle)
	{
		Pattern pattern;
		Matcher matcher; 
		
		String USERNAME_PATTERN = "^[a-zA-Z0-9 ]{1,25}$"; 
		
		pattern = Pattern.compile(USERNAME_PATTERN);
		
		matcher = pattern.matcher(strEtVehicle);
		
		if(!matcher.matches())
		{
			Toast.makeText(PoolCreate.this, "Vehicle should be in a-zA-Z0-9[space] & max 25chars ", Toast.LENGTH_SHORT).show();
			return matcher.matches();
		}
		
		return matcher.matches();
	    		
	}
	protected void setSpinnerValues() {

		setSpinnerFrom();
		setSpinnerTo();
		setSpinnerVia();		
		
	}

	protected void setSpinnerFrom()
	{
		List<String> spinnerArray =  new ArrayList<String>();
	    spinnerArray.add("Waverock");
	    spinnerArray.add("Jntu");
	    spinnerArray.add("Lingampally");
	    spinnerArray.add("Mehdipatnam");
	    
	    Collections.sort(spinnerArray);

	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    Spinner sItems = (Spinner) findViewById(R.id.spinFrom);
	    sItems.setAdapter(adapter);
	}
	
	protected void setSpinnerTo()
	{
		List<String> spinnerArray =  new ArrayList<String>();
		spinnerArray.add("Waverock");
	    spinnerArray.add("Jntu");
	    spinnerArray.add("Lingampally");
	    spinnerArray.add("Mehdipatnam");

	    Collections.sort(spinnerArray);
	    
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    Spinner sItems = (Spinner) findViewById(R.id.spinTo);
	    sItems.setAdapter(adapter);
	}
	
	protected void setSpinnerVia()
	{
		List<String> spinnerArray =  new ArrayList<String>();
	    spinnerArray.add("Hitecity");
	    spinnerArray.add("DLF");
	    spinnerArray.add("Tolichowki");

	    Collections.sort(spinnerArray);
	    
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    Spinner sItems = (Spinner) findViewById(R.id.spinVia);
	    sItems.setAdapter(adapter);
	}
	
	
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, timePickerListener, hour, minute,
					false);
		default:
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}

		// return null;

	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			etDate.setText((selectedMonth + 1) + " / " + selectedDay + " / "
					+ selectedYear);
		}
	};

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		showDialog(0);

	}

	public void setCurrentDateOnView()
	{
		// mDateButton = (Button) findViewById(R.id.date_button);
				ib = (ImageButton) findViewById(R.id.ibDate);
				cal = Calendar.getInstance();
				day = cal.get(Calendar.DAY_OF_MONTH);
				month = cal.get(Calendar.MONTH);
				year = cal.get(Calendar.YEAR);	
				
				etDate.setText((month + 1) + " / " + day + " / "
						+ year);				
				
				ib.setOnClickListener(this);
	}
	
	
	// display current time
	public void setCurrentTimeOnView() {
		

		final Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);

		// set current time into textview
		etTime.setText(new StringBuilder().append(pad(hour)).append(":")
				.append(pad(minute)));

	}

	
	
	public void addListenerOnButton() {

		btnChangeTime = (ImageButton) findViewById(R.id.ibTime);

		btnChangeTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(TIME_DIALOG_ID);

			}

		});

	}

	private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			hour = selectedHour;
			minute = selectedMinute;

			// set current time into textview
			etTime.setText(new StringBuilder().append(pad(hour))
					.append(":").append(pad(minute)));

		}
	};

	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

}
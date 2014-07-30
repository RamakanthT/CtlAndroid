package tcs.ctl.cplus.pool;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.Toast;

public class PoolSearch extends Activity implements OnClickListener {

	/** Called when the activity is first created. */
	EditText etDate;
	Spinner spinFrom, spinTo, spinVia, spinSeats;
	Button bSearch;
	ImageButton ibDate;

	String strDate, strFrom, strTo, strVia, strSeats;

	private Calendar cal;
	private int day;
	private int month;
	private int year;
	
	UserBean UBOBJ;
	PoolBean PBOBJ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pool_search);

        Intent inte=getIntent();
        UBOBJ=(UserBean)inte.getParcelableExtra("globalUbObject");
        
		initialize();
		setCurrentDateOnView();
		setSpinnerValues();

	}

	protected void initialize() {
		etDate = (EditText) findViewById(R.id.etDate);
		ibDate = (ImageButton) findViewById(R.id.ibDate);
		spinFrom = (Spinner) findViewById(R.id.spinFrom);
		spinTo = (Spinner) findViewById(R.id.spinTo);
		spinVia = (Spinner) findViewById(R.id.spinVia);
		spinSeats = (Spinner) findViewById(R.id.spinSeats);
		bSearch = (Button) findViewById(R.id.bSearch);

		bSearch.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				fetchValues();
				if (validateValues() && businessLogic()) {
					Toast.makeText(PoolSearch.this, "Search Clicked",
							Toast.LENGTH_SHORT).show();

					Intent openPoolListPage = new Intent(getApplicationContext(), PoolList.class);
					openPoolListPage.putExtra("globalUbObject",UBOBJ);
					openPoolListPage.putExtra("searchPbObject",PBOBJ);
					startActivity(openPoolListPage);	

				}

			}
		});
	}

	protected void fetchValues() {
		strDate = etDate.getText().toString();
		strFrom = spinFrom.getSelectedItem().toString();
			if(strFrom.equals("None"))
			{
				strFrom="";
			}			
			
		strTo = spinTo.getSelectedItem().toString();
			if(strTo.equals("None"))
			{
				strTo="";
			}
			
		strVia = spinVia.getSelectedItem().toString();
			if(strVia.equals("None"))
			{
				strVia="";
			}
			
		strSeats = spinSeats.getSelectedItem().toString();
	}

	protected boolean validateValues() {
		if(strFrom.equals(strTo))
		{
			Toast.makeText(PoolSearch.this, "From & To should be different",Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	protected boolean businessLogic() {
		
		PBOBJ=new PoolBean();
		
		PBOBJ.setDate(strDate);
		PBOBJ.setFrom(strFrom);
		PBOBJ.setTo(strTo);
		PBOBJ.setVia(strVia);
		PBOBJ.setSeats(strSeats);
		
		return true;
	}

	protected void setSpinnerValues() {
		setSpinnerFrom();
		setSpinnerTo();
		setSpinnerVia();
		setSpinnerSeats();
	}

	protected void setSpinnerFrom() {
		List<String> spinnerArray = new ArrayList<String>();
		spinnerArray.add("None");
		spinnerArray.add("Jntu");
		spinnerArray.add("Lingampally");
		spinnerArray.add("Mehdipatnam");
		spinnerArray.add("Waverock");

//		Collections.sort(spinnerArray);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, spinnerArray);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner sItems = (Spinner) findViewById(R.id.spinFrom);
		sItems.setAdapter(adapter);
	}

	protected void setSpinnerTo() {
		List<String> spinnerArray = new ArrayList<String>();
		spinnerArray.add("None");
		spinnerArray.add("Jntu");
		spinnerArray.add("Lingampally");
		spinnerArray.add("Mehdipatnam");
		spinnerArray.add("Waverock");

//		Collections.sort(spinnerArray);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, spinnerArray);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner sItems = (Spinner) findViewById(R.id.spinTo);
		sItems.setAdapter(adapter);
	}

	protected void setSpinnerVia() {
		List<String> spinnerArray = new ArrayList<String>();
		spinnerArray.add("None");
		spinnerArray.add("DLF");
		spinnerArray.add("Hitecity");		
		spinnerArray.add("Tolichowki");

//		Collections.sort(spinnerArray);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, spinnerArray);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner sItems = (Spinner) findViewById(R.id.spinVia);
		sItems.setAdapter(adapter);
	}

	protected void setSpinnerSeats() {
		List<String> spinnerArray = new ArrayList<String>();
		
		spinnerArray.add("None");
		spinnerArray.add("1");
		spinnerArray.add("2");
		spinnerArray.add("3");
		spinnerArray.add("4");
		spinnerArray.add("5");		

		Collections.sort(spinnerArray);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, spinnerArray);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		Spinner sItems = (Spinner) findViewById(R.id.spinSeats);
		sItems.setAdapter(adapter);
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, datePickerListener, year, month, day);
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

	public void setCurrentDateOnView() {
		// mDateButton = (Button) findViewById(R.id.date_button);
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		year = cal.get(Calendar.YEAR);

		etDate.setText((month + 1) + " / " + day + " / " + year);
		ibDate.setOnClickListener(this);
	}
}
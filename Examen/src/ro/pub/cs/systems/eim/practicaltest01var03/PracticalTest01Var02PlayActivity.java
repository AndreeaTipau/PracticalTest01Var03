package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

public class PracticalTest01Var02PlayActivity extends Activity {
	
	private Random random = new Random();
	Context context = this;
	String raspuns_corect = "";
	boolean serviciuFlag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var02_play);
		
		// informatii TEXT
		EditText riddle2 = (EditText)findViewById(R.id.activ2_riddle);
		EditText answer2 = (EditText)findViewById(R.id.activ2_answer);
		
		
		
		// C - intentia de la tata 
		Intent intentFromParent = getIntent();
		if( intentFromParent != null ){
			 Bundle data = intentFromParent.getExtras();
			  if( data != null ){
				  String answerText = data.getString(Constants.Answer);
				  String riddleText = data.getString(Constants.Riddle);
				  
				  riddle2.setText(riddleText);
				  raspuns_corect = answerText;
				  
				  
				  Log.d("Apl", answerText + " + " + riddleText);
				  
			  }
			  }
				
		BtnListener btnListener = new BtnListener();
		Button back, check;
		
		//B.1
		back = (Button)findViewById(R.id.button_back);
		back.setOnClickListener(btnListener);
		check = (Button)findViewById(R.id.button_check);
		check.setOnClickListener(btnListener);
		
		
		//INTENTIA PENTRU SERVICIU
		if( serviciuFlag == false ){
			serviciuFlag = true;
			
			Intent intentService = new Intent(getBaseContext(), PracticalTest01Var03Service.class);
			intentService.putExtra(Constants.Answer, raspuns_corect);
			startService(intentService);
			
		}
		
	}
	
	// listener butoane . Aici se fac intentii catre parinte
	private class BtnListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			
			// informatii TEXT
			EditText riddle2 = (EditText)findViewById(R.id.activ2_riddle);
			EditText answer2 = (EditText)findViewById(R.id.activ2_answer);
		
			Button b = (Button)v;
			String buttonName =  b.getText().toString();
			int bId = b.getId();
			
			if( bId == R.id.button_check ){
				
				String r,a;
				r = riddle2.getText().toString();
				a = answer2.getText().toString();
				
				if( a != null && a.equalsIgnoreCase(raspuns_corect) == true ){
					 Toast.makeText(context, "ok", Toast.LENGTH_LONG).show();
				}
				else Toast.makeText(context, "gresit", Toast.LENGTH_LONG).show();
				
				//ok
				/*
				Intent intentToParent = new Intent();
				setResult(Activity.RESULT_OK, intentToParent);
				finish();*/

				
			}else if( bId == R.id.button_back ){
				//cancel
				
				Intent intentToParent = new Intent();
				setResult(Activity.RESULT_OK, intentToParent);
				finish();
				
			}

	
			 
			
		}
		
	}
	
	// D - partea de broadcast
		@Override
		protected void onPause() {
			super.onPause();
			unregisterReceiver(receiver);
		}
		
		@Override
		protected void onResume() {
			super.onResume();
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(Constants.ACTION_1);
			registerReceiver(receiver, intentFilter);
		}
		private CustomReceiver receiver = new CustomReceiver();
		
		public class CustomReceiver extends BroadcastReceiver {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String action = intent.getAction();
				// FAC CE CERE EXERCITIUL
				
				String ans = "";
				String string_primit = intent.getStringExtra(Constants.Answer);
				int aux = random.nextInt(string_primit.length() - 1);
				for(int i= 0 ; i < aux; i++)
					ans +="*";
				
				ans += string_primit.charAt(aux);
				
				for(int i= aux + 1 ; i < string_primit.length(); i++)
					ans +="*";
				
				
				Toast.makeText(context, ans, Toast.LENGTH_LONG).show();
				
				Log.d(Constants.LOGTAG, action);			
				Log.d(Constants.LOGTAG, intent.getStringExtra(Constants.Answer));
			}
			
		}
		//.....................................................
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var02_play, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

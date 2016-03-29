package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class PracticalTest01Var03MainActivity extends Activity {

private class BtnListener implements OnClickListener{
		
		

		@Override
		public void onClick(View v) {
			
			// iau informatii BUTON 
			Button b = (Button)v;
			String buttonName =  b.getText().toString();
			int bId = b.getId();
			
			// informatii TEXT
			EditText riddle = (EditText)findViewById(R.id.riddle);
			String riddleText = riddle.getText().toString();
		
			
			
			EditText answer = (EditText)findViewById(R.id.answer);
			String answerTest = answer.getText().toString();

			

			
			// B.1 -  setez pentru butoane			
			if( bId == R.id.button1 && 
					riddleText.equalsIgnoreCase("") == false && 
					answerTest.equalsIgnoreCase("") == false ){
				
				Intent intent;
				intent = new Intent("ro.pub.cs.systems.eim.intent.action.PracticalTest01Var02PlayActivity");
				intent.putExtra(Constants.Answer, answerTest);
				intent.putExtra(Constants.Riddle, riddleText);
      		  	startActivityForResult(intent,Constants.COD_INTENTIE);
				
				
			}
			
			/*
	
			// C - intentie catre cealalta activitate
			if( bId == R.id.button_Navigate ){
				Intent intent;
				intent = new Intent("ro.pub.cs.systems.eim.intent.action.PracticalTest01SecondaryActivity");
				intent.putExtra(Constants.Value1, value1);
				intent.putExtra(Constants.Value2, value2);
      		  	startActivityForResult(intent,Constants.COD_INTENTIE);
			}
			*/
			
			
			
			
		}
		
	}
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var03_main);
		
		
		BtnListener btnListener = new BtnListener();
		Button b1;
		
		//B.1
		b1 = (Button)findViewById(R.id.button1);
		b1.setOnClickListener(btnListener);
		
		//B.2 restaurare date
				if( savedInstanceState != null ){
					if (savedInstanceState.containsKey(Constants.Riddle)) {
						EditText riddle = (EditText)findViewById(R.id.riddle);
						riddle.setText(savedInstanceState.getString(Constants.Riddle));
					}
					if (savedInstanceState.containsKey(Constants.Answer)) {
						EditText answer = (EditText)findViewById(R.id.answer);
						answer.setText(savedInstanceState.getString(Constants.Answer));
					}
					
				}
				//..............................................
		
		
		
	}
	
	// C - parintele trateaza raspunsul de la copil
		public void onActivityResult(int requestCode, int resultCode, Intent intent) {
			
			  switch(requestCode) {
			    case Constants.COD_INTENTIE:
			      if (resultCode == Activity.RESULT_OK) {
			    	  //Toast.makeText(this, "ok", Toast.LENGTH_LONG).show();
			    	  Log.d("Apl", "ok");
			    	 
			      }
			      break;
			  }
			
			}
	
		// B.2 RESTAURARE DATE
		@Override
		protected void onRestoreInstanceState(Bundle savedInstanceState) {
			super.onRestoreInstanceState(savedInstanceState);
			Log.d(Constants.LOGTAG, "onRestoreInstanceState() method was invoked");
			
			if (savedInstanceState.containsKey(Constants.Answer)) {
				EditText answer = (EditText)findViewById(R.id.answer);
				answer.setText(savedInstanceState.getString(Constants.Answer));
			}
			if (savedInstanceState.containsKey(Constants.Riddle)) {
				EditText riddle = (EditText)findViewById(R.id.riddle);
				riddle.setText(savedInstanceState.getString(Constants.Riddle));
			}
		}
		
		// B.2 SALVARE DATE
		@Override
		protected void onSaveInstanceState(Bundle savedInstanceState) {
		  super.onSaveInstanceState(savedInstanceState);
		  
		  EditText answer = (EditText)findViewById(R.id.answer);
		  EditText riddle = (EditText)findViewById(R.id.riddle);
		  
		  savedInstanceState.putString(Constants.Answer,answer.getText().toString());
		  savedInstanceState.putString(Constants.Riddle,riddle.getText().toString());

		  Log.d(Constants.LOGTAG, "onSaveInstanceState() method was invoked");  
		  
		  	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var03_main, menu);
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

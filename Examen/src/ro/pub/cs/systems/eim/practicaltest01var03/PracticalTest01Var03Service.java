package ro.pub.cs.systems.eim.practicaltest01var03;


import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
 
public class PracticalTest01Var03Service extends Service {
 
  private ProcessingThread processingThread = null;
 
  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
	
	  Bundle data = intent.getExtras();
	  String answer = data.getString(Constants.Answer);
	 
	  Log.d(Constants.LOGTAG, "in serviciu");
	  /* pornesc threadul */
	  processingThread = new ProcessingThread(this, answer);
	  processingThread.start();
    
	  return Service.START_REDELIVER_INTENT;
  }
 
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }
 
  @Override
  public void onDestroy() {
    processingThread.stopThread();
  }
 
}

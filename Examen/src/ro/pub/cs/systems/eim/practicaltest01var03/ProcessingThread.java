package ro.pub.cs.systems.eim.practicaltest01var03;


import java.util.Date;
import java.util.Random;
 
import android.content.Context;
import android.content.Intent;
import android.util.Log;
 
public class ProcessingThread extends Thread {
 
  private Context context = null;
  private boolean isRunning = true;
 
  private Random random = new Random();
 
  private double arithmeticMean;
  private double geometricMean;
  private String answer;
 
  public ProcessingThread(Context context, String ans) {
    this.context = context;
 
    answer = ans;
    
  }
 
  @Override
  public void run() {
	  
    Log.d(Constants.LOGTAG, "Thread has started!");
    
    while (isRunning) {
    	
      // odata la 10 secunde trimite mesajul 	
      sendMessage();
      sleep();
    }
    
    Log.d(Constants.LOGTAG, "Thread has stopped!");
  }
 
  private void sendMessage() {
  
	  // noua intentie catre aplicatie
	  Intent intent = new Intent();
      intent.setAction(Constants.ACTION_1);
      intent.putExtra(Constants.Answer, answer);
    
      context.sendBroadcast(intent);
  }
 
  private void sleep() {
    try {
    // aici doarme 10 secunde
      Thread.sleep(5000);
    } catch (InterruptedException interruptedException) {
      interruptedException.printStackTrace();
    }
  }
 
  public void stopThread() {
    isRunning = false;
  }
}

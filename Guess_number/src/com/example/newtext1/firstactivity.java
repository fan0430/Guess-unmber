package com.example.newtext1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class firstactivity extends Activity{
@Override
	protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.firstlayout);
	
	new Handler().postDelayed(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
		 final Intent intent1 = new Intent(firstactivity.this, mainactivity.class);	
		
		 startActivity(intent1);
		 finish();
		}
	}, 1000);
		
	}
	
	
	
	
//	new Thread(new Runnable() {
//	@Override
//	public void run() {
//		 TODO Auto-generated method stub
//		try {
//			Thread.sleep(3000);
//			startActivity(new Intent().setClass(firstactivity.this, mainactivity.class));
//		} catch (InterruptedException e) {
//			 TODO: handle exception
//			e.printStackTrace();
//			}
//		}
//	}) .start();
//	}
}
package com.youkelai.daike;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private PhoneStateChangedReceiver mPhoneStateChangedReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dynamic register the broadcast
        IntentFilter filter = new IntentFilter();

        //"android.intent.action.PHONE_STATE"
        filter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED);

        //"android.intent.action.NEW_OUTGOING_CALL"
        filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);

        mPhoneStateChangedReceiver = new PhoneStateChangedReceiver();
        registerReceiver(mPhoneStateChangedReceiver, filter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PhoneStateChangedReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            //Action is "android.intent.action.PHONE_STATE"
            //or "android.intent.action.NEW_OUTGOING_CALL"
            String action = intent.getAction();
            Log.i("INFO","action is "+action);

            if (Intent.ACTION_NEW_OUTGOING_CALL.equals(action)) {
                //"android.intent.extra.PHONE_NUMBER"
                String outgoingNum = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
                Log.i("Seven", "It's outgoing call. Number is:" + outgoingNum);
                return;
            }

            //State is RINGING/OFFHOOK/IDLE
            String state = intent.getStringExtra("state");

            //Only state is Ringing can get the incoming_number
            String incomingNum = intent.getStringExtra("incoming_number");

            //MTK add for dual SIM support
            String simId = intent.getStringExtra("simId");

            Log.i("INFO", "state is "+state);
            Log.i("INFO", "incomingNum is "+incomingNum);
            Log.i("INFO", "simId is "+simId);

            Toast.makeText(context, incomingNum, Toast.LENGTH_LONG).show();
        }
    }
}

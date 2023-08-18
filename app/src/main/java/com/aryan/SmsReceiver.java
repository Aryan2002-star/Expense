package com.aryan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver{



    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        Object[] sms = (Object[]) bundle.get("pdus");

        for (Object p : sms) {
            SmsMessage message = SmsMessage.createFromPdu((byte[]) p);

            String mbno = message.getDisplayOriginatingAddress();
            String msg = message.getDisplayMessageBody();
//            if (mbno.contains("")){

//            Log.d("AMOUNT_DEB_CRED","\nAmount : " + msg);


            String s = msg;
            s = s.replace("/", "");
            s = s.toLowerCase();


            // SubString  allocation

            String amtPre = "rs."; // currency value
            String amount = "";
            String accPre = "ac ";

            //Index Calculation

            int amt_index = -1;
            amt_index = s.indexOf(amtPre);
            int acc_index=-1;
            acc_index = s.indexOf(accPre);


            if ((amt_index != -1) && (acc_index != -1)) {

                // Account Number

                acc_index = acc_index + accPre.length();
                String account = "";
                while (s.charAt(acc_index) != ' ') {
                    account = account + s.charAt(acc_index);
                    acc_index++;
                }

                // Amount in INR


                amt_index = amt_index + amtPre.length();


                while (s.charAt(amt_index) != ' ') {
                    amount = amount + s.charAt(amt_index);
                    amt_index++;
                }


//            System.out.println("Amount "+amount);


//            System.out.println("Account Number "+account);
//            }
                double amtsss = Double.parseDouble(amount);

                Log.d("MsgVedyAsMe", "Message Number: " + mbno + "\nMessage : " + msg + "\nAmount : " + amtsss + "\nAccount Number : " + account);


            }else {
                Log.d("MsgVedyAsMe", "Message Number: " + mbno + "\nMessage : " + msg );

            }
        }


    }






}
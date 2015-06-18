package com.feigdev.testsms.app;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by ejf3 on 6/18/15.
 */
public class SmsScraper extends AsyncTask<Context, Void, Void> {

    public static void readSms(Context context) {
        // public static final String INBOX = "content://sms/inbox";
        // public static final String SENT = "content://sms/sent";
        // public static final String DRAFT = "content://sms/draft";
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        Log.d(SmsScraper.class.toString(), "======= INBOX =======");

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                }
                Log.d(SmsScraper.class.toString(), "msgData: " + msgData);
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }

        cursor = context.getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, null);
        Log.d(SmsScraper.class.toString(), "======= SENT =======");

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                }
                Log.d(SmsScraper.class.toString(), "msgData: " + msgData);
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
    }

    @Override
    protected Void doInBackground(Context... params) {
        readSms(params[0]);
        return null;
    }
}

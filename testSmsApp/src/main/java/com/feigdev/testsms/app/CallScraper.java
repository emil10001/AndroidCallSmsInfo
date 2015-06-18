package com.feigdev.testsms.app;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by ejf3 on 6/18/15.
 */
public class CallScraper extends AsyncTask<Context, Void, Void> {

    public static void readCalls(Context context) {
        Cursor cursor = context.getContentResolver().query(Uri.parse("content://call_log/calls"), null, null, null, null);
        Log.d(CallScraper.class.toString(), "======= CALL LOG =======");

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                for (int idx = 0; idx < cursor.getColumnCount(); idx++) {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                }
                Log.d(CallScraper.class.toString(), "call: " + msgData);
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }

    }

    @Override
    protected Void doInBackground(Context... params) {
        readCalls(params[0]);
        return null;
    }
}

package aru.phothong.atip.neungapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Admin on 27/9/2559.
 */

public class MyAlert {
    //Explicit
    private Context context;
    private String titlrString, messageString;

    public MyAlert(Context context, String titlrString, String messageString) {
        this.context = context;
        this.titlrString = titlrString;
        this.messageString = messageString;
    }// Constructor
    public void myDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.kon48);
        builder.setTitle(titlrString);
        builder.setMessage(messageString);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();


    }//myDialog
}//Main Class

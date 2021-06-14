package com.example.mobilhotelqr.Core;


import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mobilhotelqr.R;

public class LoadingDialog {

   private Activity activity;
   private AlertDialog alertDialog;

    public LoadingDialog(Activity myActivity){

        activity=myActivity;

    }

    public void startLoadingDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.fragment_loader,null));
        builder.setCancelable(true);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void dismissDialog(){
        alertDialog.dismiss();
    }

    public void privateNoSuccess(String text){

        TextView textView14;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View mView = inflater.inflate(R.layout.alert_dialog_custom,null);
        textView14 = mView.findViewById(R.id.textView14);
        textView14.setText(text);
        builder.setView(mView);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialog.show();

    }
}

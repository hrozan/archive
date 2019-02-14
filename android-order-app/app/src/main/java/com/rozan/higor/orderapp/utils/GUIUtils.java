package com.rozan.higor.orderapp.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

import com.rozan.higor.orderapp.R;

/**
 * Created by Higor on 21/11/2017.
 */

public class GUIUtils {

    public static void erroWarning(Context context, int textId){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.alertMsg);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(textId);

        builder.setNeutralButton(R.string.okMsg,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static void confirmAction(Context context,
                                    String message,
                                    DialogInterface.OnClickListener listener){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.confirmMsg);
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setMessage(message);

        builder.setPositiveButton(R.string.yesTxt, listener);
        builder.setNegativeButton(R.string.noTxt, listener);

        AlertDialog alert = builder.create();
        alert.show();
    }

    public static String validateTextField(Context  context,
                                          EditText editText,
                                          int errorMessageId){

        String texto = editText.getText().toString();

        if (StringUtils.emptyString(texto)){
            GUIUtils.erroWarning(context, errorMessageId);
            editText.setText(null);
            editText.requestFocus();
            return null;
        }else{
            return texto.trim();
        }
    }


}

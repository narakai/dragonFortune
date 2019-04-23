package com.jeycorp.dragonFortune.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StayDialog extends AlertDialog {
    boolean isOk = true;

    public StayDialog(Context context) {
        super(context);

        setButton(AlertDialog.BUTTON_POSITIVE, "Ok", (new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // this will never be called
            }
        }));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOk) {
                    // do something
                    dismiss();
                } else {
                    Toast.makeText(getContext(), "when you see this message, the dialog should stay open", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }




}

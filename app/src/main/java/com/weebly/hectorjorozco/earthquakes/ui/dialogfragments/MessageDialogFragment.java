package com.weebly.hectorjorozco.earthquakes.ui.dialogfragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.weebly.hectorjorozco.earthquakes.R;
import com.weebly.hectorjorozco.earthquakes.utils.UiUtils;

import java.util.Objects;

public class MessageDialogFragment extends DialogFragment {

    private static final String DIALOG_FRAGMENT_MESSAGE_ARGUMENT_KEY = "message";
    private static final String DIALOG_FRAGMENT_TITLE_ARGUMENT_KEY = "title";

    public MessageDialogFragment() {
        // Empty constructor is required for DialogFragment
    }

    public static MessageDialogFragment newInstance(CharSequence message, String title) {
        MessageDialogFragment messageDialogFragment = new MessageDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putCharSequence(DIALOG_FRAGMENT_MESSAGE_ARGUMENT_KEY, message);
        bundle.putString(DIALOG_FRAGMENT_TITLE_ARGUMENT_KEY, title);
        messageDialogFragment.setArguments(bundle);
        return messageDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        int colorPrimaryDark = getResources().getColor(R.color.colorPrimaryDark);
        int colorAccent = getResources().getColor(R.color.colorAccent);
        String colorPrimaryDarkString = Integer.toHexString(colorPrimaryDark & 0x00ffffff);

        Bundle arguments = getArguments();

        CharSequence message = null;
        String title = "";

        if (arguments != null) {
            message = arguments.getCharSequence(DIALOG_FRAGMENT_MESSAGE_ARGUMENT_KEY, "Hello");
            title = arguments.getString(DIALOG_FRAGMENT_TITLE_ARGUMENT_KEY);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()),
                R.style.ThemeDialogCustomPrimaryColor);
        builder.setMessage(message).setTitle(Html.fromHtml(getString(R.string.html_text_with_color,
                colorPrimaryDarkString, title)));

        builder.setPositiveButton(R.string.positive_button_text, (dialog, id) -> {
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

        Button button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        button.setTextColor(colorAccent);
        UiUtils.setupAlertDialogButtonBackground(getContext(), button);

        return alertDialog;
    }

}

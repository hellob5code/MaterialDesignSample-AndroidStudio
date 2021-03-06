package com.jp.materialdesignsample.dialog;

import android.content.Context;
import android.content.DialogInterface;

import com.jp.materialdesignsample.dialog.material.MaterialAlertDialog;
import com.jp.materialdesignsample.dialog.material.MaterialSelectionDialog;

import java.util.List;

public class DialogBuilder {
    private static BaseDialog mCurrentActiveDialog = null;

    public static void clear() {
        mCurrentActiveDialog = null;
    }

    public static BaseDialog buildSystemDialog(Context context, String tag, OnDialogButtonClickListener onClickListener) {
        if (mCurrentActiveDialog != null && mCurrentActiveDialog.getTag().equals(tag)) {
            return mCurrentActiveDialog;
        } else {
            SystemDialog dialog = new SystemDialog(context);

            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK");
            dialog.setTag(tag);
            dialog.setOnDialogButtonClickListener(onClickListener);

            mCurrentActiveDialog = dialog;
            return dialog;
        }
    }

    public static BaseDialog buildSystemDialog(Context context, String title, String message, String positiveBtn, String negativeBtn, String neutralBtn, String tag, OnDialogButtonClickListener onClickListener) {
        if (mCurrentActiveDialog != null && mCurrentActiveDialog.getTag().equals(tag)) {
            return mCurrentActiveDialog;
        } else {
            SystemDialog dialog = new SystemDialog(context);
            dialog.setTitle(title);
            dialog.setMessage(message);
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, positiveBtn);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, negativeBtn);
            dialog.setButton(DialogInterface.BUTTON_NEUTRAL, neutralBtn);

            dialog.setTag(tag);
            dialog.setOnDialogButtonClickListener(onClickListener);

            mCurrentActiveDialog = dialog;
            return dialog;
        }
    }

    public static BaseDialog buildSystemDialog(Context context, int titleId, int messageId, int positiveBtnId, int negativeBtnId, int neutralBtnId, String tag, OnDialogButtonClickListener onClickListener) {
        if (mCurrentActiveDialog != null && mCurrentActiveDialog.getTag().equals(tag)) {
            return mCurrentActiveDialog;
        } else {
            SystemDialog dialog = new SystemDialog(context);

            dialog.setTitle(titleId);
            dialog.setMessage(messageId);
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, positiveBtnId);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, negativeBtnId);
            dialog.setButton(DialogInterface.BUTTON_NEUTRAL, neutralBtnId);

            dialog.setTag(tag);
            dialog.setOnDialogButtonClickListener(onClickListener);

            mCurrentActiveDialog = dialog;
            return dialog;
        }
    }

    public static BaseDialog buildMaterialAlertDialog(Context context, String tag, OnDialogButtonClickListener onClickListener) {
        if (mCurrentActiveDialog != null && mCurrentActiveDialog.getTag().equals(tag)) {
            return mCurrentActiveDialog;
        } else {
            MaterialAlertDialog dialog = new MaterialAlertDialog(context);

            dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK");
            dialog.setTag(tag);
            dialog.setOnDialogButtonClickListener(onClickListener);

            mCurrentActiveDialog = dialog;
            return dialog;
        }
    }

    public static <T extends IPickerDialogItem> BaseDialog buildPickerDialog(Context context, String tag, List<T> source, OnDialogValueSelectedListener listener) {
        if (mCurrentActiveDialog != null && mCurrentActiveDialog.getTag().equals(tag)) {
            return mCurrentActiveDialog;
        } else {
            PickerDialog<T> dialog = new PickerDialog<>(context);
            dialog.setTag(tag);
            dialog.setPickerData(source);
            dialog.setOnDialogValueSelectedListener(listener);

            mCurrentActiveDialog = dialog;
            return dialog;
        }
    }

    public static <T extends IPickerDialogItem> BaseDialog buildSelectionDialog(Context context, String tag, List<T> source, OnDialogValueSelectedListener listener) {
        if (mCurrentActiveDialog != null && mCurrentActiveDialog.getTag().equals(tag)) {
            return mCurrentActiveDialog;
        } else {
            SelectionDialog<T> dialog = new SelectionDialog<>(context);
            dialog.setTag(tag);
            dialog.setSelectionData(source);
            dialog.setOnDialogValueSelectedListener(listener);

            mCurrentActiveDialog = dialog;
            return dialog;
        }
    }

    public static <T extends IPickerDialogItem> BaseDialog buildMaterialSelectionDialog(Context context, String tag, List<T> source, OnDialogValueSelectedListener listener) {
        if (mCurrentActiveDialog != null && mCurrentActiveDialog.getTag().equals(tag)) {
            return mCurrentActiveDialog;
        } else {
            MaterialSelectionDialog<T> dialog = new MaterialSelectionDialog<>(context);
            dialog.setTag(tag);
            dialog.setSelectionData(source);
            dialog.setOnDialogValueSelectedListener(listener);

            mCurrentActiveDialog = dialog;
            return dialog;
        }
    }
}

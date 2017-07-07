//package com.codepath.apps.restclienttemplate.fragments;
//
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//
///**
// * Created by snhoward on 7/6/17.
// */
//
//public class ComposeDialogFragment extends DialogFragment {
//    public ComposeDialogFragment() {
//        // Empty constructor required for DialogFragment
//    }
//
//    public static ComposeDialogFragment newInstance(String title) {
//        MyAlertDialogFragment frag = new MyAlertDialogFragment();
//        Bundle args = new Bundle();
//        args.putString("title", title);
//        frag.setArguments(args);
//        return frag;
//    }
//
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        String title = getArguments().getString("title");
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//        alertDialogBuilder.setTitle(title);
//        alertDialogBuilder.setMessage("Are you sure?");
//        alertDialogBuilder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                // on success
//            }
//        });
//        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (dialog != null && dialog.isShowing()) {
//                    dialog.dismiss();
//                }
//            }
//
//        });
//
//        return alertDialogBuilder.create();
//    }
//
//}

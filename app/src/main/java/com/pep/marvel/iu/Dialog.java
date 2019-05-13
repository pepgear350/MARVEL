package com.pep.marvel.iu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.pep.marvel.R;
import com.pep.marvel.ViewModel.ResultViewModel;
import com.pep.marvel.util.Math;

public class Dialog extends DialogFragment {

    private ResultViewModel viewModel;


    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(getActivity()).get(ResultViewModel.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);
        TextInputEditText editText = view.findViewById(R.id.edit_dialog);

        builder.setView(view)
                .setPositiveButton(R.string.result, (dialog, id) -> {
                    MathOperation(editText.getText().toString());
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }


    private void MathOperation(String text) {
        Math math = new Math();
        String [] readText = math.readText(text);
        if (readText != null) {
            try {
                double result = math.mathOperation(readText);
                double multiple = math.getMultiple(result);
                viewModel.initResult((int) multiple);
                String message = (multiple == 2) ? "Default" : String.valueOf((int) multiple);
                viewModel.getMessage().setValue(getString(R.string.multiple) + message );
            } catch (Exception e) {
                viewModel.getMessage().setValue(getString(R.string.invalid));
            }
        } else {
            viewModel.getMessage().setValue(getString(R.string.invalid));
        }
    }







}

package com.example.billigo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    public static BottomSheetDialog getInstance() { return new BottomSheetDialog(); }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_drone_request_after_addressposition_see_register_previewlayout, container,false);
        return view;
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}

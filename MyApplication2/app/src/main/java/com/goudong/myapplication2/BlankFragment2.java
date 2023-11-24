package com.goudong.myapplication2;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class BlankFragment2 extends Fragment {

    private View root;
    private TextView txt;
    private Button bnt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(root == null){
            root = inflater.inflate(R.layout.fragment_blank2,container,false);
        }
        txt= root.findViewById(R.id.txt);
        bnt = root.findViewById(R.id.bnt);

        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("南方科技大学");
                bnt.setText(R.string.answer);
            }
        });


        return root;
    }
}
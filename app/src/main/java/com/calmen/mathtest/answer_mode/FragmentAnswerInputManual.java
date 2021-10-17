package com.calmen.mathtest.answer_mode;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.calmen.mathtest.R;
import com.calmen.mathtest.online_service.LoadTestQuestion;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentAnswerInputManual#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAnswerInputManual extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentAnswerInputManual() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAnswerInputManual.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAnswerInputManual newInstance(String param1, String param2) {
        FragmentAnswerInputManual fragment = new FragmentAnswerInputManual();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_answer_input_manual, container, false);
        EditText answerManual = view.findViewById(R.id.answerInput);
        Button enterBtn = view.findViewById(R.id.enterInputBtn);

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LoadTestQuestion) getActivity()).getAnswer().setText(answerManual.getText().toString());
            }
        });
        return view;
    }
}
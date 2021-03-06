package com.calmen.mathtest.answer_mode;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.calmen.mathtest.R;
import com.calmen.mathtest.answer_mode.recycler_option.RecyclerOptionAdapter;
import com.calmen.mathtest.online_service.LoadTestQuestion;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSelectionC#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSelectionC extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentSelectionC() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSelectionC.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSelectionC newInstance(String param1, String param2) {
        FragmentSelectionC fragment = new FragmentSelectionC();
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
        View view = inflater.inflate(R.layout.fragment_selection_c, container, false);
        RecyclerView rv = view.findViewById(R.id.recyclerFragC);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerOptionAdapter recyclerAdapter =
                new RecyclerOptionAdapter(((LoadTestQuestion) getActivity()).getOptionsC(), getContext());
        rv.setAdapter(recyclerAdapter);
        return view;
    }
}
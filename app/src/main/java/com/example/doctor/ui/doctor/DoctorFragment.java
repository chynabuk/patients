package com.example.doctor.ui.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor.databinding.FragmentDoctorBinding;
import com.example.doctor.model.Doctor;

import java.util.List;


public class DoctorFragment extends Fragment {
    private DoctorViewModel doctorViewModel;

    private FragmentDoctorBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        doctorViewModel = new ViewModelProvider(this).get(DoctorViewModel.class);

        binding = FragmentDoctorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recycler = binding.recycler;
        recycler.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        recycler.setHasFixedSize(true);

        final DoctorAdapter doctorsAdapter = new DoctorAdapter();
        recycler.setAdapter(doctorsAdapter);
        doctorViewModel.getAllDoctors().observe(requireActivity(), new Observer<List<Doctor>>() {
            @Override
            public void onChanged(List<Doctor> doctors) {
                doctorsAdapter.setDoctors(doctors);
                doctorsAdapter.setDoctorViewModel(doctorViewModel);
                doctorsAdapter.setDoctorFragment(DoctorFragment.this);
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.add.setOnClickListener(view -> {
            openDoctorAdd();
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void openDoctorAdd(){
        DoctorAddFragment doctorAddFragment = new DoctorAddFragment(doctorViewModel);

        getFragmentManager()
                .beginTransaction()
                .replace(this.getId(), doctorAddFragment, "findThisFramgent")
                .addToBackStack(null)
                .commit();
    }

}
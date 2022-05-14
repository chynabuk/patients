package com.example.doctor.ui.patient;

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

import com.example.doctor.databinding.FragmentPatientBinding;
import com.example.doctor.model.Patient;

import java.util.List;

public class PatientFragment extends Fragment {
    private FragmentPatientBinding binding;
    private PatientViewModel patientViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);

        binding = FragmentPatientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recycler = binding.recyclerPacient;
        recycler.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        recycler.setHasFixedSize(true);

        final PatientAdapter patientAdapter = new PatientAdapter();
        recycler.setAdapter(patientAdapter);
        patientViewModel.getAllPatients().observe(requireActivity(), new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                patientAdapter.setPatients(patients);
                patientAdapter.setPatientViewModel(patientViewModel);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
package com.example.doctor.ui.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.doctor.R;
import com.example.doctor.databinding.FragmentAddPatientBinding;
import com.example.doctor.model.Patient;
import com.example.doctor.repository.PatientRepository;

public class PatientAddFragment extends Fragment {
    private FragmentAddPatientBinding binding;
    private PatientRepository patientRepository;

    public PatientAddFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddPatientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.save.setOnClickListener(view -> {
            savePatient();
        });
    }

    private void savePatient(){
        String name = binding.name.getText().toString();
        String lastName = binding.lastName.getText().toString();
        String phoneNumber = binding.phone.getText().toString();

        if (!name.isEmpty() && !lastName.isEmpty() && !phoneNumber.isEmpty()){
            patientRepository = new PatientRepository(getActivity().getApplication());

            patientRepository.insert(new Patient(name, lastName, phoneNumber));

            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigateUp();
            navController.navigate(R.id.navigation_dashboard);
        }
    }
}

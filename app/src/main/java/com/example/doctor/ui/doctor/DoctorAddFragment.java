package com.example.doctor.ui.doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doctor.R;
import com.example.doctor.databinding.FragmentAddDoctorBinding;
import com.example.doctor.model.Doctor;

public class DoctorAddFragment extends Fragment {

    private DoctorViewModel doctorViewModel;
    private FragmentAddDoctorBinding binding;

    public DoctorAddFragment(DoctorViewModel doctorViewModel){
        this.doctorViewModel = doctorViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddDoctorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        binding.save.setOnClickListener(view -> {
            saveDoctor();
        });
    }

    private void saveDoctor(){
        String name = binding.name.getText().toString();
        int room = Integer.valueOf(binding.room.getText().toString());
        String position = binding.position.toString();

        if (!name.isEmpty() || !position.isEmpty()){
            doctorViewModel.insert(new Doctor(name, room, position, R.drawable.img));

            DoctorFragment doctorFragment = new DoctorFragment();

            getFragmentManager()
                    .beginTransaction()
                    .replace(this.getId(), doctorFragment, "doctor_fragment")
                    .addToBackStack(null)
                    .commit();
        }
    }
}

package com.example.doctor.ui.doctor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.doctor.model.Doctor;
import com.example.doctor.repository.DoctorRepository;

import java.util.List;

public class DoctorViewModel extends AndroidViewModel {
    private DoctorRepository doctorRepository;
    private final LiveData<List<Doctor>> doctors;

    public DoctorViewModel(@NonNull Application application) {
        super(application);
        doctorRepository = new DoctorRepository(application);
        doctors = doctorRepository.getAllDoctors();
    }

    public void insert(Doctor doctor){
        doctorRepository.insert(doctor);
    }

    public void update(Doctor doctor){
        doctorRepository.update(doctor);
    }

    public void delete(Doctor doctor){
        doctorRepository.delete(doctor);
    }

    public void deleteAllDoctors(){
        doctorRepository.deleteAllDoctors();
    }

    public LiveData<List<Doctor>> getAllDoctors(){
        return doctorRepository.getAllDoctors();
    }
}
package com.example.doctor.ui.patient;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.doctor.model.Patient;
import com.example.doctor.repository.PatientRepository;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {
    private PatientRepository patientRepository;
    private final LiveData<List<Patient>> patients;


    public PatientViewModel(@NonNull Application application) {
        super(application);
        patientRepository = new PatientRepository(application);
        patients = patientRepository.getAllPatients();
    }

    public void insert(Patient patient){
        patientRepository.insert(patient);
    }

    public void update(Patient patient){
        patientRepository.update(patient);
    }

    public void delete(Patient patient){
        patientRepository.delete(patient);
    }

    public void deleteAllPatients(){
        patientRepository.deleteAllPatients();
    }

    public LiveData<List<Patient>> getAllPatients(){
        return patientRepository.getAllPatients();
    }
}
package com.example.doctor.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.doctor.daos.PatientDao;
import com.example.doctor.model.Patient;
import com.example.doctor.roomDB.PatientDB;

import java.util.List;

public class PatientRepository {
    private PatientDao patientDao;
    private LiveData<List<Patient>> patients;

    public PatientRepository(Application application){
        PatientDB patientDB = PatientDB.getInstance(application);
        patientDao = patientDB.doctorDao();
        patients = patientDao.getAllPatients();
    }

    public void insert(Patient patient){
        new PatientRepository.InsertPatientAsyncTask(patientDao).execute(patient);
    }

    public void update(Patient patient){
        new PatientRepository.UpdatePatientAsyncTask(patientDao).execute(patient);
    }

    public void delete(Patient patient){
        new PatientRepository.DeletePatientAsyncTask(patientDao).execute(patient);
    }

    public void deleteAllPatients(){
        new PatientRepository.DeleteAllPatientAsyncTask(patientDao).execute();
    }

    public LiveData<List<Patient>> getAllPatients(){
        return patients;
    }

    private static class InsertPatientAsyncTask extends AsyncTask<Patient, Void, Void> {
        private PatientDao patientDao;

        private InsertPatientAsyncTask(PatientDao patientDao){
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.insert(patients[0]);
            return null;
        }
    }

    private static class UpdatePatientAsyncTask extends AsyncTask<Patient, Void, Void>{
        private PatientDao patientDao;

        private UpdatePatientAsyncTask(PatientDao patientDao){
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.update(patients[0]);
            return null;
        }
    }

    private static class DeletePatientAsyncTask extends AsyncTask<Patient, Void, Void>{
        private PatientDao patientDao;

        private DeletePatientAsyncTask(PatientDao patientDao){
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patients) {
            patientDao.delete(patients[0]);
            return null;
        }
    }

    private static class DeleteAllPatientAsyncTask extends AsyncTask<Void, Void, Void>{
        private PatientDao patientDao;

        private DeleteAllPatientAsyncTask(PatientDao patientDao){
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            patientDao.deleteAllPatients();
            return null;
        }
    }
}

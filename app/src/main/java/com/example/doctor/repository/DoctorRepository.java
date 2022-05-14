package com.example.doctor.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.doctor.daos.DoctorDao;
import com.example.doctor.model.Doctor;
import com.example.doctor.roomDB.DoctorDB;

import java.util.List;

public class DoctorRepository {
    private DoctorDao doctorDao;
    private LiveData<List<Doctor>> doctors;

    public DoctorRepository(Application application){
        DoctorDB doctorDB = DoctorDB.getInstance(application);
        doctorDao = doctorDB.doctorDao();
        doctors = doctorDao.getAllDoctors();
    }

    public void insert(Doctor doctor){
        new InsertDoctorAsyncTask(doctorDao).execute(doctor);
    }

    public void update(Doctor doctor){
        new UpdateDoctorAsyncTask(doctorDao).execute(doctor);
    }

    public void delete(Doctor doctor){
        new DeleteDoctorAsyncTask(doctorDao).execute(doctor);
    }

    public void deleteAllDoctors(){
        new DeleteAllDoctorAsyncTask(doctorDao).execute();
    }

    public LiveData<List<Doctor>> getAllDoctors(){
        return doctors;
    }

    private static class InsertDoctorAsyncTask extends AsyncTask<Doctor, Void, Void>{
        private DoctorDao doctorDao;

        private InsertDoctorAsyncTask(DoctorDao doctorDao){
            this.doctorDao = doctorDao;
        }

        @Override
        protected Void doInBackground(Doctor... doctors) {
            doctorDao.insert(doctors[0]);
            return null;
        }
    }

    private static class UpdateDoctorAsyncTask extends AsyncTask<Doctor, Void, Void>{
        private DoctorDao doctorDao;

        private UpdateDoctorAsyncTask(DoctorDao doctorDao){
            this.doctorDao = doctorDao;
        }

        @Override
        protected Void doInBackground(Doctor... doctors) {
            doctorDao.update(doctors[0]);
            return null;
        }
    }

    private static class DeleteDoctorAsyncTask extends AsyncTask<Doctor, Void, Void>{
        private DoctorDao doctorDao;

        private DeleteDoctorAsyncTask(DoctorDao doctorDao){
            this.doctorDao = doctorDao;
        }

        @Override
        protected Void doInBackground(Doctor... doctors) {
            doctorDao.delete(doctors[0]);
            return null;
        }
    }

    private static class DeleteAllDoctorAsyncTask extends AsyncTask<Void, Void, Void>{
        private DoctorDao doctorDao;

        private DeleteAllDoctorAsyncTask(DoctorDao doctorDao){
            this.doctorDao = doctorDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            doctorDao.deleteAllDoctors();
            return null;
        }
    }
}

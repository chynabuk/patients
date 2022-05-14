package com.example.doctor.roomDB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.doctor.R;
import com.example.doctor.daos.PatientDao;
import com.example.doctor.model.Doctor;
import com.example.doctor.model.Patient;

@Database(entities = {Patient.class}, version = 1)
public abstract class PatientDB extends RoomDatabase {

    private static PatientDB patientDB;

    public abstract PatientDao doctorDao();

    public static synchronized PatientDB getInstance(Context context){
        if (patientDB == null){
            patientDB = Room.databaseBuilder(context.getApplicationContext(), PatientDB.class, "patient_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return patientDB;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(patientDB).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void> {
        private PatientDao patientDao;

        private PopulateDBAsyncTask(PatientDB patientDB){
            this.patientDao = PatientDB.patientDB.doctorDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}

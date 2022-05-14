package com.example.doctor.roomDB;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.doctor.R;
import com.example.doctor.daos.DoctorDao;
import com.example.doctor.model.Doctor;

@Database(entities = {Doctor.class}, version = 1)
public abstract class DoctorDB extends RoomDatabase {

    private static DoctorDB doctorDB;

    public abstract DoctorDao doctorDao();

    public static synchronized DoctorDB getInstance(Context context){
        if (doctorDB == null){
            doctorDB = Room.databaseBuilder(context.getApplicationContext(), DoctorDB.class, "doctor_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return doctorDB;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(doctorDB).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{
        private DoctorDao doctorDao;

        private PopulateDBAsyncTask(DoctorDB doctorDB){
            this.doctorDao = doctorDB.doctorDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            doctorDao.insert(new Doctor("Кубанычбек", 1, "Стоматолог", R.drawable.img));
            doctorDao.insert(new Doctor("Алакен", 2, "Стоматолог", R.drawable.img_1));
            doctorDao.insert(new Doctor("Эрсултан", 3, "Стоматолог", R.drawable.img_2));
            doctorDao.insert(new Doctor("Бекжан", 4, "Стоматолог", R.drawable.img_3));
            doctorDao.insert(new Doctor("Айдар", 5, "Стоматолог", R.drawable.img_4));
            doctorDao.insert(new Doctor("Адис", 6, "Стоматолог", R.drawable.img_5));
            doctorDao.insert(new Doctor("Айболот", 7, "Стоматолог", R.drawable.img_6));
            doctorDao.insert(new Doctor("Санжар", 8, "Стоматолог", R.drawable.img_7));
            doctorDao.insert(new Doctor("Салмоор", 9, "Стоматолог", R.drawable.img_8));
            doctorDao.insert(new Doctor("Адиль", 10, "Стоматолог", R.drawable.img_9));
            return null;
        }
    }

}

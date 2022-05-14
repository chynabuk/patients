package com.example.doctor.daos;

//import android.arch.persistence.room.Dao;
//import android.arch.persistence.room.Delete;
//import android.arch.persistence.room.Insert;
//import android.arch.persistence.room.Query;
//import android.arch.persistence.room.Update;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.doctor.model.Doctor;

import java.util.List;

@Dao
public interface DoctorDao {
    @Insert
    void insert(Doctor doctor);

    @Update
    void update(Doctor doctor);

    @Delete
    void delete(Doctor doctor);

    @Query("DELETE FROM doctors")
    void deleteAllDoctors();

    @Query("SELECT * FROM doctors")
    LiveData<List<Doctor>> getAllDoctors();
}

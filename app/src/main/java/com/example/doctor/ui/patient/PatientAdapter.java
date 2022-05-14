package com.example.doctor.ui.patient;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor.databinding.DoctorItemLayoutBinding;
import com.example.doctor.databinding.PatientItemLayoutBinding;
import com.example.doctor.model.Patient;
import com.example.doctor.repository.PatientRepository;
import com.example.doctor.ui.doctor.DoctorAdapter;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    private List<Patient> patients = new ArrayList<>();
    private PatientViewModel patientViewModel;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PatientItemLayoutBinding binding = PatientItemLayoutBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Patient patient = patients.get(position);
        holder.binding.name.setText(patient.getName());
        holder.binding.lastName.setText(patient.getLastName());
        holder.binding.phone.setText(patient.getPhoneNumber());
        holder.itemView.setOnClickListener(view -> {
            Toast toast = Toast.makeText(holder.binding.getRoot().getContext(),
                    patient.getName() + " " + patient.getLastName() + "\n"
                    + "Ваша очередь: " + (position + 1) + "\n" +
                    " Дата регистрации: " + patient.getCreatedAt().toString(),
                    Toast.LENGTH_SHORT);

            toast.show();
        });
        holder.itemView.setOnLongClickListener(view -> {
            AlertDialog alertDialog = new AlertDialog.Builder(holder.binding.getRoot().getContext()).setMessage("Вы действительно хотите удалить")
                    .setPositiveButton("ДА",
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AppCompatActivity activity = (AppCompatActivity) holder.binding.getRoot().getContext();
                            PatientRepository patientRepository = new PatientRepository(activity.getApplication());
                            patientRepository.delete(patient);
                        }
                    })
                    .setNegativeButton("НЕТ",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                    })
                    .create();

            alertDialog.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients){
        this.patients = patients;
        notifyDataSetChanged();
    }

    public void setPatientViewModel(PatientViewModel patientViewModel){
        this.patientViewModel = patientViewModel;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private PatientItemLayoutBinding binding;

        public ViewHolder(@NonNull PatientItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

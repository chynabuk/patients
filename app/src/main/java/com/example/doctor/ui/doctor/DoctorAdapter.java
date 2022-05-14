package com.example.doctor.ui.doctor;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctor.R;
import com.example.doctor.databinding.DoctorItemLayoutBinding;
import com.example.doctor.model.Doctor;
import com.example.doctor.ui.patient.PatientAddFragment;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    private List<Doctor> doctors = new ArrayList<>();
    private DoctorViewModel doctorViewModel;
    private DoctorFragment doctorFragment;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DoctorItemLayoutBinding binding = DoctorItemLayoutBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        ViewHolder viewHolder = new ViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = doctors.get(position);
        holder.binding.name.setText(doctor.getName());
        holder.binding.room.setText("Кабинет: " + doctor.getRoom());
        holder.binding.position.setText("Должность: " + doctor.getPosition());
        holder.binding.image.setImageDrawable(ContextCompat.getDrawable(holder.binding.getRoot().getContext(), doctor.getImage()));
        holder.binding.dropdownMenu.setOnClickListener(view ->  {
            PopupMenu popup = new PopupMenu(holder.binding.getRoot().getContext(), holder.binding.dropdownMenu);
            popup.getMenuInflater().inflate(R.menu.card_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getTitle().toString()){
                        case "Позвонить в регистратуру":
                            holder.binding.getRoot().getContext().startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0778393607")));
                            return true;
                        case "Записаться к врачу":
                            navigateToAddPacient(holder);
                            return true;
                    }
                    return true;
                }
            });
            popup.show();
        });

        holder.binding.dropdownMenu.setOnLongClickListener(view -> {
            PopupMenu popup = new PopupMenu(holder.binding.getRoot().getContext(), holder.binding.dropdownMenu);
            popup.getMenuInflater().inflate(R.menu.confirm_delete, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getTitle().toString()){
                        case "Удалить":
                            doctorViewModel.delete(doctor);
                        case "Нет":
                    }
                    return true;
                }
            });
            popup.show();

            return false;
        });

    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public void setDoctorFragment(DoctorFragment doctorFragment) {
        this.doctorFragment = doctorFragment;
    }

    public void setDoctors(List<Doctor> doctors){
        this.doctors = doctors;
        notifyDataSetChanged();
    }

    public void setDoctorViewModel(DoctorViewModel doctorViewModel){
        this.doctorViewModel = doctorViewModel;
    }

    private void openAddPacient(ViewHolder holder){
        PatientAddFragment patientAddFragment = new PatientAddFragment();
        AppCompatActivity activity = (AppCompatActivity) holder.binding.getRoot().getContext();

        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(doctorFragment.getId(), patientAddFragment, "patient add fragment")
                .addToBackStack(null)
                .commit();


//        DoctorFragment doctorFragment = holder.itemView.getF;
//        doctorFragment.getFragmentManager()
    }

    private void navigateToAddPacient(ViewHolder holder){
        NavController navController = Navigation.findNavController(doctorFragment.getActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
        navController.navigate(R.id.navigation_notifications);

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private DoctorItemLayoutBinding binding;

        public ViewHolder(@NonNull DoctorItemLayoutBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

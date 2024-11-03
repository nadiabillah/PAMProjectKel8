package com.notnot.PAMProject.Activity;

import androidx.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import com.notnot.PAMProject.R;
import com.notnot.PAMProject.databinding.ActivityProfileBinding; // Pastikan ini adalah binding yang benar
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class ProfileActivity extends BaseActivity {

    private ActivityProfileBinding binding;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = binding.userName.getText().toString().trim();
                String firstName = binding.firstName.getText().toString().trim();
                String lastName = binding.lastname.getText().toString().trim();
                String age = binding.age.getText().toString().trim();

                if (userName.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || age.isEmpty()) {
                    Toast.makeText(ProfileActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                updateData(userName, firstName, lastName, age);
            }
        });
    }

    private void updateData(String userName, String firstName, String lastName, String age) {
        HashMap<String, Object> user = new HashMap<>();
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("age", age);

        databaseReference.child(userName).updateChildren(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    binding.userName.setText("");
                    binding.firstName.setText("");
                    binding.lastname.setText("");
                    binding.age.setText("");
                    Toast.makeText(ProfileActivity.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileActivity.this, "Failed to Update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

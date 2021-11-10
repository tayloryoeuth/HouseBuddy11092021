package com.example.housebuddy2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.housebuddy2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    EditText mEditTextTo;
    EditText mEditTextSubject;
    EditText mEditTextMessage;
    Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.housebuddy2.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_wishlist, R.id.navigation_browse, R.id.navigation_profile, R.id.navigation_more)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        //Support Email things
        mEditTextTo = findViewById(R.id.edit_text_to);
        mEditTextSubject = findViewById(R.id.edit_text_subject);
        mEditTextMessage = findViewById(R.id.edit_text_message);
        buttonSend = findViewById(R.id.button_send);

        buttonSend.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!mEditTextTo.getText().toString().isEmpty() && !mEditTextSubject.getText().toString().isEmpty() && !mEditTextMessage.getText().toString().isEmpty()) {
                //get all users info
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mEditTextTo.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, mEditTextSubject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, mEditTextMessage.getText().toString());

                intent.setType("message/rfc822"); //narrows down the users email applications on their device
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "There are no applications that support this action!", Toast.LENGTH_SHORT).show();
                }


            }else {
                Toast.makeText(MainActivity.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
            }
        }
    });


}
}



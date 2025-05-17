package com.yusufyilmaz00.edulai;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.yusufyilmaz00.edulai.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // 5 sekmeli navigation yapılandırması
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home,
                R.id.navigation_mentor,
                R.id.navigation_upload,
                R.id.navigation_progress,
                R.id.navigation_profile
        ).build();
        
        
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);


        navView.setOnItemReselectedListener(item -> {
            if (item.getItemId() == R.id.navigation_home) {
                Log.d("NavigationEvent", "Home butonuna tekrar tıklandı (reselected).");
            }
        });
        
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }
}

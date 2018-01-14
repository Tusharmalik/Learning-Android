package com.example.tusharmalik.fragementtest1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button analogButton, digitalButton;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle("Clock");
        toolbar.setSubtitle("tells the time");
        toolbar.setLogo(R.drawable.clock_logo);

        analogButton = findViewById(R.id.button_analog);
        analogButton.setOnClickListener(this);

        digitalButton = findViewById(R.id.button_digital);
        digitalButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment;

        int id = item.getItemId();
        switch (id) {
            case R.id.menu_date:
                startActivity(new Intent(android.provider.Settings
                        .ACTION_DATE_SETTINGS));
                break;
            case R.id.menu_location:
                startActivity(new Intent(android.provider.Settings
                        .ACTION_LOCALE_SETTINGS));
                break;
            case R.id.menu_sleep:
                startActivity(new Intent(android.provider.Settings
                        .ACTION_SOUND_SETTINGS));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Fragment fragment;
        if (view == analogButton) {
            fragment = new FragmentAnalog();
        }
        else {
            fragment = new FragmentDigital();
        }
        replaceFragment(fragment);

    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.replace(R.id.fragment_container,fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }
}

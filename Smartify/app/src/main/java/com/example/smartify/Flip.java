package com.example.smartify;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import java.security.acl.Group;
import java.util.zip.Inflater;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;

import static com.example.smartify.ExampleService.flipSettings;

public class Flip extends AppCompatActivity {
    FloatingActionButton fab;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.flip_menu,menu);
        if(flipSettings==3)
        menu.getItem(0).setChecked(true);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
           /* case R.id.offline:
                if(item.isChecked())
                {
                    item.setChecked(false);
                    //flipSettings[0]=0;
                }
                else
                {
                    item.setChecked(true);
                   // flipSettings[0]=1;
                }
                return true;*/
            case R.id.alarm:
                if(item.isChecked())
                {
                    item.setChecked(false);
                    flipSettings=2;
                }
                else
                {
                    item.setChecked(true);
                    flipSettings=3;
                }
                return true;

            default:





        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);
        if(ExampleService.flip==true)
        {
            fab.setImageResource(R.drawable.ic_do_not_disturb_on_white_24dp);
        }
        else
        {
            fab.setImageResource(R.drawable.ic_do_not_disturb_off_white_24dp);
        }
        new MaterialIntroView.Builder(this)
                .enableDotAnimation(true)
                .enableIcon(true)
                .setFocusGravity(FocusGravity.CENTER)
                .setFocusType(Focus.MINIMUM)
                .setDelayMillis(500)
                .enableFadeAnimation(true)
                .performClick(true)
                .enableDotAnimation(true)
                .setInfoText("Click here to enable DND when phone is flipped")
                .setShape(ShapeType.CIRCLE)
                .setTarget(fab)
                .setUsageId("intro_fab") //THIS SHOULD BE UNIQUE ID
                .setMaskColor(ContextCompat.getColor(Flip.this,R.color.whiteTransparent))
                .show();
        fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(Flip.this, R.color.colorPrimary)));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                if(ExampleService.flip==true)
                {
                    ExampleService.flip=false;
                    Toast.makeText(Flip.this, "Switched off", Toast.LENGTH_SHORT).show();
                    fab.setImageResource(R.drawable.ic_do_not_disturb_off_white_24dp);
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v.vibrate(200);
                    }
                }
                else
                {
                    ExampleService.flip=true;
                    Toast.makeText(Flip.this, "Switched on", Toast.LENGTH_SHORT).show();
                    fab.setImageResource(R.drawable.ic_do_not_disturb_on_white_24dp);
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        v.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                    } else {
                        //deprecated in API 26
                        v.vibrate(200);
                    }
                }
            }
        });

        /*if(flipSettings==3)
        {
            MenuItem alarms = findViewById(R.id.alarm);
            alarms.setChecked(true);
        }*/

    }

}

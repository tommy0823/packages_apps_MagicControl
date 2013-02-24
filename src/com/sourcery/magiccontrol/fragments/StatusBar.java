/*
 * Copyright (C) 2012 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sourcery.magiccontrol.fragments;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemProperties;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.Spannable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.sourcery.magiccontrol.R;
import com.sourcery.magiccontrol.util.Helpers;
import com.sourcery.magiccontrol.SettingsPreferenceFragment;
import com.sourcery.magiccontrol.util.CMDProcessor;
import com.sourcery.magiccontrol.util.Utils;
import com.sourcery.magiccontrol.widgets.AlphaSeekBar;
import com.sourcery.magiccontrol.widgets.SeekBarPreference;

import net.margaritov.preference.colorpicker.ColorPickerPreference;
import net.margaritov.preference.colorpicker.ColorPickerView;

public class StatusBar extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    public static final String TAG = "StatusBar";
   
    
    private static final String PREF_STATUSBAR_BRIGHTNESS = "statusbar_brightness_slider";
    private static final String STATUS_BAR_DONOTDISTURB = "status_bar_donotdisturb";
   
 	
      
   
    CheckBoxPreference mStatusbarSliderPreference;
    
    private CheckBoxPreference mStatusBarDoNotDisturb;
    
    
    private Activity mActivity;

    private int seekbarProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();

        addPreferencesFromResource(R.xml.prefs_statusbar_add);

        PreferenceScreen prefs = getPreferenceScreen();

        mStatusBarDoNotDisturb = (CheckBoxPreference) findPreference(STATUS_BAR_DONOTDISTURB);
        mStatusBarDoNotDisturb.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                 Settings.System.STATUS_BAR_DONOTDISTURB, 0) == 1));
       
        
        mStatusbarSliderPreference = (CheckBoxPreference) findPreference(PREF_STATUSBAR_BRIGHTNESS);
        mStatusbarSliderPreference.setChecked(Settings.System.getBoolean(mContext.getContentResolver(),
                 Settings.System.STATUSBAR_BRIGHTNESS_SLIDER, true));
      
                
        }

     
        
     @Override
     public void onResume() {
         super.onResume();
     }
 	
   
 

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
            Preference preference) {

       if (preference == mStatusbarSliderPreference) {
           Settings.System.putBoolean(getActivity().getContentResolver(),
                    Settings.System.STATUSBAR_BRIGHTNESS_SLIDER,
                    isCheckBoxPreferenceChecked(preference));
            return true;
       } else if (preference == mStatusBarDoNotDisturb) {
           Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.STATUS_BAR_DONOTDISTURB,
                    mStatusBarDoNotDisturb.isChecked() ? 1 : 0);
            return true;   
       
    }
         return super.onPreferenceTreeClick(preferenceScreen, preference);

     }

     

          
         @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        
         
         return true;
           }
    
}

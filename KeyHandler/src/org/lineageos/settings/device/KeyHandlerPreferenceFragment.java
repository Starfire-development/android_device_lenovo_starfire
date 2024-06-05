/*
 * Copyright (C) 2024 The LineageOS Project
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

package org.lineageos.settings.device;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.preference.PreferenceManager;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.Preference;
import android.support.v14.preference.SwitchPreference;
import android.widget.EditText;
import android.view.KeyEvent;


public class KeyHandlerPreferenceFragment extends PreferenceFragment {
    private static final String TAG = KeyHandler.class.getSimpleName();

    private static final String KEY_MIC_ON = "MIC_ON_enable";
    private static final String KEY_MIC_OFF = "MIC_OFF_enable";
    private static final String KEY_CAM_ON = "CAM_ON_enable";
    private static final String KEY_CAM_OFF = "CAM_OFF_enable";

    private SwitchPreference mSwitchKeycodeMicOnEnabled;
    private SwitchPreference mSwitchKeycodeMicOffEnabled;
    private SwitchPreference mSwitchKeycodeCamOnEnabled;
    private SwitchPreference mSwitchKeycodeCamOffEnabled;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.switch_panel);

        mSwitchKeycodeMicOnEnabled = (SwitchPreference) findPreference(KEY_MIC_ON);
        mSwitchKeycodeMicOnEnabled.setChecked(SettingsUtils.getSwitchKeycodeMicOnEnabled(getActivity()));
        mSwitchKeycodeMicOnEnabled.setOnPreferenceChangeListener(mSwitchKeycodePrefListener);

        mSwitchKeycodeMicOffEnabled = (SwitchPreference) findPreference(KEY_MIC_OFF);
        mSwitchKeycodeMicOffEnabled.setChecked(SettingsUtils.getSwitchKeycodeMicOffEnabled(getActivity()));
        mSwitchKeycodeMicOffEnabled.setOnPreferenceChangeListener(mSwitchKeycodePrefListener);

        mSwitchKeycodeCamOnEnabled = (SwitchPreference) findPreference(KEY_CAM_ON);
        mSwitchKeycodeCamOnEnabled.setChecked(SettingsUtils.getSwitchKeycodeCamOnEnabled(getActivity()));
        mSwitchKeycodeCamOnEnabled.setOnPreferenceChangeListener(mSwitchKeycodePrefListener);

        mSwitchKeycodeCamOffEnabled = (SwitchPreference) findPreference(KEY_CAM_OFF);
        mSwitchKeycodeCamOffEnabled.setChecked(SettingsUtils.getSwitchKeycodeCamOffEnabled(getActivity()));
        mSwitchKeycodeCamOffEnabled.setOnPreferenceChangeListener(mSwitchKeycodePrefListener);
    }

    @Override
    public void onResume() {
        super.onResume();
        getListView().setPadding(0, 0, 0, 0);
    }

    private Preference.OnPreferenceChangeListener mSwitchKeycodePrefListener =
        new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            final String key = preference.getKey();
            boolean enabled = (boolean) value;
            if (KEY_MIC_ON.equals(key)) {
                SettingsUtils.setSwitchKeycodeMicOnEnabled(getActivity(), enabled);
                SettingsUtils.writeMicPowerOnSysfs(enabled);
            } else if (KEY_MIC_OFF.equals(key)) {
                SettingsUtils.setSwitchKeycodeMicOffEnabled(getActivity(), enabled);
                SettingsUtils.writeMicPowerOffSysfs(enabled);
            } else if (KEY_CAM_ON.equals(key)) {
                SettingsUtils.setSwitchKeycodeCamOnEnabled(getActivity(), enabled);
                SettingsUtils.writeCamPowerOnSysfs(enabled);
            } else if (KEY_CAM_OFF.equals(key)) {
                SettingsUtils.setSwitchKeycodeCamOffEnabled(getActivity(), enabled);
                SettingsUtils.writeCamPowerOffSysfs(enabled);
            }
            return true;
        }
    };
}
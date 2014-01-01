/*
 * Copyright (C) 2010-2011 Felix Bechstein
 * 
 * This file is part of WebSMS.
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program; If not, see <http://www.gnu.org/licenses/>.
 */
package de.ub0r.android.websms;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import de.ub0r.android.lib.IPreferenceContainer;

/**
 * {@link PreferenceFragment} for API>=11.
 * 
 * @author flx
 */
public final class HeaderPreferenceFragment extends PreferenceFragment
		implements IPreferenceContainer {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Activity a = this.getActivity();
		int res = a.getResources().getIdentifier(
				this.getArguments().getString("resource"), "xml",
				a.getPackageName());
		this.addPreferencesFromResource(res);

		PreferencesActivity.registerPreferenceChecker(this);
		PreferencesActivity.addConnectorPreferences(this);
		PreferencesActivity.registerOnPreferenceChangeListener(this);

        // MDS - Override summary for drop_sent preference on Kitkat or later to
        // indicate that App Ops is needed to set WRITE_SMS permission

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Preference pref = this.findPreference("drop_sent");
            if (pref != null) {
                pref.setSummary(this.getString(R.string.drop_sent_hint_kitkat));
            }
        }

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Context getContext() {
		return this.getActivity();
	}
}

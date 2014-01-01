/*
 * Copyright (C) 2010-2012 Felix Bechstein
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

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.view.MenuItem;

import de.ub0r.android.websms.connector.common.Log;

/**
 * Preferences.
 * 
 * @author flx
 */
public class PreferencesBehaviorActivity extends PreferenceActivity {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.addPreferencesFromResource(R.xml.prefs_behavior);
		this.setTitle(this.getString(R.string.settings) + " > "
				+ this.getString(R.string.behavior_));

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
			// removing diacritics is not supported in API8-
			PreferenceGroup pg = (PreferenceGroup) this
					.findPreference("container");
			pg.removePreference(this.findPreference("remove_diacritics"));
		}

        // MDS - Remove Kitkat check as this was the wrong place anyway
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean onOptionsItemSelected(final MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in Action Bar clicked; go home
			Intent intent = new Intent(this, WebSMS.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			this.startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}

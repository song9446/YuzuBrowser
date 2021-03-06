/*
 * Copyright (C) 2017 Hazuki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.hazuki.yuzubrowser.legacy.adblock.fragment

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import jp.hazuki.yuzubrowser.legacy.R
import jp.hazuki.yuzubrowser.legacy.settings.activity.YuzuPreferenceFragment

class AdBlockMainFragment : YuzuPreferenceFragment() {

    private var listener: OnAdBlockMainListener? = null

    override fun onCreateYuzuPreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setHasOptionsMenu(true)
        addPreferencesFromResource(R.xml.pref_ad_block)

        findPreference("black_list").setOnPreferenceClickListener {
            listener!!.openBlackList()
            false
        }

        findPreference("white_list").setOnPreferenceClickListener {
            listener!!.openWhiteList()
            false
        }

        findPreference("white_page_list").setOnPreferenceClickListener {
            listener!!.openWhitePageList()
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = activity as OnAdBlockMainListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnAdBlockMainListener {
        fun openBlackList()

        fun openWhiteList()

        fun openWhitePageList()
    }
}
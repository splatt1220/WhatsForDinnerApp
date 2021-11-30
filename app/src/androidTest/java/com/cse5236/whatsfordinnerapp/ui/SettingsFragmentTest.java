package com.cse5236.whatsfordinnerapp.ui;

import android.app.Activity;
import android.view.View;
import androidx.constraintlayout.utils.widget.MockView;
import androidx.fragment.app.testing.FragmentScenario;
import com.cse5236.whatsfordinnerapp.R;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SettingsFragmentTest {
    private FragmentScenario<SettingsFragment> fragmentScenario;

    @Before
    public void setUp()  {
        fragmentScenario = FragmentScenario.launchInContainer(SettingsFragment.class, null, R.style.Theme_Design_NoActionBar);
    }

    @Test
    // test if the user successfully logs out after clicking log out button
    public void testLogoutOnClick() {
        fragmentScenario.onFragment(fragment -> {
            Activity activity = fragment.requireActivity();
            View view = new MockView(activity);
            view.setId(R.id.btn_settings_log_out);
            fragment.onClick(view);

            Assert.assertTrue(activity.isFinishing());
            Assert.assertNull(fragment.getAuth().getCurrentUser());
        });
    }
}
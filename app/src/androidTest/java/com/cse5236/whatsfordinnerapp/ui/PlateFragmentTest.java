package com.cse5236.whatsfordinnerapp.ui;

import androidx.fragment.app.testing.FragmentScenario;

import com.cse5236.whatsfordinnerapp.R;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlateFragmentTest {

    private FragmentScenario<PlateFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(PlateFragment.class, null, R.style.Theme_Design_NoActionBar);
    }

    @Test
    public void testValidShaking() {
        fragmentScenario.onFragment(fragment -> {
            float[] values = {100, 100, 100};
            Assert.assertTrue(fragment.sensorChanged(values));
        });
    }

    @Test
    public void testInvalidShaking() {
        fragmentScenario.onFragment(fragment -> {
            float[] values = {1, 1, 1};
            Assert.assertFalse(fragment.sensorChanged(values));
        });
    }
}
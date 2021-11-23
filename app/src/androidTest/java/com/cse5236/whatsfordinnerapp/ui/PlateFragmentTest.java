package com.cse5236.whatsfordinnerapp.ui;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

import androidx.fragment.app.testing.FragmentScenario;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class PlateFragmentTest extends TestCase {

    private FragmentScenario<PlateFragment> fragmentScenario;

    @Before
    public void setUp() throws Exception {
        fragmentScenario = FragmentScenario.launchInContainer(PlateFragment.class);
    }

    @Test
    public void testValidShaking() {
        fragmentScenario.onFragment(fragment -> {
            float[] values = {100, 100, 100};
            assertTrue(fragment.sensorChanged(values));
        });
    }

    @Test
    public void testInvalidShaking() {
        fragmentScenario.onFragment(fragment -> {
            float[] values = {1, 1, 1};
            assertFalse(fragment.sensorChanged(values));
        });
    }
}
package com.cse5236.whatsfordinnerapp.ui;

import androidx.fragment.app.testing.FragmentScenario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ForgetPasswordFragmentTest {

    private FragmentScenario<ForgetPasswordFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(ForgetPasswordFragment.class);
    }

    @Test
    // test with an empty email
    public void testEmptyEmail() {
        fragmentScenario.onFragment(fragment -> {
            String email = "";
            Assert.assertFalse(fragment.forgetPassword(email));
        });
    }

    @Test
    // test with a valid email
    public void testValid() {
        fragmentScenario.onFragment(fragment -> {
            String email = "t@1.com";
            Assert.assertTrue(fragment.forgetPassword(email));
        });
    }
}
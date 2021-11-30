package com.cse5236.whatsfordinnerapp.ui;

import androidx.fragment.app.testing.FragmentScenario;

import com.cse5236.whatsfordinnerapp.R;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChangePasswordFragmentTest {
    private FragmentScenario<ChangePasswordFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(ChangePasswordFragment.class, null, R.style.Theme_Design_NoActionBar);
    }

    @Test
    // test with an empty old password
    public void testEmptyOld() {
        fragmentScenario.onFragment(fragment -> {
            String oldPwd = "";
            String newPwd = "123456";
            String confirmPwd = "123456";
            Assert.assertFalse(fragment.changePassword(oldPwd, newPwd, confirmPwd));
        });
    }

    @Test
    // test with an empty new password
    public void testEmptyNew() {
        fragmentScenario.onFragment(fragment -> {
            String oldPwd = "123456";
            String newPwd = "";
            String confirmPwd = "123456";
            Assert.assertFalse(fragment.changePassword(oldPwd, newPwd, confirmPwd));
        });
    }

    @Test
    // test with an empty confirm password
    public void testEmptyConfirm() {
        fragmentScenario.onFragment(fragment -> {
            String oldPwd = "123456";
            String newPwd = "123456";
            String confirmPwd = "";
            Assert.assertFalse(fragment.changePassword(oldPwd, newPwd, confirmPwd));
        });
    }

    @Test
    // test with unmatched new and confirm password
    public void testUnmatched() {
        fragmentScenario.onFragment(fragment -> {
            String oldPwd = "123456";
            String newPwd = "111111";
            String confirmPwd = "123456";
            Assert.assertFalse(fragment.changePassword(oldPwd, newPwd, confirmPwd));
        });
    }

    @Test
    // test with all valid
    public void testValid() {
        fragmentScenario.onFragment(fragment -> {
            String oldPwd = "123456";
            String newPwd = "123456";
            String confirmPwd = "123456";
            Assert.assertTrue(fragment.changePassword(oldPwd, newPwd, confirmPwd));
        });
    }

}
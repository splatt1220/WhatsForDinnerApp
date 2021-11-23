package com.cse5236.whatsfordinnerapp.ui;

import androidx.fragment.app.testing.FragmentScenario;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthFragmentTest {
    private FragmentScenario<AuthFragment> fragmentScenario;

    @Before
    public void setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(AuthFragment.class);
    }

    @Test
    // test with an empty email
    public void testEmptyEmail() {
        fragmentScenario.onFragment(fragment -> {
            String email = "";
            String pwd = "123456";
            Assert.assertFalse(fragment.userLogin(email, pwd));
        });
    }

    @Test
    // test with an invalid email
    public void testInvalidEmail() {
        fragmentScenario.onFragment(fragment -> {
            String email = "1";
            String pwd = "123456";
            Assert.assertFalse(fragment.userLogin(email, pwd));
        });
    }

    @Test
    // test with an empty password
    public void testEmptyPwd() {
        fragmentScenario.onFragment(fragment -> {
            String email = "t@1.com";
            String pwd = "";
            Assert.assertFalse(fragment.userLogin(email, pwd));
        });
    }

    @Test
    // test with an invalid password
    public void testInvalidPwd() {
        fragmentScenario.onFragment(fragment -> {
            String email = "t@1.com";
            String pwd = "12345";
            Assert.assertFalse(fragment.userLogin(email, pwd));
        });
    }

    @Test
    // test with valid email and password
    public void testValid() {
        fragmentScenario.onFragment(fragment -> {
            String email = "t@1.com";
            String pwd = "123456";
            Assert.assertTrue(fragment.userLogin(email, pwd));
        });
    }
}
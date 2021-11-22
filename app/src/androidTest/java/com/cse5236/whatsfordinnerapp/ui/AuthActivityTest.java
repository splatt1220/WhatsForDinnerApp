package com.cse5236.whatsfordinnerapp.ui;

import androidx.fragment.app.testing.FragmentScenario;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class AuthActivityTest extends TestCase {
    private FragmentScenario<AuthFragment> fragmentScenario;

    @Before
    public void setUp() throws Exception {
        fragmentScenario = FragmentScenario.launchInContainer(AuthFragment.class);
    }

    @Test
    // test with an empty email
    public void testEmptyEmail() {
        fragmentScenario.onFragment(fragment -> {
            String email = "";
            String pwd = "123456";
            assertFalse(fragment.userLogin(email, pwd));
        });
    }

    @Test
    // test with an invalid email
    public void testInvalidEmail() {
        fragmentScenario.onFragment(fragment -> {
            String email = "1";
            String pwd = "123456";
            assertFalse(fragment.userLogin(email, pwd));
        });
    }

    @Test
    // test with an empty password
    public void testEmptyPwd() {
        fragmentScenario.onFragment(fragment -> {
            String email = "t@1.com";
            String pwd = "";
            assertFalse(fragment.userLogin(email, pwd));
        });
    }

    @Test
    // test with an invalid password
    public void testInvalidPwd() {
        fragmentScenario.onFragment(fragment -> {
            String email = "t@1.com";
            String pwd = "12345";
            assertFalse(fragment.userLogin(email, pwd));
        });
    }

    @Test
    // test with valid email and password
    public void testValid() {
        fragmentScenario.onFragment(fragment -> {
            String email = "t@1.com";
            String pwd = "123456";
            assertTrue(fragment.userLogin(email, pwd));
        });
    }
}
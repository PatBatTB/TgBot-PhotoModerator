package com.github.patbattb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class VersionCheckTest {
    @Test
    @Tag("VersionCheck")
    @SuppressWarnings("all")
    void check() {
        Assertions.assertNotEquals(System.getProperty("latestVersion"), System.getProperty("currentVersion"));
    }
}

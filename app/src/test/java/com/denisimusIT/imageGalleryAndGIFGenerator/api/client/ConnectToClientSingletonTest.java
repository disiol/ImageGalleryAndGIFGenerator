package com.denisimusIT.imageGalleryAndGIFGenerator.api.client;

import com.denisimusIT.imageGalleryAndGIFGenerator.api.ConnectToClientSingleton;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConnectToClientSingletonTest {


    private ConnectToClientSingleton singleton1 = ConnectToClientSingleton.getInstance();
    private ConnectToClientSingleton singleton2 = ConnectToClientSingleton.getInstance();
    private ConnectToClientSingleton singleton3 = ConnectToClientSingleton.getInstance();


    @Test
    public void Only_one_copy_was_created_Test() {
        assertTrue("Only one copy was created", singleton1 == singleton2 && singleton2 == singleton3);
    }
}

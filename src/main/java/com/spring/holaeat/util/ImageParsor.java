package com.spring.holaeat.util;

import java.beans.Transient;
import java.util.Base64;

public class ImageParsor {
    @Transient
    public static String parseBlobToBase64(byte[] blob) {
        if (blob != null) {
            return Base64.getEncoder().encodeToString(blob);
        }
        return null;
    }
}



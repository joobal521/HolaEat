package com.spring.holaeat.util;

import java.beans.Transient;
import java.util.Base64;

public class ImageParsor {

    @Transient
    public static String parseBlobToBase64(byte[] blob) {
        String base64Image = Base64.getEncoder().encodeToString(blob);
        return base64Image;
    }
}

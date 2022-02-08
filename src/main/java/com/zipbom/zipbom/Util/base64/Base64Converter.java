package com.zipbom.zipbom.Util.base64;

import java.util.Objects;
import java.util.Base64;

public class Base64Converter {
    public static byte[] encodeToBase64(byte[] source) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = null;

        try {
            encodedBytes = encoder.encode(source);
        } catch (Exception e) {
            return null;
        }

        return encodedBytes;
    }

    public static byte[] decodeFromBase64(byte[] source) {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedBytes = null;

        try {
            decodedBytes = decoder.decode(source);
        } catch (Exception e) {
            return null;
        }

        return decodedBytes;
    }

    public static String getEncodedStringFromBlob(byte[] source) {
        return new String(Objects.requireNonNull(encodeToBase64(source)));
    }

    public static byte[] getDecodedBase64FromString(String source) {
        return decodeFromBase64(source.getBytes());
    }
}
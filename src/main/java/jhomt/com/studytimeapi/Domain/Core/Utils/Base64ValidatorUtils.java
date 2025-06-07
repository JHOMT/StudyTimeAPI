package jhomt.com.studytimeapi.Domain.Core.Utils;

import jhomt.com.studytimeapi.Domain.UnitMaterial.MaterialType;

import java.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Base64ValidatorUtils {

    public static boolean isValidBase64ForType(String base64String, MaterialType materialType) {
        if (base64String == null || base64String.isBlank()) {
            return false;
        }

        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodedBytes)) {
            return validateFileSignature(byteArrayInputStream, materialType);
        } catch (IOException e) {
            return false;
        }
    }

    private static boolean validateFileSignature(ByteArrayInputStream byteArrayInputStream, MaterialType materialType) throws IOException {
        byte[] signature = new byte[8];
        byteArrayInputStream.read(signature);

        return switch (materialType) {
            case VIDEO -> isVideo(signature);
            case AUDIO -> isAudio(signature);
            case IMAGE -> isImage(signature);
            case DOC -> isDocument(signature);
        };
    }

    private static boolean isVideo(byte[] signature) {
        return signature[0] == 0x66 && signature[1] == 0x74 && signature[2] == 0x79 && signature[3] == 0x70;
    }

    private static boolean isAudio(byte[] signature) {
        return signature[0] == (byte) 0x49 && signature[1] == (byte) 0x44 && signature[2] == (byte) 0x33;
    }

    private static boolean isImage(byte[] signature) {
        return signature[0] == (byte) 0x89 && signature[1] == (byte) 0x50 && signature[2] == (byte) 0x4E && signature[3] == (byte) 0x47; // PNG signature
    }

    private static boolean isDocument(byte[] signature) {
        return (signature[0] == (byte) 0x25 && signature[1] == (byte) 0x50 && signature[2] == (byte) 0x44 && signature[3] == (byte) 0x46) ||  // "%PDF"
                (signature[0] == (byte) 0xD0 && signature[1] == (byte) 0xCF && signature[2] == (byte) 0x11 && signature[3] == (byte) 0xE0); // DOC signature
    }
}
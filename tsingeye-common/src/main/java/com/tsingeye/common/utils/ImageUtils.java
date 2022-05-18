package com.tsingeye.common.utils;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * Base64转MultipartFIle
 * @author 姜风
 */
public class ImageUtils {
    public static MultipartFile base64ToMultipartFile(String base64) {
        try {
            String[] baseStrs = base64.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b;
            b = decoder.decodeBuffer(baseStrs[0]);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64DecodedMultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
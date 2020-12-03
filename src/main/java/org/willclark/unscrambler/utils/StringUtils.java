package org.willclark.unscrambler.utils;

import java.util.UUID;

public class StringUtils {

    public static String generateUniqueId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
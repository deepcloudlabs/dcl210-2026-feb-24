package com.example.util;

public final class MaskUtil {
    private MaskUtil() {}

    public static final int FLAG_PAID = 80;
    public static final int FLAG_NEW = 64;
    public static final int FLAG_CANCELLED = 32;

    public static final int ONE_MILLION = 1000000;

    public static boolean isSet(int flags, int flag) {
        return (flags & flag) == flag;
    }
}

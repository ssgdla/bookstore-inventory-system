package com.zheye.assignment.util;

import com.zheye.assignment.constant.BusinessException;

public class PreconditionUtil {
    public static void checkArgument(boolean expression, BusinessException e) {
        if (!expression) {
            throw e;
        }
    }
}

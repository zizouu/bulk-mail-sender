package com.zizou.bulkmail.util;

import org.apache.commons.lang3.StringUtils;

import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;

/**
 * Created by zizou on 2017-08-17.
 */
public class EncodingTextUtil{
    public static String encodeText(String text, String charset, String encoding) throws UnsupportedEncodingException{
        return MimeUtility.encodeWord(StringUtils.defaultString(text, ""), StringUtils.defaultString(charset, "UTF-8"), StringUtils.defaultString(encoding, "B"));
    }
}

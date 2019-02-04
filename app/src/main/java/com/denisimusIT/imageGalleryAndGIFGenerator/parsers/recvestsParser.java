package com.denisimusIT.imageGalleryAndGIFGenerator.parsers;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class recvestsParser {
    public static RequestBody parseStringIntoRequestBody(String string) {
        return RequestBody.create(MediaType.parse("text/plain"), string);
    }
}

package com.mgm_cen_57.coder.mgmcseuploads;

public class uploadData {

    public uploadData(String url, String extension) {
        this.url = url;
        this.extension = extension;
    }

    public String getUrl() {
        return url;
    }

    public String getExtension() {
        return extension;
    }

    String url;
    String extension;
}

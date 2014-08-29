package com.vomtung.util;

public class Utils {
	public static String getFolderStoreImage(String webContentRoot) {
		if (webContentRoot.endsWith("\\")){
    		webContentRoot = webContentRoot.substring(0,webContentRoot.length() - 1);
    		}
        String folder = webContentRoot.substring(0, webContentRoot.lastIndexOf("\\") + 1) + "upload\\";
        return folder;
	}
}

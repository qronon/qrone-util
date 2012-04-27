package org.qrone.util;

import java.net.URI;
import java.net.URISyntaxException;

public class Net {

    public static URI relativize(URI basePath, URI targetPathString) {
    	String uri = relativize(basePath.toString(), targetPathString.toString());
    	try {
			return new URI(uri);
		} catch (URISyntaxException e) {
			return targetPathString;
		}
    }
    
    public static String relativize(String basePath, String targetPathString) {
    	if(targetPathString.startsWith("/")){
    		return targetPathString;
    	}
    	
        // We modify targetPath to become the result.
		StringBuilder targetPath = new StringBuilder(targetPathString);
	
		// Find the longest common initial sequence of path elements.
		int length = Math.min(basePath.length(), targetPath.length());
		int diff = 0;
		for (int i = 0; i < length; i++) {
		    char c = basePath.charAt(i);
		    if (c != targetPath.charAt(i))
			break;
		    if (c == '/')
			diff = i + 1;
		}
	
		// Remove the common initial elements from the target, including
		// their trailing slashes.
		targetPath.delete(0, diff);
	
		
		
		// Count remaining complete path elements in the base,
		// prefixing the target with "../" for each one.
		for (int slash = basePath.indexOf('/', diff); slash > -1;
		     slash = basePath.indexOf('/', slash + 1))
		    targetPath.insert(0, "../");
	
		// Make sure the result is not empty.
		if (targetPath.length() == 0)
		    targetPath.append("./");

        return targetPath.toString();
    }
}

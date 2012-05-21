package org.qrone.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class QueryString {

	private Map<String, List<String>> parameters;

	public QueryString() {
		parameters = new TreeMap<String, List<String>>();
	}
	
	public QueryString(String qs) {
		parameters = new TreeMap<String, List<String>>();
		if(qs == null) return;

		// Parse query string
		String pairs[] = qs.split("&");
		for (String pair : pairs) {
			String name;
			String value;
			int pos = pair.indexOf('=');
			// for "n=", the value is "", for "n", the value is null
			if (pos == -1) {
				name = pair;
				value = null;
			} else {
				try {
					name = URLDecoder.decode(pair.substring(0, pos), "UTF-8");
					value = URLDecoder.decode(
							pair.substring(pos + 1, pair.length()), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// Not really possible, throw unchecked
					throw new IllegalStateException("No UTF-8");
				}
			}
			List<String> list = parameters.get(name);
			if (list == null) {
				list = new ArrayList<String>();
				parameters.put(name, list);
			}
			list.add(value);
		}
	}
	
	public String get(String name){
		return getParameter(name);
	}

	public String getParameter(String name) {
		List<String> values = parameters.get(name);
		if (values == null)
			return null;

		if (values.size() == 0)
			return "";

		return values.get(0);
	}

	public List<String> getParameterValues(String name) {
		return parameters.get(name);
	}

	public Set<String> getParameterNames() {
		return parameters.keySet();
	}

	public Map<String, List<String>> getParameterMap() {
		return parameters;
	}
	
	public Map<String, Object> getParameterMapSingle() {
		Map<String, Object> map = new HashMap<String, Object>();
		for (Map.Entry<String, List<String>> entry : parameters.entrySet()) {
			if(entry.getValue().size() == 1){
				map.put(entry.getKey(), entry.getValue().get(0));
			}else if(entry.getValue().size() > 1){
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}
	
	public void add(String name, String value){
		List<String> values = parameters.get(name);
		if(values == null){
			values = new ArrayList<String>();
			parameters.put(name, values);
		}
		values.add(value);
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		for (Iterator<Map.Entry<String, List<String>>> iter = parameters.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, List<String>> entry = iter.next();
			List<String> l = entry.getValue();
			
			try{
				for (String string : l) {
					s.append("&");
					s.append(URLEncoder.encode(entry.getKey(),"utf8"));
					s.append("=");
					s.append(URLEncoder.encode(string,"utf8"));
				}
			}catch(UnsupportedEncodingException ex){}
		}
		if(s.length() > 0)
			return s.substring(1).toString();
		return "";
	}
	
	
	
}
package org.qrone.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class Stream {

	public static byte[] read(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		copy(in, out);
		in.close();
		out.close();
		return out.toByteArray();
	}
	
	public static String read(Reader r) throws IOException {
		StringWriter w = new StringWriter();
		copy(r, w);
		r.close();
		w.close();
		return w.toString();
	}

	public static void copy(Reader r, Writer w) throws IOException {
		if(r == null || w == null) throw new IOException();
		int buf;
		while ((buf = r.read()) != -1) {
			w.write(buf);
		}
	}
	
	public static void copy(InputStream in, OutputStream out) throws IOException {
		if(in == null || out == null) throw new IOException();
		int buf;
		while ((buf = in.read()) != -1) {
			out.write(buf);
		}
	}
}

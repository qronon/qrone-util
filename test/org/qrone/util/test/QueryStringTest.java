package org.qrone.util.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.qrone.util.QueryString;

public class QueryStringTest {

	@Test
	public void queryTest() {
		QueryString qs = new QueryString();
		assertEquals(qs.toString(), "");
		
		qs.add("test1", "test2");
		assertEquals("test1=test2", qs.toString());
	}

	@Test
	public void queryTest2() {
		QueryString qs = new QueryString();
		assertEquals(qs.toString(), "");
		
		qs.add("test &1", "test &2");
		assertEquals("test+%261=test+%262", qs.toString());
	}
	

	@Test
	public void queryParseTest() {
		QueryString qs = new QueryString("test1=test2&test3=test4");
		
		assertEquals("test2", qs.get("test1"));
	}

}

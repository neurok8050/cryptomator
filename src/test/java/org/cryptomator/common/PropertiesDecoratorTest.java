/*
Test file added by Claudéric DeRoy 08/10/2024
*/

package org.cryptomator.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;

@DisplayName("Properties decorator Test")
public class PropertiesDecoratorTest {

	// "global" variable used for the tests
	private Properties prop;
	private PropertiesDecorator propDec;


	// variable needed to be initialized before each test
	@BeforeEach
	public void init(){
		prop = new Properties();
		propDec = new PropertiesDecorator(prop);
	}

	// Test added by Claudéric 08/10/2024
	@Test
	public void setPropertyTest(){
		// arrange
		String key = "this is my key to set"; // first key
		String value = "this my value to set"; // first value
		String key_two = "this is my new key to set"; // second key
		String value_two = "this is my new key to set"; // second value

		// act
		Object actual = propDec.setProperty(key, value);
		propDec.setProperty(key_two, value_two);

		// assert
		Assertions.assertNull(actual); // because it is the first set should equal null
		Assertions.assertEquals(propDec.getProperty(key_two), key_two); // test the key should equal key_two
		Assertions.assertEquals(propDec.getProperty(key_two, value_two), value_two); // test the value should equal value_two
	}

	// Test added by Claudéric 08/10/2024
	@Test
	public void replaceTest(){
		// arrange
		String key = "first key";
		String value = "first value";
		propDec.setProperty(key, value);

		// act
		Boolean actual = propDec.replace(key, value, "second key");
		Boolean actual_2 = propDec.replace(key, "random key", "third key");
		Object second_key = propDec.replace(key, "third key");

		// assert
		Assertions.assertTrue(actual);
		Assertions.assertFalse(actual_2);
		Assertions.assertEquals(propDec.getProperty(key, "third key"), "third key");

	}
}

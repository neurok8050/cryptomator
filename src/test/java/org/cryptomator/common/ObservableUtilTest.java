package org.cryptomator.common;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObservableUtilTest {

	private SimpleObjectProperty<String> observable;

	@BeforeEach
	public void setUp() {
		observable = new SimpleObjectProperty<>();
	}

	@Test
	@DisplayName("Test mapWithDefault with non-null value")
	public void testMapWithDefaultNonNull() {
		observable.set("test");
		ObservableValue<Integer> mapped = ObservableUtil.mapWithDefault(observable, String::length, 0);
		assertEquals(4, mapped.getValue());
	}

	@Test
	@DisplayName("Test mapWithDefault with null value")
	public void testMapWithDefaultNull() {
		observable.set(null);
		ObservableValue<Integer> mapped = ObservableUtil.mapWithDefault(observable, String::length, 0);
		assertEquals(0, mapped.getValue());
	}

	@Test
	@DisplayName("Test mapWithDefault with Supplier and non-null value")
	public void testMapWithDefaultSupplierNonNull() {
		observable.set("test");
		ObservableValue<Integer> mapped = ObservableUtil.mapWithDefault(observable, String::length, () -> 0);
		assertEquals(4, mapped.getValue());
	}

	@Test
	@DisplayName("Test mapWithDefault with Supplier and null value")
	public void testMapWithDefaultSupplierNull() {
		observable.set(null);
		ObservableValue<Integer> mapped = ObservableUtil.mapWithDefault(observable, String::length, () -> 0);
		assertEquals(0, mapped.getValue());
	}

	@Test
	@DisplayName("Test mapWithDefault with Supplier and changing value")
	void testMapWithDefaultSupplierChangingValue() {
		observable.set("initial");
		ObservableValue<Integer> mapped = ObservableUtil.mapWithDefault(observable, String::length, () -> 0);
		assertEquals(7, mapped.getValue());

		observable.set("changed");
		assertEquals(7, mapped.getValue());

		observable.set(null);
		assertEquals(0, mapped.getValue());
	}

}
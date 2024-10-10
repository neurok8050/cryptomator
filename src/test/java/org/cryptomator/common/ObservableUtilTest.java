package org.cryptomator.common;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ObservableUtil Test") // Nom du test pour le rendre plus descriptif
public class ObservableUtilTest {

    private SimpleObjectProperty<String> observable;

    // Initialisation avant chaque test : création d'une instance de SimpleObjectProperty
    @BeforeEach
    public void setUp() {
        observable = new SimpleObjectProperty<>();
    }

    // Test ajouté par Mohammed Aiman Rahmani 08/10/2024
    // Test : Vérifier que mapWithDefault fonctionne correctement avec une valeur non-nulle dans l'observable
    @Test
    @DisplayName("Test mapWithDefault with non-null value")
    public void testMapWithDefaultNonNull() {
        // arrange
        observable.set("test"); // assignation d'une chaîne de caractères non-nulle à l'observable
        
        // act
        ObservableValue<Integer> mapped = ObservableUtil.mapWithDefault(observable, String::length, 0); // mappage de la longueur de la chaîne
        
        // assert
        assertEquals(4, mapped.getValue()); // vérification que la longueur obtenue est correcte
    }

    // Test ajouté par Mohammed Aiman Rahmani 08/10/2024
    // Test : Vérifier que mapWithDefault renvoie la valeur par défaut (0) quand l'observable contient une valeur nulle
    @Test
    @DisplayName("Test mapWithDefault with null value")
    public void testMapWithDefaultNull() {
        // arrange
        observable.set(null); // assignation de null à l'observable
        
        // act
        ObservableValue<Integer> mapped = ObservableUtil.mapWithDefault(observable, String::length, 0); // mappage avec une valeur par défaut de 0
        
        // assert
        assertEquals(0, mapped.getValue()); // vérification que la valeur par défaut est renvoyée
    }

    // Test ajouté par Mohammed Aiman Rahmani 08/10/2024
    // Test : Vérifier que mapWithDefault fonctionne avec un Supplier et une valeur non-nulle dans l'observable
    @Test
    @DisplayName("Test mapWithDefault with Supplier and non-null value")
    public void testMapWithDefaultSupplierNonNull() {
        // arrange
        observable.set("test"); // assignation d'une chaîne de caractères non-nulle à l'observable
        
        // act
        ObservableValue<Integer> mapped = ObservableUtil.mapWithDefault(observable, String::length, () -> 0); // mappage avec un Supplier pour la valeur par défaut
        
        // assert
        assertEquals(4, mapped.getValue()); // vérification que la longueur de la chaîne est correcte
    }

    // Test ajouté par Mohammed Aiman Rahmani 08/10/2024
    // Test : Vérifier que mapWithDefault fonctionne avec un Supplier et une valeur nulle dans l'observable
    @Test
    @DisplayName("Test mapWithDefault with Supplier and null value")
    public void testMapWithDefaultSupplierNull() {
        // arrange
        observable.set(null); // assignation de null à l'observable
        
        // act
        ObservableValue<Integer> mapped = ObservableUtil.mapWithDefault(observable, String::length, () -> 0); // mappage avec un Supplier pour la valeur par défaut
        
        // assert
        assertEquals(0, mapped.getValue()); // vérification que la valeur par défaut est renvoyée
    }

    // Test ajouté par Mohammed Aiman Rahmani 08/10/2024
    // Test : Vérifier que mapWithDefault avec un Supplier fonctionne correctement quand la valeur observable change
    @Test
    @DisplayName("Test mapWithDefault with Supplier and changing value")
    void testMapWithDefaultSupplierChangingValue() {
        // arrange
        observable.set("initial"); // assignation d'une première chaîne de caractères
        
        // act
        ObservableValue<Integer> mapped = ObservableUtil.mapWithDefault(observable, String::length, () -> 0); // mappage de la longueur de la chaîne avec un Supplier
        assertEquals(7, mapped.getValue()); // vérification que la longueur est correcte
        
        // act - changement de valeur
        observable.set("changed"); // modification de la chaîne de caractères
        assertEquals(7, mapped.getValue()); // vérification que la longueur reste correcte après le changement
        
        // act - mise à null
        observable.set(null); // assignation de null à l'observable
        assertEquals(0, mapped.getValue()); // vérification que la valeur par défaut est renvoyée
    }

}

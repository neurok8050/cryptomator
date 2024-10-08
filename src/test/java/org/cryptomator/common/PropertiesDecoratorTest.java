/*
Test file added by Claudéric DeRoy 08/10/2024
*/

package org.cryptomator.common;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.function.BiFunction;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Properties decorator Test")
public class PropertiesDecoratorTest {


	private PropertiesDecorator propDec;

	// variable "globale" utilisé dans chaque test
	@BeforeEach
	public void init(){
		Properties prop = new Properties();
		propDec = new PropertiesDecorator(prop);
	}


	// Test ajouté par Claudéric 08/10/2024
	// But : Est-ce qu'on bien "setter" les paires de clés/valeurs à notre
	//		 variable "propDec". En maintenant on test aussi si la méthode
	//		 "getProperty" fonctionne aussi.
	@Test
	public void setPropertyTest(){
		// arrange
		String key = "this is my key to set";
		String value = "this my value to set"; 
		String key_two = "this is my new key to set"; 
		String value_two = "this is my new key to set";

		// act
		Object actual = propDec.setProperty(key, value);
		propDec.setProperty(key_two, value_two);

		// assert
		Assertions.assertNull(actual);
		Assertions.assertEquals(propDec.getProperty(key_two), key_two);
		Assertions.assertEquals(propDec.getProperty(key_two, value_two), value_two);
	}

	// Test ajouté par Claudéric 08/10/2024
	// But : Est-ce qu'on bien remplacer les valeurs des clés déjà instanciées
	//		 de notre variable de la classe PropertiesDecorator. On test les
	//		 deux signatures de la fonction "replace()".
	@Test
	public void replaceTest(){
		// arrange
		String key = "first key";
		String value = "first value";
		propDec.setProperty(key, value);

		// act
		boolean actual = propDec.replace(key, value, "second key");
		boolean actual_2 = propDec.replace(key, "random key", "third key");
		Object second_key = propDec.replace(key, "third key");

		// assert
		Assertions.assertTrue(actual);
		Assertions.assertFalse(actual_2);
		Assertions.assertEquals(String.valueOf(second_key), "second key");

	}

	// Test ajouté par Claudéric 08/10/2024
	// But : Est-ce qu'on peut bien retirer les éléments d'une variable de la classe
	//		 PropertiesDecorator. Ici aussi on test les deux signatures de la fonction
	//		 "remove()".
	@Test
	public void removeTest(){
		// arrange
		String key = "first key";
		String value = "first value";
		String key_two = "second key";
		String value_two = "second value";
		propDec.setProperty(key, value);
		propDec.setProperty(key_two, value_two);

		// act
		boolean actual_true = propDec.remove(key, value); // remove the first key should remove and return True
		boolean actual_false = propDec.remove(key, value); // remove again the first should not remove and return False
		Object second_key = propDec.remove(key_two);

		// assert
		Assertions.assertTrue(actual_true);
		Assertions.assertFalse(actual_false);
		Assertions.assertEquals(String.valueOf(second_key), value_two);
	}

	// BiFunction qui sera appliqué par "replaceAll()" ajouté par Claudéric 08/10/2024
	public BiFunction<? super Object, ? super Object, ?> funkyFiedFunction = (key, val) ->
			this.propDec.getProperty(key.toString(), val.toString())+"_funkyfiedEntry";

	// Test ajouté par Claudéric 08/10/2024
	// But : Est-ce que la fonction "replaceAll()" applique la fonction qui lui passe
	// 		 à notre variable de type PropertiesDecorator. On utilise aussi en même
	//		 les fonctions "clone()" et "keySet()"
	@Test
	public void replaceAllTest(){
		// arrange
		for(int i = 0; i < 10; i++){
			String key = "key"+i;
			String value = "value"+i;
			propDec.setProperty(key, value);
		}
		propDec.replaceAll(funkyFiedFunction); // applying the the funkyFied function to propDec

		// act
		Properties prop_two = new Properties();
		PropertiesDecorator propDec_two = new PropertiesDecorator(prop_two);

		PropertiesDecorator propDec_three = (PropertiesDecorator) propDec.clone();


		for(int i = 0; i < 10; i++){
			String key = "key"+i;
			String value = "value"+i+"_funkyfiedEntry";
			propDec_two.setProperty(key, value); // setting the actual funkyfied values of propDec_two
		}

		// assert
		for(Object keys: propDec.keySet()){
			Assertions.assertEquals(propDec.getProperty(keys.toString()), propDec_two.getProperty(keys.toString()));
			Assertions.assertEquals(propDec_three.getProperty(keys.toString()), propDec_two.getProperty(keys.toString()));
		}
	}

	// Test ajouté par Claudéric 08/10/2024
	// But : Est-ce que la fonction "storeToXML()" permet de bien storer les clés et
	//		 valeurs contenu dans une variable de la classe PropertiesDecorator. On
	//		 utilise aussi la fonction "loadFromXML()" pour lire un fichier .xml
	//		 et créer une instance de la classe PropertiesDecorator.
	@Test
	public void storeToXMLTest() throws IOException {
		// arrange
		for(int i = 0; i < 10; i++){
			String key = "key"+i;
			String value = "value"+i;
			propDec.setProperty(key, value);
		}

		OutputStream out = new FileOutputStream("./target/surefire-reports/propDec.txt");
		propDec.storeToXML(out, propDec.toString());

		// act
		InputStream in = new FileInputStream("./target/surefire-reports/propDec.txt");
		Properties prop_two = new Properties();
		PropertiesDecorator propDec_two = new PropertiesDecorator(prop_two);
		propDec_two.loadFromXML(in);

		// assert
		Assertions.assertEquals(prop_two, propDec_two);
	}
}

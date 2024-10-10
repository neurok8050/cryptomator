# Documentation des nouveaux tests
## Claudéric DeRoy
## Mohammed Aiman Rahmani
### Étude de cas choisi : Cryptomator

#### Classe [`PropertiesDecoratorTest.java`](https://github.com/neurok8050/cryptomator/blob/develop/src/test/java/org/cryptomator/common/PropertiesDecoratorTest.java)

1. `setPropertyTest()` :  
Raison du choix : La classe `PropertiesDecorator` ce base sur des propriétés. Donc, s'assurer que la fonction  `setProperty(String key, String value)` permet de bien attribuer la valeur *value* à la clé *key* à une instance de la classe est assez importante.

2. `replaceTest()` :  
Raison du choix : Essentiellement la même raison que pour le test `setPropertyTest()` les fonctions `replace(Object key, Object oldValue, Object newValue)` et `replace(Object key, Object value)` permettent aussi, d'une certaine façon, de modifier/attribuer des paires de clé/valeur différente à une instance de la classe.

3. `removeTest()` :  
Raison du choix : C'est important de pouvoir attribuer des valeurs à l'instance de la classe, mais les retirer complètement devrait être aussi utile et c'est pourquoi nous avons aussi testé la fonction `remove(Object key, Object value)`.

4. `replaceAllTest()` :  
Raison du choix : Encore une fois, la fonction `replaceAll(BiFunction<? super Object, ? super Object, ?> function)` permet l'attribution de paire de clé/valeur, de façon très différente aux autres, à une instance de la classe et il faut donc aussi la tester.

5. `storeToXMLTest()` :  
Raison du choix : La fonction `storeToXML(OutputStream os, String comment)` a été choisi parce qu'elle permet d'enregistrer les paires de clé/valeur de l'instance de l'objet sous format `.xml`, il est intéressant de tester si les valeurs placées dans l'objet s'enregistre correctement. Si on est capable de retirer/attribuer des valeurs à l'objet est-ce qu'on est aussi capable d'enregistrer ses valeurs, c'est la question qu'on cherche à répondre avec le test.



#### Classe [`ObservableUtilTest.java`](https://github.com/neurok8050/cryptomator/blob/develop/src/test/java/org/cryptomator/common/ObservableUtilTest.java)

1. `testMapWithDefaultNonNull()` :  
Raison : Vérifie que mapWithDefault fonctionne correctement avec une valeur observable non-nulle (transformation d’une chaîne en sa longueur).

2. `testMapWithDefaultNull()` :  
Raison : Vérifie que mapWithDefault retourne la valeur par défaut lorsque l’observable est nulle.

3. `testMapWithDefaultSupplierNonNull()` :  
Raison : Vérifie que l’utilisation d’un Supplier pour la valeur par défaut fonctionne correctement avec une valeur observable non-nulle.

4. `testMapWithDefaultSupplierNull()` :  
Raison : Teste le comportement de mapWithDefault avec un Supplier lorsque l’observable est nulle.

5. `testMapWithDefaultSupplierChangingValue()` :  
Raison : Vérifie le comportement lorsque la valeur observable change dynamiquement, en s’assurant que mapWithDefault réagit correctement aux modifications.

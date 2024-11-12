# Documentation des nouveaux flags
## Claudéric DeRoy
## Mohammed Aiman Rahmani
### Étude de cas choisi : Cryptomator

#### Classe [`tache_3.yml`](https://github.com/neurok8050/cryptomator/blob/develop/.github/workflows/tache_3.yml)

#### Changement apportés
1. Utilisation de la balise `strategy` : Nous avons décidé d'utiliser la balise comme suit 
```yaml
strategy:
	matrix:
		flag: [flag_1, flag2]
```
afin de paralléliser le build des différentes «*jvm*» avec les 5 «*flags*»
2. Du ficher [test.yml](https://github.com/neurok8050/cryptomator/blob/develop/.github/workflows/test.yml), nous avons changer le bloc «*Build and Test*» où la «*jvm*» était «*build*»  et le code tester pour inclure l'utilisation de nos différents «*flags*» ainsi qu'un message qui permet de connaître quel «*flag*» est actuellement utilisé par la «*jvm*». Finalement, nous avons aussi rajouté à la toute fin du bloc la commande `unset MAVEN_OPTS` afin de réinitialiser les paramètres de la «*jvm*».
3. (Humour) On a ajouté un bloc à la fin qui imprime un message de fin avec une vache en «*ASCII art*» et qui nous affiche un message de fin incluant un petit jeux de mot.


#### Motivation
- `-XX:SurvivorRatio=4`   
	Permet de modifier le nombre de copie conserver avant de passer au Garbage collector
- `-XX:+PrintClassHistogram`  
	Permet d'imprimer un diagramme de l'historique des classes créé durant les tests
- `-XX:CompilationMode='quick-only'`  
	Permet de modifier la façon dont la compilation est faite

#### Référence intéressantes
1. [JaColine](https://jacoline.dev/inspect) : Java command Line Inspector permet de tester/vérifier des commandes de Java et donc les flags, possède aussi de l'information sur les flags ainsi que leurs options. Ça fonctionne pour une multitude de machine Java différente
2. 

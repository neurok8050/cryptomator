# Documentation des nouveaux flags
## Claudéric DeRoy
## Mohammed Aiman Rahmani
### Étude de cas choisi : Cryptomator

#### GitHub Action [`tache_3.yml`](https://github.com/neurok8050/cryptomator/blob/develop/.github/workflows/tache_3.yml)

#### Changement apportés
1. Utilisation de la balise `strategy` : Nous avons décidé d'utiliser la balise comme suit 
```yaml
strategy:
 matrix:
  flag:[flag_1, flag2]
```
afin de paralléliser le build des différentes «*jvm*» avec les 5 «*flags*»
2. Du ficher [test.yml](https://github.com/neurok8050/cryptomator/blob/develop/.github/workflows/test.yml), nous avons changer le bloc «*Build and Test*» où la «*jvm*» était «*build*»  et le code tester pour inclure l'utilisation de nos différents «*flags*» ainsi qu'un message qui permet de connaître quel «*flag*» est actuellement utilisé par la «*jvm*». Finalement, nous avons aussi rajouté à la toute fin du bloc la commande `unset MAVEN_OPTS` afin de réinitialiser les paramètres de la «*jvm*».
3. (Humour) On a ajouté un bloc à la fin qui imprime un message de fin avec une vache en «*ASCII art*» et qui nous affiche un message de fin incluant un petit jeux de mot.


#### Motivation
- [`-XX:SurvivorRatio=4`][1]    
	Permet de modifier la taille de l'espace mémoire aloué à l'espace «*Survivor*» comme étant la ratio entre l'espace «*Eden*» et «*Survivor*». Plus le ratio est petit plus la taille des objets de l'espace l'«*Eden*» est petit  survivent au «*Garbage Collector*» et donc la copie de ces objets finira en débordement («*overflow*») vers un autre espace mémoire. En soit ça n'effectera pas la performance du programme, mais pourrait nous montrer s'il y a trop d'écriture en mémoire.
- [`-XX:+PrintClassHistogram`][2]  
	Permet d'imprimer un diagramme de l'historique des instances des classes créées durant l'exécution, pratique pour trouver une ou des fuites produit par le code.
- [`-XX:CompilationMode='quick-only'`][3]  
	Permet de modifier la façon dont la compilation est faite. Normalement, la compilation est effectuée en «*normal tiered compilation*» qui commence avec une compilation C1 et poursuit avec une compilation C2. Par contre, on peut changer de type de compilation grâce à ce «*flag*». Ici, on utilise la compilation `'quick-only'` qui utilise seulement la compilation C1 qui est optimizé pour être beaucoup plus rapide que la compilation C2 ou «*normal tiered compilation*», mais est moins performante. On pourra voir si le code performe aussi bien, mais s'il n'est pas compilé normalement.

[-XX:+HeapDumpOnOutOfMemoryError] [4]

    Ce flag crée un dump mémoire en cas d'erreur OutOfMemoryError, permettant de capturer l'état complet de la mémoire à ce moment.
    Utilité : Idéal pour diagnostiquer les fuites de mémoire ou des charges excessives de mémoire dans l'application.

[-XX:+UseG1GC] [5]

    Active le Garbage Collector G1, optimisé pour des pauses de collecte de mémoire courtes et régulières.
    Utilité : Conçu pour les applications avec de gros volumes de données, il améliore les performances en réduisant les interruptions dues au GC.

#### Référence intéressantes

1. [JaColine](https://jacoline.dev/inspect) : Java Command Line Inspector permet de tester/vérifier des commandes de Java et donc les «*flags*», possède aussi de l'information sur les «*flags*» ainsi que leurs options. Ça fonctionne pour une multitude de machine Java différente



[1]: https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/sizing.html
[2]:https://docs.oracle.com/en/java/javase/11/troubleshoot/troubleshooting-memory-leaks.html#GUID-93A2C2F1-C22C-4E76-83B8-785D3DD869E9
[3]: https://www.baeldung.com/jvm-tiered-compilation

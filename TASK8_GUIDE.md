# Tâche 8 - Guide d'utilisation

## Objectif
Trouver la taille de tableau qui prend **1 seconde (±10%)** à trier sur votre ordinateur.

## Comment ça fonctionne ?

### Concept : Recherche Dichotomique
Au lieu d'essayer chaque taille possiblement (1000, 2000, 3000...), on utilise la **recherche dichotomique** :
- On teste une taille au **milieu** de la plage
- Si c'est trop rapide → on augmente la taille (chercher plus haut)
- Si c'est trop lent → on diminue la taille (chercher plus bas)
- On répète jusqu'à trouver la bonne taille

Cela prend seulement **20 essais maximum** au lieu de centaines !

## Comment lancer le programme ?

### Compiler
```bash
cd /home/jaures/Documents/tri-algo
scalac -d out src/Task8.scala src/sort/*.scala src/factory/*.scala
```

### Exécuter avec YSort (plus rapide - recommandé)
```bash
scala -cp out Task8 ysort
```

### Exécuter avec Selection Sort (plus lent)
```bash
scala -cp out Task8 selection
```

### Résultats possibles

#### Avec YSort :
```
Algorithme choisi: YSort (plus rapide)
Recherche en cours...
Itération 1: Taille = 5000500, Temps = 1993ms
Itération 2: Taille = 2500749, Temps = 1036ms
✓ TROUVÉ!
Taille optimale: 2500749 éléments
```
→ YSort prend **1 seconde** pour trier **2.5 millions** d'éléments

#### Avec Selection Sort :
```
Algorithme choisi: Selection Sort (plus lent)
Recherche en cours...
Itération 1: Taille = 25500, Temps = 321ms
Itération 2: Taille = 37750, Temps = 561ms
Itération 3: Taille = 43875, Temps = 735ms
Itération 4: Taille = 46938, Temps = 819ms
Itération 5: Taille = 48469, Temps = 928ms
✓ TROUVÉ!
Taille optimale: 48469 éléments
```
→ Selection Sort prend **1 seconde** pour trier **48 469** éléments

## Pourquoi YSort est plus rapide ?

- **Selection Sort** : O(n²) - Test chaque élément avec chaque autre
- **YSort** : O(n log n) - Divise et conquière (Merge Sort)

Donc YSort peut trier **50 fois plus** d'éléments dans le même temps !

## Code - Partie clé : La recherche dichotomique

```scala
var tailleMin = 1000
var tailleMax = 10000000

while (tailleMin <= tailleMax && iterationCompteur < 20) {
  // Calculer le milieu
  val tailleMilieu = (tailleMin + tailleMax) / 2
  
  // Créer un tableau aléatoire
  val tableau = RandomArrayFactory.create(tailleMilieu)
  
  // Mesurer le temps
  val temps = mesurerTempsDelaTrie(tableau, algoritme)
  
  if (temps >= 900 && temps <= 1100) {
    // BINGO! Taille trouvée
    tailleOptimale = tailleMilieu
    tailleMin = tailleMax + 1  // Sortir de la boucle
  } else if (temps < 1000) {
    // Trop rapide → chercher plus grand
    tailleMin = tailleMilieu + 1
  } else {
    // Trop lent → chercher plus petit
    tailleMax = tailleMilieu - 1
  }
}
```

## Notes
- Les temps peuvent varier légèrement d'une exécution à l'autre (processeur occupé par d'autres tâches)
- C'est normal si la 2ème vérification donne un temps légèrement différent
- Selection Sort peut être **très lent** pour les grandes tailles, soyez patient !


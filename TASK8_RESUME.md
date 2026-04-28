# TÂCHE 8 - RÉSUMÉ DE L'IMPLÉMENTATION

## Tâche Complétée

Vous avez créé un **programme de recherche dichotomique** qui détermine la taille de tableau qui prend **1 seconde (±10%)** à trier.

## Fichiers Créés

1. **`src/Task8.scala`**
   - Version compacte et efficace
   - Utilisation simple : `scala -cp out Task8 ysort` ou `scala -cp out Task8 selection`

2. **`src/Task8Pedagogique.scala`**
   - Version **très débutant** avec beaucoup de commentaires
   - Explique chaque étape du processus
   - Affiche les messages détaillés de la recherche

3. **`TASK8_GUIDE.md`**
   - Guide complet avec exemples
   - Explique le concept et la méthode

## Comment Utiliser

### Compiler
```bash
cd /home/jaures/Documents/tri-algo
scalac -d out src/Task8Pedagogique.scala src/sort/*.scala src/factory/*.scala
```

### Exécuter
```bash
# Version pédagogique avec Selection Sort
scala -cp out Task8Pedagogique selection

# Version pédagogique avec YSort
scala -cp out Task8Pedagogique ysort

# Version compacte
scala -cp out Task8 ysort
```

## Résultats sur le Système

### Selection Sort
```
Taille optimale trouvée: 46938 éléments
Temps mesuré: 908ms ✓
```

### YSort
```
Taille optimale trouvée: 3750624 éléments
Temps mesuré: 1005ms ✓
```

→ **YSort est ~80 fois plus rapide que Selection Sort !**

## Comment Fonctionne la Recherche Dichotomique ?

```
But: Trouver taille X telle que trier X éléments = 1 seconde

Étape 1: Test taille 5 000 500 → 1519ms (trop lent!)
         Chercher plus bas

Étape 2: Test taille 2 500 749 → 667ms (trop rapide!)
         Chercher plus haut

Étape 3: Test taille 3 750 624 → 994ms (parfait!)
         ✓ Trouvé en seulement 3 itérations !
```

## Concepts Utilisés

1. **Recherche Dichotomique (Binary Search)**
   - Divise la plage par 2 à chaque itération
   - Très efficace : 20 essais max pour 1 million d'éléments

2. **Mesure de Temps**
   - `System.currentTimeMillis()` pour obtenir l'heure
   - Différence = temps d'exécution

3. **Création de Tableaux Aléatoires**
   - `RandomArrayFactory.create(size)` pour trier des données réalistes
   - Les résultats varient légèrement d'une exécution à l'autre

## Points Clés du Code Débutant

### 1. Fonction de Mesure
```scala
def mesurerTempsDelaTrie(array: Array[Int], nomAlgorithme: String): Long = {
  val timeAvant = System.currentTimeMillis()
  SelectionSort.sort(array)  // ou YSort.sort(array)
  val timeApres = System.currentTimeMillis()
  timeApres - timeAvant  // Retourner le temps en ms
}
```

### 2. Boucle Dichotomique
```scala
var taille_min = 1000
var taille_max = 50000

while (taille_min <= taille_max) {
  val taille_milieu = (taille_min + taille_max) / 2
  val temps = mesurerTempsDelaTrie(tableau, "selection")
  
  if (temps est acceptable) {
    // Taille parfaite trouvée !
  } else if (temps < 1000) {
    taille_min = taille_milieu + 1  // Chercher plus grand
  } else {
    taille_max = taille_milieu - 1  // Chercher plus petit
  }
}
```

## Résumé des Apprentissages

✅ Recherche dichotomique  
✅ Mesure de temps d'exécution  
✅ Comparaison d'algorithmes (Selection Sort vs YSort)  
✅ Programmation structurée avec commentaires  
✅ Gestion des paramètres en ligne de commande  

---

**La Tâche 8 est complète et fonctionnelle !** 🎉


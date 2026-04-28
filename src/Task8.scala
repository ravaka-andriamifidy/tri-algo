import factory.RandomArrayFactory
import sort.{SelectionSort, YSort}
import scala.io.StdIn

object Task8 extends App {

  // Fonction pour mesurer le temps de tri
  def mesurerTempsDelaTrie(array: Array[Int], nomAlgorithme: String): Long = {
    // Enregistrer le temps avant
    val timeAvant = System.currentTimeMillis()

    // Trier le tableau avec l'algorithme choisi
    if (nomAlgorithme == "selection") {
      SelectionSort.sort(array)
    } else if (nomAlgorithme == "ysort") {
      YSort.sort(array)
    }

    // Enregistrer le temps après
    val timeApres = System.currentTimeMillis()

    // Retourner la différence
    timeApres - timeAvant
  }

  // Afficher les algorithmes disponibles
  println("==== TÂCHE 8 - Trouver la taille qui prend 1 seconde ====")
  println()
  println("Algorithmes disponibles:")
  println("1. selection (Selection Sort - plus lent)")
  println("2. ysort (YSort - plus rapide)")
  println()
  print("Choisir l'algorithme (1 ou 2): ")
  val choix = scala.io.StdIn.readLine()

  val algoritme = if (choix == "1") "selection" else "ysort"
  println(s"Vous avez choisi: ${if (algoritme == "selection") "Selection Sort" else "YSort"}")
  println()

  // Recherche dichotomique pour trouver la taille
  println("Recherche en cours... (cela peut prendre quelques secondes)")
  println()

  // Valeurs min et max pour la recherche
  var tailleMin = 1000        // Commencer petit
  var tailleMax = 1000000     // Aller jusqu'à 1 million
  var tailleOptimale = 0

  // But: trouver la taille où le temps est entre 900ms et 1100ms (±10% de 1000ms)
  val tempsBas = 900L    // 1000 - 10%
  val tempsHaut = 1100L  // 1000 + 10%
  val tempsCible = 1000L // 1 seconde

  // Boucle de recherche dichotomique
  var iterationCompteur = 0

  while (tailleMin <= tailleMax && iterationCompteur < 20) {
    iterationCompteur = iterationCompteur + 1

    // Calculer la taille du milieu
    val tailleMilieu = (tailleMin + tailleMax) / 2

    // Créer un tableau aléatoire
    val tableau = RandomArrayFactory.create(tailleMilieu)

    // Mesurer le temps
    val temps = mesurerTempsDelaTrie(tableau, algoritme)

    println(s"Itération ${iterationCompteur}: Taille = ${tailleMilieu}, Temps = ${temps}ms")

    // Vérifier si on a trouvé la bonne taille
    if (temps >= tempsBas && temps <= tempsHaut) {
      // Bingo! On a trouvé la taille idéale
      println()
      println("✓ TROUVÉ!")
      tailleOptimale = tailleMilieu
      tailleMin = tailleMax + 1  // Sortir de la boucle
    } else if (temps < tempsCible) {
      // Le temps est trop court, il faut une taille plus grande
      tailleMin = tailleMilieu + 1
    } else {
      // Le temps est trop long, il faut une taille plus petite
      tailleMax = tailleMilieu - 1
    }
  }

  // Afficher le résultat final
  println()
  println("========== RÉSULTAT ==========")
  println(s"Algorithme: $algoritme")
  println(s"Taille optimale: $tailleOptimale éléments")
  println(s"Cible: 1000ms ±10% (entre 900ms et 1100ms)")
  println()

  // Vérification finale
  println("Vérification finale...")
  val tableauFinal = RandomArrayFactory.create(tailleOptimale)
  val tempsFinal = mesurerTempsDelaTrie(tableauFinal, algoritme)
  println(s"Temps mesuré: ${tempsFinal}ms")

  if (tempsFinal >= tempsBas && tempsFinal <= tempsHaut) {
    println("✓ Le temps est dans la bonne plage!")
  } else {
    println("✗ Le temps n'est pas dans la bonne plage, mais c'est normal avec les variations du système")
  }

}


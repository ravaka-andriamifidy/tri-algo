
 //MÉTHODE: Recherche dichotomique (binary search)


import factory.RandomArrayFactory
import sort.{SelectionSort, YSort}

object Task8Pedagogique extends App {

  // ============================================
  // FONCTION POUR MESURER LE TEMPS
  // ============================================
  // Cette fonction trie un tableau et retourne le temps qu'il a pris
  def mesurerTempsDelaTrie(array: Array[Int], nomAlgorithme: String): Long = {
    // Enregistrer l'heure AVANT de trier
    val heure_avant = System.currentTimeMillis()
    // Trier le tableau
    if (nomAlgorithme == "selection") {
      SelectionSort.sort(array)  // Algorithme lent
    } else if (nomAlgorithme == "ysort") {
      YSort.sort(array)  // Algorithme rapide
    }
    // Enregistrer l'heure APRÈS de trier
    val heure_apres = System.currentTimeMillis()
    // Retourner la différence en millisecondes
    heure_apres - heure_avant
  }
  // ============================================
  // LIRE L'ALGORITHME CHOISI
  // ============================================
  // Vérifier si on a un argument en ligne de commande
  val choixUtilisateur = if (args.length > 0) args(0) else "selection"
  // Décider quel algorithme utiliser
  val algoritme_choisi = if (choixUtilisateur == "selection" || choixUtilisateur == "1") {
    "selection"
  } else {
    "ysort"
  }
  // ============================================
  // AFFICHER LES INFOS DE DÉMARRAGE
  // ============================================
  println()
  println("╔════════════════════════════════════════════════════════╗")
  println("║      RECHERCHE DE LA TAILLE OPTIMALE         ║")
  println("╚════════════════════════════════════════════════════════╝")
  println()
  println(s"Algorithme choisi: ${if (algoritme_choisi == "selection") "Selection Sort (lent)" else "YSort (rapide)"}")
  println("Cible: Trouver une taille qui prend 1 seconde à trier")
  println("Plage acceptable: 900ms à 1100ms (±10%)")
  println()
  println("Recherche en cours...")
  println()
  // ============================================
  // PARAMÈTRES POUR LA RECHERCHE
  // ============================================
  // Plage de recherche minimale (commencement)
  var taille_minimum = 1000
  // Plage de recherche maximale (fin)
  // Selection Sort est lent, donc max petit
  // YSort est rapide, donc max grand
  var taille_maximum = if (algoritme_choisi == "selection") 50000 else 10000000
  // Ici on va stocker la taille qu'on a trouvée
  var taille_trouvee = 0
  // Compter combien de fois on a essayé
  var compteur_iteration = 0
  // Les limites acceptables du temps cible
  val temps_plus_bas = 900L      // 1000 - 10%
  val temps_plus_haut = 1100L    // 1000 + 10%

  // ============================================
  // BOUCLE DE RECHERCHE DICHOTOMIQUE
  // ============================================

  // On répète la recherche jusqu'à trouver une solution
  // (on met un max de 20 itérations pour éviter une boucle infinie)
  while (taille_minimum <= taille_maximum && compteur_iteration < 20) {

    // Augmenter le compteur d'itération
    compteur_iteration = compteur_iteration + 1
    // CALCUL DU MILIEU
    // C'est l'astuce de la recherche dichotomique !
    val taille_test = (taille_minimum + taille_maximum) / 2
    // Créer un tableau aléatoire de cette taille
    val mon_tableau = RandomArrayFactory.create(taille_test)
    // Mesurer combien de temps prend le tri
    val temps_mesure = mesurerTempsDelaTrie(mon_tableau, algoritme_choisi)
    // Afficher le résultat de cette itération
    println(s"Itération ${compteur_iteration}: Taille = ${taille_test}, Temps = ${temps_mesure}ms")
    if (temps_mesure >= temps_plus_bas && temps_mesure <= temps_plus_haut) {
      // ✓ BINGO ! On a trouvé une taille acceptable !
      println()
      println("TAILLE TROUVÉE")
      println()
      taille_trouvee = taille_test
      // Pour sortir de la boucle, on fait en sorte que la condition devient fausse
      taille_minimum = taille_maximum + 1
    } else if (temps_mesure < 1000) {
      // ✗ C'est trop RAPIDE
      // Il faut essayer une TAILLE PLUS GRANDE
      println("   → Trop rapide, on augmente la taille")
      // On cherche plus haut
      taille_minimum = taille_test + 1
    } else {
      // ✗ C'est trop LENT
      // Il faut essayer une TAILLE PLUS PETITE
      println("   → Trop lent, on diminue la taille")
      // On cherche plus bas
      taille_maximum = taille_test - 1
    }
  }
  // ============================================
  // AFFICHER LE RÉSULTAT FINAL
  // ============================================
  println()
  println("╔════════════════════════════════════════════════════════╗")
  println("║                    RÉSULTAT FINAL                      ║")
  println("╚════════════════════════════════════════════════════════╝")
  println()
  println(s"Algorithme utilisé: ${algoritme_choisi}")
  println(s"Taille optimale trouvée: ${taille_trouvee} éléments")
  println(s"Cible: 1000ms ±10% (entre ${temps_plus_bas}ms et ${temps_plus_haut}ms)")
  println()

  // ============================================
  // VÉRIFICATION FINALE
  // ============================================

  println("Vérification finale avec la taille trouvée...")

  val tableau_final = RandomArrayFactory.create(taille_trouvee)
  val temps_final = mesurerTempsDelaTrie(tableau_final, algoritme_choisi)

  println(s"Temps mesuré: ${temps_final}ms")
  println()

  if (temps_final >= temps_plus_bas && temps_final <= temps_plus_haut) {
    println("✓ Le temps est DANS la bonne plage !")
  } else {
    println("✗ Le temps n'est pas dans la bonne plage")
    println("  (C'est normal car le système est variable)")
  }
  println()
  println("Fin du programme.")
  println()
}


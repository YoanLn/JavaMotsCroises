# MotsCroisés

## Description
Ce projet implémente un solveur de mots croisés en Java en utilisant des techniques de propagation de contraintes (CSP - Constraint Satisfaction Problem). Il permet de générer et résoudre des grilles de mots croisés en français en utilisant un dictionnaire de mots.

## Structure du projet
Le projet est organisé en plusieurs packages :

- **pobj.csp** : Contient l'implémentation générique du solveur de contraintes
  - `ICSP.java` : Interface pour un problème de satisfaction de contraintes
  - `IVariable.java` : Interface pour une variable dans un CSP
  - `IContrainte.java` : Interface pour une contrainte dans un CSP
  - `CSPSolver.java` : Implémentation du solveur de contraintes par backtracking

- **pobj.motx.grille** : Contient les classes de gestion de la grille de mots croisés
  - `Grille.java` : Représentation d'une grille de mots croisés
  - `Case.java` : Représentation d'une case dans la grille
  - `Emplacement.java` : Représentation d'un emplacement pour un mot
  - `EmplacementType.java` : Enumération pour les types d'emplacements (horizontal/vertical)
  - `GrillePlaces.java` : Extraction des emplacements dans une grille
  - `GrillePotentiel.java` : Gestion des mots potentiels pour chaque emplacement

- **pobj.motx.mots** : Contient les classes de gestion des mots
  - `Dictionnaire.java` : Gestion du dictionnaire de mots
  - `EnsembleLettre.java` : Ensemble de lettres possibles à une position

- **pobj.motx.csp** : Contient les classes spécifiques au problème des mots croisés
  - `MotX.java` : Implémentation de l'interface ICSP pour les mots croisés
  - `DicoVariable.java` : Implémentation d'une variable pour le problème des mots croisés
  - `GrilleContrainte.java` : Gestion des contraintes de la grille
  - `CroixContrainte.java` : Implémentation des contraintes de croisement

## Fonctionnalités
- Chargement de grilles de mots croisés
- Chargement de dictionnaires de mots
- Résolution automatique de grilles par propagation de contraintes
- Filtrage efficace des mots possibles à chaque emplacement
- Détection des croisements entre mots horizontaux et verticaux

## Comment utiliser
1. Créer une instance de `Grille` ou la charger depuis un fichier
2. Charger un dictionnaire avec `Dictionnaire.loadDictionnaire(path)`
3. Créer une instance de `GrillePlaces` à partir de la grille
4. Créer une instance de `GrilleContrainte` avec la grille et le dictionnaire
5. Créer une instance de `MotX` avec la `GrilleContrainte`
6. Utiliser `CSPSolver` pour résoudre le problème

## Exemple d'utilisation
```java
// Charger le dictionnaire
Dictionnaire dico = Dictionnaire.loadDictionnaire("data/dictionnaire.txt");

// Créer ou charger une grille
Grille grille = new Grille(5, 5);
// Remplir la grille avec des cases pleines et vides...

// Extraire les emplacements
GrillePlaces grillePlaces = new GrillePlaces(grille);

// Créer les contraintes
GrilleContrainte grilleContrainte = new GrilleContrainte(grillePlaces, dico);

// Créer le problème
MotX motx = new MotX(grilleContrainte);

// Résoudre
CSPSolver solver = new CSPSolver();
ICSP solution = solver.solve(motx);

// Afficher la solution
System.out.println(solution);
```

## Prérequis
- Java 17 ou supérieur
- JUnit 4 (pour les tests)

## Améliorations possibles
- Interface graphique pour visualiser les grilles et solutions
- Optimisation de l'algorithme de résolution
- Possibilité de créer manuellement des grilles personnalisées
- Support de différentes langues pour les dictionnaires

## Licence
Ce projet est disponible sous licence open source. Vous êtes libre de l'utiliser, le modifier et le distribuer selon les termes de la licence. 
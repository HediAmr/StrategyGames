# l2s4-projet-2021

Vous devez *forker* ce projet dans votre espace de travail Gitlab (bouton `Fork`) et vidéo sur le [portail](https://www.fil.univ-lille1.fr/portail/index.php?dipl=L&sem=S4&ue=Projet&label=Documents)

Une fois cela réalisé supprimer ces premières lignes et remplissez les noms des membres de votre équipe.

# Equipe

- Hedi AMEUR
- Nicolas LEAL CARVALHO
- Choukri BELKHABIR
- Hassan MAHAMAT IMAM

# Comment exécuter les autres jars

Les deux jars suivant prenent en compte une stratégie interactive au clavier. Les choix ne sont donc plus
réaliser au hasard, mais ce sont les joueurs qui décident.
Ces derniers décident des dimensions de la grille, de où déployé une unité et quelle unité doit être supprimé en cas de manque
en or/nourriture.

## Pour warinput.jar

Placez-vous à la racine du projet et tapez la commande suivant dans un terminal:
- `make warinput.jar`

Le fichier `warinput.jar` peut ensuite être exécuté avec la commande suivante:
- `java -jar jar/warinput.jar Renet Gustave`

## Pour fieldinput.jar

Placez-vous à la racine du projet et tapez la commande suivant dans un terminal:
- `make fieldinput.jar`

Le fichier `fieldinput.jar` peut ensuite être exécuté avec la commande suivante:
- `java -jar jar/fieldinput.jar Renet Gustave`

Renet et Gustave sont passés comme arguments en tant qu'exemple, des arguments différents peuvent être fourni.
Les jars peuvent accepter jusqu'à 8 arguments; soit 8 joueurs maximum.


# Sujet

[Le sujet 2021](https://www.fil.univ-lille1.fr/portail/index.php?dipl=L&sem=S4&ue=Projet&label=Documents)

# Livrables

## Livrable 1

### Atteinte des objectifs

- Conception des personnages (guerrier, ouvrier)

### Difficultés restant à résoudre

## Livrable 2

### Atteinte des objectifs

- Conception du plateau, d'une cellule, d'une ressource.
- Programmation et conception terminées.

### Difficultés restant à résoudre

## Livrable 3

### Atteinte des objectifs

- Conception des actions du joueur, d'un tour, de l'état d'une partie.

### Difficultés restant à résoudre

- Voir si la conception des actions est correcte

## Livrable 4

### Atteinte des objectifs

- Regroupement des toutes les anciennes modélisations avec les nouvelles modifications après programmation.

### Difficultés restant à résoudre

# Journal de bord

## Semaine 1

- Conception des personnages incluant une classe commune aux deux jeux, puis celles propres à chaque jeu. Ces différents classes sont sous forme de diagramme UML dans le fichier Unit.mdj (fait avec StarUML). Conception d'une interface unit qui regroupe les attributs et méthodes communes aux unités des 2 jeux.

## Semaine 2

- Suppression de le classe Warrior, remplacé par Army (groupe de Warrior); le nombre de guerrier disponible sera gardé sous forme d'entier

## Semaine 3

- Conception de la classe player (nouvelle version), deux classes filles hérite d'une classe mère "player", cela est du au fait que les deux classes filles partagent entre-elles des méthodes et attributs similaires.

## Semaine 4

- Conception de la classe Grid, Case et Ressource, éventuellement voir comment représenter le type de la case. (probablement un enum)
La grille sera composé de case qui auront un certains types (énum), les cases pourront accueillir des unités sauf les cases correspondant à un océan. La classe Ressource représente les ressources produites par les cases de certains types.

## Semaine 5

- Choix d'un type énuméré pour le type des cases étant donné qu'une case peut uniquement avoir certaines valeurs en type. (Classe Ground)
- Programmation des classes liés aux personnages (Player + Unit),

## Semaine 6

- Programmation des classes de la conception du plateau (Grid, Case, Ressource & Ground)
- Modification de l'UML de Player (beaucoup moins d'attributs, de méthodes), certains attributs et méthodes ne servaient pas trop à grand chose.

## Semaine 7

- Conception des actions du joueur et des règles du jeu
- Renommage de le classe Grid (Grille) en Board (Plateau)
- Renommage de la classe Case en Cell

## Semaine 8

- Correction au niveau des actions, meilleur conception pour le rajout d'éventuels actions.
- Programmation du plateau, de cellule, ainsi que des classes nécessaires à celle-ci.
- Petit programme de test afin de vérifier la bonne génération du plateau (Test.java), ce fichier sera probablement supprimé ou complètement modifié.

## Semaine 9

- Amélioration des Actions du joueur sous forme de classe static (c'est à dire qu'une instance de l'objet), pas besoin du coup de devoir créer 10 fois un objet action. Un seul est créé seulement ses attributs sont changés afin de pouvoir l'utiliser pour plusieurs joueurs.

## Semaine 10

- Fusion de Turn et Gamestate en une classe Game, classe beaucoup plus complète et permet de gérer l'état d'une partie beaucoup plus facilement. La classe Game est elle-aussi une classe static.

## Semaine 11

- Programmation du main pour chaque jeu (afin de pouvoir au moins lancer le jeu)
- Modification pour prendre en compte les arguments lors du lancement

## Semaine 12

- Programmation des tests (ce qui aurait du être fait beaucoup plus tôt)
- Makefile
- Petites corrections dans le code (correction d'erreurs / optimisations)


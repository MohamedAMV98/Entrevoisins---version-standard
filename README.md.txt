---Application Entrevoisins---
Liste de voisins avec profil pour chacun d'entre eux

---Fonctionnalité déjà présente---
Affichage liste voisin
Ajout voisin
Suprression voisin

---Fonctionnalité ajouté---
Affichage du profil voisin
Ajouter/supprimer voisin en favoris

---Télécharger l'application---

Lien du repository github : https://github.com/MohamedAMV98/Entrevoisins---version-standard.git

Méthode 1 : - Téléchargez en ZIP et décompresser le dossier à l'aide de WinRar ou 7Zip OU
	      effectuez un git clone du en grâce au lien github dans le dossier souhaité	
	    - Ouvrez Android Studio, la fenêtre d'accueil s'affiche
	    - Cliquez sur "Open existed project file" et rendez-vous au chemin d'accès du dossier
	    - Cliquez sur "Entrevoisins", un icône Android s'affiche sur le fichier en question.

Méthode 2  : - Copiez le lien github 
	     - Ouvrez Android Studio et sélectionner "Project From Version Control"
	     - Collez le lien github et cliquez sur Ok.

---Démarrer l'application---	
L'application n'a pas été conçu pour toutes les tailles et densité d'écran.
Veuillez la lancer sur un émulateur de type mobile et de densité comprises entre mdpi et xxxhdpi.
Pour ce faire : - rendez-vous dans le menu "Tools" et séléctionnez "AVD Manager".	
		- Cliquez sur "Create virtual device", une nouvelle fenêtre s'affiche.
		- Veillez à bien sélectionner la catégorie d'appareil "Phone".
		- Sur la droite, à côté de la caractéristiques résolution, veillez à sélectionner une densité comprise
		  entre celles indiquées plus haut. Nommez comme vous le souhaitez l'émulateur et allez jusque la fin du processus
		  puis fermez la fenêtre.
		- Cliquez sur le menu "Run" et cliquez sur "Run app" ou appuyez sur les touches Shift + F10 de votre clavier.

Pour démarrer l'application sur votre téléphone mobile: - Il est nécessaire débogger votre téléphone, pour ce faire suivez les instructions indiquez sur ce lien :
							https://www.frandroid.com/comment-faire/tutoriaux/229753_questcequelemodedebogageusb	
							- Branchez votre appareil mobile à l'ordinateur grâce à un câble USB
							- Android Studio repère automatiquement votre appareil, vous pouvez maintenant lancez l'application (Shif + F10)

---Testez l'application---
Pour lancer les tests unitaires : - Rendez vous au fichier "NeighbourServiceTest" au chemin d'accès suivant ->
                                    "P3_SHENEET_Mohamed\P3_02_code\Entrevoisins\app\src\test\java\com\openclassrooms\entrevoisins\service\NeighbourServiceTest.java"
				  - Sur la marge de gauche (là où sont indiqués les numéros de ligne du code) vous pouvez cliquez sur un icône run pour chaque test
				    ou pour tester tous les tests unitaires d'un seul coup.
Pour lancer les tests instrumentalisés : - Rendez vous au fichier "NeighbourListText" au chemin d'accès suivant -> 
					 "P3_SHENEET_Mohamed\P3_02_code\Entrevoisins\app\src\androidTest\java\com\openclassrooms\entrevoisins\neighbour_list\NeighboursListTest.java"
					 - Sélectionnez l'émulateur adéquate
					 - Pour lancer, effectuez la même chose que pour les tests unitaires.


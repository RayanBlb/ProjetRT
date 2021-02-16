package projetRT;

import java.util.Scanner;

public class Parc {
	// Constantes
		final static int NB_PARC_MAX = 50 ;
		
		// Initialisation capture clavier 
		final static Scanner clavier = new Scanner(System.in);
		
		// Variables globales
		private static Parc[] tab_parc = new Parc[NB_PARC_MAX] ;
		private static int nb_Parcs_Enregistres_Total = 0;
		private static Parc parc_actuel ;

		private static String[] tab_type_parc_nom = {"Antenne","Pylone","Noeud"} ;


		// Variables d'instance
		private int type_parc;
		private String nom;
		
		//Constructeur
		Parc(String nom){
			this.nom =nom;
		}
		
		
		public static void nouveauParc() { //methode permettant de creer un parc pas a� pas
			System.out.println("Creation d'un parc d'antenne");
			System.out.print("Nom du parc a creer : ");
			String new_nom = clavier.nextLine();
			System.out.println("Le parc " + new_nom + " a bien ete cree");
			
			tab_parc[nb_Parcs_Enregistres_Total] = new Parc(new_nom ); //un objet de type parc est cree et stocke dans tab_parc
			nb_Parcs_Enregistres_Total++ ;
			
			System.out.print("Voulez-vous creer un autre parc [0-Non | 1-Oui] : ");
			byte choix_creation_encore_un_autre_parc = clavier.nextByte(); clavier.nextLine();
			if (choix_creation_encore_un_autre_parc == 1) {
				nouveauParc();
			}
			
			}
		
		public static void afficherInfoParc(Parc parc_a_afficher) { //methode permettant d'afficher toutes les informations relatives a� un parc
			System.out.println("Ce parc "+ tab_type_parc_nom[parc_a_afficher.type_parc-1] );
		}
		
		public static void changerParc() { //methode permettant a l'utilisateur de naviguer parmi ses parc et de selectionner celui sur lequel il souhaite effectuer des operations
			int choix_parc ;
			int i;
			if (nb_Parcs_Enregistres_Total < 2) {
				choix_parc = 1;
			}
			else {
				System.out.println("Merci de selectionner un parc :");
				for (i=0 ; i< nb_Parcs_Enregistres_Total ; i++) {
						System.out.println((i+1) +": "+ tab_parc[i].nom);
				}
				choix_parc = clavier.nextInt(); clavier.nextLine();
			}
			parc_actuel = tab_parc[choix_parc-1] ;
			System.out.println("Park actuel : " + parc_actuel.nom);
			
		}
		
		public static void supprimerParc() { //methode permettant a l'utilisateur de supprimer l'un de ses parc
			int choix;
			System.out.println("Quel parc voulez-vous supprimer ? (mettre le nombre associe)");
			
			for (int i=0 ; i< nb_Parcs_Enregistres_Total ; i++) {
				System.out.println((i+1) +": " + tab_parc[i].nom);
			}
			choix = clavier.nextInt(); clavier.nextLine() ;
			Parc parc_a_supprimer = tab_parc[choix-1] ;
			
			String nom_supp = parc_a_supprimer.nom ;
			int index = 0;
			for (int i=0 ; i<nb_Parcs_Enregistres_Total ; i++) {
				if (tab_parc[i] == parc_a_supprimer) { //
					index=i;
					break;
				}
			}
			for (int i=index ; i< (nb_Parcs_Enregistres_Total-1) ; i++ ) {
				tab_parc[i] = tab_parc[i+1] ;
			}
			System.out.println("Votre parc "+ nom_supp + " a ete supprime.");
			nb_Parcs_Enregistres_Total-- ;
		}

		public static Parc getParcActuel() { //methode retournant le parc qui est actuellement celui ou l'on effectue les operations
			return parc_actuel;
		}
}

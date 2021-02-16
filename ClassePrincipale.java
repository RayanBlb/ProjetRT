package projetRT;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ClassePrincipale {

	// Initialisation capture clavier de ce que tappe l'utilisateur
	static Scanner clavier = new Scanner(System.in);	
	

	public static void main(final String[] args) throws IOException, URISyntaxException { 
		System.out.println("");
		System.out.println("---------------------------");
		System.out.println("Projet informatique applique aux RT");
		System.out.println("Gestion d'un parc telecom");
		System.out.println("---------------------------");
		System.out.println("[1] Serveur");
		System.out.println("[2] Client");
		System.out.println("---------------------------");
		System.out.print("Quel est votre choix : ");
		final byte choix = clavier.nextByte(); clavier.nextLine();
		switch(choix) {
			case 1 : Serveur.serveur_mode(); break;
			case 2 : demarrage(); affichage_menu_principal(); break;
			default: System.out.print("Choix invalide !"); main(null); //Si l'utilisateur entre une valeur inconnu on relance la fonction principal
            break;
		}
	  }
	
	public static void demarrage() { //Pour le premier demarrage on fait un nouveau parc et on fait une nouvelle antenne

		Parc.nouveauParc();
		Parc.changerParc();
		Antenne.Creation_antenne();
		

	}
	
	public static void affichage_menu_principal() throws IOException, URISyntaxException { //Permet d'afficher le menu principal
		System.out.println("");
		System.out.println("----Menu Principal----");
		System.out.println("[1] Menu Parc");
		System.out.println("[2] Menu Creation");
		System.out.println("[3] Menu Suppression");
		System.out.println("[4] Menu Affichage");
		System.out.println("[5] Menu Recherche Reseau");
		System.out.println("----------------------");
		System.out.print("Quel est votre choix : ");
		final byte choix = clavier.nextByte(); clavier.nextLine();

		switch(choix) {
			case 1 : affichage_menu_parc(); break;
			case 2 : afficher_menu_creer(); break;
			case 3 : afficher_menu_supprimer(); break;
			case 4 : afficher_menu_afficherall();break;
			case 5 : afficher_menu_recherche_reseau(); break;
			default: System.out.print("Choix invalide !"); affichage_menu_principal(); //Si l'utilisateur entre une valeur inconnu on relance la fonction principal
		}
		affichage_menu_principal();
	}
	
	public static void affichage_menu_parc() throws IOException, URISyntaxException { //Permet d'afficher les actions en rapport avec les parcs
		System.out.println("");
		System.out.println("----Menu Parc----");
		System.out.println("[1] Lister les parcs");
		System.out.println("[2] Creation d'un parc");
		System.out.println("[3] Supprimer un parc");
		System.out.println("[4] Menu Principal");
		System.out.println("----------------------");
		System.out.print("Quel est votre choix : ");;
		final byte choix = clavier.nextByte(); clavier.nextLine();
		switch(choix) {
			case 1 : Parc.changerParc(); break;
			case 2 : Parc.nouveauParc(); break;
			case 3 : Parc.supprimerParc(); break;
			case 4 : affichage_menu_principal(); break;
			default: System.out.print("Choix invalide !"); affichage_menu_parc(); //Si l'utilisateur entre une valeur inconnu on relance la fonction principal
		}
		affichage_menu_principal();
	}
	
	
public static void afficher_menu_creer() throws IOException, URISyntaxException { //Permet d'afficher le menu de création
	System.out.println("");
	System.out.println("----Menu Creation----");
	System.out.println("[1] Creer une antenne");
	System.out.println("[2] Creer un pylone");
	System.out.println("[3] Creer un noeud");
	System.out.println("[4] Menu Principal");
	System.out.println("----------------------");
	System.out.print("Quel est votre choix : ");;
	final byte choix = clavier.nextByte(); clavier.nextLine();
	switch(choix) {
		case 1 : Antenne.Creation_antenne(); break;
		case 2 : Pylone.Creation_pylone(); break;
		case 3 : Noeud.Creation_noeud(); break;
		case 4 : affichage_menu_principal(); break;
		default: System.out.print("Choix invalide !"); afficher_menu_creer(); //Si l'utilisateur entre une valeur inconnu on relance la fonction principal
	}
	affichage_menu_principal();
}	
	
	
public static void afficher_menu_supprimer() throws IOException, URISyntaxException { //Permet d'afficher le menu de suppression 
	System.out.println("");
	System.out.println("----Menu Suppression----");
	System.out.println("[1] Supprimer une antenne");
	System.out.println("[2] Supprimer un pylone");
	System.out.println("[3] Supprimer un noeud");
	System.out.println("[4] Menu Principal");
	System.out.println("----------------------");
	System.out.print("Quel est votre choix : ");
		final byte choix = clavier.nextByte(); clavier.nextLine();
		switch(choix) {
			case 1 : Antenne.Supprimer() ; break ;
			case 2 : Pylone.Supprimer() ; break ;
			case 3 : Noeud.Supprimer() ; break ;
			case 4 : affichage_menu_principal(); break;
			default: System.out.print("Choix invalide !"); afficher_menu_supprimer(); //Si l'utilisateur entre une valeur inconnu on relance la fonction principal
		}
		affichage_menu_principal();
	}
	

public static void afficher_menu_afficherall() throws IOException, URISyntaxException { //Permet d'afficher le menu d'affichage
	System.out.println("");
	System.out.println("----Menu Affichage----");
	System.out.println("[1] Afficher les antennes");
	System.out.println("[2] Afficher les pylones");
	System.out.println("[3] Afficher les noeuds");
	System.out.println("[4] Afficher les antennes d'un noeud");
	System.out.println("[5] Afficher la liste des antennes avec laquelle une antenne peut communiquer");
	System.out.println("[6] Menu Principal");
	System.out.println("----------------------");
	System.out.print("Quel est votre choix : ");
	final byte choix = clavier.nextByte(); clavier.nextLine();
	switch(choix) {
		case 1 : Antenne.afficher_all();break;
		case 2 : Pylone.afficher_all();break;
		case 3 : Noeud.afficher_all();break;
		case 4 : Noeud.Obtenir_Antennes(); break;
		case 5 : Antenne.GetAntennes_DirectementAttachees(); break;
		case 6 : affichage_menu_principal(); break;
		default: System.out.print("Choix invalide !"); afficher_menu_afficherall(); //Si l'utilisateur entre une valeur inconnu on relance la fonction principal
	}
	affichage_menu_principal();
}	
	
	public static void afficher_menu_parametres() throws IOException, URISyntaxException { //Permet d'afficher le menu des parametres
		System.out.println("");
		System.out.println("----Menu des parametres----");
		System.out.println("[1] Modifier une antenne");
		System.out.println("[2] Modifier un pylone");
		System.out.println("[3] Menu Principal");
		System.out.println("----------------------");
		System.out.print("Quel est votre choix : ");
		final byte choix = clavier.nextByte(); clavier.nextLine();
		switch(choix) {
			case 1 : break; //To do
			case 2 : break; //To do
			case 3 : affichage_menu_principal(); break;
			default: System.out.print("Choix invalide !"); afficher_menu_parametres(); //Si l'utilisateur entre une valeur inconnu on relance la fonction principal
		}
		affichage_menu_principal();
		
	}
	
	public static void afficher_menu_recherche_reseau() throws IOException, URISyntaxException { //Permet d'afficher le menu des parametres
		System.out.println("");
		System.out.println("----Menu Recherche Reseau----");
		System.out.println("[1] Rechercher un equipement");
		System.out.println("[2] Menu Principal");
		System.out.println("----------------------");
		System.out.print("Quel est votre choix : ");
		final byte choix = clavier.nextByte(); clavier.nextLine();
		switch(choix) {
			case 1 : Clients.recuperation_info_equipement(); break;
			case 2 : affichage_menu_principal(); break;
			default: System.out.print("Choix invalide !"); afficher_menu_recherche_reseau(); //Si l'utilisateur entre une valeur inconnu on relance la fonction principal
		}
		affichage_menu_principal();
		
	}


}






package projetRT;

import java.util.Scanner;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Pylone {

	
	static Scanner clavier = new Scanner(System.in);
	final static int Nb_tab_pylones = 10000;
	static Pylone[] tab_pylones = new Pylone [Nb_tab_pylones]; 
	static int Nb_pylones_total = 0;
	
	//Constantes
	public static final String NOM_PYLONE="Pylone_Inconnu";
	public static final Double LOCALISATION_PYLONE_LONG=0.0;
	public static final Double LOCALISATION_PYLONE_LAT=0.0;
	public static final int NBANTENNE_PYLONE=0;
	
	//Variable de classe
	public static int nbPylones=0;
	
	//variable d'instance
	
	Antenne[] antennes_relies;
	public String nom;
	public Double longi;
	public Double lat;

	

	public Pylone (String nom, Double longi, Double lat, Antenne[] antennes_relies) {
		this.antennes_relies = antennes_relies;
		this.nom = nom;
		this.longi =longi;
		this.lat =lat;

	}
	

	public static void Creation_pylone() {
		try {
		int cpt=0;
		Antenne[] new_antennes = new Antenne[9999];
		System.out.println("");
    	System.out.println("Création d'un pylone");
		System.out.print("Nom du pylone : ");
	    String new_nom = clavier.nextLine();
	    
	    System.out.print("Longitude du pylone : ");
	    double new_longi = clavier.nextDouble();clavier.nextLine();
	    
	    System.out.print("Latitude du pylone : ");
	    double new_lat = clavier.nextDouble(); clavier.nextLine();
	 
	    
	    System.out.println("Quelles antennes sont reliées à ce pylone : ");

	    for(int i=0;i<Antenne.Nb_antennes_totales;i++){ //affichage de toutes les antennes existantes

	    	System.out.println("[-] " + Antenne.tab_antennes[i].id_antenne);
	    }
	    
	    String choix_antenne = "1";
	    while( true ) {	
	    System.out.print("Merci de rentrer le nom d'une antenne à relier au pylone (0 pour quitter) : ");
	    choix_antenne = clavier.nextLine();
	    if (choix_antenne.equals("0")) break; //tant que l'user ne tape pas 0, on propose de taper un nom d'antenne a relier en plus
	    for(int i=0;i<Antenne.Nb_antennes_totales;i++){ //cherche parmis toutes les antennes existantes et 

	    	if (Antenne.tab_antennes[i].id_antenne.equals(choix_antenne ) ) {	//ajoute celle dont l'id correspond a celle entree
    			new_antennes[cpt]=(Antenne.tab_antennes[i]); //dans la case suivant la derni�re remplie
    			cpt++;
    			System.out.println("Cette antenne sera ajoutée au pylone !");
    			break;
    		}
	    }
	   
    }
	    

	    System.out.println("Votre pylone "+new_nom + " a bien ete crée !");
	    

	    tab_pylones[Nb_pylones_total]= new Pylone (new_nom,new_longi, new_lat,new_antennes);
	    Nb_pylones_total++;	//on incremente le nombre pylone creees
		}
	    catch(Exception e){
            System.out.println("Mauvaise valeur, la longititude et la latitude doivent etre des nombres !");
            clavier.nextLine();
            Creation_pylone();
        }    
	}
	
	public static void  afficher_all() throws IOException, URISyntaxException{ //affiche tous les pylones existants
		for(int i=0;i<Nb_pylones_total;i++){
			System.out.println("Le nom du pylone est "+tab_pylones[i].nom);
			System.out.println("La longitude est "+tab_pylones[i].longi);
			System.out.println("La latittude est "+tab_pylones[i].lat);
			System.out.println("Les antennes relies a ce pylone sont "+tab_pylones[i].antennes_relies);
			System.out.println(" ");
			System.out.println("Voulez-vous visualiser ce pylone sur OpenStreetMap [0-Non | 1-Oui] ?");
			
			final byte choix = clavier.nextByte(); clavier.nextLine();
			switch(choix) {
				case 1 : openstreetmap_affiche(tab_pylones[i].longi,tab_pylones[i].lat); break;
				case 2 : afficher_all(); break;
				default: System.out.print("Choix invalide !"); afficher_all(); //Si l'utilisateur entre une valeur inconnu on relance la fonction principal
			
			}
			
		 }
	}
	
	public static void openstreetmap_affiche(double longitude, double latitude) throws IOException, URISyntaxException { //permet d'afficher le pylone sur OpenStreetMap par rapport a ses coordonn�es g�ographique
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			
			Desktop.getDesktop().browse(new URI("https://www.openstreetmap.org/#map=10/" + latitude + "/" + longitude));
			}
	}
	
 
	public void Supprime() {
    	int index=0; //contient emplacement pylone a sup
		
		
		for (int i=0 ; i< Nb_pylones_total ; i++ ) { //on recherche l'index du pylone fournie en parcourant tout le tab_pylone
			if ( this == tab_pylones[i] ) index=i;
		}
		
		
		

		for (int i=index ; i< (Nb_pylones_total-1) ; i++ ) {
			tab_pylones[i] = tab_pylones[i+1] ;
		}
		tab_pylones[Nb_pylones_total-1]=null; //une fois arrive a la derniere case on la supprime pour eviter un doublon
		
		Nb_pylones_total-- ;
		
		for (int i=0; antennes_relies[i]!=null;i++) { //on parcourt l'ensemble des antennes relies au pylone a supprimer
			this.antennes_relies[i].Supprime();		//et on les suppriment toutes
			
		}
			
	
		System.out.println("Vous avez bien supprime " + this.nom +"\n");
    	
    }
    
	public static void Supprimer(){	//quand on supprime un pylone
		if (Nb_pylones_total<1) {
			System.out.println("Impossible, il n'existe aucun pylone !");
			return;
		}
		int choix_utilisateur;
		
		System.out.println("Veuillez selectionner le pylone que vous voulez supprimer. (mettre le nombre associé)");
		
		for (int i=0; i<Nb_pylones_total ; i++) // listage de toutes les pylones existants
			System.out.println( (i+1) + ": " + tab_pylones[i].nom);

		try {
			choix_utilisateur = clavier.nextInt(); clavier.nextLine(); //entree du choix par l'utilisateur
		} catch (java.util.InputMismatchException e) { //si l'utilisateur entre autre chose qu'un entier, l'erreur est catchee
			choix_utilisateur=0;	} //et on simule une saisie erronee	
			
		while (choix_utilisateur < 1 || choix_utilisateur > Nb_pylones_total) { //si le numero du pylone n'existe pas
				
			System.out.println("Veuillez choisir un pylone existant."); //on refuse tant que le numero entre est faux
			try {
				choix_utilisateur = clavier.nextInt(); clavier.nextLine(); //entree du choix
			} catch (java.util.InputMismatchException e) {
				choix_utilisateur=0; 
			}
		}
		
		tab_pylones[choix_utilisateur-1].Supprime();

		
}

	public void Obtenir_Antenne() {
		for (int i=0; this.antennes_relies[i]!=null;i++) { //on parcourt l'ensemble des pylones auquel le noeud est relie
			System.out.println("Informations sur une antenne reliee au noeud");
			this.antennes_relies[i].afficher();	//on affiche les infos sur chacunes des antennes
		}
		
		
	}
	
}	

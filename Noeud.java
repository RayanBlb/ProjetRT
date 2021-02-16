package projetRT;
import java.util.Scanner;

public class Noeud {

	final static int Nb_tab_noeuds = 10000;
	static Noeud[] tab_noeuds = new Noeud [Nb_tab_noeuds]; 
	static int Nb_noeuds_total = 0;

	Pylone[] pylones_relies;
	String ip;
	int port;
	String localisation;
	int id_noeud;

	
	
	static Scanner clavier = new Scanner(System.in);
	
	public Noeud (String ip, int port,String localisation,int id_noeud, Pylone[] pylones_relies){
		 this.pylones_relies = new Pylone[0];
		 this.ip=ip;
		 this.port=port;
		 this.localisation=localisation;
		 this.id_noeud=id_noeud;
		 this.pylones_relies=pylones_relies;
	}

	 
	 
	 
	public static void Creation_noeud(){
				//verifie si au moins 1 pylone existe sinon quitte
				if (Pylone.Nb_pylones_total == 0) {
					System.out.println("Attention ! Avant de faire un noeud vous devez créer un pylone !");
					return ;
				}
				
				try {
				Pylone[] new_pylones = new Pylone[9999];
				int cpt = 0;
		

			    
			    System.out.print("Ip du noeud : ");
			    String new_ip = clavier.nextLine();
			    
			    System.out.print("Port du noeud : ");
			    int new_port = clavier.nextInt(); clavier.nextLine();
			    
			    System.out.print("Localisation : ");
			    String new_localisation = clavier.nextLine();
			    
			    System.out.print("Identifiant du noeud : ");
			    int new_id_noeud = clavier.nextInt(); clavier.nextLine();

			    System.out.println("Quelles pylones ce noeud va-il relier ?");
			    for(int i=0;i<Pylone.Nb_pylones_total;i++){ //affichage de tous les pylones existants
			    		System.out.println("- " + Pylone.tab_pylones[i].nom);
			    }
			    
			    String choix_pylone = "1";
			    while( true) {	
			    System.out.println("Entrez un pylone qui sera relier, 0 pour sortir");
			    choix_pylone = clavier.nextLine();
			    if (choix_pylone.equals("0")) break; //tant que l'user ne tape pas 0, on propose de taper un nom de pylone a relier en plus
			    
			    for(int i=0;i<Pylone.Nb_pylones_total;i++){ //cherche parmis tous les pylones existants et 
		    		
			    	if (Pylone.tab_pylones[i].nom.equals( choix_pylone) ) {	//ajoute celui dont le nom correspond a celui entre
		    			new_pylones[cpt]=(Pylone.tab_pylones[i]); //dans la case suivant la derni�re remplie
		    			cpt++;
		    			System.out.println("Ce pylone sera ajoutee !  ");   			
		    			break;
		    		} 
			    }
			    
		    }
			    System.out.println("Votre noeud "+ new_id_noeud + " a bien ete crée !");
			    
			    tab_noeuds[Nb_noeuds_total]= new Noeud (new_ip, new_port, new_localisation,new_id_noeud,new_pylones);
			    Nb_noeuds_total++; //on incremente le nombre de noeuds crees
				
		
				}
				catch(Exception e){
		            System.out.println("Mauvaise valeur, l'identifiant et le port doivent etre des nombres !");
		            clavier.nextLine();
		            Creation_noeud();
		        }

	
	 }
	public static void  afficher_all(){ //affiche tous les noeuds existants
		for(int i=0;i<Nb_noeuds_total;i++){
			System.out.println("L'id du noeud est " + tab_noeuds[i].id_noeud);
			System.out.println("Son adresse IP est " + tab_noeuds[i].ip);
			System.out.println("Son port est " + tab_noeuds[i].port);
			System.out.println("Le noeud se situe a " + tab_noeuds[i].localisation);
			System.out.println("Les pylones relies a ce noeud sont " + tab_noeuds[i].pylones_relies);
		 }
	}
	

	public void Supprime() {
    	int index=0; //emplacement noeud a supprimer
		
		
		for (int i=0 ; i< Nb_noeuds_total ; i++ ) { //on recherche l'index du noeud fournie en parcourant tout le tab_noeud
			if ( this == tab_noeuds[i] ) index=i;
		}
		

		for (int i=index ; i< (Nb_noeuds_total-1) ; i++ ) {
			tab_noeuds[i] = tab_noeuds[i+1] ;
		}
		tab_noeuds[Nb_noeuds_total-1]=null; //une fois arrive a la derniere case on la supprime pour eviter un doublon
		
		Nb_noeuds_total-- ;
		
		for (int i=0; this.pylones_relies[i]!=null;i++) { //on parcourt l'ensemble des pylones auquel le noeud est relie
			
			this.pylones_relies[i].Supprime();		//et on les suppriment tous
			
		}
			
	
		System.out.println("Vous avez bien supprime " + this.id_noeud +"\n");
    	
    }
    
	public static void Supprimer(){	//quand on supprime un pylone
		if (Nb_noeuds_total<1) {
			System.out.println("Impossible, il n'existe aucun pylone !");
			return;
		}
		int choix_utilisateur;
		
		System.out.println("Veuillez selectionner le pylone que vous voulez supprimer.");
		
		for (int i=0; i<Nb_noeuds_total ; i++) // listage de toutes les noeuds existants
			System.out.println( (i+1) + ": " + tab_noeuds[i].id_noeud);

		try {
			choix_utilisateur = clavier.nextInt(); clavier.nextLine(); //entree du choix par l'utilisateur
		} catch (java.util.InputMismatchException e) { //si l'utilisateur entre autre chose qu'un entier, l'erreur est catchee
			choix_utilisateur=0;	} //et on simule une saisie erronee	
			
		while (choix_utilisateur < 1 || choix_utilisateur > Nb_noeuds_total) { //si le numero du pylone n'existe pas
				
			System.out.println("Veuillez choisir un pylone existant."); //on refuse tant que le numero entre est faux
			try {
				choix_utilisateur = clavier.nextInt(); clavier.nextLine(); //entree du choix
			} catch (java.util.InputMismatchException e) {
				choix_utilisateur=0; 
			}
		}
		
		tab_noeuds[choix_utilisateur-1].Supprime();
	}
		
	public static void Obtenir_Antennes() {	//affiche la liste des antennes associees au noeud choisi
		if (Nb_noeuds_total<1) {	//on ne peut avoir des infos sur des noeuds s'il n'en existe pas
			System.out.println("Impossible, il n'existe aucun noeud !");
			return;
		}
		
		int choix_utilisateur;
		
		System.out.println("Veuillez selectionner le noeud dont vous voulez les infos ");
		
		for (int i=0; i<Nb_noeuds_total ; i++) // listage de toutes les noeuds existants
			System.out.println( (i+1) + ": " + tab_noeuds[i].id_noeud);

		try {
			choix_utilisateur = clavier.nextInt(); clavier.nextLine(); //entree du choix par l'utilisateur
		} catch (java.util.InputMismatchException e) { //si l'utilisateur entre autre chose qu'un entier, l'erreur est catchee
			choix_utilisateur=0;	} //et on simule une saisie erronee	
			
		while (choix_utilisateur < 1 || choix_utilisateur > Nb_noeuds_total) { //si le numero du noeud n'existe pas
				
			System.out.println("Veuillez choisir un noeud existant."); //on refuse tant que le numero entre est faux
			try {
				choix_utilisateur = clavier.nextInt(); clavier.nextLine(); //entree du choix
			} catch (java.util.InputMismatchException e) {
				choix_utilisateur=0; 
			}
		}	
		
		
		for (int i=0; tab_noeuds[choix_utilisateur-1].pylones_relies[i]!=null;i++) { //on parcourt l'ensemble des pylones auquel le noeud est relie
			tab_noeuds[choix_utilisateur-1].pylones_relies[i].Obtenir_Antenne();	
		}
	
	
	
	
}


	
}

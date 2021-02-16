package projetRT;
import java.util.Scanner;

public class Antenne {
	
	final static int Nb_tab_antennes = 10000;
	public static Antenne[] tab_antennes = new Antenne [Nb_tab_antennes]; 
	static int Nb_antennes_totales = 0;

	String id_antenne;
	String direct_ou_omni;
	int frequence;
	int puissance;
	int sensibilite;
	String polarisation;
	String orientation;
	int ouverture;

	
	
	static Scanner clavier = new Scanner(System.in);
	
	public Antenne (String id_antenne, String direct_ou_omni, int frequence, int puissance, int sensibilite, String polarisation, String orientation, int ouverture){
		 this.id_antenne = id_antenne;
		 this.direct_ou_omni=direct_ou_omni;
		 this.frequence=frequence;
		 this.puissance=puissance;
		 this.sensibilite=sensibilite;
		 this.polarisation=polarisation;
		 this.orientation=orientation;
		 this.ouverture=ouverture;
		 
	}
    public static void Creation_antenne(){
    	try {
    		System.out.println("");
	    	System.out.println("Creation d'une antenne a associer au parc");
	    	System.out.print("Identifiant de l'antenne : ");
		    String new_id_antenne = clavier.nextLine();
		    
		    System.out.print("Antenne directive ou omnidirectionnelle : ");
		    String new_direct_ou_omni = clavier.nextLine();
		    
		    System.out.print("Frequence (hz) : ");
		    int new_frequence = clavier.nextInt(); clavier.nextLine();
		    
		    System.out.print("Puissance (db) de l'antenne : ");
		    int new_puissance = clavier.nextInt(); clavier.nextLine();
		    
		    System.out.print("Sensibilite de l'antenne : ");
		    int new_sensibilite = clavier.nextInt(); clavier.nextLine();
		    
		    System.out.print("Polarisation de l'antenne : ");
		    String new_polarisation = clavier.nextLine();
		    
		    System.out.print("Orientation de l'antenne : ");
		    String new_orientation = clavier.nextLine();
		    
		    System.out.print("Ouverture de l'antenne : ");
		    int new_ouverture = clavier.nextInt(); clavier.nextLine();
		    
		    tab_antennes[Nb_antennes_totales]= new Antenne ( new_id_antenne, new_direct_ou_omni, new_frequence, new_puissance, new_sensibilite, new_polarisation, new_orientation, new_ouverture);
		    Nb_antennes_totales++;
    	}
    	catch(Exception e){
            System.out.println("Mauvaise valeur, la frequence, la puissance, la sensibilite et l'ouverture doivent etre des nombres !");
            clavier.nextLine();
            Creation_antenne();
        }
    }

    public static void afficher_all(){
		for(int i=0;i<Nb_antennes_totales;i++){
			tab_antennes[i].afficher();
			
    
		}
    }
    
    public void afficher() { //affiche infos sur une antenne 
    	
    	System.out.println("L'antennes possede l'id numero "+ id_antenne);
		System.out.println("L'antennes est de type "+ direct_ou_omni);
		System.out.println("Sa frequence(en hz) est de "+ frequence);
		System.out.println("Sa puissance(en db) est de "+ puissance);
		System.out.println("Sa sensibilite est de "+ sensibilite);
		System.out.println("Sa polarisation est de "+ polarisation);
		System.out.println("Son orientation est de "+ orientation);
		System.out.println("L'antenne a une ouverture de "+ ouverture);
    	
    	
    }
    
 
    
    public void Supprime() {
    	int index=0; //contient emplacement antenne a sup
		Antenne id_antenne_a_sup = this ;
		
		for (int i=0 ; i< Nb_antennes_totales ; i++ ) { //on recherche l'index de l'antenne fournie en parcourant tout le tab_antennes
			if ( this == tab_antennes[i] ) index=i;
		}
		

		for (int i=index ; i< (Nb_antennes_totales-1) ; i++ ) {
			tab_antennes[i] = tab_antennes[i+1] ;
		}
		tab_antennes[Nb_antennes_totales-1]=null; //une fois arrive a la derniere case on la supprime pour eviter un doublon
		
		Nb_antennes_totales-- ;
	
		System.out.println("Vous avez bien supprime " + id_antenne_a_sup.id_antenne +"\n");
    	
    }
    
	public static void Supprimer(){	//quand on supprime une antenne
		if (Nb_antennes_totales<1) {
			System.out.println("Impossible, il n'existe aucune antenne !");
			return;
		}
		int choix_utilisateur;
		
		System.out.println("Veuillez selectionner l'antenne que vous voulez supprimer. (mettre le nombre associé)");
		
		for (int i=0; i<Nb_antennes_totales ; i++) // listage de toutes les antennes existantes
			System.out.println( (i+1) + ": " + tab_antennes[i].id_antenne);

		try {
			choix_utilisateur = clavier.nextInt(); clavier.nextLine(); //entree du choix par l'utilisateur
		} catch (java.util.InputMismatchException e) { //si l'utilisateur entre autre chose qu'un entier, l'erreur est catchee
			choix_utilisateur=0;	} //et on simule une saisie erronee	
			
		while (choix_utilisateur < 1 || choix_utilisateur > Nb_antennes_totales) { //si le numero de l'antenne n'existe pas
				
			System.out.println("Veuillez choisir une antenne existante."); //on refuse tant que le numero entre est faux
			try {
				choix_utilisateur = clavier.nextInt(); clavier.nextLine(); //entree du choix
			} catch (java.util.InputMismatchException e) {
				choix_utilisateur=0; 
			}
		}
		
		tab_antennes[choix_utilisateur-1].Supprime();
	}
		
		public  static void GetAntennes_DirectementAttachees(){ //affiche pour l'antenne choisie liste antennes avec lesquelles elle peut directement communiquer
			boolean trouve=false;
			//affichage selection antenne
			if (Nb_antennes_totales<1) {	//on ne peut avoir des infos sur des noeuds s'il n'en existe pas
				System.out.println("Impossible, il n'existe aucun noeud !");
				return;
			}
			
			int choix_utilisateur;
			
			System.out.println("Veuillez selectionner l'antenne dont vous voulez connaitre les autres antennes directement joignables");
			
			for (int i=0; i<Nb_antennes_totales ; i++) // listage de toutes les antenne existants
				System.out.println( (i+1) + ": " + tab_antennes[i].id_antenne);

			try {
				choix_utilisateur = clavier.nextInt(); clavier.nextLine(); //entree du choix par l'utilisateur
			} catch (java.util.InputMismatchException e) { //si l'utilisateur entre autre chose qu'un entier, l'erreur est catchee
				choix_utilisateur=0;	} //et on simule une saisie erronee	
				
			while (choix_utilisateur < 1 || choix_utilisateur > Nb_antennes_totales) { //si le numero du noeud n'existe pas
					
				System.out.println("Veuillez choisir une antenne existante."); //on refuse tant que le numero entre est faux
				try {
					choix_utilisateur = clavier.nextInt(); clavier.nextLine(); //entree du choix
				} catch (java.util.InputMismatchException e) {
					choix_utilisateur=0; 
				}
			}	
			Pylone pylone_pere = null;
			Antenne choisie = tab_antennes[choix_utilisateur-1];
			for (int i=0; Pylone.tab_pylones[i]!=null;i++) { //pour chaques pylones 
				
				for (int j=0; Pylone.tab_pylones[i]!=null;i++) { //on parcourt les antennes qui lui sont relies
					
				if (Pylone.tab_pylones[i].antennes_relies[j] == choisie )	//et si l'une d'entre elle est celle recherche
					pylone_pere = Pylone.tab_pylones[j]; //puis on identifie le pylone a laquelle elle appartient
				}
			}
			if (pylone_pere==null) return;
			
			for (int j=0; pylone_pere.antennes_relies[j]!=null;j++) { //puis pour celui-ci on identifie les antennes qui lui sont relies //en regardant dans son tableau antennes_relies
				 
				if (choisie!=pylone_pere.antennes_relies[j]) { //on affiche seulement si l'antenne fille n'est pas deja elle meme
					System.out.println("\nCette antenne pourra joindre directement :"); //
					pylone_pere.antennes_relies[j].afficher();
					trouve=true;
				}
			}
			if (!trouve) System.out.println("\nCette antenne ne pourra pas joindre d'antennes directement "); // si aucune antenne n'est trouvee
			
			
		}		

}
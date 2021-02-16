package projetRT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class Serveur {
	
	public int port;
	public static String resultat[];
	
	static Scanner clavier = new Scanner(System.in);
	
	
	public Serveur(int port) {
		this.port = port;
	}
	
	
	public static void serveur_mode() throws IOException {
		System.out.println("");
		System.out.println("----Menu Serveur----");
		System.out.println("[1] Nouvelle antenne");
		System.out.println("[2] Nouveau pylone");
		System.out.println("[3] Nouveau noeud");
		System.out.println("----------------------");
		System.out.print("Quel est votre choix : ");
		
		final byte choix = clavier.nextByte(); clavier.nextLine();
		
		switch(choix) {
		case 1 : new_equipement("antenne"); break;
		case 2 : new_equipement("pylone"); break;
		case 3 : new_equipement("noeud"); break;
		default: System.out.print("Choix invalide !"); serveur_mode(); //Si l'utilisateur entre une valeur inconnu on relance la fonction principal
		}
	}
	
	public static void new_equipement(String equipement) throws IOException{
		if(equipement =="antenne") {
			
			System.out.println("");
	    	System.out.println("Creation d'une antenne");
			
			System.out.println("Identifiant de l'antenne : ");
		    String new_id_antenne = clavier.nextLine();
		    
		    System.out.println("Antenne directive ou omnidirectionnelle : ");
		    String new_direct_ou_omni = clavier.nextLine();
		    
		    System.out.println("Frequence (hz) : ");
		    String new_frequence = clavier.nextLine();
		    
		    System.out.println("Puissance (db) de l'antenne : ");
		    String new_puissance = clavier.nextLine();
		    
		    System.out.println("Sensibilite de l'antenne : ");
		    String new_sensibilite = clavier.nextLine();
		    
		    System.out.println("Polarisation de l'antenne : ");
		    String new_polarisation = clavier.nextLine();
		    
		    System.out.println("Orientation de l'antenne : ");
		    String new_orientation = clavier.nextLine();
		    
		    System.out.println("Ouverture de l'antenne : ");
		    String new_ouverture = clavier.nextLine();
		    
		    resultat = null;
		    
		    resultat = new String[]{"Equipement : Antenne"," / Identifiant de l'antenne : "+new_id_antenne," / Antenne directive ou omnidirectionnelle : "+new_direct_ou_omni," / Frequence (hz) : "+new_frequence," / Puissance (db) de l'antenne : "+new_puissance," / SensibilitÃ© de l'antenne : "+new_sensibilite," / Polarisation de l'antenne : "+new_polarisation," / Orientation de l'antenne : "+new_orientation," / Ouverture de l'antenne : "+new_ouverture};
		    
			
		}else if(equipement == "noeud") {
			System.out.println("Ip du noeud : ");
		    String new_ip = clavier.nextLine();
		    
		    System.out.println("Port du noeud : ");
		    String new_port = clavier.nextLine();
		    
		    System.out.println("Localisation : ");
		    String new_localisation = clavier.nextLine();
		    
		    System.out.println("Identifiant du noeud : ");
		    String new_id_noeud = clavier.nextLine();
		    
		    System.out.println("Quelles pylones ce noeud va-il relier : ");
		    String choix_noeud = clavier.nextLine();
		    
		    System.out.println("Entrez un pylone qui sera relier : ");
		    String choix_pylone = clavier.nextLine();
		    
		    resultat = null;
		    
		    resultat = new String[]{"Equipement : noeud"," / Ip du noeud : "+new_ip," / Port du noeud : "+new_port," / Localisation : "+new_localisation," / Identifiant du noeud : "+new_id_noeud," / noeud relier : "+choix_noeud," / pylone relier : "+choix_pylone};
		    
		}else if(equipement == "pylone") {
			System.out.println("Nom du pylone : ");
		    String new_nom = clavier.nextLine();
		    
		    System.out.println("Longitude du pylone : ");
		    String new_longi = clavier.nextLine();
		    
		    System.out.println("Latitude du pylone : ");
		    String new_lat = clavier.nextLine();
		    
		    System.out.println("Quelles antennes sont reliees a ce pylone : ");
		    String choix_antenne_reliees = clavier.nextLine();
		    
		    System.out.print("nom d'une antenne a relier au pylone  : ");
		    String choix_noeud_a_relier = clavier.nextLine();
		    
		    resultat = null;
		    
		    resultat = new String[]{"Equipement : pylone"," / Nom du pylone : "+new_nom," / Longitude du pylone : "+new_longi," / Latitude du pylone : "+new_lat," / Antennes qui sont reliees a ce pylone : "+choix_antenne_reliees," / antenne a relier au pylone  : "+choix_noeud_a_relier};
		    
		}
		
		envoie_info_equipement();
		
	}
	
	public static void envoie_info_equipement() throws IOException {
		
		ServerSocket listener = null;
		System.out.println("Le serveur est en attente de requetes d'utilisateurs...");
		
		int clientNumber = 0;
		
		try {
			listener = new ServerSocket(9999);
			} catch (IOException e) {
				System.out.println(e);
				System.exit(1);
				}
		try {
			while (true)  {
				Socket socketOfServer = listener.accept();
				new ModeThread(socketOfServer, clientNumber++).start();
				}
			} finally {
				listener.close();
				}
		}
	
	
	private static void log(String message) {
		System.out.println(message);
		}
	
	private static class ModeThread extends Thread {
		private int clientNumber;
		private Socket socketOfServer;
		
		public ModeThread(Socket socketOfServer, int clientNumber) {
			this.clientNumber = clientNumber;
			this.socketOfServer = socketOfServer;
			
			log("Nouveau client# " + this.clientNumber + " à " + socketOfServer);
			
		}
		
		public void run() {
			
			try {
				
				BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
				BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
				
				while (true) {
					
					String line = is.readLine();
					
					if (line.equals("Recup_equipement")) {
						
						os.write(">> OK - DEMANDE ACCEPTEE || ");
						os.write(String.join(" ", resultat));
						os.newLine();
						os.flush();
						
						break;
					}
				}
				
			} catch (IOException e) {
				
				System.out.println(e);
				
				e.printStackTrace();
				
			}
			
		}
		
	}
	
	
}
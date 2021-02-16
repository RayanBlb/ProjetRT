package projetRT;

import java.net.*;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Clients {

	public String hostname;
	public int port;
	
	static Scanner clavier = new Scanner(System.in);

	public Clients(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}
	
	public static void recuperation_info_equipement() {
		System.out.println("Quelle est l'adresse IP de l'élément que vous essayer de joindre ?");
		String adresse_IP = clavier.nextLine();
		
		recuperation_info_equipement(adresse_IP);
	}
	

	public static void recuperation_info_equipement(String serverHost) {
		
		Socket socketOfClient = null;
		BufferedWriter os = null;
		BufferedReader is = null;
		
		try {
			socketOfClient = new Socket(serverHost, 9999);
			os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
			is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
			} catch (UnknownHostException e) {
				System.err.println("Je ne connais pas l'hôte : " + serverHost);
				return;
				} catch (IOException e) {
					System.err.println("Impossible de se connecter : " + serverHost);
					return;
					}
		try {
			os.write("Recup_equipement");
			os.newLine();
			os.flush();
			String responseLine;
			while ((responseLine = is.readLine()) != null) {
				System.out.println("Serveur: " + responseLine);
				break;
				}
			
			os.close();
			is.close();
			socketOfClient.close();
			} catch (UnknownHostException e) {
				System.err.println("Tentative de se connecter à un hôte inconnu : " + e);
				} catch (IOException e) {
					System.err.println("IOException:  " + e);
					}
		}
}

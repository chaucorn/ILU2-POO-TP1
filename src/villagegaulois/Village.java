package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	private int nbEtals;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	// TP1: Classe interne Marche
	private static class Marche{
		private Etal[] etals;
		
		// Constructeur de la classe Marche
		private Marche(int nbEtals) {
			etals =  new Etal[nbEtals]; // initializing 
			for (int i = 0; i < nbEtals; i++) {
				etals[i] = new Etal();
			}
		}
		
		// Methode de la classe Marche:
		
		// MetMethode de Marchéhode utiliserEtal
		 private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			 // case indice invalid
			 if ( indiceEtal < 0 || indiceEtal > etals.length -1 ) {
				 throw new ArrayIndexOutOfBoundsException("indiceEtal trop petit ou trop grand");
			 }else {
				 etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			 } 
		 }
		 
		 // Methode de Marché trouverEtalLibre
		 private int trouverEtalLibre() {
			 for (int i = 0; i < etals.length; i++) {
				 if(!etals[i].isEtalOccupe()) {
					 return i;
				 }
			 }
			 return -1;
		 }
		 
		 //Methode de Marché trouverEtal
		 private Etal[] trouverEtals(String produit) {
			 int nbEtalsHavingProduit = 0;
			 for (int i = 0; i < etals.length; i++) {
				 if(etals[i].contientProduit(produit)) {
					 nbEtalsHavingProduit++;
				 }
			 }
			 
			 Etal[] etalsHaveProduit = new Etal[nbEtalsHavingProduit];
			 int count = 0;
			 for(int i = 0; i < etals.length; i++) {
				 if (etals[i].contientProduit(produit)) {
					etalsHaveProduit[count] = etals[i];
					count ++;
				}
			 }
			 return etalsHaveProduit;
		 }
		 
		 //Methode de Marché trouverVendeur
		 private Etal trouverVendeur(Gaulois gaulois) {
			 for(int i = 0; i < etals.length; i++) {
				 if(etals[i].getVendeur() == gaulois) {
					 return etals[i];
				 }
			 }
			 return null;
		 }
		 
		 // Methode de Marché afficherMarche
		 private void afficherMarche(){
			 int nbEtalVide = trouverEtalLibre(); //pas besoin de l'instance etals
			 for(int i = 0; i < etals.length; i++) {
				 if(etals[i].isEtalOccupe()) {
					 System.out.println(etals[i].afficherEtal());
				 }
			 }
			 System.out.println("Il reste "+ nbEtalVide + " étals non utilisés dans le marché.\n"); 
		 }
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	// Methode de Village installerVendeur
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + "\n");
		int indiceEtalVide = trouverEtalLibre();
		if (indiceEtalVide == -1) {
			chaine.append("Il n'y a plus d'etal vide dans le marché.\n");
			}else {
				etals[indiceEtalVide].occuperEtal(vendeur, produit, nbProduit);
				chaine.append("Le vendeur" + vendeur.getNom() + " vend des " + produit + " à l'étal n°" + indiceEtalVide );
				}
		return chaine.toString();
	}
}
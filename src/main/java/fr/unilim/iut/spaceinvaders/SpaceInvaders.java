package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;

public class SpaceInvaders {
	private static final char MARQUE_FIN_LIGNE = '\n';
	private static final char MARQUE_VIDE = '.';
	private static final char MARQUE_VAISSEAU = 'V';
	//ATTRIBUTS
	int longueur;
	int hauteur;
	Vaisseau vaisseau;

	//CONSTRUCTEUR
	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}
	
	//METHODES
	public void positionnerUnNouveauVaisseau(int x, int y) {
		if(x>=longueur)
			throw new HorsEspaceJeuException("Vous etes en dehors de l'espace de jeu");
		this.vaisseau = new Vaisseau (x,y);
	}
	
	//TOSTRING
	@Override
	public String toString() {
		return recupererEspaceJeuDansChaineASCII();
	}
	
	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (aUnVaisseauQuiOccupeLaPosition(x, y))
			marque=MARQUE_VAISSEAU;
		else
			marque=MARQUE_VIDE;
		return marque;
	}
	
	//REFACTOR DEGRE 0
	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && aUnVaisseauQuiOccupe(x, y);
	}

	private boolean aUnVaisseauQuiOccupe(int x, int y) {
		return vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnVaisseau() {
		return vaisseau!=null;
	}
}	

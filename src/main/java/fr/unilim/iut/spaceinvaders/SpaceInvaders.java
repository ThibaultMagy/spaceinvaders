package fr.unilim.iut.spaceinvaders;

public class SpaceInvaders {
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
		Vaisseau vaisseau = new Vaisseau (x,y);
	}
	
	//TOSTRING
	@Override
	public String toString() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int x = 0; x < hauteur; x++) {
			for (int y = 0; y < longueur; y++) {
				if (vaisseau.occupeLaPosition(x, y)) {
				     espaceDeJeu.append('V');
				}
				else {
					espaceDeJeu.append('.');
				}
			}
			espaceDeJeu.append('\n');
		}
		return espaceDeJeu.toString();
	}
}	

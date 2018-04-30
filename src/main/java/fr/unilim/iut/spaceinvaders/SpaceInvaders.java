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
		 this.vaisseau = new Vaisseau (x,y);
	}
	
	//TOSTRING
	@Override
	public String toString() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				if (this.aUnVaisseau() && aUnVaisseauQuiOccupe(x, y))
					espaceDeJeu.append('V');
				else
					espaceDeJeu.append('.');
			}
			espaceDeJeu.append('\n');
		}
		return espaceDeJeu.toString();
	}

	private boolean aUnVaisseauQuiOccupe(int x, int y) {
		return vaisseau.occupeLaPosition(x, y);
	}

	private boolean aUnVaisseau() {
		return vaisseau!=null;
	}
}	

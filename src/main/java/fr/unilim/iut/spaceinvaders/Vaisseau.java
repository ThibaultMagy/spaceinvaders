package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	//ATTRIBUTS
	Position origine;
    int longueur;
    int hauteur;

	//CONSTRUCTEURS
    public Vaisseau(int longueur, int hauteur, int x, int y) {
 	   this.longueur=longueur;
 	   this.hauteur=hauteur;
 	   this.origine = new Position (x,y);
 	   }
	public Vaisseau(int longueur, int hauteur) {
		this(longueur,hauteur,0,0);
	}

	//METHODES
	public boolean occupeLaPosition(int x, int y) {
		 return (estAbscisseCouverte(x) && ordonneeEstCouverte(y));
	}
	public boolean ordonneeEstCouverte(int y) {
		return (ordonneeLaPlusBasse()<=y) && (y<=ordonneeLaPlusHaute());
	}
	public int ordonneeLaPlusHaute() {
		return this.origine.ordonnee();
	}
	public int ordonneeLaPlusBasse() {
		return ordonneeLaPlusHaute()-this.hauteur+1;
	}
	public boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche()<=x) && (x<=abscisseLaPlusADroite());
	}
	public int abscisseLaPlusAGauche() {
		return this.origine.abscisse();
	}
	public int abscisseLaPlusADroite() {
		return abscisseLaPlusAGauche()+this.longueur-1;
	}
	public void seDeplacerVersLaDroite() {
	    this.origine.changerAbscisse(this.origine.abscisse()+1);
	}
    public void seDeplacerVersLaGauche() {
		this.origine.changerAbscisse(this.origine.abscisse()-1);
	}
	public void positionner(int x, int y) {
    	this.origine.changerAbscisse(x);
		this.origine.changerOrdonnee(y);
   }
	
	
	
	
	//GETTERS SETTERS
	public int abscisse() {
		return origine.x;
	}
}
package fr.unilim.iut.spaceinvaders;

public class Vaisseau {
	//ATTRIBUTS
	Position origine;
    Dimension dimension;
    private int vitesse;

	//CONSTRUCTEURS
    public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		this.dimension = dimension;
		this.origine = positionOrigine;
		this.vitesse = vitesse;
	}
    public Vaisseau(Dimension dimension, Position positionOrigine) {
 	   this(dimension,positionOrigine,1);
    }
	public Vaisseau(Dimension dimension) {
		this(dimension,new Position(0, 0),1);
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
		return ordonneeLaPlusHaute()-this.dimension.hauteur()+1;
	}
	public boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche()<=x) && (x<=abscisseLaPlusADroite());
	}
	public int abscisseLaPlusAGauche() {
		return this.origine.abscisse();
	}
	public int abscisseLaPlusADroite() {
		return abscisseLaPlusAGauche()+this.dimension.longueur()-1;
	}
	public void seDeplacerVersLaDroite() {
		this.origine.changerAbscisse(this.origine.abscisse() + vitesse);
	}
    
    public void seDeplacerVersLaGauche() {
		this.origine.changerAbscisse(this.origine.abscisse() - vitesse);
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
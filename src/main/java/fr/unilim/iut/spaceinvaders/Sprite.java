package fr.unilim.iut.spaceinvaders;

public abstract class Sprite {

	protected Position origine;
	protected Dimension dimension;
	protected int vitesse;

	public Sprite(Dimension dimension, Position origine, int vitesse) {
		super();
		this.dimension = dimension;
		this.origine = origine;
		this.vitesse = vitesse;
	}
	public Sprite() {
		super();
	}

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
		return this.origine.ordonnee()-this.dimension.hauteur()+1;
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

	public void deplacerHorizontalementVers(Direction direction) {
		this.origine.changerAbscisse(this.origine.abscisse() + direction.valeur()*vitesse);
	}

	public void deplacerVerticalement() {
		this.origine.changerOrdonnee(this.origine.ordonnee() + vitesse);;
	}
	public void deplacerVerticalementVer(Direction direction) {
		this.origine.changerOrdonnee(this.origine.ordonnee() + direction.valeur()*vitesse);
	}
	public void positionner(int x, int y) {
	    	this.origine.changerAbscisse(x);
			this.origine.changerOrdonnee(y);
	   }

	public int abscisse() {
		return origine.x;
	}

}
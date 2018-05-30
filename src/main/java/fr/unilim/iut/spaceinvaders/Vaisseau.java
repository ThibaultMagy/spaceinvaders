package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class Vaisseau extends Sprite {
	//CONSTRUCTEURS
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		super(dimension, positionOrigine, vitesse);
	}
	
	public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		if(dimensionMissile.longueur>this.dimension.longueur || dimensionMissile.hauteur>this.dimension.hauteur) {
			throw new MissileException("Le missile est trop grand pour le vaisseau !");
		}
		else
		{
			Position positionOrigineMissile = calculerPositionMissile(dimensionMissile);
			return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
		}
	}
	
	public Position calculerPositionMissile(Dimension dimensionMissile) {
		Position position = new Position(recupererOrigineMissile(dimensionMissile), recupererOrdoneeOrigineMissile());		
		return position;
	}

	public int recupererOrdoneeOrigineMissile() {
		return this.ordonneeLaPlusBasse() - 1;
	}
	public int recupererOrigineMissile(Dimension dimensionMissile) {
		return recupererAbscisseMilieuVaisseau() - (dimensionMissile.longueur() / 2);
	}
	public int recupererAbscisseMilieuVaisseau() {
		return this.abscisseLaPlusAGauche() + (this.dimension.longueur() / 2);
	}
}
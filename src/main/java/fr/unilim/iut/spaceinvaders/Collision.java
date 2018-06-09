package fr.unilim.iut.spaceinvaders;

public class Collision {
	//
	public boolean detecterCollision(Sprite missile, Sprite envahisseur) {
		boolean b = false;
		if(corellationDesAbscisses(missile, envahisseur) && (missile.ordonneeLaPlusHaute() >= envahisseur.ordonneeLaPlusBasse())) {
			b = true;
		}
		return b;
	}

	private boolean corellationDesAbscisses(Sprite missile, Sprite envahisseur) {
		return missileTouchelEnvahisseurADroite(missile, envahisseur) && missileToucheEnvahisseurAGauche(missile, envahisseur);
	}

	private boolean missileToucheEnvahisseurAGauche(Sprite missile, Sprite envahisseur) {
		return missile.abscisseLaPlusADroite() >= envahisseur.abscisseLaPlusAGauche();
	}

	private boolean missileTouchelEnvahisseurADroite(Sprite missile, Sprite envahisseur) {
		return missile.abscisseLaPlusAGauche() <= envahisseur.abscisseLaPlusADroite();
	}
}

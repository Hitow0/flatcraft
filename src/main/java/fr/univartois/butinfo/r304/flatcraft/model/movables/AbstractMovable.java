/**
 * Ce logiciel est distribué à des fins éducatives.
 *
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 *
 * (c) 2023 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.flatcraft.model.movables;

import fr.univartois.butinfo.r304.flatcraft.model.FlatcraftGame;
import fr.univartois.butinfo.r304.flatcraft.model.IMovable;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * La classe {@link AbstractMovable} fournit une implantation de base pour tous les objets
 * élémentaires pouvant se déplacer dans le jeu.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public abstract class AbstractMovable implements IMovable {

    /**
     * Le jeu dans lequel cet objet évolue.
     */
    protected final FlatcraftGame game;

    /**
     * La position en x de cet objet.
     */
    protected final DoubleProperty xPosition;

    /**
     * La position en y de cet objet.
     */
    protected final DoubleProperty yPosition;

    /**
     * La vitesse horizontale actuelle de cet objet (en pixels/s).
     */
    protected double horizontalSpeed;

    /**
     * La vitesse verticale actuelle de cet objet (en pixels/s).
     */
    protected double verticalSpeed;

    /**
     * L'instance de {@link Sprite} représentant cet objet.
     */
    protected final ObjectProperty<Sprite> sprite;

    /**
     * Crée une nouvelle instance de AbstractMovable.
     *
     * @param game Le jeu dans lequel l'objet évolue.
     * @param xPosition La position en x initiale de l'objet.
     * @param yPosition La position en y initiale de l'objet.
     * @param sprite L'instance de {@link Sprite} représentant l'objet.
     */
    protected AbstractMovable(FlatcraftGame game, double xPosition,
            double yPosition, Sprite sprite) {
        this.game = game;
        this.xPosition = new SimpleDoubleProperty(xPosition);
        this.yPosition = new SimpleDoubleProperty(yPosition);
        this.sprite = new SimpleObjectProperty<>(sprite);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#getWidth()
     */
    @Override
    public int getWidth() {
        return sprite.get().getWidth();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#getHeight()
     */
    @Override
    public int getHeight() {
        return sprite.get().getHeight();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#setX(int)
     */
    @Override
    public void setX(int xPosition) {
        this.xPosition.set(xPosition);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#getX()
     */
    @Override
    public int getX() {
        return xPosition.intValue();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#getXProperty()
     */
    @Override
    public DoubleProperty getXProperty() {
        return xPosition;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#setY(int)
     */
    @Override
    public void setY(int yPosition) {
        this.yPosition.set(yPosition);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#getY()
     */
    @Override
    public int getY() {
        return yPosition.intValue();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#getYProperty()
     */
    @Override
    public DoubleProperty getYProperty() {
        return yPosition;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#setHorizontalSpeed(double)
     */
    @Override
    public void setHorizontalSpeed(double speed) {
        this.horizontalSpeed = speed;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#getHorizontalSpeed()
     */
    @Override
    public double getHorizontalSpeed() {
        return horizontalSpeed;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#setVerticalSpeed(double)
     */
    @Override
    public void setVerticalSpeed(double speed) {
        this.verticalSpeed = speed;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#getVerticalSpeed()
     */
    @Override
    public double getVerticalSpeed() {
        return verticalSpeed;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.flatcraft.model.IMovable#setSprite(fr.univartois.butinfo
     * .r304.flatcraft.view.Sprite)
     */
    @Override
    public void setSprite(Sprite sprite) {
        this.sprite.set(sprite);
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#getSprite()
     */
    @Override
    public Sprite getSprite() {
        return sprite.get();
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#getSpriteProperty()
     */
    @Override
    public ObjectProperty<Sprite> getSpriteProperty() {
        return sprite;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#move(long)
     */
    @Override
    public boolean move(long delta) {
        // On met à jour la position de l'objet sur l'axe x.
        int limitMaxX = game.getWidth() - getWidth();
        double newX = updatePosition(xPosition.get(), horizontalSpeed, delta, 0, limitMaxX);
        xPosition.set(newX);

        // On met à jour la position de l'objet sur l'axe y.
        int limitMaxY = game.getHeight() - getHeight();
        double newY = updatePosition(yPosition.get(), verticalSpeed, delta, 0, limitMaxY);
        yPosition.set(newY);

        if ((newX == 0) || (newX == limitMaxX)) {
            // L'objet a atteint la limite sur l'axe x.
            return false;
        }

        if ((newY == 0) || (newY == limitMaxY)) {
            // L'objet a atteint la limite sur l'axe y.
            return false;
        }

        // L'objet n'a atteint aucune limite
        return true;
    }

    /**
     * Calcule la nouvelle position d'un objet sur un axe particulier, en fonction de sa
     * position actuelle sur cet axe.
     *
     * @param current La position courante de l'objet.
     * @param speed La vitesse actuelle de l'objet.
     * @param delta Le temps qui s'est écoulé depuis la dernière mise à jour de la
     *        position de cet objet.
     * @param limitMin La limite inférieure pour la position de l'objet.
     * @param limitMax La limite supérieure pour la position de l'objet.
     *
     * @return La nouvelle position de l'objet après la mise à jour.
     */
    private static double updatePosition(double current, double speed, long delta,
            int limitMin, int limitMax) {
        double newPosition = current + (delta * speed) / 1000;

        if (newPosition < limitMin) {
            // Lorsque la limite inférieure est atteinte, on s'arrête à cette limite.
            newPosition = limitMin;

        } else if (newPosition > limitMax) {
            // Lorsque la limite supérieure est atteinte, on s'arrête à cette limite.
            newPosition = limitMax;
        }

        return newPosition;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.IMovable#self()
     */
    @Override
    public IMovable self() {
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        // L'égalité est déterminée par l'identité entre le self() de deux objets.
        // Il faut donc conserver l'adresse mémoire comme valeur de hachage.
        return super.hashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            // Les deux objets sont forcément différents.
            return false;
        }

        if (obj == this) {
            // Les deux objets sont strictement identiques.
            return true;
        }

        if (obj instanceof IMovable other) {
            // On compare les "vrais objets".
            return other.self() == self();
        }

        // L'objet donné n'est pas d'une classe compatible.
        return false;
    }

}

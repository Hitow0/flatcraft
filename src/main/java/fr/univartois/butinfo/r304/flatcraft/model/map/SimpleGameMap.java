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

package fr.univartois.butinfo.r304.flatcraft.model.map;

import fr.univartois.butinfo.r304.flatcraft.model.Cell;
import fr.univartois.butinfo.r304.flatcraft.model.GameMap;

/**
 * La classe {@link SimpleGameMap} propose une implémentation de base de {@link GameMap},
 * utilisant un tableau à deux dimensions.
 *
 * @author Daniel Le Berre
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class SimpleGameMap implements GameMap {

    /**
     * Les cellules composant cette carte.
     */
    private Cell[][] cells;

    /**
     * La hauteur de cette carte, en nombre de cellules.
     */
    private final int height;

    /**
     * La largeur de cette carte, en nombre de cellules.
     */
    private final int width;

    /**
     * La hauteur à laquelle se situe la surface du sol.
     */
    private final int soilHeight;

    /**
     * Crée une nouvelle instance de SimpleGameMap.
     *
     * @param height La hauteur de la carte, en nombre de cellules.
     * @param width La largeur de la carte, en nombre de cellules.
     * @param soilHeight La hauteur à laquelle se situe la surface du sol.
     */
    public SimpleGameMap(int height, int width, int soilHeight) {
        if ((width <= 0) || (height <= 0)) {
            throw new IllegalArgumentException("Incorrect map dimension!");
        }

        this.cells = new Cell[height][width];
        this.height = height;
        this.width = width;
        this.soilHeight = soilHeight;
        init();
    }

    /**
     * Initialise cette carte en la remplissant de cellules initialement vides.
     */
    private void init() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cellule(i,j);
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.GameMap#getHeight()
     */
    @Override
    public int getHeight() {
        return height;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.GameMap#getWidth()
     */
    @Override
    public int getWidth() {
        return width;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.GameMap#getSoilHeight()
     */
    @Override
    public int getSoilHeight() {
        return soilHeight;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.GameMap#getAt(int, int)
     */
    @Override
    public Cell getAt(int row, int column) {
        if (((row < 0) || (height <= row))) {
            throw new IllegalArgumentException("Incorrect cell location!");
        }
        if(column == 1280){
            return cells[row][79];
        }
        return cells[row][column];
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.GameMap#setAt(int, int,
     * fr.univartois.butinfo.r304.flatcraft.model.Cell)
     */
    @Override
    public void setAt(int row, int column, Cell cell) {
        if (((row < 0) || (height <= row)) || ((column < 0) || (width <= column))) {
            throw new IllegalArgumentException("Incorrect cell location!");
        }
        cells[row][column].replaceBy(cell);
    }

}

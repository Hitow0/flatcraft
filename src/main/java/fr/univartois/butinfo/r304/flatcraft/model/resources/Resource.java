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

package fr.univartois.butinfo.r304.flatcraft.model.resources;

import java.util.Objects;
import fr.univartois.butinfo.r304.flatcraft.model.resources.mineralstate.FinalState;
import fr.univartois.butinfo.r304.flatcraft.model.resources.mineralstate.IStateResource;
import fr.univartois.butinfo.r304.flatcraft.model.resources.mineralstate.MineralState;
import fr.univartois.butinfo.r304.flatcraft.view.Sprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.*;

/**
 * Une ressource est un élément de la carte avec lequel le joueur peut interagir.
 * Il peut soit l'extraire, soit la laisser sur place.
 *
 * @author Daniel Le Berre
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class Resource implements Inventoriable {

    /**
     * Le nom unique identifiant le type de cette ressource.
     */
    private String name;

    /**
     * L'état de la ressource
     */
    private IStateResource state;

    /**
     * Le sprite représentant cette ressource.
     */
    private Sprite sprite;

    /**
     * Le type d'outils nécessaire pour extraire cette ressource de la carte.
     */
    private final ToolType toolType;

    /**
     * La dureté de cette ressource.
     * Il s'agit du nombre de coups devant être appliqués avec un outil pour extraire
     * cette ressource depuis la map.
     */
    private int hardness;

    /**
     * Crée une nouvelle instance de Resource.
     *
     * @param name Le nom unique identifiant le type de la ressource.
     * @param sprite Le sprite représentant la ressource.
     * @param toolType Le type d'outils nécessaire pour extraire la ressource de la carte.
     * @param hardness La dureté initiale de la ressource.
     *
     * @throws IllegalArgumentException Si la valeur de {@code hardness} est négative.
     */
    public Resource(String name, Sprite sprite, ToolType toolType, int hardness) {
        if (hardness < 0) {
            throw new IllegalArgumentException("Resource hardness should be non-negative!");
        }

        this.name = name;
        this.sprite = sprite;
        this.toolType = toolType;
        this.hardness = hardness;
        if (name.contains("mineral") || name.contains("stone"))
            state = new MineralState();
        else
            state = new FinalState();
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", state=" + state +
                ", sprite=" + sprite +
                ", toolType=" + toolType +
                ", hardness=" + hardness +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable#getName()
     */
    @Override
    public String getName() {
        return name;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable#getSprite()
     */
    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public void setHardness (int hardness) {
        this.hardness = hardness;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable#getToolType()
     */
    @Override
    public ToolType getToolType() {
        return toolType;
    }

    /**
     * Donne la dureté de cet élément.
     * Il s'agit du nombre de coups devant être appliqués avec un outil pour extraire
     * cet élément depuis la carte.
     *
     * @return La dureté de cet élément.
     */
    public int getHardness() {
        return hardness;
    }

    /**
     * Donne un coup sur cette ressource pour l'extraire de la carte.
     * Cela réduit sa dureté.
     *
     * @throws IllegalStateException Si la dureté de la ressource est déjà égale
     *         à {@code 0}.
     */
    public void dig() {
        if (hardness <= 0) {
            throw new IllegalStateException("Cannot dig resource with 0 hardness!");
        }
        hardness--;
    }

    /**
     * Cette fonction permet de récupérer le Sprite du niveau de cassage souhaité.
     * @param niveau : niveau de cassage
     * @return un nouveau Sprite comportant le niveau de cassage
     */
    public static Sprite obtenirNiveauCassage(int niveau) {
        Image patronImage = new Image("/fr/univartois/butinfo/r304/flatcraft/view/images/default_crackLevel.png");

        // Taille d'un niveau de cassage (16 pixels de hauteur)
        int hauteurNiveau = 16;

        // Calculer les coordonnées de début et de fin pour le niveau spécifié
        int startY = (niveau-1) * hauteurNiveau;
        // Créer une nouvelle image avec seulement la partie désirée
        return new Sprite(new WritableImage(patronImage.getPixelReader(),
                0, startY, (int) patronImage.getWidth(), hauteurNiveau));
    }

    /**
     * Donne la ressource obtenue lorsque cette ressource est extraite de la carte.
     * Par défaut, la ressource obtenue ne change pas.
     *
     * @return La ressource obtenue après son extraction.
     */
    public Resource digBlock() {
        state = state.changeState(this);
        return this;
    }

    /*
     * (non-Javadoc)
     *
     * @see fr.univartois.butinfo.r304.flatcraft.model.resources.Inventoriable#execute()
     */
    @Override
    public void execute() {
        // On ne fait rien ici.
        // Une ressource ne propose pas d'action par défaut.
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Resource resource) {
            return name.equals(resource.name);
        }
        return false;
    }
}

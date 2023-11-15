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

package fr.univartois.butinfo.r304.flatcraft.view;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javafx.scene.image.Image;

/**
 * La classe {@link SpriteStore} permet de charger les différentes images utilisées dans
 * les {@link Sprite} une et une seule fois pendant l'exécution du programme.
 *
 * @author Romain Wallon
 *
 * @version 0.1.0
 */
public final class SpriteStore implements ISpriteStore {

    private static SpriteStore instance;

    /**
     * La {@link Map} permettant de conserver en cache les différentes instances de
     * {@link Sprite} déjà chargées.
     */
    private final Map<String, Sprite> spriteCache = new HashMap<>();

    /*
     * (non-Javadoc)
     *
     * @see
     * fr.univartois.butinfo.r304.flatcraft.view.ISpriteStore#getSprite(java.lang.String)
     */
    @Override
    public Sprite getSprite(String identifier) {
        // On commence par regarder si l'instance a déjà été chargée.
        Sprite cached = spriteCache.get(identifier);
        if (cached != null) {
            return cached;
        }

        // On crée maintenant l'instance de Sprite, et on la met en cache.
        Image image = loadImage(identifier);
        Sprite sprite = new Sprite(image);
        spriteCache.put(identifier, sprite);
        return sprite;
    }

    /**
     * Charge une image donnée par son nom.
     *
     * @param name Le nom de l'image à charger.
     *
     * @return L'image ayant le nom donné.
     *
     * @throws NoSuchElementException S'il n'existe pas d'image ayant le nom donné.
     */
    private Image loadImage(String name) {
        try {
            URL urlImage = getClass().getResource("images/default_" + name + ".png");
            return new Image(urlImage.toExternalForm(), getSpriteSize(), getSpriteSize(), true, true);

        } catch (NullPointerException | IllegalArgumentException e) {
            throw new NoSuchElementException("Could not load image " + name, e);
        }
    }

    public static SpriteStore getInstance() {
        if (instance == null)
            instance = new SpriteStore();
        return instance;
    }

    private SpriteStore() {}
}

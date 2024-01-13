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

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
            assert urlImage != null;
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

    /**
     * Cette fonction permet de fusionner deux sprites.
     * @param image : le sprite du block
     * @param spriteToFusion : le sprite à fusionner
     * @return le sprite fusionné
     */
    public Sprite fusionSprite(Sprite image, Sprite spriteToFusion){
        Image image2 = spriteToFusion.image();
        Image image1 = image.image();
        double width = Math.max(image1.getWidth(), image2.getWidth());
        double height = Math.max(image1.getHeight(), image2.getHeight());

        // Créer un nouveau canevas
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Dessiner la première image
        gc.drawImage(image1, 0, 0);
        gc.drawImage(image2, 0, 0);

        // Convertir le canevas en image
        return new Sprite(canvas.snapshot(null, null));
    }

    /**
     * Cette fonction permet de fusionner le sprite du joueur et l'item en main.
     * @param playerImage : le sprite du joueur
     * @param spriteToFusion : le sprite à fusionner
     * @return le sprite fusionné
     */
    public Sprite displayItemInHand(Sprite playerImage, Sprite spriteToFusion) {
        Image image1 = playerImage.image();
        Image image2 = spriteToFusion.image();

        double maxWidth = Math.max(image1.getWidth(), image2.getWidth());
        double maxHeight = Math.max(image1.getHeight(), image2.getHeight());

        Canvas canvas = new Canvas(maxWidth, maxHeight);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.drawImage(image1, 0, 0);

        double scaledWidth = Math.min(image2.getWidth(), maxWidth / 2);
        double scaledHeight = scaledWidth * (image2.getHeight() / image2.getWidth());
        double x = maxWidth - scaledWidth;
        double y = (maxHeight - scaledHeight) / 2;
        gc.drawImage(image2, x, y, scaledWidth, scaledHeight);

        // Convertir le canevas en image
        Image combinedImage = canvas.snapshot(null, null);

        // Retourner le Sprite représentant l'image combinée
        return new Sprite(combinedImage);
    }
}

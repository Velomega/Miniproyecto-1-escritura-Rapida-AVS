package com.example.typinggame.model;
import com.example.typinggame.model.Interface.IWords;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class responsible for managing the game's word bank.
 * It uses a "Master List" pattern to ensure that word pools can be
 * repopulated from an immutable source whenever the game is reset.
 */
public class Words implements IWords {

    /** Immutable source lists for each difficulty level (Season). */
    private final List<String> MASTER_LEVEL1 = Arrays.asList("Cine", "Film", "Gala", "Arte", "Obra", "Actor", "Fila", "Foco", "Sede", "Guía");
    private final List<String> MASTER_LEVEL2 = Arrays.asList("Escena", "Trama", "Guion", "Cámara", "Sonido", "Papel", "Ritmo", "Corte", "Tomas", "Video");
    private final List<String> MASTER_LEVEL3 = Arrays.asList("Género", "Acción", "Estreno", "Edición", "Doblaje", "Rodaje", "Óscar", "Butaca", "Elenco", "Crítica");
    private final List<String> MASTER_LEVEL4 = Arrays.asList("Director", "Pantalla", "Reparto", "Estudio", "Secuela", "Precuela", "Montaje", "Efectos", "Tráiler", "Taquilla");
    private final List<String> MASTER_LEVEL5 = Arrays.asList("Película", "Musical", "Suspenso", "Comedia", "Ficción", "Animación", "Festival", "Proyector", "Vestuario", "Maquillaje");
    private final List<String> MASTER_LEVEL6 = Arrays.asList("Productora", "Secuencia", "Clímax", "Iluminación", "Escenografía", "Cinematografía", "Documental", "Cartelera", "Protagonista", "Antagonista");
    private final List<String> MASTER_LEVEL7 = Arrays.asList("Postproducción", "Preproducción", "Cortometraje", "Mediometraje", "Largometraje", "Storyboard", "Guionización", "Ambientación", "Localización", "Fotografía");
    private final List<String> MASTER_LEVEL8 = Arrays.asList("Cine de Autor", "Efectos Visuales", "Alfombra Roja", "Banda Sonora", "Plano Secuencia", "Cámara Lenta", "Cine Mudo", "Súper Ocho", "Claqueta Digital", "Doble de Acción");
    private final List<String> MASTER_LEVEL9 = Arrays.asList("Cinematográficamente", "Multidimensionalidad", "Descontextualización", "Institucionalización", "Intertextualidad", "Representatividad", "Inconmensurabilidad", "Transdisciplinariedad", "Superproducción", "Experimentalismo");

    /** Active word pools that are consumed (emptied) during gameplay. */
    private List<String> level1, level2, level3, level4, level5, level6, level7, level8, level9;

    /** Current difficulty level (Season). */
    private int currentLevel = 1;

    /** Counter for words successfully completed within the current level. */
    private int wordsServedInLevel = 0;

    /**
     * Default constructor.
     * Triggers the reset method to initialize the word pools for the first time.
     */
    public Words() {
        reset();
    }

    /**
     * Resets the entire game progress to Level 1 and repopulates all
     * active word lists from the immutable master sources.
     */
    @Override
    public void reset() {
        this.currentLevel = 1;
        this.wordsServedInLevel = 0;

        // Create fresh ArrayLists from the master templates
        level1 = new ArrayList<>(MASTER_LEVEL1);
        level2 = new ArrayList<>(MASTER_LEVEL2);
        level3 = new ArrayList<>(MASTER_LEVEL3);
        level4 = new ArrayList<>(MASTER_LEVEL4);
        level5 = new ArrayList<>(MASTER_LEVEL5);
        level6 = new ArrayList<>(MASTER_LEVEL6);
        level7 = new ArrayList<>(MASTER_LEVEL7);
        level8 = new ArrayList<>(MASTER_LEVEL8);
        level9 = new ArrayList<>(MASTER_LEVEL9);

        shuffleAll();
    }

    /**
     * Shuffles all active word lists to ensure a randomized order for every session.
     */
    private void shuffleAll() {
        Collections.shuffle(level1); Collections.shuffle(level2); Collections.shuffle(level3);
        Collections.shuffle(level4); Collections.shuffle(level5); Collections.shuffle(level6);
        Collections.shuffle(level7); Collections.shuffle(level8); Collections.shuffle(level9);
    }

    /**
     * Generates a new word by removing the first element of the active level list.
     * @return The next word to type, or "Fin" if the pool for the current level is exhausted.
     */
    @Override
    public String wordsGenerator() {
        List<String> currentList = getActiveList();

        if (currentList.isEmpty()) {
            return "Fin";
        }

        return currentList.remove(0);
    }

    /**
     * Determines which list should be used based on the current game level.
     * @return The active List of strings for the player's current difficulty.
     */
    private List<String> getActiveList() {
        return switch (currentLevel) {
            case 1 -> level1;
            case 2 -> level2;
            case 3 -> level3;
            case 4 -> level4;
            case 5 -> level5;
            case 6 -> level6;
            case 7 -> level7;
            case 8 -> level8;
            case 9 -> level9;
            default -> new ArrayList<>();
        };
    }

    /**
     * Updates the game progress. Advances to the next season after completing
     * the required number of chapters and resets the internal counter.
     */
    @Override
    public void advanceProgress() {
        wordsServedInLevel++;
        if (wordsServedInLevel >= 5) {
            if (currentLevel < 9) {
                currentLevel++;
                wordsServedInLevel = 0;
            }
        }
    }

    /**
     * Retrieves the current level/season index.
     * @return Current level as an integer.
     */
    @Override
    public int getCurrentLevel() { return currentLevel; }

    /**
     * Retrieves the number of words served in the current season.
     * @return The count of words processed in the current level.
     */
    @Override
    public int getWordsServedInLevel() { return wordsServedInLevel; }
}
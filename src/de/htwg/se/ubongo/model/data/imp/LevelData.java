package de.htwg.se.ubongo.model.data.imp;

import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.model.loader.IResourceLoader;

/** Implementation of ILevelSelection. */
public final class LevelData implements ILevelData {

    private final int[] numbVariants;

    private int selectedIndex;
    private int selectedVariant;

    /** Default-Constructor.
     * @param loader ResourceLoader. */
    public LevelData(final IResourceLoader loader) {
        numbVariants = new int[loader.getNumberBoards()];
        for (int i = 0; i < numbVariants.length; i++) {
            numbVariants[i] = loader.getNumberVariantsOfBoard(i);
        }
    }

    @Override
    public int getBoardIndex() {
        return selectedIndex;
    }

    @Override
    public int getVariant() {
        return selectedVariant;
    }

    @Override
    public void setBoard(final int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    @Override
    public void setVariant(final int selectedVariant) {
        this.selectedVariant = selectedVariant;
    }

    @Override
    public int getNumberBoards() {
        return numbVariants.length;
    }

    @Override
    public int getNumberVariantsOfBoard(final int index) {
        return numbVariants[index];
    }

}

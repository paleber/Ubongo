package de.htwg.se.ubongo.model.data.imp;

import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.model.loader.IResourceLoader;

/** Implementation of ILevelSelection. */
public final class LevelData implements ILevelData {

    private final int[] numbVariants;

    private int index;
    private int variant;

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
        return index;
    }

    @Override
    public int getVariant() {
        return variant;
    }

    @Override
    public void setBoard(final int index) {
        if (index < 0 || index >= numbVariants.length) {
            throw new IllegalArgumentException();
        }
        this.index = index;
        if (numbVariants[index] <= variant) {
            variant = numbVariants[index] - 1;
        }
    }

    @Override
    public void setVariant(final int variant) {
        if (variant < 0 || variant >= numbVariants[index]) {
            throw new IllegalArgumentException();
        }
        this.variant = variant;
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

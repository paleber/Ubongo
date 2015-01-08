package de.htwg.se.ubongo.model.data.imp;

import java.util.Random;

import de.htwg.se.ubongo.model.data.ILevelData;
import de.htwg.se.ubongo.model.loader.IResourceLoader;

/** Implementation of ILevelSelection. */
public final class LevelData implements ILevelData {

    private final int[] numVariants;

    private int index;
    private int variant;

    /** Default-Constructor.
     * @param loader ResourceLoader. */
    public LevelData(final IResourceLoader loader) {
        numVariants = new int[loader.getNumberBoards()];
        for (int i = 0; i < numVariants.length; i++) {
            numVariants[i] = loader.getNumberVariantsOfBoard(i);
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
        if (index < 0 || index >= numVariants.length) {
            throw new IllegalArgumentException();
        }
        this.index = index;
        if (numVariants[index] <= variant) {
            variant = numVariants[index] - 1;
        }
    }

    @Override
    public void setVariant(final int variant) {
        if (variant < 0 || variant >= numVariants[index]) {
            throw new IllegalArgumentException();
        }
        this.variant = variant;
    }

    @Override
    public int getNumberBoards() {
        return numVariants.length;
    }

    @Override
    public int getNumberVariantsOfBoard(final int index) {
        return numVariants[index];
    }

	@Override
	public void random() {
		Random r = new Random();
		index = r.nextInt(numVariants.length);
		variant = r.nextInt(numVariants[index]);
	}

}

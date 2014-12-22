package de.htwg.se.ubongo.model.data.imp;

import de.htwg.se.ubongo.model.data.ILevelSelection;

/** Implementation if ILevelSelection. */
public final class LevelSelection implements ILevelSelection {

    private int index;
    private int variant;

    @Override
    public void setData(final int index, final int variant) {
        this.index = index;
        this.variant = variant;
    }

    @Override
    public int getBoardIndex() {
        return index;
    }

    @Override
    public int getVariantIndex() {
        return variant;
    }

}

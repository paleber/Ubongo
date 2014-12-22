package de.htwg.se.ubongo.model.data.imp;

import de.htwg.se.ubongo.model.data.ILevelSelection;

/** Implementation of ILevelSelection. */
public final class LevelSelection implements ILevelSelection {

    private int index;
    private int variant;

    @Override
    public int getBoardIndex() {
        return index;
    }

    @Override
    public int getVariant() {
        return variant;
    }

    @Override
    public void setBoard(int index) {
        this.index = index;
    }

    @Override
    public void setVariant(int variant) {
        this.variant = variant;
    }

}

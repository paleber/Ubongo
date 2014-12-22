package de.htwg.se.ubongo.model.data;

/** Interface for saving Level-Selection. */
public interface ILevelSelection {

    /** Set board-index and variant.
     * @param index board-index
     * @param variant variant */
    void setData(int index, int variant);

    /** Get the index of the board.
     * @return board-index */
    int getBoardIndex();

    /** Get the variant of the board.
     * @return variant */
    int getVariantIndex();
}

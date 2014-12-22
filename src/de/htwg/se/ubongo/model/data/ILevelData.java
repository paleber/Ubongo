package de.htwg.se.ubongo.model.data;

/** Interface for saving Level-Selection. */
public interface ILevelData {

    /** Get the number of boards.
     * @return number of boards */
    int getNumberBoards();
    
    /** Get the number variants of a board.
     * @param index board-index
     * @return */
    int getNumberVariantsOfBoard(int index);
    
    /** Set the board-index.
     * @param index board-index */
    void setBoard(int index);

    /** Set variant of board.
     * @param variant variant */
    void setVariant(int variant);

    /** Get the index of the board.
     * @return board-index */
    int getBoardIndex();

    /** Get the variant of the board.
     * @return variant */
    int getVariant();

}

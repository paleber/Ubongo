package de.htwg.se.ubongo.model.loader;

import de.htwg.se.ubongo.model.gameobject.IBlock;
import de.htwg.se.ubongo.model.gameobject.IBoard;
import de.htwg.se.ubongo.model.loader.imp.BufferedResourceLoader;

/** Resource Loader. */
public final class ResourceLoader implements IResourceLoader {

    private static final ResourceLoader INSTANCE = new ResourceLoader();
    private static final IResourceLoader LOADER = new BufferedResourceLoader();

    private ResourceLoader() {}

    /** Return the unique instance of the Singleton.
     * @return instance */
    public static ResourceLoader getInstance() {
        return INSTANCE;
    }

    @Override
    public int getNumberBoards() {
        return LOADER.getNumberBoards();
    }

    @Override
    public IBoard createBoard(final int index) {
        return LOADER.createBoard(index);
    }

    @Override
    public int getNumberVariantsOfBoard(final int index) {
        return LOADER.getNumberVariantsOfBoard(index);
    }

    @Override
    public IBlock[] createBlocksOfBoard(final int index, final int variant) {
        return LOADER.createBlocksOfBoard(index, variant);
    }

}
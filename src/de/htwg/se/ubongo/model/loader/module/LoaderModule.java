package de.htwg.se.ubongo.model.loader.module;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import de.htwg.se.ubongo.model.loader.IResourceLoader;
import de.htwg.se.ubongo.model.loader.fake.FakeResourceLoader;

/** Module for Loader. */
public class LoaderModule extends AbstractModule {

    private static final Injector INJECTOR = Guice
            .createInjector(new LoaderModule());

    private LoaderModule() {}

    @Override
    protected void configure() {
        bind(IResourceLoader.class).to(FakeResourceLoader.class).in(
                Singleton.class);
    }

    /** Get an instance of ResourceLoader.
     * @return instance of ResourceLoader */
    public static IResourceLoader getResourceLoaderInstance() {
        return INJECTOR.getInstance(IResourceLoader.class);
    }

}

package dev.cstv.musify.main;

import dev.cstv.musify.resources.DefaultResource;
import dev.cstv.musify.resources.PlayResource;
import dev.cstv.musify.resources.SongResource;
import dev.cstv.musify.security.CORSResponseFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Jersey application
 */
public class JerseyApp extends ResourceConfig {

    public JerseyApp() {
        register(RequestContextFilter.class);
        register(DefaultResource.class);
        register(SongResource.class);
        register(PlayResource.class);
        register(CORSResponseFilter.class);
    }

}

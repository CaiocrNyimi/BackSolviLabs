package br.com.solvilabs.config;

import br.com.solvilabs.resource.VeiculoResource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(VeiculoResource.class);
        resources.add(CorsFilter.class);
        return resources;
    }
}

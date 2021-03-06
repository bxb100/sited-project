package io.sited.admin.impl.web.api;

import io.sited.App;
import io.sited.admin.ConsoleBundle;
import io.sited.admin.impl.service.Console;
import io.sited.admin.impl.service.ConsoleMessageBundle;
import io.sited.admin.impl.service.ConsoleMessageBundleBuilder;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author chi
 */
@Path("/admin/api/bundle")
public class BundleMessageAdminController {
    @Inject
    App app;

    @Inject
    Console console;

    @Path("/{bundleName}/message/{language}")
    @GET
    public Response message(@PathParam("bundleName") String bundleName, @PathParam("language") String language) {
        List<ConsoleBundle> consoleBundles = console.bundles();
        for (ConsoleBundle consoleBundle : consoleBundles) {
            if (consoleBundle.name.equals(bundleName)) {
                ConsoleMessageBundle messageBundle = new ConsoleMessageBundle(consoleBundle, app.message());
                return Response.ok(new ConsoleMessageBundleBuilder(messageBundle, language).build())
                    .type(MediaType.APPLICATION_JSON).build();
            }
        }
        return Response.ok().build();
    }

}

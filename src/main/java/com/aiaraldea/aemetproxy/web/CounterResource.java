package com.aiaraldea.aemetproxy.web;

import java.util.Map;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author inaki
 */
@Path("counter")
public class CounterResource {

    @Context
    private ServletContext servletContext;

    public CounterResource() {
    }

    @GET
    @Produces("application/json")
    @Path("counter.json")
    public Stats getJson() {
        return getFull();
    }

    @GET
    @Produces("application/xml")
    @Path("counter.xml")
    public Stats getXml() {
        return getFull();
    }

    private Stats getFull() {
        return new Stats((Map<Integer, Integer>) servletContext.getAttribute("counter"));
    }
}

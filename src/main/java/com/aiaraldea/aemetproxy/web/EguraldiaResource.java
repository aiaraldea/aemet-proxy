package com.aiaraldea.aemetproxy.web;

import com.aiaraldea.aemetproxy.InfoAemet;
import com.aiaraldea.aemetproxy.dto.FullForecast;
import com.aiaraldea.aemetproxy.dto.FullForecastBuilder;
import com.aiaraldea.aemetproxy.dto.SimpleForecast;
import com.aiaraldea.aemetproxy.dto.SimpleForecastBuilder;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.xmlpull.v1.XmlPullParserException;

/**
 * REST Web Service
 *
 * @author inaki
 */
@Path("eguraldia")
public class EguraldiaResource {

    @Context
    private ServletContext servletContext;

    /**
     * Creates a new instance of HelloworldResource
     */
    public EguraldiaResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.aiaraldea.aemetproxy.web.HelloworldResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("{param}.json")
    @Cacheable(cc = "public, maxAge=3600")
    public FullForecast getJson(@PathParam("param") int code) {
        return getFull(code);
    }

    @GET
    @Produces("application/xml")
    @Path("{param}.xml")
    public FullForecast getXml(@PathParam("param") int code) {
        return getFull(code);
    }

    private FullForecast getFull(int code) {
        String stringCode = Integer.toString(code);
        FullForecast predicciones = (FullForecast) servletContext.getAttribute(stringCode);
        if (predicciones == null || predicciones.isOld()) {
            try {
                predicciones = FullForecastBuilder.build(InfoAemet.getPrediccion(code));
            } catch (XmlPullParserException | IOException | ParseException ex) {
                Logger.getLogger(EguraldiaResource.class.getName()).log(Level.SEVERE, null, ex);
            }
            servletContext.setAttribute(stringCode, predicciones);
        }
        return predicciones;
    }

    @GET
    @Produces("application/xml")
    @Path("sinple/{param}.xml")
    public SimpleForecast getSimpleXml(@PathParam("param") int code) {
        return getSimple(code);
    }

    @GET
    @Produces("application/json")
    @Path("sinple/{param}.json")
    @Cacheable(cc = "public, max-age=1800")
    public SimpleForecast getSimpleJson(@PathParam("param") int code) {
        return getSimple(code);
    }

    private SimpleForecast getSimple(int code) {
        incrementCounter(code);
        String stringCode = Integer.toString(code);
        SimpleForecast predicciones = (SimpleForecast) servletContext.getAttribute("sf_" + stringCode);
        if (predicciones == null || predicciones.isOld()) {
            try {
                predicciones = SimpleForecastBuilder.build(InfoAemet.getPrediccion(code));

            } catch (XmlPullParserException | IOException | ParseException ex) {
                Logger.getLogger(EguraldiaResource.class.getName()).log(Level.SEVERE, null, ex);
            }
            servletContext.setAttribute("sf_" + stringCode, predicciones);
        }
        return predicciones;
    }

    private void incrementCounter(int code) {
        Map<Integer, Integer> counter = (Map<Integer, Integer>) servletContext.getAttribute("counter");
        if (counter == null) {
            counter = new HashMap<>();
            servletContext.setAttribute("counter", counter);
        }
        if (!counter.containsKey(code)) {
            counter.put(code, 0);
        }
        counter.put(code, counter.get(code) + 1);
    }
}

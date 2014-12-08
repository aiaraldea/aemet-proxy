/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aiaraldea.aemetproxy.web;

import com.aiaraldea.aemetproxy.InfoAemet;
import com.aiaraldea.aemetproxy.model.PrediccionesAemet;
import com.aiaraldea.aemetproxy.model.SimpleForecast;
import com.aiaraldea.aemetproxy.model.SimpleForecastBuilder;
import java.io.IOException;
import java.text.ParseException;
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
    @Produces("application/xml")
    @Path("{param}")
    public PrediccionesAemet getJson(@PathParam("param") int code) {
        String stringCode = Integer.toString(code);
        PrediccionesAemet predicciones = (PrediccionesAemet) servletContext.getAttribute(stringCode);
        if (predicciones == null || predicciones.isOld()) {
            try {
                predicciones = InfoAemet.getPrediccion(code);
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
    public SimpleForecast getSimpleJson(@PathParam("param") int code) {
        return getSimple(code);
    }

    private SimpleForecast getSimple(int code) {
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
}

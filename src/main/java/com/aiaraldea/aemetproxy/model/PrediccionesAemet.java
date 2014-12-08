package com.aiaraldea.aemetproxy.model;

import java.beans.Transient;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

/**
 *
 * @author inaki
 */
@XmlRootElement(name = "eguraldia")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public final class PrediccionesAemet {

    private List<PrediccionAemet> prediccionesAemet;
    private final DateTime date = DateTime.now();

    public PrediccionesAemet() {
    }

    public PrediccionesAemet(List<PrediccionAemet> prediccionesAemet) {
        this.prediccionesAemet = prediccionesAemet;
    }

    @XmlElement
    public List<PrediccionAemet> getPrediccionesAemet() {
        return prediccionesAemet;
    }

    protected void setPrediccionesAemet(List<PrediccionAemet> prediccionesAemet) {
        this.prediccionesAemet = prediccionesAemet;
    }

    @Transient
    public boolean isOld() {
        return Minutes.minutesBetween(date, new DateTime())
                .isGreaterThan(Minutes.minutes(20));
    }

    @Transient
    public boolean isTooOld() {
        return Minutes.minutesBetween(date, new DateTime())
                .isGreaterThan(Minutes.minutes(60));
    }
}

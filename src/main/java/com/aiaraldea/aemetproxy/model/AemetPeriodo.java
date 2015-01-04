package com.aiaraldea.aemetproxy.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "periodo")
@XmlEnum
public enum AemetPeriodo {

    @XmlEnumValue("00-24") p0024(DetailLevel.day),
    @XmlEnumValue("00-12") p0012(DetailLevel.halfday),
    @XmlEnumValue("12-24") p1224(DetailLevel.halfday),
    @XmlEnumValue("00-06") p0006(DetailLevel.quarterday),
    @XmlEnumValue("06-12") p0612(DetailLevel.quarterday),
    @XmlEnumValue("12-18") p1218(DetailLevel.quarterday),
    @XmlEnumValue("18-24") p1824(DetailLevel.quarterday);
    private final DetailLevel detailLevel;

    private AemetPeriodo(DetailLevel detailLevel) {
        this.detailLevel = detailLevel;
    }

    public static AemetPeriodo getPeriodo(String periodo) {
        AemetPeriodo ret = null;
        if (periodo == null) {
            ret = p0024;
        } else {
            for (AemetPeriodo ap : AemetPeriodo.values()) {
                if (ap.aCadena().equals(periodo)) {
                    ret = ap;
                    break;
                }
            }
        }
        return ret;
    }

    private String aCadena() {
        StringBuilder retValue = new StringBuilder();
        retValue.append(name().substring(1, 3)).append("-")
                .append(name().substring(3));
        return retValue.toString();
    }

    public DetailLevel level() {
        return detailLevel;
    }

    public enum DetailLevel {

        day, halfday, quarterday
    }
}

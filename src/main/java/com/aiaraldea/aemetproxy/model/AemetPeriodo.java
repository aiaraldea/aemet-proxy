package com.aiaraldea.aemetproxy.model;

public enum AemetPeriodo {

    p0024(DetailLevel.day),
    p0012(DetailLevel.halfday),
    p1224(DetailLevel.halfday),
    p0006(DetailLevel.quarterday),
    p0612(DetailLevel.quarterday),
    p1218(DetailLevel.quarterday),
    p1824(DetailLevel.quarterday);
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

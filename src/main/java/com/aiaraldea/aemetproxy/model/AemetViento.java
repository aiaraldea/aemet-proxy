package com.aiaraldea.aemetproxy.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class AemetViento {

    private AemetPeriodo periodo;
    private String direccion;
    private Integer velocidad;

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the velocidad
     */
    public Integer getVelocidad() {
        return velocidad;
    }

    /**
     * @param velocidad the velocidad to set
     */
    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * @return the periodo
     */
    public AemetPeriodo getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(AemetPeriodo periodo) {
        this.periodo = periodo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AemetViento [periodo=" + periodo + ", direccion=" + direccion
                + ", velocidad=" + velocidad + "]";
    }
}

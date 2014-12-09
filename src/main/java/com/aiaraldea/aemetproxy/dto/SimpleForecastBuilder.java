package com.aiaraldea.aemetproxy.dto;

import com.aiaraldea.aemetproxy.model.AemetEstadoCielo;
import com.aiaraldea.aemetproxy.model.AemetPeriodo;
import com.aiaraldea.aemetproxy.model.AemetViento;
import com.aiaraldea.aemetproxy.model.PrediccionAemet;
import com.aiaraldea.aemetproxy.model.PrediccionesAemet;
import com.aiaraldea.aemetproxy.dto.SimpleForecast.Day;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author inaki
 */
public class SimpleForecastBuilder {

    public static SimpleForecast build(PrediccionesAemet predicciones) {
        SimpleForecast forecast = new SimpleForecast();
        List<SimpleForecast.Day> days = new ArrayList<>();
        for (PrediccionAemet prediccion : predicciones.getPrediccionesAemet()) {
            Day day = new Day();
            day.setDay(prediccion.getDia());
            day.setMaxTemperature(prediccion.getMaxTemperatura());
            day.setMinTemperature(prediccion.getMinTemperatura());
            forecast.setTown(prediccion.getLocalidad());
            forecast.setProvince(prediccion.getProvincia());
            SimpleForecast.Period morning = new SimpleForecast.Period();
            SimpleForecast.Period afternoon = new SimpleForecast.Period();
            for (AemetEstadoCielo estadoCielo : prediccion.getEstadoCielo()) {
                if (estadoCielo.getPeriodo() == AemetPeriodo.p0012) {
                    morning.setSkyStatusCode((estadoCielo.getCode()));
                } else if (estadoCielo.getPeriodo() == AemetPeriodo.p1224) {
                    afternoon.setSkyStatusCode((estadoCielo.getCode()));
                }
            }
            for (Map.Entry<AemetPeriodo, Integer> e : prediccion.getProbPrecipitacion().entrySet()) {
                if (e.getKey() == AemetPeriodo.p0012) {
                    morning.setRain(e.getValue());
                } else if (e.getKey() == AemetPeriodo.p1224) {
                    afternoon.setRain(e.getValue());
                }
            }
            for (AemetViento g : prediccion.getViento()) {
                if (g.getPeriodo() == AemetPeriodo.p0012) {
                    morning.setWind(new SimpleForecast.SimpleWind(g.getDireccion(), g.getVelocidad()));
                } else if (g.getPeriodo() == AemetPeriodo.p1224) {
                    afternoon.setWind(new SimpleForecast.SimpleWind(g.getDireccion(), g.getVelocidad()));
                }
            }

            if (morning.getSkyStatusCode() != null) {
                day.setMorning(morning);
            }
            day.setAfternoon(afternoon);
            if (day.getAfternoon().getSkyStatusCode() != null) {
                days.add(day);
            }
//            day.setMorningRain(prediccion.getProbPrecipitacion());
        }
        forecast.setDays(days);
        return forecast;
    }

}

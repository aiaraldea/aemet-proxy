package com.aiaraldea.aemetproxy.dto;

import com.aiaraldea.aemetproxy.model.AemetEstadoCielo;
import com.aiaraldea.aemetproxy.model.AemetPeriodo;
import com.aiaraldea.aemetproxy.model.AemetViento;
import com.aiaraldea.aemetproxy.model.PrediccionAemet;
import com.aiaraldea.aemetproxy.model.PrediccionesAemet;
import com.aiaraldea.aemetproxy.dto.FullForecast.Day;
import com.aiaraldea.aemetproxy.model.AemetPeriodo.DetailLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author inaki
 */
public class FullForecastBuilder {

    public static FullForecast build(PrediccionesAemet predicciones) {
        FullForecast forecast = new FullForecast();
        List<FullForecast.Day> days = new ArrayList<>();
        for (PrediccionAemet prediccion : predicciones.getPrediccionesAemet()) {
            DetailLevel level = level(prediccion);
            Day day = new Day();
            day.setDay(prediccion.getDia());
            day.setMaxTemperature(prediccion.getMaxTemperatura());
            day.setMinTemperature(prediccion.getMinTemperatura());
            forecast.setTown(prediccion.getLocalidad());
            forecast.setProvince(prediccion.getProvincia());
            Map<AemetPeriodo, FullForecast.Period> periods = new HashMap<>();
            for (AemetEstadoCielo estadoCielo : prediccion.getEstadoCielo()) {
                if (estadoCielo.getPeriodo().level() == level) {
                    periods.put(estadoCielo.getPeriodo(), new FullForecast.Period(estadoCielo.getPeriodo()));
                    periods.get(estadoCielo.getPeriodo()).setSkyStatusCode((estadoCielo.getCode()));
                }
            }
            for (Map.Entry<AemetPeriodo, Integer> e : prediccion.getProbPrecipitacion().entrySet()) {
                if (e.getKey().level() == level) {
                    periods.get(e.getKey()).setRain(e.getValue());
                }
            }
            for (Object ç : prediccion.getHoraTemperatura().entrySet()) {
                // Map the temperatures
            }
            for (AemetViento g : prediccion.getViento()) {
                if (g.getPeriodo().level() == level && periods.containsKey(g.getPeriodo())) {
                    periods.get(g.getPeriodo()).setWind(new FullForecast.SimpleWind(g.getDireccion(), g.getVelocidad()));
                }
            }

            day.setPeriods(new ArrayList<FullForecast.Period>());
            day.getPeriods().addAll(periods.values());

            days.add(day);
        }
        forecast.setDays(days);
        return forecast;
    }

    private static DetailLevel level(PrediccionAemet prediccionAemet) {
        boolean half = false;
        for (AemetEstadoCielo estadoCielo : prediccionAemet.getEstadoCielo()) {
            if (estadoCielo.getPeriodo().level() == AemetPeriodo.DetailLevel.quarterday) {
                return DetailLevel.quarterday;
            }

            if (estadoCielo.getPeriodo().level() == AemetPeriodo.DetailLevel.halfday) {
                half = true;
            }
        }
        if (half) {
            return DetailLevel.halfday;
        }
        return DetailLevel.day;
    }
}

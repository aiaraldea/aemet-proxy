package com.aiaraldea.aemetproxy.dto;

import com.aiaraldea.aemetproxy.BasqueSimpleDateFormat;
import java.beans.Transient;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

/**
 *
 * @author inaki
 */
@XmlRootElement(name = "eguraldia")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class SimpleForecast {

    private String province;
    private String town;
    private List<Day> days;
    private final DateTime date = DateTime.now();
    private String link;

    /**
     * @return the provincia
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the provincia to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the localidad
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the localidad to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link.replace("/es/", "/eu/");
    }

    /**
     * @return the days
     */
    @XmlElementWrapper(name = "days")
    @XmlElement(name = "day")
    public List<Day> getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(List<Day> days) {
        this.days = days;
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

    public static class Period {

        private SimpleWind wind;
        private int rain;
        private String skyStatusCode;

        /**
         * @return the wind
         */
        public SimpleWind getWind() {
            return wind;
        }

        /**
         * @param wind the wind to set
         */
        public void setWind(SimpleWind wind) {
            this.wind = wind;
        }

        /**
         * @return the rain
         */
        @XmlAttribute
        public int getRain() {
            return rain;
        }

        /**
         * @param rain the rain to set
         */
        public void setRain(int rain) {
            this.rain = rain;
        }

        /**
         * @return the skayStatusCode
         */
        @XmlAttribute
        public String getSkyStatusCode() {
            return skyStatusCode;
        }

        /**
         * @param skyStatusCode the skayStatusCode to set
         */
        public void setSkyStatusCode(String skyStatusCode) {
            this.skyStatusCode = skyStatusCode;
        }
    }

    public static class Day {

        private Date day;
        private int minTemperature;
        private Integer maxTemperature;
        private Period morning;
        private Period afternoon;

        /**
         * @return the day
         */
        @XmlAttribute
        public String getDay() {
            return BasqueSimpleDateFormat.format(day);
        }

        public void setDay(String day) {
//            this.day = day;
        }

        public void setDay(Date day) {
            this.day = day;
        }

        /**
         * @return the minTemperature
         */
        @XmlAttribute
        public int getMinTemperature() {
            return minTemperature;
        }

        /**
         * @param minTemperature the minTemperature to set
         */
        public void setMinTemperature(int minTemperature) {
            this.minTemperature = minTemperature;
        }

        /**
         * @return the maxTemperature
         */
        @XmlAttribute
        public Integer getMaxTemperature() {
            return maxTemperature;
        }

        /**
         * @param maxTemperature the maxTemperature to set
         */
        public void setMaxTemperature(Integer maxTemperature) {
            this.maxTemperature = maxTemperature;
        }

        public Period getMorning() {
            return morning;
        }

        public void setMorning(Period morning) {
            this.morning = morning;
        }

        public Period getAfternoon() {
            return afternoon;
        }

        public void setAfternoon(Period afternoon) {
            this.afternoon = afternoon;
        }
    }

    public static class SimpleWind {

        private String direction;
        private Integer speed;

        public SimpleWind() {
        }

        public SimpleWind(String direction, Integer speed) {
            this.direction = direction;
            this.speed = speed;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public Integer getSpeed() {
            return speed;
        }

        public void setSpeed(Integer speed) {
            this.speed = speed;
        }
    }
}

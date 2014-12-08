@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type = DateTime.class, value = DateTimeAdapter.class),
    @XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
})

package com.aiaraldea.aemetproxy.model;

import com.aiaraldea.aemetproxy.jaxb.DateTimeAdapter;
import com.aiaraldea.aemetproxy.jaxb.LocalDateAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

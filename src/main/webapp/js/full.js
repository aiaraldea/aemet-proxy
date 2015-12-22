var aemetProxy = aemetProxy || {};
aemetProxy.full = (function () {
  var day = function (container, day) {
    var element = $('<table class=""></table>').appendTo(container);

    $.each(day.periods.period, function (i, p) {
      console.log(i === 0 ? day.periods.period.length : 0);
      period1(element, p, p.periodo, i === 0 ? day.periods.period.length : 0, day);
    });
  };
  var period1 = function (element, period, time, rowSpan, day) {
    console.log(period);
    element.append('<tr class="ilara">' +
            (rowSpan > 0 ? '<td class="eguna" rowspan="' + rowSpan + '">' +
                    '<div class="eguna">' + day.day + '</div>' +
                    '<div class="temp"><span class="min">' + day.minTemperature + '</span> / <span class="max">' + day.maxTemperature + '</span></div>' +
                    '</td>' : '') +
            '<td class="ordua">' + time + '</td>' +
            '<td><i class="wi wi-' + period.skyStatusCode + '"></i></td>' +
            '<td title="Prezipitazio -probabilitatea: %' + period.rain + '">%' + period.rain + '</td>' +
            '<td class="wind"><span><i class="wi wi-wind-' + period.wind.direction + '"></i></span> <span>' + period.wind.speed + '</span></td>' +
            '</tr>');
  };

  var initData = function (data, container) {
    $.each(data.days.day, function (k, v) {
      console.log(v);
      day(container, v);
    });
  };
  return {"initData": initData};
}());
aemetProxy.full.init = function () {

  function getUrlParameter(sParam)
  {
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
      var sParameterName = sURLVariables[i].split('=');
      if (sParameterName[0] == sParam)
      {
        return sParameterName[1];
      }
    }
  }

  $('.aemet-eguraldia.full').each(function (i) {
    var container = $(this);
    var code = getUrlParameter('aemet-code');
    $.get("/AemetProxy/webresources/eguraldia/" + code + ".json", function (data) {
      aemetProxy.full.initData(data, container);
    });
    console.log(code);
  });
};
$(document).ready(function () {
  aemetProxy.full.init();
});
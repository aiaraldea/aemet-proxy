var aemetProxy = aemetProxy || {};
aemetProxy.full = (function () {
  var day = function (container, day) {
    var element = $('<div class="aemet-day periods-' + day.periods.period.length + '"></div>').appendTo(container);
    element.append('<div class="eguna">' + day.day + '</div>');

    $.each(day.periods.period, function (i, p) {
      period1(element, p, p.periodo);
    });
    element.append(
            '<div class="temp"><span class="min">' + day.minTemperature + '</span> / <span class="max">' + day.maxTemperature + '</span></div>');
    $.each(day.periods.period, function (i, p) {
      period2(element, p);
    });
  };
  var period1 = function (element, period, time) {
    console.log(period);
    element.append('<div class="aemet-period">' +
            '<div class="ordua">' + time + '</div>' +
            '<i class="wi wi-' + period.skyStatusCode + '"></i>' +
            '</div>');
  };
  var period2 = function (element, period) {
    console.log(period);
    element.append('<div class="aemet-period">' +
            '<div title="Prezipitazio -probabilitatea: %' + period.rain + '">%' + period.rain + '</div>' +
            '<div class="wind"><span><i class="wi wi-wind-' + period.wind.direction + '"></i></span> <span>' + period.wind.speed + '</span></div>' +
            '</div>');
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
  $('.aemet-eguraldia.full').each(function (i) {
    var container = $(this);
    var code = container.data('aemet-code');
    $.get("/AemetProxy/webresources/eguraldia/" + code + ".json", function (data) {
      aemetProxy.full.initData(data, container);
    });
    console.log(code);
  });
};
$(document).ready(function () {
  aemetProxy.full.init();
});
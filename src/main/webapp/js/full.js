var aemetProxy = aemetProxy || {};
aemetProxy.full = (function () {
  var day = function (container, day) {
    var full = "";
    if (!day.morning) {
      full = "full";
    }
    var element = $('<div class="aemet-day ' + full + '"></div>').appendTo(container);
    element.append('<div class="eguna">' + day.day + '</div>');
    if (day.morning) {
      period1(element, day.morning, '00-12');
    }
    period1(element, day.afternoon, '12-24');
    element.append(
            '<div class="temp"><span class="min">' + day.minTemperature + '</span> / <span class="max">' + day.maxTemperature + '</span></div>');
    if (day.morning) {
      period2(element, day.morning, '00-12');
    }
    period2(element, day.afternoon, '12-24');
  };
  var period1 = function (element, period, time) {
    console.log(period);
    element.append('<div class="aemet-period">' +
            '<div class="ordua">' + time + '</div>' +
            '<i class="wi wi-' + period.skyStatusCode + '"></i>' +
            '</div>');
  };
  var period2 = function (element, period, time) {
    console.log(period);
    element.append('<div class="aemet-period">' +
            '<div title="Prezipitazio -probabilitatea: %' + period.rain + '">%' + period.rain + '</div>' +
            '<div class="wind"><span><i class="wi wi-wind-' + period.wind.direction + '"></i></span> <span>' + period.wind.speed + '</span></div>' +
            '</div>');
  };

  var initData = function (data, container) {
    $.each(data.days.day, function (k, v) {
      if (k > 2) {
        return;
      }
      console.log(v);
      day(container, v);
    });
  };
  return {"initData": initData};
}());
aemetProxy.full.init = function () {
  $('.aemet-eguraldia').each(function (i) {
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
var aemetProxy = aemetProxy || {};
aemetProxy.day = function (container, day) {
  var full = "";
  if (!day.morning) {
    full = "full";
  }
  var element = $('<div class="aemet-day ' + full + '"></div>').appendTo(container);

  element.append('<div class="eguna">' + day.day + '</div>');
  if (day.morning) {
    aemetProxy.period1(element, day.morning, '00-12');
  }
  aemetProxy.period1(element, day.afternoon, '12-24');
  element.append(
          '<div class="temp"><span class="min">' + day.minTemperature + '</span> / <span class="max">' + valueOrDefault(day.maxTemperature, '') + '</span></div>');
  if (day.morning) {
    aemetProxy.period2(element, day.morning, '00-12');
  }
  aemetProxy.period2(element, day.afternoon, '12-24');
};

var valueOrDefault = function(variable, defaultValue) {
      return (typeof variable === 'undefined') ? defaultValue : variable;
};

aemetProxy.period1 = function (element, period, time) {
  element.append('<div class="aemet-period">' +
          '<div class="ordua">' + time + '</div>' +
          '<i class="wi wi-' + period.skyStatusCode + '"></i>' +
          '</div>');

};

aemetProxy.period2 = function (element, period, time) {
  element.append('<div class="aemet-period">' +
          '<div title="Prezipitazio -probabilitatea: %' + period.rain + '">%' + period.rain + '</div>' +
          '<div class="wind"><span><i class="wi wi-wind-' + period.wind.direction + '"></i></span> <span>' + period.wind.speed + '</span></div>' +
          '</div>');

};
aemetProxy.auto = function () {
  $('.aemet-eguraldia.auto').each(function (i) {
    var code;
    var container = $(this);
    code = container.data('aemet-code');
    $.get("/AemetProxy/webresources/eguraldia/sinple/" + code + ".json", function (data) {
      aemetProxy.initData(data, container);
    });
  });
};

aemetProxy.reload = function (container, code) {
  $.get("/AemetProxy/webresources/eguraldia/sinple/" + code + ".json", function (data) {
    aemetProxy.initData(data, container);
  });
};

aemetProxy.initData = function (data, container) {
  container.empty();
  $.each(data.days.day, function (k, v) {
    if (k > 2) {
      return;
    }
    aemetProxy.day(container, v);
  });
  container.append('<div style="clear: both;" class="aemet-copy"><a href="' + data.link + '" target="_blank">&copy; AEMET</a></div>');
};
$(document).ready(function () {
  aemetProxy.auto();
});

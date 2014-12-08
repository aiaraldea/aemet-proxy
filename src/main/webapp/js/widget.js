var aemetProxy = aemetProxy || {};
aemetProxy.dummyData =
        {
          "days": {
            "day": [
              {
                "day": "lar 22",
                "maxTemperature": 20,
                "minTemperature": 15,
                "afternoon": {
                  "rain": 15,
                  "skyStatusCode": "13",
                  "wind": {"direction": "S", "speed": 10}}
              },
              {
                "day": "iga 23",
                "maxTemperature": 21,
                "minTemperature": 13,
                "afternoon": {
                  "rain": 45,
                  "skyStatusCode": "11",
                  "wind": {"direction": "NO", "speed": 10}},
                "morning": {
                  "rain": 5,
                  "skyStatusCode": "12",
                  "wind": {
                    "direction": "SE", "speed": 10}}
              },
              {
                "day": "asl 24",
                "maxTemperature": 19,
                "minTemperature": 12,
                "afternoon": {
                  "rain": 75,
                  "skyStatusCode": "25",
                  "wind": {"direction": "N", "speed": 5}},
                "morning": {
                  "rain": 45, "skyStatusCode": "15",
                  "wind": {"direction": "O", "speed": 10}}
              },
              {
                "day": "ast 25",
                "maxTemperature": 15,
                "minTemperature": 12,
                "afternoon": {"rain": 95, "skyStatusCode": "25", "wind": {"direction": "C", "speed": 0}},
                "morning": {"rain": 80, "skyStatusCode": "25", "wind": {"direction": "C", "speed": 0}}}
            ]},
          "province": "Bizkaia",
          "town": "Sestao"
        };

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
          '<div class="temp"><span class="min">' + day.minTemperature + '</span> / <span class="max">' + day.maxTemperature + '</span></div>');
  if (day.morning) {
    aemetProxy.period2(element, day.morning, '00-12');
  }
  aemetProxy.period2(element, day.afternoon, '12-24');
};

aemetProxy.period1 = function (element, period, time) {
  console.log(period);
  element.append('<div class="aemet-period">' +
          '<div class="ordua">' + time + '</div>' +
          '<i class="wi wi-' + period.skyStatusCode + '"></i>' +
          '</div>');

};

aemetProxy.period2 = function (element, period, time) {
  console.log(period);
  element.append('<div class="aemet-period">' +
          '<div title="Prezipitazio -probabilitatea: %' + period.rain + '">%' + period.rain + '</div>' +
          '<div class="wind"><span><i class="wi wi-wind-' + period.wind.direction + '"></i></span> <span>' + period.wind.speed + '</span></div>' +
          '</div>');

};
aemetProxy.init = function () {
  $('.aemet-eguraldia').each(function (i) {
    var container = $(this);
    var code = container.data('aemet-code');
    $.get("/AemetProxy/webresources/eguraldia/sinple/"+code+".json", function (data) {
      aemetProxy.initData(data, container);
    });
    console.log(code);
  });
};
aemetProxy.initData = function (data, container) {
    $.each(data.days.day, function (k, v) {
      if (k > 2) {
        return;
      }
      console.log(v);
      aemetProxy.day(container, v);
    });
};
$(document).ready(function () {
  aemetProxy.init();
});
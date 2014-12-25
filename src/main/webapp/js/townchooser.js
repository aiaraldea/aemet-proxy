var aemetProxy = aemetProxy || {};
aemetProxy.townChooser = (function ($, aemetProxy) {
  var herriak = [
    {code: "01010", name: "Aiara"},
    {code: "01002", name: "Amurrio"},
    {code: "48005", name: "Arakaldo"},
    {code: "48009", name: "Arrrankudiaga"},
    {code: "01004", name: "Artziniega"},
    {code: "01036", name: "Laudio"},
    {code: "01042", name: "Okondo"},
    {code: "48075", name: "Orozko"},
    {code: "48074", name: "Urduña"}
  ];
  var herria = herriak[1];
  var select = function (code) {
    $.each(herriak, function (i, h) {
      if (h.code === code) {
        herria = h;
      }
    });
  };
  var chooser = (function (herriak) {
    var chooser;
    chooser = "<ul class='town-chooser-list'>\n";
    $.each(herriak, function (i, h) {
      chooser += "<li data-code='" + h.code + "'>" + h.name + "</li>";
    });

    chooser += "</ul>";
    return chooser;
  })(herriak);
  var selector = "\n\
<div class='town-chooser-selector'><span class='label'>Herria: </span><span class='town-chooser-selected'>" + herria.name + "</span></div>";

  var init = function () {

    function readCookie(name) {
      var nameEQ = escape(name) + "=";
      var ca = document.cookie.split(';');
      for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ')
          c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) === 0)
          return unescape(c.substring(nameEQ.length, c.length));
      }
      return null;
    }
    $('.aemet-widget >.town-chooser').each(function (i) {
      var container = $(this);
      var eguraldiaContainer = container.siblings('.aemet-eguraldia');
      container.append(selector);
      $(container.parent().append(chooser));

      var cookie = readCookie("eguraldia.herria");
      if (cookie != null) {
        select(cookie);
      }
      console.log(herria);

      aemetProxy.reload(eguraldiaContainer, herria.code);
      container.find('.town-chooser-selected').text(herria.name);

      container.click(function () {
        container.parent().find('ul').first().toggle();
      });
      $('ul.town-chooser-list li').click(function () {
        container.parent().find('ul').first().toggle();
        aemetProxy.reload(eguraldiaContainer, herria.code);
        select($(this).attr('data-code'));
        container.find('.town-chooser-selected').text(herria.name);
        document.cookie = "eguraldia.herria=" + herria.code;
        console.log(herria);
      });
    });
  };
  return {
    "init": init
  };
})(jQuery, aemetProxy);


$(document).ready(function () {
  aemetProxy.townChooser.init();
});
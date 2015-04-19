<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="angular">
	<title>Angular demo</title>
</head>

<body>
	<h1>Angular Demo</h1>

	<div ng-controller="PersonCtrl">

		<div class="allPersons" ng-cloak>
			<div class="person" ng-repeat="person in allPersons">
				<div class="title">{{person.title}}</div>
				<div class="name">{{person.name}}</div>
			</div>
		</div>

	</div>



</body>
</html>
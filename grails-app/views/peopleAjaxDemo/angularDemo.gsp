<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="angular">
	<title>Angular demo</title>
	<style>
		h1 {
			padding-left: 30px;
		}
		.some-message {
			padding: 10px;
			margin: 15px;
			background-color: forestgreen;
		}
	</style>

</head>

<body>
	<h1>Angular Demo</h1>

	<div ng-controller="InfoCtrl">
			<div class="some-message" ng-repeat="message in allMessages">
				<div class="from">{{message.from.id}}</div>
				<div class="info">{{message.message}}</div>
			</div>
	</div>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Show Hack</title>
</head>

<body>

	<h1>Show a hack</h1>

	<dl>
		<dt>Id:</dt><dd>${hack?.id}</dd>
		<dt>Value</dt><dd>${hack?.value}</dd>
		<dt>Owner</dt><dd>${hack?.owner?.username}</dd>
	</dl>



Different ways to render a value from a domain object

	<ul>
		<g:each in="${1..7}" var="demo">
			<li><g:link action="showdemo${demo}" id="${hack?.id}">Demo ${demo}</g:link></li>
		</g:each>
	</ul>




</body>
</html>
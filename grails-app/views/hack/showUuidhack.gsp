<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Show Hack</title>
</head>

<body>

<h1>Show a Uuidhack</h1>

<dl>
	<dt>Id:</dt><dd>${uuidHack?.id}</dd>
	<dt>Uuid</dt><dd>${uuidHack?.uuid}</dd>
	<dt>Value</dt><dd>${uuidHack?.value}</dd>
	<dt>Owner</dt><dd>${uuidHack?.owner?.username}</dd>
</dl>



</body>
</html>
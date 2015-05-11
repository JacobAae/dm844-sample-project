<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Hack demo 1</title>
</head>

<body>
<h1>Hack Demo 2</h1>
Using just the ${ '<g:fieldValue/>'} tag is safe
<h2>Code in the gsp</h2>
<pre>
	${'The value is <g:fieldValue field="value" bean="${hack}"/>'}
</pre>

<h2>The Result</h2>
<h3>The value is <g:fieldValue field="value" bean="${hack}"/></h3>

</body>
</html>
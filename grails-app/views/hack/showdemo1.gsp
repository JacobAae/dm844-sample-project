<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Hack demo 1</title>
</head>

<body>
	<h1>Hack Demo 1</h1>
	Using just the ${ raw('${}')} syntax is safe
	<h2>Code in the gsp</h2>
	<pre>
		${raw('<h2>The value is ${hack?.value}</h2>')}
	</pre>

	<h2>The Result</h2>
	<h3>The value is ${hack?.value}</h3>

</body>
</html>
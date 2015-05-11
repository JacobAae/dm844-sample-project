<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Hack demo 5</title>
</head>

<body>
<h1>Hack Demo 5</h1>
Using just raw() method in a gsp enables you to get the unencoded value from a field. Use this with care!
<h2>Code in the gsp</h2>
<pre>
	${'The value is ${ raw(hack?.value)}'}
</pre>

<h2>The Result</h2>
<h3>The value is ${ raw(hack?.value)}</h3>

</body>
</html>
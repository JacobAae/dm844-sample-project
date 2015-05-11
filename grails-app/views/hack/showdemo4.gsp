<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Hack demo 4</title>
</head>

<body>
<h1>Hack Demo 4</h1>
Using a taglib with none encoding is unsafe
<h2>Code in the gsp</h2>
<pre>
	${'<g:displayHackBad hack="${hack}"/>'}
</pre>

<h2>The Result</h2>
<h3><g:displayHackBad hack="${hack}"/> </h3>

<h2>The taglib code</h2>
<pre>
	static encodeAsForTags = [displayHackBad: [taglib:'none']]

	def displayHackBad = { attrs , body ->
		Hack hack = attrs.hack
		out << "The value is &amp;{hack?.value}"
	}
</pre>

</body>
</html>
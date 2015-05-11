<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Hack demo 3</title>
</head>

<body>
<h1>Hack Demo 3</h1>
Using a taglib with html encoding is safe
<h2>Code in the gsp</h2>
<pre>
	${'<g:displayHackGood hack="${hack}"/>'}
</pre>

<h2>The Result</h2>
<h3><g:displayHackGood hack="${hack}"/> </h3>

<h2>The taglib code</h2>
<pre>
static encodeAsForTags = [displayHackGood: [taglib:'html']]

def displayHackGood = { attrs , body ->
	Hack hack = attrs.hack
	out << "The value is &amp;{hack?.value}"
}
</pre>

</body>
</html>
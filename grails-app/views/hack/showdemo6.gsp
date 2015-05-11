<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Hack demo 6</title>
</head>

<body>
<h1>Hack Demo 6</h1>
Using a taglib with none encoding is unsafe - you need to handle encoding yourself!
<h2>Code in the gsp</h2>
<pre>
	${'<g:displayHackMixed hack="${hack}"/>'}
</pre>

<h2>The Result</h2>
<h3><g:displayHackMixed hack="${hack}"/> </h3>

<h2>The taglib code</h2>
<pre>
static encodeAsForTags = [displayHackMixed: [taglib:'none']]

def displayHackMixed = { attrs , body ->
	Hack hack = attrs.hack
	out << "The value is &lt;span style='color: red;'&gt;&amp;{hack?.value.encodeAsHTML()}</span>"
}
</pre>

</body>
</html>
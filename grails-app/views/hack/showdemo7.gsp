<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>Hack demo 7</title>
</head>

<body>
<h1>Hack Demo 6</h1>
Using a taglib with a markup builder
<h2>Code in the gsp</h2>
<pre>
	${'<g:displayHackWithMarkupbuilder hack="${hack}"/>'}
</pre>

<h2>The Result</h2>
<h3><g:displayHackWithMarkupbuilder hack="${hack}"/> </h3>

<h2>The taglib code</h2>
<pre>
static encodeAsForTags = [ displayHackWithMarkupbuilder: [taglib:'none'] ]

def displayHackWithMarkupbuilder = { attrs , body ->
	Hack hack = attrs.hack
	MarkupBuilder mb = new MarkupBuilder(out)

	mb.h3 {
		mb.yield "The value is"
		span(style:'color: red') {
			mb.yield(hack?.value, true)
		}
	}
}
</pre>

</body>
</html>
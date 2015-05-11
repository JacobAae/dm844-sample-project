<%--
  Created by IntelliJ IDEA.
  User: jacob
  Date: 5/9/15
  Time: 8:47 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<title>List of hacks</title>
</head>

<body>

<h1>Current user: ${secUser}</h1>

<h2>Hacks</h2>
<ul>
	<g:each in="${hacks}" var="hack">
		<li><g:link action="show" id="${hack.id}">${hack.value}</g:link> &nbsp; <g:link action="showSecured" id="${hack.id}">secured</g:link></li>
	</g:each>
</ul>

<h2>Uuid Hacks</h2>
<ul>
	<g:each in="${uuidHacks}" var="hack">
		<li><g:link action="showUuidhack" id="${hack.uuid}">${hack.value}</g:link></li>
	</g:each>
</ul>

<h2>Search for hacks</h2>

<g:form action="search" useToken="true" method="GET">
	<dl>
		<dt><label>Search item</label></dt>
		<dd><input type="text" name="term" size="80"/></dd>
		<g:submitButton name="submit"/>
	</dl>
</g:form>

<h2>Links for databinding demo</h2>

<ul>
	<li><g:link action="bindDemoBAd" params="[value: 'Bind using new Hack(params)', 'owner.id': 1]">Bind using new Hack(params)</g:link> </li>
	<li><g:link action="bindDemoGood" params="[value: 'Bind using binddata with exclude', 'owner.id': 1]">Bind using binddata with exclude</g:link> </li>
</ul>

</body>
</html>
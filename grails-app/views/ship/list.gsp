<%--
  Created by IntelliJ IDEA.
  User: jacob
  Date: 3/19/15
  Time: 9:03 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main">
	<title><g:message code="ships.all.label" /></title>
	<style>
		article {
			padding: 10px;
			margin: 10px;
			border: double;
			background: #dfddde;
		}
		article.quote {
			border: none;
			background: #fff;
		}
		dl {
			width:100%;
			overflow:hidden;
		}
		dt {
			float:left;
			width:30%;
		}
		dd {
			float:left;
			width:70%;
		}	</style>
</head>

<body>

<div class="nav" role="navigation">
	<ul>
		<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
		<li><g:link class="create" action="create"><g:message code="default.new.label" args="[message(code:'ship.label')]" /></g:link></li>
	</ul>
</div>

<div class="content scaffold-list" role="main">

	<h1><g:message code="ships.all.label"/></h1>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>

	<section>
		<article class="quote">
			<bsg:randomQuote/>
		</article>
	</section>

	<section class="shiplist">
		<g:each in="${shipInstanceList}" status="i" var="shipInstance">
			<article>
			<dl>
				<dt><g:message code="ship.name.label"/></dt>
				<dd><g:fieldValue field="name" bean="${shipInstance}"/></dd>

				<dt><g:message code="ship.shiptype.label"/></dt>
				<dd><g:fieldValue field="shiptype" bean="${shipInstance}"/></dd>

				<dt><g:message code="ship.crewsize.label"/></dt>
				<dd><g:fieldValue field="crewsize" bean="${shipInstance}"/></dd>

				<dt><g:message code="ship.productionDate.label"/></dt>
				<dd><g:fieldValue field="productionDate" bean="${shipInstance}"/></dd>

				<dt><g:message code="ship.description.label"/></dt>
				<dd><g:fieldValue field="description" bean="${shipInstance}"/></dd>

			</dl>
				<g:link controller="ship" action="edit" id="${shipInstance.id}">Edit</g:link>
			</article>
		</g:each>

	</section>

</div>
<div class="pagination">
	<g:paginate total="${shipInstanceCount ?: 0}" />
</div>

</body>
</html>
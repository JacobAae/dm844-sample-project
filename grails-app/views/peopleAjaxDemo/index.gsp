<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main">
	<title>Ajax Demo</title>
	<asset:stylesheet src="chucknorrisStyle.css"/>
	<style>
		.content {
			padding: 20px;;
		}
		article {
			padding: 20px;
			margin: 15px;
			border: 2px;
			border-style: solid;
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
		}
		.ajax-content {
			padding: 20px;
			margin: 15px;
			background-color: #abbf78;
			font-size: large;
		}
		#loading-indicator {
			display: none;
			background-color: dodgerblue;
			padding-left: 20px;
			padding-right: 20px;
		}
	</style>
</head>

<body>

	<div class="content">
		<h1>Demo of Ajax - timestamp: ${new Date()}</h1>

		<p>Here we just take the reply and inserts it directly into the DOM - and ignoring any error</p>
		<button id="get-activation">GET Activation</button>
		<button id="post-activation">POST Activation</button>
		<button id="load-activation">Using load</button>

		<span id="loading-indicator">Loading...</span>

		<div id="ajax-content" class="ajax-content">&nbsp;</div>

		<br/>
		<br/>


		<div class="ajax-content">
			<cn:randomChuckNorrisJoke/>
		</div>

		<button id="ajax-activation">Using AJAX-Method</button>
		<button id="duplication-activation">Mixed result json</button>


		<script>
			$(function() {
				$('#get-activation').on( 'click' , function() {
					$.get( '${g.createLink(controller: 'peopleAjaxDemo', action: 'quote')}', function(response) {
						$('#ajax-content').html(response);
					});
				});

				$('#post-activation').on( 'click' , function() {
					$.post( '${g.createLink(controller: 'peopleAjaxDemo', action: 'quote')}', function(response) {
						$('#ajax-content').html(response);
					});
				});

				$('#load-activation').on( 'click' , function() {
					$('#ajax-content').load( '${g.createLink(controller: 'peopleAjaxDemo', action: 'quote')}' );
				});

				$('#ajax-activation').on( 'click' , function() {
					$.ajax({
						url: '${g.createLink(controller: 'peopleAjaxDemo', action: 'quoteJson')}',
						data: { id: 42 }, // Not used here
						type: "GET",
						dataType : "json", // Expected return type: text, html, script, json, jsonp, xml
						success: function( json ) {
							console.debug(json);
							$('#ajax-content').html(json.quote);
							alert(json.status);
						},
						error: function( xhr, status, errorThrown ) {
							alert( "Auch. something did not go as planned :(" );
							console.log( "Error: " + errorThrown );
							console.log( "Status: " + status );
							console.log( xhr );
						},
						complete: function( xhr, status ) {
							console.log("The request is done")
						}
					});
				});

				$(document).bind("ajaxSend", function(){
					$("#loading-indicator").fadeIn();
				}).bind("ajaxComplete", function(){
					$("#loading-indicator").fadeOut();
				});

				$('form').on('submit', function(event) {
					event.preventDefault();
					var params = $(this).serialize();
					var formToReplace = $(this).parent('article');

					$.post( '${g.createLink(controller: 'peopleAjaxDemo', action: 'updateTitle')}', params ,function(response) {
						formToReplace.replaceWith(response);
					});
				});

			});

			$('#duplication-activation').on( 'click' , function() {
				$.ajax({
					url: '${g.createLink(controller: 'peopleAjaxDemo', action: 'somePersonJson')}',
					type: "GET",
					dataType : "json", // Expected return type: text, html, script, json, jsonp, xml
					success: function( json ) {
						console.debug("Retrieved person with id: " + json.id)
						console.debug("Last updated is: " + json.lastUpdated)
						var duplicatePerson = $(json.personHtml)
						$('#ajax-content').html(json.quote);
						$('#people').append( duplicatePerson );
					},
					error: function( xhr, status, errorThrown ) {
						alert( "Auch. Thats not good :(" );
					}
				});
			});



		</script>

		<br/>
		<br/>
		<h2>People</h2>
		<section id="people">
			<g:each in="${people}" var="person">
				<g:render template="person" model="[person:person]"/>
			</g:each>
		</section>


	</div>

</body>
</html>
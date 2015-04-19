<article>
	<dl>
		<dt>Title</dt><dd><g:fieldValue field="title" bean="${person}"/>&nbsp;</dd>
		<dt>Name</dt><dd><g:fieldValue field="name" bean="${person}"/>&nbsp;</dd>
		<dt>Home ship</dt><dd><g:fieldValue field="homeShip" bean="${person}"/>&nbsp;</dd>
	</dl>
	<form> <!-- We don't want to submit this completely -->
		<input type="text" name="title" size="20">
		<input type="hidden" name="id" value="${person?.id}"/>
		<button type="submit" class="update-title">Update Title</button>
	</form>
</article>

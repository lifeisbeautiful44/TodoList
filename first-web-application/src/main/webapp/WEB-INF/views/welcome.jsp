<%@ include file="/common/header.jspf"%>

<%@ include file="/common/navigation.jspf"%>


<div class="container">
	<spring:message code="welcome.message" />
	${name}.
	<p>
		Now, you can manage the todos. <a href=" /list-todos"> CLick here
			^^</a>
	</p>

</div>


<%@ include file="/common/footer.jspf"%>

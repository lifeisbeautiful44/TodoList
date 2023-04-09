
 
 <%@ include file="/common/header.jspf" %>
<%@ include file="/common/navigation.jspf" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Todos</title>

<link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<div class="container">
		<h1>Add a Todo</h1>
		<form:form method="post"  modelAttribute="todo">


			<fieldset class="form-group">
				<label> Description </label>
				 <form:input path="desc" type="text"  class="form-control" required="required"/>
					<form:errors path="desc" cssClass="text-warning" />
			
			</fieldset>
			
				<fieldset class="form-group">
				<label> Target Date </label>
				 <form:input path="targetDate" type="text"  class="form-control" required="required"/>
					<form:errors path="targetDate" cssClass="text-warning" />
			
			</fieldset>

			<input class="btn btn-primary" type="submit" value="Add">
			

		</form:form>
	</div>
<%@ include file="/common/footer.jspf" %>
	<script>
	$('#targetDate').datepicker({
		format : 'dd/mm/yyyy'
	});
</script>
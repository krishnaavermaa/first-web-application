<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<h1 >Add a ToDo</h1>
	
	<!-- **********************************************************
	
	<form method="post">
		<fieldset class="form-group">
			<label>Description</label><input name="desc" type="text" class="form-control" required="required"/>
			
		</fieldset>
			<input type="submit" value="Add" class="btn btn-success"/>
	</form>
	
	 ***************************************************************-->
	 <!--now using spring's tags for form/ form binding i.e. binding form elements to a bean/ making them available in bean-->
	 
	 <form:form method="post" modelAttribute="todo">
	 	<form:hidden path="id"/>
		<fieldset class="form-group">
			<form:label path="desc">Description</form:label><form:input path="desc" name="desc" type="text" class="form-control" required="required"/>
			<form:errors path="desc"></form:errors>
			
		</fieldset>
		<fieldset class="form-group">
			<form:label path="targetDate">Target Date</form:label><form:input path="targetDate" name="targetDate" type="text" class="form-control" required="required"/>
			<form:errors path="targetDate"></form:errors>
			
		</fieldset>
			<input type="submit" value="Submit" class="btn btn-success"/>
	</form:form>

</div>
<%@ include file="common/footer.jspf" %>
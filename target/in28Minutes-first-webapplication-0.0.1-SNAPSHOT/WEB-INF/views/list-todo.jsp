<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<div>Hi ${name }</div>
	<table class="table table-striped">
	<caption><spring:message code="todo.caption"/> </caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>is Completed?</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
			 <br/>
		
			<tr>
				<td>${todo.desc}</td>
				<td><fmt:formatDate value="${todo.targetDate}" pattern="dd/MM/yyyy"/></td>
				<td>${todo.done}</td>
				<td>
					<a href="/update-todo?id=${todo.id }" class="btn btn-success">Update</a>
					<a href="/delete-todo?id=${todo.id }" class="btn btn-danger">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<br/>
	<a href="/add-todo" type="button" class="btn btn-success">Add</a>
</div>
<%@ include file="common/footer.jspf" %>
<p class="lead">Shop Name</p>
<div class="list-group">

	<c:forEach items="${catagories}" var ="catagory">
		<a href="${contextRoot}/show/catagory/${catagory.id}/products" class="list-group-item" id="a_${catagory.name}">${catagory.name}</a> 		
	</c:forEach>
	

</div>
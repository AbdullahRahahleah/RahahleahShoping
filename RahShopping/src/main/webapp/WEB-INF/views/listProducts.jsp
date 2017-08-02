<div class="container">
	<div class="row">

		<!--  Would be to display sidebar -->
		<div class="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>

		<!--  to display the actual products -->
		<div class="col-md-9">
			<!--  added breadcrumb component  -->
				<div class="col-lg-12">
			<div class="row">

					<c:if test="${userClickAllProducts ==true }">
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>
						</ol>
					</c:if>

					<c:if test="${userClickCatagoryProducts ==true }">
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Catagory</li>
							<li class="active">${catagory.name}</li>
						</ol>
					</c:if>


				</div>
			</div>


		</div>
	</div>
</div>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<div class="row">
	
	<!--  message read from managmentcontroller.java (message =Product submitted successfully !) -->
	<c:if test="${not empty message}" >
	<div class="col-xs-12"> 	
		<div class="alert alert-success alert-dimissible">
		<!--  time filled in myaoo.js -->
			<button type ="button" class="close" data-dismiss="alert">%times;</button>
			${message}
		</div>
	</div>
	
	
	</c:if>
	
	
	
		<div class="col-md-offset-2 col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Product Managment</h4>
				</div>
				<div class="panel-body">
					<!-- Form elements -->
					<!--  spring  will link nproduct from managmentcontroller.java with (product) in the modelAttribute-->
					<!--  The action will forward you to ManagmentController.java then to post method as we defined it bellow -->
					<sf:form class="form-horizontal" modelAttribute="product"
					action ="${contextRoot}/manage/products"
					method="POST"
					> 
						<div class="form-group">
							<label class="control-label col-md-4" for="name">EnterProduct Name: </label>
							<div class="col-md-8">
							<!--  path attribute here mathched with the field name in the product.java -->
								<sf:input type="text" path="name" id="name"	placeholder="Proruct Name" class="form-control" />
								<!--  em:emphasized text <em class="help-block">Please enter Product Name!</em> -->
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="brand">Enter	Brand Name: </label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"	placeholder="Brand Name" class="form-control" />
								<!--  em:emphasized text <em class="help-block">Please enter Brand Name!</em> -->
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="description">Product	Description: </label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" placeholder="Write a description" class="form-control" rows="4"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="unitPrice">Enter	Unit Price: </label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice"	placeholder="Unit Price in &#8364;" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="quantity">Quantity Availabe : </label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity" placeholder="Quantity Available" class="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4" for="categoryId">Select Category : </label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId" path="categoryId"
								items="${categories}"
								itemLabel="name"
								itemValue="id"
								/>
								
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<input type="submit" name="submit" id="submit" value="Submit"
									class="btn btn-primary" />
								<!-- Those are hidden fields we created to prevent the system from resetting there value to default values -->
								<sf:hidden path="id"/>
								<sf:hidden path="code"/>
								<sf:hidden path="supplierId"/>
								<sf:hidden path="purchases"/>
								<sf:hidden path="views"/>
								<sf:hidden path="active"/>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>



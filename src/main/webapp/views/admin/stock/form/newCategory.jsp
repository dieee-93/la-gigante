<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="mx-3 my-3">
	<input type="text" class="form-control" name="newcategory-name" />
</div>
<div class="mx-3 mb-2">

	<select class="form-select" aria-label="Default select example"
		name="category-select">
		<option value="0" selected>Categoria principal</option>
		<c:if test="${!mainCategory.getAllCategorias().isEmpty()}">
			<c:forEach items="${mainCategory.getAllCategorias()}" var="cat">
				<option value="${cat.id}">${cat.nombre}</option>
			</c:forEach>
		</c:if>

	</select>
</div>



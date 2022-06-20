<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<input type="text" name="newcategory-name" />
<select class="form-select" aria-label="Default select example"
	name="category-select">
	<option value="0" selected>Categoria principal</option>
	<c:if test="${!mainCategory.getAllCategorias().isEmpty()}">
		<c:forEach items="${mainCategory.getAllCategorias()}" var="cat">
			<option value="${cat.id}">${cat.nombre}</option>
		</c:forEach>
	</c:if>

</select>
<button type="submit" class="btn btn-danger">Crear Categoria</button>

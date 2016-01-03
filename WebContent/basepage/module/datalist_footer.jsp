
	</div>
	<!-- Pager bat BEGIN -->
    <div class="portlet-row page-bar">
    	<span style="float:right;">
    	<c:if test="${thisObj.dsMeta!=null && not empty thisObj.dsMeta.searchFieldsmap}">
    	<form name="sys_search_form" action="" method="post">
    		<select name="sys_query_field">
    		<c:forEach var="sField" items="${thisObj.dsMeta.searchFieldsmap}">
    		<option value="<c:out value="${sField.key.name}"/>"><c:out value="${sField.key.showName}"/></option>
    		</c:forEach>
    		</select>
    		<input tyle="text" name="sys_query_value" value="" />
    		<input name="search" type="button" class="btn btnSmall colors" value="Search" onclick="sys_search()"/>
    	</c:if>
    	</form>
    	</span>
        <%--  <%@ include file="/basepage/bar/pager_bar.jsp" %> --%>
    </div>
	<!-- Pager bat END -->
    <!-- END PAGE -->
   </div>
   <!-- END CONTAINER -->
      <script>
      function sys_search(){
      
	    var search = window.location.search;
		var objectEvent = search.getQuery("objectEvent");
		if(objectEvent != null)
		  search = search.replace("objectEvent=" + objectEvent,"objectEvent=Query");
		else
		  search = search + "&objectEvent=Query";
		
		sys_search_form.action = window.location.pathname + search;
		sys_search_form.submit();
   
      }
      jQuery(document).ready(function() {    
      
         //$("#category_title").text(CATEGORY_T);
         $("#table_title").text(TABLE_NAME);
      });
   </script>
   
</body>
<!-- END BODY -->
</html>
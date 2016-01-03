</div>
<script type="text/javascript">
String.prototype.getQuery = function(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = this.substr(this.indexOf("\?")+1).match(reg);
	if (r!=null) return unescape(r[2]); return null;
}

function go(p)
{
  var search = window.location.search;
  var size = document.getElementById("FV_PAGESIZE").value;
  if(search == "")
    window.location.href = window.location.href + "?page="
        + p + "&size=" + size;
  else
  {
    var page = search.getQuery("page");
    var pagesize = search.getQuery("size");
    if(page != null)
      search = search.replace("page=" + page,"page="+p);
    else
      search = search + "&page="+p;
    if(pagesize != null)
      search = search.replace("size=" + pagesize,"size="+size);
    else
      search = search + "&size="+size;
    window.location.href = window.location.pathname + search;
  }
}
function pre(){
	//var k = window.event.keyCode;
	//var e = window.event.srcElement;
	//if (e.type == "text" && k == 13){
	//	window.event.keyCode = 0;
		go(1);
	//}
}
</script>
<div class="ui divider"></div>
<div class="pagetMargin">&nbsp;</div>
<div class="pagerBar">
	<table>
		<tr>
			<td>总数:<c:out value="${pageCount}" />&nbsp;&nbsp; 页数:<c:out
					value="${pager.pages}" />&nbsp;&nbsp; 每页:<select
				name="FV_PAGESIZE" id="FV_PAGESIZE" onchange="pre()">
					<option <c:if test="${pager.pageSize=='15'}">selected</c:if>
						value="15">15</option>
					<option <c:if test="${pager.pageSize=='20'}">selected</c:if>
						value="20">20</option>
					<option <c:if test="${pager.pageSize=='50'}">selected</c:if>
						value="50">50</option>
					<option <c:if test="${pager.pageSize=='100'}">selected</c:if>
						value="100">100</option>
			</select>
			</td>
			<td style="text-align: right;"><c:set var="bp"
					value="${pager.currentPage-5}" /> <c:if test="${bp<=0}">
					<c:set var="bp" value="1" />
				</c:if> <c:set var="ep" value="${bp+20 }" /> <c:if
					test="${ep>pager.pages}">
					<c:set var="ep" value="${pager.pages }" />
				</c:if>
				<ul class="page">
					<c:forEach var="p" begin="${bp}" end="${ep}" step="1">
						<li><c:if test="${p==pager.currentPage}">
								<c:out value="${p}" />
							</c:if> <c:if test="${p!=pager.currentPage}">
								<a href="javascript:go(<c:out value="${p}"/>)"><c:out
										value="${p}" /></a>
							</c:if></li>
					</c:forEach>
				</ul></td>
		</tr>
	</table>
</div>
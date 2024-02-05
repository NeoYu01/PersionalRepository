<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
	
	<jsp:include page="/templates/Resource.jsp" />

	<style type="text/css">
		a:link    { text-decoration: none; color: #FFFFFF }
		a:visited { text-decoration: none; color: #FFFFFF }
		a:hover   { text-decoration: underline; color: #FFF000 }
	</style>

</head>

<div id="layeroutDiv" style="position:absolute; left:0px; top:0px; width:100%; height:100%; z-index:1;">

<table border="0" cellspacing="0" cellpadding="0" style="width:100%; height:100%;">
	<tr>
		<td colspan="2" class="layoutTop">
			<jsp:include page="/templates/LayoutTop.jsp" />
		</td>
	</tr>
	<tr>
		<td class="layoutLeft">
			<jsp:include page="/templates/LayoutLeft.jsp" />
		</td>
		<td class="layoutMain">
			<div id="memberDiv" style="position:absolute; left:320px; top:120px; z-index:1;">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<table border="0" cellspacing="3" cellpadding="3" class="mainBarTable">
							<tr>
								<td class="mainBarTd">會員資料 \ 列表</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td style="padding-top:20px;">
						<table border="0" cellspacing="3" cellpadding="0" class="memberTable1">
							<tr>
								<td style="width:500px;">
									<table border="0" cellspacing="0" cellpadding="5" class="memberTable2">
										<tr>
											<td align="center" class="listMemberTh1">&nbsp;</td>
											<td align="center" class="listMemberTh2">會員編號</td>
											<td align="center" class="listMemberTh3">會員ID</td>
											<td align="center" class="listMemberTh4">會員名稱</td>
											<td align="center" class="listMemberTh1">&nbsp;</td>
										</tr>
										<c:forEach items="${members}" var="member">
										<tr>
											<td class="listMemberTd1">
												<c:choose>
													<c:when test="${loginRole=='admin' || loginName==member.name}">
														<input type="button" size="12" class="listMemberBtn2" onclick="redirectPage('/editMember?seq=${member.seq}');" />
													</c:when>    
													<c:otherwise>
														&nbsp;
													</c:otherwise>
												</c:choose>
											</td>
											<td class="listMemberTd2">${member.seq}</td>
											<td class="listMemberTd2">${member.id}</td>
											<td class="listMemberTd2">${member.name}</td>
											<td class="listMemberTd1">
												<c:choose>
													<c:when test="${loginRole=='admin'}">
														<input type="button" size="12" class="listMemberBtn1" onclick="redirectPage('/deleteMember?seq=${member.seq}');" />
													</c:when>    
													<c:otherwise>
														&nbsp;
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" style="padding-top:5px;">
						<table border="0" cellspacing="0" cellpadding="5">
							<tr>
							<c:forEach items="${pageCounts}" var="pageCount">
								<td style="font-size:12px;"><a href='${pageCount.url}'>${pageCount.label}</a></td>
							</c:forEach>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</div>
		</td>
	</tr>
</table>

</div>

</html>

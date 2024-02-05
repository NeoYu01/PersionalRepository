<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
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

<form:form action="addMember" method="post" modelAttribute="member">
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
								<td class="mainBarTd">會員資料 \ 新增</td>
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
											<td class="memberTd1">會員ID</td>
											<td class="memberTd2">
												<form:input path="id" cssClass="memberInput1" />
												@pland.com
											</td>
										</tr>
										<tr>
											<td class="memberTd1">會員名稱</td>
											<td class="memberTd2">
												<form:input path="name" cssClass="memberInput2" />
											</td>
										</tr>
										<tr>
											<td class="memberTd1">會員密碼</td>
											<td class="memberTd2">
												<form:input path="pwd" cssClass="memberInput2" />
											</td>
										</tr>
										<tr>
											<td align="center" colspan="2" class="memberTd2">
												<input type="submit" value="新增" class="memberBtn" />
												&nbsp;/&nbsp;
												<input type="button" value="取消" class="memberBtn" onclick="redirectPage('/');" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</div>
		</td>
	</tr>
</table>
</form:form>

</div>

</html>

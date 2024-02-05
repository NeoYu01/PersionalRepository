<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			<table border="0" cellspacing="0" cellpadding="0" style="width:975px; height:100px;">
				<tr>
					<td align="right" valign="bottom" class="layoutTopMenu">
						登入者: 
						<c:choose>
							<c:when test="${empty loginRole}">
								訪客
							</c:when>    
							<c:otherwise>
								${loginRole}
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
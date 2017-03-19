/*
 * CaiZiMei
 * File: SignInFilter.java
 * Author: 詹晟
 * Date: 2017/3/19
 * Version: 1.0
 * Since: JDK 1.8
 */
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.caizimei.model.entity.MemberBean;

/**
 * 登入過濾器
 * 
 * @author 詹晟
 */
public class SignInFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			System.out.println("start of signInFilter");
			String servletPath = req.getServletPath();
			System.out.println("111--->" + servletPath);
			if (checkSignIn(req)) {
				System.out.println("222--->需要Login, 已經Login");
				chain.doFilter(request, response); // 將請求導向原本要送去的資源
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("target", req.getServletPath());
				System.out.println("333--->需要Login,尚未Login,ServletPath=" + req.getServletPath());
				String path = req.getContextPath();
				resp.sendRedirect(path + "/secure/login.jsp");
				return;
			}
		} else {
			throw new ServletException("Request/Response Type Error");
		}
	}

	@Override
	public void destroy() {

	}

	private boolean checkSignIn(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberBean user = (MemberBean) session.getAttribute("user");
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}

}

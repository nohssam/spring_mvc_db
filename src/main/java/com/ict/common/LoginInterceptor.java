package com.ict.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements AsyncHandlerInterceptor{
	
	// Controller로 가기전에 구동됨
	// 결과 true이면 Controller, 결과 false이며 특정 JSP로 이동
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 로그인 체그를 해서 만약 로그인이 안된 상태이면 value에 flase를 저장
		HttpSession session = request.getSession(true);
		// true의 의미는 
		// 만약 session이 삭제된 상태라면 새로운 session를 생성해 준다.
		// 삭제가 안된 상태라면 사용하고 있던 session를 그대로 전달해 준다.
		Object obj =  session.getAttribute("loginChk");
		if(obj == null) {
			// 로그인 하지 않은 상태일때 
			request.getRequestDispatcher("/WEB-INF/views/login_error.jsp").forward(request, response);
			return false;
		}
		return true;
	}
	
	/*
	  Controller에서 리턴되어 뷰 리졸버로 가기전에 구동 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	뷰 리졸버가 뷰를 찾아서 내보내고 나면 구동 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	*/
}

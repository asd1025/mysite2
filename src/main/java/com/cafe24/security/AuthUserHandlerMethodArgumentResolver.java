package com.cafe24.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.cafe24.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	// 여기에서는 세션 관리를 할 거야  
	@Override
	public Object resolveArgument(MethodParameter parameter, 
			ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, 
			WebDataBinderFactory binderFactory) throws Exception {
		
		// 파라미터에 @AuthUser가 붙어있고 타입이 UserVo 인지 확인
		// 내가 지원하는 애가 아니야 다른 애로 셋팅해줘
		if(supportsParameter(parameter)==false) {
			return WebArgumentResolver.UNRESOLVED; 
		}
		
		// @며스유저 붙었고 타입도 유저브이오 임!
		HttpServletRequest request= webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session= request.getSession();
		if(session==null) {
			return null;
		}
		return session.getAttribute("authUser");
	}
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser= parameter.getParameterAnnotation(AuthUser.class);
		// @AuthUser가 안붙어 있음.
		if(authUser==null) {
			return false;
		}
		
		// 
		// class 객체를 비교하는 것. 같은 클래스면 하나만 있는 것
		//파라미터 타입이 UserVo
		if(parameter.getParameterType().equals(UserVo.class)==false){
			return false;
		}
		
		return true;
	}

}

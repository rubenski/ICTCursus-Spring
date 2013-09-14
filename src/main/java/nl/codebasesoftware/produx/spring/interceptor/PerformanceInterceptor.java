package nl.codebasesoftware.produx.spring.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: rvanloen
 * Date: 13-9-13
 * Time: 23:32
 * To change this template use File | Settings | File Templates.
 */
public class PerformanceInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long start = (Long) request.getAttribute("startTime");
        if(modelAndView != null){
            modelAndView.addObject("requestTime", System.currentTimeMillis() - start);
        }
    }
}




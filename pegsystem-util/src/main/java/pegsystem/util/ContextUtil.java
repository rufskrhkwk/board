package pegsystem.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ContextUtil {

	
	/**
     * bean id를 기준으로 bean Object return
     * ex) bean id가 characterSetConvertUtil인 경우 
     *     --> CharacterSetConvertUtil convert = (CharacterSetConvertUtil)ContextUtil.getBean("characterSetConvertUtil");
     * @param beanID
     * @return
     */
    public static Object getBean(String beanID) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        return context.getBean(beanID);
    }

    
    /**
     * HttpServletReqeust Object
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }

    
    /**
     * HttpServletResponse Object
     * @return
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getResponse();
    }

    
    /**
     * HttpSession Object
     * @param isNew 새로운 세션 생성 여부
     * @return
     */
    public static HttpSession getSession(boolean isNew) {
        return ContextUtil.getRequest().getSession(isNew);
    }

    
    /**
     * SCOPE_REQUEST 영역에서 가져오기
     * @param key
     * @return
     */
    public static Object getAttrFromRequest(String key) {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getAttribute(key, ServletRequestAttributes.SCOPE_REQUEST);
    }

    
    /**
     * SCOPE_REQUEST 영역에 객체 저장
     * @param key
     * @param obj
     */
    public static void setAttrToRequest(String key, Object obj) {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        attr.setAttribute(key, obj, ServletRequestAttributes.SCOPE_REQUEST);
    }

    
    /**
     * SCOPE_SESSION 영역에서 가져오기
     * @param key
     * @return
     */
    public static Object getAttrFromSession(String key) {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return attr.getAttribute(key, ServletRequestAttributes.SCOPE_SESSION);
    }

    
    /**
     * SCOPE_SESSION 영역에 객체 저장
     * @param key
     * @param obj
     */
    public static void setAttrToSession(String key, Object obj) {
        ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        attr.setAttribute(key, obj, ServletRequestAttributes.SCOPE_SESSION);
    }
}

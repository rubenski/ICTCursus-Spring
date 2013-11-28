package nl.codebasesoftware.produx.controller.exception;

import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
import nl.codebasesoftware.produx.properties.Properties;
import nl.codebasesoftware.produx.service.PageBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: rvanloen
 * Date: 25-11-13
 * Time: 23:35
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private boolean debuggingEnabled;

    @Autowired
    public GlobalExceptionHandler(Properties properties){
        this.debuggingEnabled = properties.isDebuggingEnabled();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView("main");
        modelAndView.addObject("mainContent", "content/error");
        modelAndView.addObject("rightColumn", "components/empty");
        modelAndView.addObject("broadView", true);
        modelAndView.addObject("title", "Pagina niet gevonden");
        modelAndView.addObject("message", "De pagina die u opgevraagd heeft bestaat niet");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView genericException(Exception ex, HttpServletRequest request, HttpServletResponse response)
    {
        String message = ex.getMessage();
        ModelAndView modelAndView = new ModelAndView("main");
        modelAndView.addObject("mainContent", "content/error");
        modelAndView.addObject("rightColumn", "components/empty");
        modelAndView.addObject("broadView", true);
        modelAndView.addObject("title", "Er is een fout opgetreden");
        modelAndView.addObject("message", "Er is een fout opgetreden. Excuses voor het ongemak. De webmaster is automatisch op de hoogte gesteld");
        modelAndView.addObject("stack", getStack(ex));
        modelAndView.addObject("showStack", debuggingEnabled);
        modelAndView.addObject("errorMessage", message);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return modelAndView;
    }

    private String getStack(Exception ex){
        StringBuilder stack = new StringBuilder();
        for (StackTraceElement e : ex.getStackTrace()) {

            if(e.getClassName().startsWith("nl.codebasesoftware")){
                stack.append(String.format("<b style='color:red'>%s:%d %s </b><br/>", e.getClassName(), e.getLineNumber(), e.getMethodName()));
            }else{
                stack.append(String.format("%s:%d %s <br/>", e.getClassName(), e.getLineNumber(), e.getMethodName()));
            }
        }
        return stack.toString();
    }


}

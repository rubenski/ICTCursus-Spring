package nl.codebasesoftware.produx.controller.exception;

import nl.codebasesoftware.produx.exception.ResourceNotFoundException;
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
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: rvanloen
 * Date: 25-11-13
 * Time: 23:35
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request, HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView("main");
        modelAndView.addObject("mainContent", "error/404");
        modelAndView.addObject("rightColumn", "components/empty");
        modelAndView.addObject("broadView", true);
        modelAndView.addObject("title", "Pagina niet gevonden");
        modelAndView.addObject("message", "De pagina die u opgevraagd heeft bestaat niet");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return modelAndView;
    }

}

package nl.codebasesoftware.produx.service.impl;


import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Service;

/**
 * @Service enables the class to be used as a Spring service
 * @RemoteProxy enables the class to be used as a DWR service
 * @Transactional enables transaction support for this clas
 */
@Service
@RemoteProxy(name = "dwrService")
public class ArithmeticServiceImpl {


    /**
     * @RemoteMethod exposes this method to DWR.
     * Your JSP pages can access this method as Javascript
     * Your Spring beans can still access this method.
     */
    @RemoteMethod
    public Integer add(Integer operand1, Integer operand2) {
        return operand1 + operand2;
    }

}
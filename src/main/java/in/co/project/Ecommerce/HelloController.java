package in.co.project.Ecommerce;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@RestController
public class HelloController {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello() {
        return "Hello I'm mayank " ;
    }
    @RequestMapping(value = "/hello/{id}",method = RequestMethod.GET)
    public String sayHelloPersonal(@PathVariable("id") int id) {
        return "Hello I'm mayank_raje " +id;
    }


}

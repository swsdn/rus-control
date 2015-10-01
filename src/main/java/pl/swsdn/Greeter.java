package pl.swsdn;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.spring.annotation.ViewScope;

@SpringComponent
@UIScope
public class Greeter {

    public String sayHello() {
        return "Hello from " + this;
    }

}

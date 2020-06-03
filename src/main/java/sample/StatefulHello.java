package sample;

import javax.ejb.Local;
import javax.ejb.Stateful;

@Stateful
//@Remote(HelloIF.class)
@Local(HelloIF.class)
public class StatefulHello implements HelloIF {

    @Override
    public void hello() {
        System.out.printf("## hello from StatefulHello: %s%n", this);
    }
}

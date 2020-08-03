package sample;

import javax.ejb.Stateful;

@Stateful
public class StatefulHello implements HelloIF {

    @Override
    public void hello() {
        System.out.printf("## hello from StatefulHello: %s%n", this);
    }
}

package sample;

import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
@LocalBean
public class StatelessBean {
    public void foo() {
        HelloIF hello = lookup("", "hello", "", StatefulHello.class.getSimpleName(), HelloIF.class.getName(), true);
        System.out.printf("## lookup result: %s%n", hello);

        try {
            hello.hello();
        } catch (EJBException e) {
            System.out.printf("## got EJBException:%n");
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.out.printf("## got IllegalStateException:%n");
            e.printStackTrace();
        }
    }

    <T> T lookup(String appName, String moduleName, String distinctName, String beanName, String interfaceName, boolean isStateful) {
        String jndiName = "ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + interfaceName + (isStateful ? "?stateful" : "");
        System.out.printf("## jndi name: %s%n", jndiName);
        try {
            final InitialContext jndiContext = new InitialContext();
            return (T) jndiContext.lookup(jndiName);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}

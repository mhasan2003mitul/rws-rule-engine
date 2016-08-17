package nl.rws.re.frontend;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 07-Jul-16.
 */
@ApplicationPath("/rest")
public class ReRESTApplication extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(DefinitieRESTService.class);
        return resources;
    }
}

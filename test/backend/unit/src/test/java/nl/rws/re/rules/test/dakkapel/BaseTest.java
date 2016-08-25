package nl.rws.re.rules.test.dakkapel;

import nl.rws.re.facts.Node;
import nl.rws.re.facts.Vraag;
import nl.rws.re.facts.Error;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Collection;

/**
 * Created by Md. Mainul Hasan Patwary on 16-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class BaseTest {
    protected KieSession kieSession;

    protected KieSession getKieSession(String sessionName) {
        KieServices kieServices = KieServices.Factory.get();

        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        kieSession = kieContainer.newKieSession(sessionName);

        kieSession.addEventListener(new DebugAgendaEventListener());

        return kieSession;
    }


    protected void printAllObjects(Collection<? extends  Object> objects) {
        for (Object obj : objects) {
            System.out.println(obj.toString());
            if (obj instanceof Error) {
                System.out.println(((Error) obj).getMessage());
            } else if (obj instanceof Node) {
                System.out.println(((Node) obj).getId()+" : "+((Node) obj).getAntwoord());
            } else if (obj instanceof Vraag) {
                System.out.println(((Vraag) obj).getVraagId());
            }
        }
    }
}

package nl.rws.re.backend.service;

import nl.rws.re.backend.DRLTemplateFileName;
import nl.rws.re.backend.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Md. Mainul Hasan Patwary on 22-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
@Stateless
@WebService
public class Transformatie {

    private static final Logger LOGGER = LoggerFactory.getLogger(Transformatie.class);
    private static String vraagTemplate;
    private static String orJaTemplate;
    private static String orNeeTemplate;
    private static String andJaTemplate;
    private static String andNeeTemplate;
    private static String nodeTemplate;
    private static StringBuffer drl;

    static {
        drl = new StringBuffer("package nl.rws.re.rules.dakkapel;\n" +
                "import nl.rws.re.facts.dakkapelx.Node;\n" +
                "import nl.rws.re.facts.dakkapelx.Vraag;\n" +
                "import nl.rws.re.facts.dakkapelx.Antwoord;\n" +
                "\n" +
                "dialect  \"mvel\"\n\n");
    }

    public String decisionTreeTODrl() {
        try {
            File file = new File("backend\\src\\main\\java\\vergunningvrijTree.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Tree.class);
            Tree tree = (Tree) jaxbContext.createUnmarshaller().unmarshal(file);

            System.out.println(tree.getNode().size());

            for (Node node : tree.getNode()) {
                if (node.getAnd() != null) {

                } else if (node.getOr() != null) {

                } else if (node.getVragen() != null) {

                }
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

        return "asdfasdf";
    }

    public static void main(String[] arg) {
        try {
            File file = new File("backend\\src\\main\\java\\vergunningvrijTree.xml");
//            File file = new File("backend\\src\\main\\java\\definitieTree.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Tree.class);
            Tree tree = (Tree) jaxbContext.createUnmarshaller().unmarshal(file);

            System.out.println(tree.getNode().size());

            vraagTemplate = loadTemplateFile(DRLTemplateFileName.DRL_VRAAG.getTemplateFileName());
            orJaTemplate = loadTemplateFile(DRLTemplateFileName.DRL_OR_JA.getTemplateFileName());
            orNeeTemplate = loadTemplateFile(DRLTemplateFileName.DRL_OR_NEE.getTemplateFileName());
            andJaTemplate = loadTemplateFile(DRLTemplateFileName.DRL_AND_JA.getTemplateFileName());
            andNeeTemplate = loadTemplateFile(DRLTemplateFileName.DRL_AND_NEE.getTemplateFileName());
            nodeTemplate = loadTemplateFile(DRLTemplateFileName.DRL_NODE.getTemplateFileName());

            for (Node node : tree.getNode()) {
                if (node.getAnd() != null) {
                    StringBuffer andJAConditie = new StringBuffer();
                    for (Noderef noderef : node.getAnd().getNoderef()) {
                        drl.append(String.format(andNeeTemplate, node.getId(),
                                noderef.getId(),node.getId(), noderef.getId(), !noderef.isValue()?"Antwoord.JA.getValue()" : "Antwoord.NEE.getValue()", node.getId()));

                        andJAConditie.append(String.format(nodeTemplate, noderef.getId(), noderef.isValue() ? "Antwoord.JA.getValue()" : "Antwoord.NEE.getValue()")+"\n\t\t");
                    }

                    drl.append(String.format(andJaTemplate, node.getId(),andJAConditie,node.getId()));

                } else if (node.getOr() != null) {
                    StringBuffer orNeeConditie = new StringBuffer();
                    for (Noderef noderef : node.getOr().getNoderef()) {
                        drl.append(String.format(orJaTemplate, node.getId(),
                                noderef.getId(), node.getId(), noderef.getId(), noderef.isValue()?"Antwoord.JA.getValue()" : "Antwoord.NEE.getValue()", node.getId()));

                        orNeeConditie.append(String.format(nodeTemplate, noderef.getId(), !noderef.isValue() ? "Antwoord.JA.getValue()" : "Antwoord.NEE.getValue()")+"\n\t\t");
                    }

                    drl.append(String.format(orNeeTemplate, node.getId(),orNeeConditie,node.getId()));

                } else if (node.getVragen() != null) {
                    for (Vraag vraag : node.getVragen().getVraag()) {
                        drl.append(String.format(vraagTemplate, vraag.getId(),node.getId(),
                                node.getId(), vraag.getId(), vraag.getConversionid() == null ? null : "\"" + vraag.getConversionid() + "\""));
                    }
                }
            }

            System.out.println(drl.toString());

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    public static String loadTemplateFile(String templateFileName) throws Exception {
        File file = new File("backend\\src\\main\\drlTemplate\\" + templateFileName);
        StringBuffer drlTemplate = new StringBuffer();

        final int MAX = 1024;
        byte buffer[] = new byte[MAX];

        FileInputStream fileInputStream = new FileInputStream(file);
        int len = 0;
        while ((len = fileInputStream.read(buffer)) != -1) {
            drlTemplate.append(new String(buffer, 0, len));
        }

        return drlTemplate.toString();
    }
}

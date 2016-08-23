package nl.rws.re.backend;

/**
 * Created by Md. Mainul Hasan Patwary on 22-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public enum DRLTemplateFileName {

    DRL_VRAAG("drl_vraag.template"),DRL_OR_JA("drl_or_ja.template"),DRL_OR_NEE("drl_or_nee.template"),
    DRL_AND_JA("drl_and_ja.template"),DRL_AND_NEE("drl_and_nee.template"),DRL_NODE("drl_node.template");

    private String templateFileName;

    private DRLTemplateFileName(String templateFileName){
        this.templateFileName = templateFileName;
    }

    public String getTemplateFileName() {
        return this.templateFileName;
    }
}

package nl.rws.re.facts.dakkapelx;

import java.util.List;
import java.util.Map;

/**
 * Created by Md. Mainul Hasan Patwary on 02-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class StepBuilder {
    private final Step step;

    public StepBuilder() {
        this.step = new Step();
    }

    public StepBuilder metStepBeschrijven(String stepBeschrijven) {
        this.step.setStepBeschrijven(stepBeschrijven);
        return this;
    }

    public StepBuilder metSteps(List<Step> steps) {
        this.step.setSteps(steps);
        return this;
    }

    public StepBuilder metGrondslags(List<Grondslag> grondslags) {
        this.step.setGrondslags(grondslags);
        return this;
    }

    public StepBuilder metStepConversie(Map<Step, Conversie> stepConversieMap){
        this.step.setStepConversieMap(stepConversieMap);
        return this;
    }

    public StepBuilder metGrondslagConversie(Map<Grondslag, Conversie> grondslagConversieMap){
        this.step.setGrondslagConvertieMap(grondslagConversieMap);
        return this;
    }

    public StepBuilder metStepConditie(Map<Step, String> stepConditieMap){
        this.step.setStepConditieMap(stepConditieMap);
        return this;
    }

    public StepBuilder metGrondslagConditie(Map<Grondslag, String> grondslagConditieMap){
        this.step.setGrondslagConditieMap(grondslagConditieMap);
        return this;
    }

    public Step build() {
        return step;
    }
}

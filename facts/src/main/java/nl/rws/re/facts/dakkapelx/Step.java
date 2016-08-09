package nl.rws.re.facts.dakkapelx;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created by Md. Mainul Hasan Patwary on 02-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
@XmlRootElement
public class Step {
    @XmlElement
    private String stepBeschrijven;
    @XmlElement
    private List<Grondslag> grondslags;
    @XmlElement
    private List<Step> steps;
    @XmlElement
    private Map<Step,Conversie> stepConversieMap;
    @XmlElement
    private Map<Grondslag,Conversie> grondslagConvertieMap;
    @XmlElement
    private Map<Step,String> stepConditieMap;
    @XmlElement
    private Map<Grondslag,String> grondslagConditieMap;
    @XmlElement
    private String definitieveBeslissing;

    public Step() {
        grondslags = new ArrayList<>();
        steps = new ArrayList<>();
        stepConversieMap = new HashMap<>();
        grondslagConvertieMap = new HashMap<>();
        stepConditieMap = new HashMap<>();
        grondslagConvertieMap = new HashMap<>();
    }

    public Step(String stepBeschrijven, List<Grondslag> grondslags, List<Step> steps, Map<Step, Conversie> stepConversieMap,
                Map<Grondslag, Conversie> grondslagConvertieMap,Map<Step,String> stepConditieMap,Map<Grondslag,String> grondslagConditieMap,
                String definitieveBeslissing) {
        this.stepBeschrijven = stepBeschrijven;
        this.grondslags = grondslags;
        this.steps = steps;
        this.stepConversieMap = stepConversieMap;
        this.grondslagConvertieMap = grondslagConvertieMap;
        this.stepConversieMap = stepConversieMap;
        this.grondslagConvertieMap = grondslagConvertieMap;
        this.definitieveBeslissing = definitieveBeslissing;
    }

    public String getStepBeschrijven() {
        return stepBeschrijven;
    }

    public void setStepBeschrijven(String stepBeschrijven) {
        this.stepBeschrijven = stepBeschrijven;
    }

    public List<Grondslag> getGrondslags() {
        return grondslags;
    }

    public void setGrondslags(List<Grondslag> grondslags) {
        this.grondslags = grondslags;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Map<Step, Conversie> getStepConversieMap() {
        return stepConversieMap;
    }

    public void setStepConversieMap(Map<Step, Conversie> stepConversieMap) {
        this.stepConversieMap = stepConversieMap;
    }

    public Map<Grondslag, Conversie> getGrondslagConvertieMap() {
        return grondslagConvertieMap;
    }

    public void setGrondslagConvertieMap(Map<Grondslag, Conversie> grondslagConvertieMap) {
        this.grondslagConvertieMap = grondslagConvertieMap;
    }

    public Map<Step, String> getStepConditieMap() {
        return stepConditieMap;
    }

    public void setStepConditieMap(Map<Step, String> stepConditieMap) {
        this.stepConditieMap = stepConditieMap;
    }

    public Map<Grondslag, String> getGrondslagConditieMap() {
        return grondslagConditieMap;
    }

    public void setGrondslagConditieMap(Map<Grondslag, String> grondslagConditieMap) {
        this.grondslagConditieMap = grondslagConditieMap;
    }

    public String getDefinitieveBeslissing() {
        return definitieveBeslissing;
    }

    public void setDefinitieveBeslissing(String definitieveBeslissing) {
        this.definitieveBeslissing = definitieveBeslissing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Step)) return false;

        Step step = (Step) o;

        return new EqualsBuilder()
                .append(getStepBeschrijven(), step.getStepBeschrijven())
                .append(getGrondslags(), step.getGrondslags())
                .append(getSteps(), step.getSteps())
                .append(getStepConversieMap(), step.getStepConversieMap())
                .append(getGrondslagConvertieMap(), step.getGrondslagConvertieMap())
                .append(getStepConditieMap(),step.getStepConditieMap())
                .append(getGrondslagConditieMap(),step.getGrondslagConditieMap())
                .append(getDefinitieveBeslissing(),step.getDefinitieveBeslissing())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getStepBeschrijven())
                .append(getGrondslags())
                .append(getSteps())
                .append(getStepConversieMap())
                .append(getGrondslagConvertieMap())
                .append(getStepConditieMap())
                .append(getGrondslagConditieMap())
                .toHashCode();
    }
}

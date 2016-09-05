package nl.rws.re.facts;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by Md. Mainul Hasan Patwary on 17-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class Node {
    int id;
    String antwoord;

    public Node() {
    }

    public Node(int id, String antwoord) {
        this.id = id;
        this.antwoord = antwoord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(String antwoord) {
        this.antwoord = antwoord;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return new EqualsBuilder()
                .append(getId(), node.getId())
                .append(getAntwoord(), node.getAntwoord())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getAntwoord())
                .toHashCode();
    }
}

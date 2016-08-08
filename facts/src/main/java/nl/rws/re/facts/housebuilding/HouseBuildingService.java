package nl.rws.re.facts.housebuilding;

import nl.rws.re.facts.details.Address;
import nl.rws.re.facts.details.ApplicantDetail;

/**
 * Created by Md. Mainul Hasan Patwary on 20-Jul-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */

public class HouseBuildingService extends Service {

    private Address applicantCurrentAddress;

    private Address newHouseBuildingLocationAddress;

    private String typeOfTheLand;

    private int deep;

    private int altitude;

    private int size;

    public int getDeep() {
        return deep;
    }

    public void setDeep(int deep) {
        this.deep = deep;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Address getApplicantCurrentAddress() {
        return applicantCurrentAddress;
    }

    public void setApplicantCurrentAddress(Address applicantCurrentAddress) {
        this.applicantCurrentAddress = applicantCurrentAddress;
    }

    public Address getNewHouseBuildingLocationAddress() {
        return newHouseBuildingLocationAddress;
    }

    public void setNewHouseBuildingLocationAddress(Address newHouseBuildingLocationAddress) {
        this.newHouseBuildingLocationAddress = newHouseBuildingLocationAddress;
    }

    public String getTypeOfTheLand() {
        return typeOfTheLand;
    }

    public void setTypeOfTheLand(String typeOfTheLand) {
        this.typeOfTheLand = typeOfTheLand;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }
}

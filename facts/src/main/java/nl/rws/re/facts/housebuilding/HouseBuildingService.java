package nl.rws.re.facts.housebuilding;

import nl.rws.re.facts.details.ApplicantDetail;

/**
 * Created by Md. Mainul Hasan Patwary on 20-Jul-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */

public class HouseBuildingService extends Service {
    private ApplicantDetail applicantDetail;

    public ApplicantDetail getApplicantDetail() {
        return applicantDetail;
    }

    public void setApplicantDetail(ApplicantDetail applicantDetail) {
        this.applicantDetail = applicantDetail;
    }
}


package organization.process;

import java.util.Date;

/**
 *
 * @author Emre
 */
public class Meeting {

    private int meetingID;
    private String meetingTitle;
    private String meetingDescription;
    private Date meetingDate;
    private int meetingCreatedBy;

    /**
     *
     * @param meetingTitle
     * @param meetingDescription
     * @param meetingDate
     * @param meetingCreatedBy
     */
    public Meeting(String meetingTitle, String meetingDescription, Date meetingDate, int meetingCreatedBy) {
        this.meetingTitle = meetingTitle;
        this.meetingDescription = meetingDescription;
        this.meetingDate = meetingDate; 
        this.meetingCreatedBy = meetingCreatedBy;
    }

    public int getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(int meetingID) {
        this.meetingID = meetingID;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public String getMeetingDescription() {
        return meetingDescription;
    }

    public void setMeetingDescription(String meetingDescription) {
        this.meetingDescription = meetingDescription;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public int getMeetingCreatedBy() {
        return meetingCreatedBy;
    }

    public void setMeetingCreatedBy(int meetingCreatedBy) {
        this.meetingCreatedBy = meetingCreatedBy;
    }
}

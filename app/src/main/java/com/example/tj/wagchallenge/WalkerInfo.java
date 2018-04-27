package com.example.tj.wagchallenge;


/**
 * A class that stores all the profile data
 */

public class WalkerInfo {

    private final String TAG = WalkerInfo.class.getSimpleName();


    private String m_bronzeBadges;
    private String m_silverBadges;
    private String m_goldBadges;
    private String m_profileImageURL;
    private String m_displayName;

    public WalkerInfo(String bronzeBadges, String silverBadges, String goldBadges,
                      String profileImageURL, String displayName){
        super();
        this.m_bronzeBadges = bronzeBadges;
        this.m_silverBadges = silverBadges;
        this.m_goldBadges = goldBadges;
        this.m_profileImageURL = profileImageURL;
        this.m_displayName = displayName;
    }

    //Getters and Setters
    public String getM_bronzeBadges() {
        return m_bronzeBadges;
    }

    public void setM_bronzeBadges(String m_bronzeBadges) {
        this.m_bronzeBadges = m_bronzeBadges;
    }

    public String getM_silverBadges() {
        return m_silverBadges;
    }

    public void setM_silverBadges(String m_silverBadges) {
        this.m_silverBadges = m_silverBadges;
    }

    public String getM_goldBadges() {
        return m_goldBadges;
    }

    public void setM_goldBadges(String m_goldBadges) {
        this.m_goldBadges = m_goldBadges;
    }

    public String getM_profileImageURL() {
        return m_profileImageURL;
    }

    public void setM_profileImageURL(String m_profileImageURL) {
        this.m_profileImageURL = m_profileImageURL;
    }

    public String getM_displayName() {
        return m_displayName;
    }

    public void setM_displayName(String m_displayName) {
        this.m_displayName = m_displayName;
    }
}

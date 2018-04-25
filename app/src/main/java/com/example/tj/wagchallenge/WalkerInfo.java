package com.example.tj.wagchallenge;

public class WalkerInfo {

    private final String TAG = WalkerInfo.class.getSimpleName();

    public String m_bronzeBadges;
    public String m_silverBadges;
    public String m_goldBadges;
    public String m_profileImageURL;
    public String m_displayName;

    public WalkerInfo(String bronzeBadges, String silverBadges, String goldBadges,
                      String profileImageURL, String displayName){
        super();
        this.m_bronzeBadges = bronzeBadges;
        this.m_silverBadges = silverBadges;
        this.m_goldBadges = goldBadges;
        this.m_profileImageURL = profileImageURL;
        this.m_displayName = displayName;
    }
}

package com.javapda.formarten.domain;

public class FootballPlayer {

    private Integer footballPlayerId;
    private String description;
    protected FootballPlayer() {}
    public FootballPlayer(Integer footballPlayerId, String description) {
        this.footballPlayerId=footballPlayerId;
        this.description=description;
    }
    public Integer getFootballPlayerId() {
        return footballPlayerId;
    }
    public void setFootballPlayerId(Integer footballPlayerId) {
        this.footballPlayerId = footballPlayerId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime
                * result
                + ((footballPlayerId == null) ? 0 : footballPlayerId.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FootballPlayer other = (FootballPlayer) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (footballPlayerId == null) {
            if (other.footballPlayerId != null)
                return false;
        } else if (!footballPlayerId.equals(other.footballPlayerId))
            return false;
        return true;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FootballPlayer [footballPlayerId=");
        builder.append(footballPlayerId);
        builder.append(", description=");
        builder.append(description);
        builder.append("]");
        return builder.toString();
    }
    
}

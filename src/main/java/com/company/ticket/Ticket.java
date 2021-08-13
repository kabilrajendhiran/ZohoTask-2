package com.company.ticket;

import com.company.helper.Utility;

import java.time.LocalDateTime;

public class Ticket {
    private final String ticketId;
    private final LocalDateTime createdAt;
    private final LocalDateTime validUpto;

    public Ticket() {
        ticketId = Utility.generateTicketID();
        createdAt = LocalDateTime.now();
        validUpto = createdAt.plusHours(24);
    }

    public Ticket(String ticketId, LocalDateTime createdAt, LocalDateTime validUpto) {
        this.ticketId = ticketId;
        this.createdAt = createdAt;
        this.validUpto = validUpto;
    }

    public String getCreatedAtAsString()
    {
        return Utility.convertDateTime(createdAt);
    }

    public String getValidUpToAsString()
    {
        return Utility.convertDateTime(validUpto);
    }

    public String getTicketId() {
        return ticketId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getValidUpto() {
        return validUpto;
    }


}

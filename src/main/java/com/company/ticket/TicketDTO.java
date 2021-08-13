package com.company.ticket;

import java.time.LocalDateTime;

public class TicketDTO {
    private final String ticketId;
    private final LocalDateTime createdAt;
    private final LocalDateTime validUpto;
    private final String emailId;

    public TicketDTO(String ticketId, LocalDateTime createdAt, LocalDateTime validUpto, String emailId) {
        this.ticketId = ticketId;
        this.createdAt = createdAt;
        this.validUpto = validUpto;
        this.emailId = emailId;
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

    public String getEmailId() {
        return emailId;
    }
}

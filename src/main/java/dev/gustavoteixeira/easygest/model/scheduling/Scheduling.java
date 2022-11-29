package dev.gustavoteixeira.easygest.model.scheduling;

import dev.gustavoteixeira.easygest.model.service.Service;
import dev.gustavoteixeira.easygest.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.joda.money.Money;

import java.time.LocalDateTime;
import java.util.List;

import static dev.gustavoteixeira.easygest.model.scheduling.SchedulingState.*;
import static lombok.AccessLevel.PRIVATE;
import static org.joda.money.CurrencyUnit.USD;
import static org.joda.money.Money.zero;

@Data
@Builder
@AllArgsConstructor(access = PRIVATE)
public class Scheduling {

    String id;

    LocalDateTime dateTime;

    List<Service> services;

    SchedulingState status;

    User customer;

    public int getDuration() {
        return this.services.stream()
                .map(Service::getDurationInMinutes)
                .reduce(0, Integer::sum);
    }

    public Money getPrice() {
        return this.services.stream()
                .map(Service::getPrice)
                .map(Money::parse)
                .reduce(zero(USD), Money::plus);
    }

    public void confirm() {
        if (this.status == REQUESTED)
            this.status = CONFIRMED;
    }

    public void finish() {
        if (this.status == CONFIRMED)
            this.status = FINISHED;
    }

    public void cancel() {
        if (this.status != FINISHED)
            this.status = CANCELLED;
    }
}

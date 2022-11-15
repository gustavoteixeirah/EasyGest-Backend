package dev.gustavoteixeira.easygest.model.scheduling;

import java.util.List;

public interface SchedulingService {

    String requestNewScheduling(NewScheduling newScheduling);

    List<Scheduling> list();

}

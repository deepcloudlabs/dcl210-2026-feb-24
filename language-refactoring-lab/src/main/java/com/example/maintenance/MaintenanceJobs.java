package com.example.maintenance;

import com.example.anno.Schedule;
import com.example.anno.Schedules;

public class MaintenanceJobs {

    @Schedules({
        @Schedule(dayOfWeek="Fri", hour=23),
        @Schedule(dayOfWeek="Sun", hour=2)
    })
    public void cleanup() { }
}

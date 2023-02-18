package kyonggi.cspop.domain.schedule.enums;

import lombok.Getter;

@Getter
public enum ScheduleState {

    PROCEEDING("진행중"), END("마감");

    private String ScheduleStateToString;

    ScheduleState(String scheduleStateToString) {
        ScheduleStateToString = scheduleStateToString;
    }
}

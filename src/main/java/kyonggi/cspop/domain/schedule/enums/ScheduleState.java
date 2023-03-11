package kyonggi.cspop.domain.schedule.enums;

import lombok.Getter;

@Getter
public enum ScheduleState {

    WAIT("대기"),PROCEEDING("진행중"), END("마감");

    private final String ScheduleStateToString;

    ScheduleState(String scheduleStateToString) {
        ScheduleStateToString = scheduleStateToString;
    }
}

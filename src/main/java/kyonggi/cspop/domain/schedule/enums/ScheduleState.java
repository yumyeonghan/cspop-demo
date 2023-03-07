package kyonggi.cspop.domain.schedule.enums;

import lombok.Getter;

@Getter
public enum ScheduleState {

    WAITING("대기"),PROCEEDING("진행중"), END("마감");

    private String ScheduleStateToString;

    ScheduleState(String scheduleStateToString) {
        ScheduleStateToString = scheduleStateToString;
    }
}

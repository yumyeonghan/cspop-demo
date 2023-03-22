package kyonggi.cspop.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@EnableScheduling
@Slf4j
public class ScheduledTask {

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?") // logic to be executed 'every day at 00:00'
    public void updateSchedulesState() {
        //시간에 따른 진행일정 표의 상태 자동 업데이트 쿼리 작성
    }
}

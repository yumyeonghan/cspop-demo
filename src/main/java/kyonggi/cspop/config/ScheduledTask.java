package kyonggi.cspop.config;

import kyonggi.cspop.domain.board.service.ScheduleBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class ScheduledTask {

    private final ScheduleBoardService scheduleBoardService;

    @Scheduled(cron = "0 0 0 * * ?") // logic to be executed 'every day at 00:00'
    public void updateSchedulesState() {
        scheduleBoardService.autoUpdateSchedulesState();
    }
}

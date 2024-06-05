package com.example.spring_into.converter;

import com.example.spring_into.config.DurationConfig;
import com.example.spring_into.dto.TollRequest;
import com.example.spring_into.enums.Duration;
import com.example.spring_into.model.TollPass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.Period;

@Component
public class TollConverter {
    private final DurationConfig durationConfig;

    @Autowired
    public TollConverter(DurationConfig durationConfig) {
        this.durationConfig = durationConfig;
    }

    public TollPass toTollPass(TollRequest tollRequest) throws Exception {
        TollPass tollPass = new TollPass();
        tollPass.setRegNumber(tollRequest.getRegNumber());
        tollPass.setCountry(tollRequest.getCountry());
        setDateTime(tollPass,tollRequest);

        return tollPass;
    }

    private void setDateTime(TollPass tollPass,TollRequest tollRequest) throws Exception {
        Instant startTime = Instant.now();
        int duration = durationToDays(tollRequest.getDuration());
        Instant expDate = startTime.plus(Period.ofDays(duration));
        tollPass.setStartDate(startTime);
        tollPass.setExpDate(expDate);
    }

    private int durationToDays(Duration duration) throws Exception {
        switch (duration) {
            case WEEKEND:
                return this.durationConfig.getWeekend();
            case WEEK:
                return this.durationConfig.getWeek();
            case MONTH:
                return this.durationConfig.getMonth();
            case YEAR:
                return this.durationConfig.getYear();
            default:
                throw new Exception();
        }

    }


}

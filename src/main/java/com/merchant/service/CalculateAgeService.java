package com.merchant.service;

import com.merchant.model.AgeRequest;
import com.merchant.model.ResponseData;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@AllArgsConstructor
public class CalculateAgeService {

    public ResponseEntity<ResponseData> calculate(AgeRequest request){
        log.info("ageResquest {}", request);
        ResponseData responseData = new ResponseData();

        BigDecimal deadFirstPerson =
                request.getDeadPersonFirst()
                        .subtract(
                                request.getAgePersonFirst()
                        );
        log.info("deadFirstPerson {}", deadFirstPerson);

        BigDecimal deadSecondPerson =
                request.getDeadPersonSecond()
                        .subtract(
                                request.getAgePersonSecond()
                        );
        log.info("deadSecondPerson {}", deadSecondPerson);

        if(deadFirstPerson.intValue() < 0 || deadSecondPerson.intValue() < 0){
            responseData.setAverage(new BigDecimal(-1));
            ResponseEntity.ok(
                    responseData
            );
        }

        BigDecimal numberOfVictimFirst = patternDead(deadFirstPerson);
        BigDecimal numberOfVictimSecond = patternDead(deadSecondPerson);

        BigDecimal averageOfVictim = numberOfVictimFirst
                .add(numberOfVictimSecond)
                .divide(new BigDecimal(2));

        responseData.setAverage(averageOfVictim);

        return ResponseEntity.ok(
                responseData
        );
    }

    public BigDecimal patternDead(BigDecimal year){
        BigDecimal numberVillager = BigDecimal.ZERO;

        Integer yearInteger = year.intValue();

        if(yearInteger == 0){
            return new BigDecimal(0);
        }
        else if(yearInteger == 1){
            return new BigDecimal(1);
        }
        else{
            int[] villagersArray = new int[yearInteger];
            int villagers = 1;
            for(int x = 0; x < yearInteger; x++){

                if (x > 1) {
                    villagers = villagersArray[x - 1] + villagersArray[x - 2];
                }

                villagersArray[x] = villagers;
                numberVillager = numberVillager.add(new BigDecimal(villagers));
            }

            log.info("villagersArray {}", villagersArray);
        }

        log.info("numberVIllager {}", numberVillager);

        return numberVillager;
    }
}

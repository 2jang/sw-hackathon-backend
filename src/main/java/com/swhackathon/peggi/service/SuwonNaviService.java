package com.swhackathon.peggi.service;

import com.swhackathon.peggi.dto.CollegeResponse;
import com.swhackathon.peggi.dto.ErrorResponse;
import com.swhackathon.peggi.dto.SuwonNaviResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SuwonNaviService {

    public CollegeResponse getColleges() {
        List<String> buildings = Arrays.asList(
                "mainGate", "humanitiesCollege", "gym", "futureInnovationCenter",
                "innovationEngineeringCollege", "advancedScienceInstitute", "engineering1", "engineering2",
                "engineering3", "engineering4", "sculptureHall", "designArtCollege", "aceCenter",
                "swFusionCollege", "lifeCareScienceCollege", "socialCollege", "musicCollege",
                "amarensHall", "dormitory", "globalManagement", "globalTalentCollege", "administrationBuilding",
                "backGate"
        );

        return new CollegeResponse(buildings);
    }

    private final Map<String, Map<String, Integer>> graph = new HashMap<>();

    {
        addEdge("mainGate", "humanitiesCollege", 5);
        addEdge("mainGate", "gym", 5);
        addEdge("mainGate", "futureInnovationCenter", 8);
        addEdge("mainGate", "innovationEngineeringCollege", 10);
        addEdge("mainGate", "advancedScienceInstitute", 12);
        addEdge("mainGate", "engineering1", 13);
        addEdge("mainGate", "engineering2", 14);
        addEdge("mainGate", "engineering3", 15);
        addEdge("mainGate", "engineering4", 17);
        addEdge("mainGate", "sculptureHall", 16);
        addEdge("mainGate", "designArtCollege", 15);
        addEdge("mainGate", "aceCenter", 13);
        addEdge("mainGate", "swFusionCollege", 15);
        addEdge("mainGate", "lifeCareScienceCollege", 15);
        addEdge("mainGate", "socialCollege", 18);
        addEdge("mainGate", "musicCollege", 19);
        addEdge("mainGate", "amarensHall", 21);
        addEdge("mainGate", "dormitory", 24);
        addEdge("mainGate", "globalManagement", 25);
        addEdge("mainGate", "globalTalentCollege", 17);
        addEdge("mainGate", "administrationBuilding", 16);
        addEdge("mainGate", "backGate", 18);

        addEdge("humanitiesCollege", "gym", 2);
        addEdge("humanitiesCollege", "futureInnovationCenter", 5);
        addEdge("humanitiesCollege", "innovationEngineeringCollege", 7);
        addEdge("humanitiesCollege", "advancedScienceInstitute", 9);
        addEdge("humanitiesCollege", "engineering1", 10);
        addEdge("humanitiesCollege", "engineering2", 11);
        addEdge("humanitiesCollege", "engineering3", 12);
        addEdge("humanitiesCollege", "engineering4", 14);
        addEdge("humanitiesCollege", "sculptureHall", 14);
        addEdge("humanitiesCollege", "designArtCollege", 13);
        addEdge("humanitiesCollege", "aceCenter", 10);
        addEdge("humanitiesCollege", "swFusionCollege", 14);
        addEdge("humanitiesCollege", "lifeCareScienceCollege", 14);
        addEdge("humanitiesCollege", "socialCollege", 17);
        addEdge("humanitiesCollege", "musicCollege", 18);
        addEdge("humanitiesCollege", "amarensHall", 20);
        addEdge("humanitiesCollege", "dormitory", 23);
        addEdge("humanitiesCollege", "globalManagement", 24);
        addEdge("humanitiesCollege", "globalTalentCollege", 16);
        addEdge("humanitiesCollege", "administrationBuilding", 15);
        addEdge("humanitiesCollege", "backGate", 15);

        addEdge("gym", "futureInnovationCenter", 3);
        addEdge("gym", "innovationEngineeringCollege", 5);
        addEdge("gym", "advancedScienceInstitute", 7);
        addEdge("gym", "engineering1", 8);
        addEdge("gym", "engineering2", 9);
        addEdge("gym", "engineering3", 10);
        addEdge("gym", "engineering4", 12);
        addEdge("gym", "sculptureHall", 12);
        addEdge("gym", "designArtCollege", 11);
        addEdge("gym", "aceCenter", 9);
        addEdge("gym", "swFusionCollege", 14);
        addEdge("gym", "lifeCareScienceCollege", 14);
        addEdge("gym", "socialCollege", 17);
        addEdge("gym", "musicCollege", 18);
        addEdge("gym", "amarensHall", 20);
        addEdge("gym", "dormitory", 23);
        addEdge("gym", "globalManagement", 24);
        addEdge("gym", "globalTalentCollege", 16);
        addEdge("gym", "administrationBuilding", 14);
        addEdge("gym", "backGate", 13);

        addEdge("futureInnovationCenter", "innovationEngineeringCollege", 2);
        addEdge("futureInnovationCenter", "advancedScienceInstitute", 3);
        addEdge("futureInnovationCenter", "engineering1", 4);
        addEdge("futureInnovationCenter", "engineering2", 6);
        addEdge("futureInnovationCenter", "engineering3", 7);
        addEdge("futureInnovationCenter", "engineering4", 9);
        addEdge("futureInnovationCenter", "sculptureHall", 9);
        addEdge("futureInnovationCenter", "designArtCollege", 7);
        addEdge("futureInnovationCenter", "aceCenter", 5);
        addEdge("futureInnovationCenter", "swFusionCollege", 11);
        addEdge("futureInnovationCenter", "lifeCareScienceCollege", 11);
        addEdge("futureInnovationCenter", "socialCollege", 14);
        addEdge("futureInnovationCenter", "musicCollege", 15);
        addEdge("futureInnovationCenter", "amarensHall", 17);
        addEdge("futureInnovationCenter", "dormitory", 20);
        addEdge("futureInnovationCenter", "globalManagement", 21);
        addEdge("futureInnovationCenter", "globalTalentCollege", 13);
        addEdge("futureInnovationCenter", "administrationBuilding", 12);
        addEdge("futureInnovationCenter", "backGate", 10);

        addEdge("innovationEngineeringCollege", "advancedScienceInstitute", 1);
        addEdge("innovationEngineeringCollege", "engineering1", 2);
        addEdge("innovationEngineeringCollege", "engineering2", 3);
        addEdge("innovationEngineeringCollege", "engineering3", 4);
        addEdge("innovationEngineeringCollege", "engineering4", 6);
        addEdge("innovationEngineeringCollege", "sculptureHall", 6);
        addEdge("innovationEngineeringCollege", "designArtCollege", 5);
        addEdge("innovationEngineeringCollege", "aceCenter", 3);
        addEdge("innovationEngineeringCollege", "swFusionCollege", 9);
        addEdge("innovationEngineeringCollege", "lifeCareScienceCollege", 9);
        addEdge("innovationEngineeringCollege", "socialCollege", 12);
        addEdge("innovationEngineeringCollege", "musicCollege", 13);
        addEdge("innovationEngineeringCollege", "amarensHall", 15);
        addEdge("innovationEngineeringCollege", "dormitory", 18);
        addEdge("innovationEngineeringCollege", "globalManagement", 19);
        addEdge("innovationEngineeringCollege", "globalTalentCollege", 13);
        addEdge("innovationEngineeringCollege", "administrationBuilding", 11);
        addEdge("innovationEngineeringCollege", "backGate", 9);

        addEdge("advancedScienceInstitute", "engineering1", 1);
        addEdge("advancedScienceInstitute", "engineering2", 2);
        addEdge("advancedScienceInstitute", "engineering3", 3);
        addEdge("advancedScienceInstitute", "engineering4", 4);
        addEdge("advancedScienceInstitute", "sculptureHall", 6);
        addEdge("advancedScienceInstitute", "designArtCollege", 5);
        addEdge("advancedScienceInstitute", "aceCenter", 3);
        addEdge("advancedScienceInstitute", "swFusionCollege", 9);
        addEdge("advancedScienceInstitute", "lifeCareScienceCollege", 9);
        addEdge("advancedScienceInstitute", "socialCollege", 12);
        addEdge("advancedScienceInstitute", "musicCollege", 13);
        addEdge("advancedScienceInstitute", "amarensHall", 15);
        addEdge("advancedScienceInstitute", "dormitory", 18);
        addEdge("advancedScienceInstitute", "globalManagement", 19);
        addEdge("advancedScienceInstitute", "globalTalentCollege", 14);
        addEdge("advancedScienceInstitute", "administrationBuilding", 13);
        addEdge("advancedScienceInstitute", "backGate", 12);

        addEdge("engineering1", "engineering2", 1);
        addEdge("engineering1", "engineering3", 2);
        addEdge("engineering1", "engineering4", 2);
        addEdge("engineering1", "sculptureHall", 2);
        addEdge("engineering1", "designArtCollege", 3);
        addEdge("engineering1", "aceCenter", 3);
        addEdge("engineering1", "swFusionCollege", 4);
        addEdge("engineering1", "lifeCareScienceCollege", 5);
        addEdge("engineering1", "socialCollege", 6);
        addEdge("engineering1", "musicCollege", 7);
        addEdge("engineering1", "amarensHall", 8);
        addEdge("engineering1", "dormitory", 10);
        addEdge("engineering1", "globalManagement", 11);
        addEdge("engineering1", "globalTalentCollege", 9);
        addEdge("engineering1", "administrationBuilding", 8);
        addEdge("engineering1", "backGate", 7);

        addEdge("engineering2", "engineering3", 1);
        addEdge("engineering2", "engineering4", 3);
        addEdge("engineering2", "sculptureHall", 4);
        addEdge("engineering2", "designArtCollege", 5);
        addEdge("engineering2", "aceCenter", 5);
        addEdge("engineering2", "swFusionCollege", 6);
        addEdge("engineering2", "lifeCareScienceCollege", 7);
        addEdge("engineering2", "socialCollege", 8);
        addEdge("engineering2", "musicCollege", 9);
        addEdge("engineering2", "amarensHall", 10);
        addEdge("engineering2", "dormitory", 12);
        addEdge("engineering2", "globalManagement", 13);
        addEdge("engineering2", "globalTalentCollege", 11);
        addEdge("engineering2", "administrationBuilding", 10);
        addEdge("engineering2", "backGate", 9);

        addEdge("engineering3", "engineering4", 1);
        addEdge("engineering3", "sculptureHall", 2);
        addEdge("engineering3", "designArtCollege", 3);
        addEdge("engineering3", "aceCenter", 4);
        addEdge("engineering3", "swFusionCollege", 4);
        addEdge("engineering3", "lifeCareScienceCollege", 5);
        addEdge("engineering3", "socialCollege", 6);
        addEdge("engineering3", "musicCollege", 7);
        addEdge("engineering3", "amarensHall", 8);
        addEdge("engineering3", "dormitory", 10);
        addEdge("engineering3", "globalManagement", 11);
        addEdge("engineering3", "globalTalentCollege", 9);
        addEdge("engineering3", "administrationBuilding", 8);
        addEdge("engineering3", "backGate", 7);

        addEdge("engineering4", "sculptureHall", 3);
        addEdge("engineering4", "designArtCollege", 4);
        addEdge("engineering4", "aceCenter", 5);
        addEdge("engineering4", "swFusionCollege", 6);
        addEdge("engineering4", "lifeCareScienceCollege", 7);
        addEdge("engineering4", "socialCollege", 8);
        addEdge("engineering4", "musicCollege", 9);
        addEdge("engineering4", "amarensHall", 10);
        addEdge("engineering4", "dormitory", 12);
        addEdge("engineering4", "globalManagement", 13);
        addEdge("engineering4", "globalTalentCollege", 11);
        addEdge("engineering4", "administrationBuilding", 10);
        addEdge("engineering4", "backGate", 9);

        addEdge("sculptureHall", "designArtCollege", 7);
        addEdge("sculptureHall", "aceCenter", 7);
        addEdge("sculptureHall", "swFusionCollege", 7);
        addEdge("sculptureHall", "lifeCareScienceCollege", 9);
        addEdge("sculptureHall", "socialCollege", 12);
        addEdge("sculptureHall", "musicCollege", 14);
        addEdge("sculptureHall", "amarensHall", 15);
        addEdge("sculptureHall", "dormitory", 18);
        addEdge("sculptureHall", "globalManagement", 18);
        addEdge("sculptureHall", "globalTalentCollege", 17);
        addEdge("sculptureHall", "administrationBuilding", 16);
        addEdge("sculptureHall", "backGate", 15);

        addEdge("designArtCollege", "aceCenter", 5);
        addEdge("designArtCollege", "swFusionCollege", 5);
        addEdge("designArtCollege", "lifeCareScienceCollege", 6);
        addEdge("designArtCollege", "socialCollege", 7);
        addEdge("designArtCollege", "musicCollege", 7);
        addEdge("designArtCollege", "amarensHall", 7);
        addEdge("designArtCollege", "dormitory", 7);
        addEdge("designArtCollege", "globalManagement", 9);
        addEdge("designArtCollege", "globalTalentCollege", 8);
        addEdge("designArtCollege", "administrationBuilding", 7);
        addEdge("designArtCollege", "backGate", 6);

        addEdge("aceCenter", "swFusionCollege", 3);
        addEdge("aceCenter", "lifeCareScienceCollege", 5);
        addEdge("aceCenter", "socialCollege", 6);
        addEdge("aceCenter", "musicCollege", 7);
        addEdge("aceCenter", "amarensHall", 7);
        addEdge("aceCenter", "dormitory", 10);
        addEdge("aceCenter", "globalManagement", 11);
        addEdge("aceCenter", "globalTalentCollege", 8);
        addEdge("aceCenter", "administrationBuilding", 7);
        addEdge("aceCenter", "backGate", 5);

        addEdge("swFusionCollege", "lifeCareScienceCollege", 9);
        addEdge("swFusionCollege", "socialCollege", 10);
        addEdge("swFusionCollege", "musicCollege", 10);
        addEdge("swFusionCollege", "amarensHall", 11);
        addEdge("swFusionCollege", "dormitory", 12);
        addEdge("swFusionCollege", "globalManagement", 14);
        addEdge("swFusionCollege", "globalTalentCollege", 12);
        addEdge("swFusionCollege", "administrationBuilding", 10);
        addEdge("swFusionCollege", "backGate", 9);

        addEdge("lifeCareScienceCollege", "socialCollege", 12);
        addEdge("lifeCareScienceCollege", "musicCollege", 14);
        addEdge("lifeCareScienceCollege", "amarensHall", 15);
        addEdge("lifeCareScienceCollege", "dormitory", 16);
        addEdge("lifeCareScienceCollege", "globalManagement", 17);
        addEdge("lifeCareScienceCollege", "globalTalentCollege", 16);
        addEdge("lifeCareScienceCollege", "administrationBuilding", 15);
        addEdge("lifeCareScienceCollege", "backGate", 14);

        addEdge("socialCollege", "musicCollege", 1);
        addEdge("socialCollege", "amarensHall", 7);
        addEdge("socialCollege", "dormitory", 10);
        addEdge("socialCollege", "globalManagement", 11);
        addEdge("socialCollege", "globalTalentCollege", 8);
        addEdge("socialCollege", "administrationBuilding", 7);
        addEdge("socialCollege", "backGate", 4);

        addEdge("musicCollege", "amarensHall", 2);
        addEdge("musicCollege", "dormitory", 5);
        addEdge("musicCollege", "globalManagement", 6);
        addEdge("musicCollege", "globalTalentCollege", 5);
        addEdge("musicCollege", "administrationBuilding", 5);
        addEdge("musicCollege", "backGate", 5);

        addEdge("amarensHall", "dormitory", 5);
        addEdge("amarensHall", "globalManagement", 6);
        addEdge("amarensHall", "globalTalentCollege", 7);
        addEdge("amarensHall", "administrationBuilding", 7);
        addEdge("amarensHall", "backGate", 7);

        addEdge("dormitory", "globalManagement", 3);
        addEdge("dormitory", "globalTalentCollege", 3);
        addEdge("dormitory", "administrationBuilding", 5);
        addEdge("dormitory", "backGate", 6);

        addEdge("globalManagement", "globalTalentCollege", 5);
        addEdge("globalManagement", "administrationBuilding", 7);
        addEdge("globalManagement", "backGate", 9);

        addEdge("globalTalentCollege", "administrationBuilding", 8);
        addEdge("globalTalentCollege", "backGate", 12);

        addEdge("administrationBuilding", "backGate", 15);


    }

    private void addEdge(String from, String to, int time) {
        graph.computeIfAbsent(from, k -> new HashMap<>()).put(to, time);
        graph.computeIfAbsent(to, k -> new HashMap<>()).put(from, time); // 양방향
    }

    public ResponseEntity<Object> calculatePathTime(List<String> buildings) {
        String from = buildings.get(0);
        String to = buildings.get(1);

        Integer time = graph.getOrDefault(from, Map.of()).get(to);

        if (time == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse(from + " → " + to + " 경로를 찾을 수 없습니다."));
        }

        return ResponseEntity.ok(new SuwonNaviResponse(time));
    }
}